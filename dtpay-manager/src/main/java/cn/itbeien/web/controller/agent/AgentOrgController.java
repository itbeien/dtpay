package cn.itbeien.web.controller.agent;

import cn.itbeien.business.agent.domain.AgentOrg;
import cn.itbeien.business.agent.service.IAgentOrgService;
import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.domain.entity.SysUser;
import cn.itbeien.common.core.domain.model.LoginUser;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.common.utils.SecurityUtils;
import cn.itbeien.business.enums.SystemEnum;
import cn.itbeien.system.service.ISysUserService;
import cn.itbeien.business.util.DtUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author beien
 * @date 2024-05-26 9:10
 * Copyright© 2024 beien
 * 代理商业务控制类
 */
@RestController
@Slf4j
@RequestMapping("/dt/agent")
public class AgentOrgController extends BaseController {

    @Value("${dtpay.profile}")
    private String uploadDir;

    @Autowired
    private IAgentOrgService agentOrgService;

    @Autowired
    private ISysUserService userService;

    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:agent:list')")
    @GetMapping("/list")
    public TableDataInfo list(AgentOrg agentOrg)
    {
        startPage();
        List<AgentOrg> list = agentOrgService.selectAgentOrgList(agentOrg);
        return getDataTable(list);
    }

    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("picMode")String picMode){
        Object fildId = agentImgUpload(multipartFile,picMode);
        return success(fildId);
    }

    private String agentImgUpload(MultipartFile multipartFile,String picMode) {
        if (multipartFile.isEmpty()) {
            return SystemEnum.UPLOAD_IMG_NOT_EXIST.getMessage();
        }
        String result = null;
        try{
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String fildId = picMode+DtUtil.uuid();
            String fileName = fildId +".png";
            result = fildId;
            String filePath = uploadDir + File.separator + fileName;
            multipartFile.transferTo(new File(filePath));//写文件在本地
        }catch (Exception e){
            log.error("上传商户图片到聚合支付系统异常:{}",e);
        }
        return result;
    }

    @PreAuthorize("@dss.hasPermi('dt:agent:add')")
    @Log(title = "代理商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public String agentSave(@Validated @RequestBody String data){
        AgentOrg agentOrg = JSONObject.toJavaObject(JSONObject.parseObject(data),AgentOrg.class);
        agentOrg.setImgInfo(JSONObject.parseObject(data).getString("imgInfo"));
        String result = SystemEnum.ERROR.getMessage();
        try{
            agentOrg.setCreateTime(new Date());
            String userName = getUsername();
            Long userId = getUserId();
            agentOrg.setCreator(String.valueOf(userId));//创建人
            agentOrg.setCreatorName(userName);//创建人姓名
            String userType = getLoginUser().getUser().getUserType();
            if(SystemEnum.USER_TYPE_SYSTEM.getCode().equals(userType)){
                agentOrg.setLevel(SystemEnum.ORG_LEVEL_ONE.getCode());//机构级别
                agentOrg.setNode(SystemEnum.ORG_NODE_EXIST.getCode());//是否有子节点,0没有子节点 1有子节点
            }else{
                agentOrg.setLevel(SystemEnum.ORG_LEVEL_TWO.getCode());//机构级别
                agentOrg.setNode(SystemEnum.ORG_NODE_NONE.getCode());//是否有子节点,0没有子节点 1有子节点
            }
            this.agentOrgService.saveAgent(agentOrg);
            SysUser sysUser = new SysUser();
            sysUser.setUserName(agentOrg.getOrgAccount());//用户名
            sysUser.setPassword(SecurityUtils.encryptPassword(SystemEnum.DEFAULT_PASSWORD.getCode()));//默认密码
            sysUser.setUserType(SystemEnum.USER_TYPE_ORDINARY.getCode());//用户类型为普通用户
            sysUser.setNickName(agentOrg.getOrgAccount());//昵称
            this.userService.insertUser(sysUser);
            result =  SystemEnum.SUCCESS.getMessage();
        }catch(Exception e){
            log.error("新增代理商异常:{}",e);
        }
        return result;
    }

    /**
     * 上线上级代理商
     * 如果是系统用户userType(00)则查询所有代理商信息+聚合支付运营平台
     * 如果是普通用户userType(01)则查询当前登录用户代理商信息,根据用户名到代理商表查询
     */
    @PreAuthorize("@dss.hasPermi('dt:agent:parentAgent')")
    @GetMapping("/parentAgent")
    public AjaxResult parentAgent()
    {
        LoginUser sysUser = getLoginUser();
        if(SystemEnum.USER_TYPE_SYSTEM.getCode().equals(sysUser.getUser().getUserType())){
           //查询所有代理商
            return success(this.agentOrgService.selectAgentOrgList(null));

        }else{
            //查询当前登录用户代理商
            List<AgentOrg> agentOrgs = new ArrayList<>();
            agentOrgs.add(this.agentOrgService.selectAgentByOrgAccount(sysUser.getUsername()));
            return success(agentOrgs);

        }

    }

    /**
     * 根据代理商编号获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:agent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(this.agentOrgService.selectAgentOrg(id));
    }

    /**
     * 删除代理商
     */
    @PreAuthorize("@dss.hasPermi('dt:agent:remove')")
    @Log(title = "删除代理商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long[] id)
    {
        return toAjax(this.agentOrgService.deleteAgentByIds(id));
    }


    /**
     * 修改代理商
     */
    @PreAuthorize("@dss.hasPermi('dt:agent:edit')")
    @Log(title = "修改代理商信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody String data)
    {
        AgentOrg agentOrg = JSONObject.toJavaObject(JSONObject.parseObject(data),AgentOrg.class);

        agentOrg.setImgInfo(JSONObject.parseObject(data).getString("imgInfo"));
        return toAjax(this.agentOrgService.updateAgent(agentOrg));
    }

    public static void main(String[] args) {
        System.out.println(SecurityUtils.encryptPassword(SystemEnum.DEFAULT_PASSWORD.getCode()));
    }

}
