package cn.itbeien.api.controller;

import cn.itbeien.api.entity.agent.AgentOrg;
import cn.itbeien.api.enums.SystemEnum;
import cn.itbeien.api.service.agent.IAgentOrgService;
import cn.itbeien.api.vo.ResultVO;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class AgentController {
    @Autowired
    private IAgentOrgService agentOrgService;

    @RequestMapping("/test/agent/AgentSave")
    public ResultVO testAgentSave(@RequestBody String data){
        ResultVO<String> resultVO = new ResultVO<>();
       try{
           AgentOrg agentOrg = JSONObject.toJavaObject(JSONObject.parseObject(data),AgentOrg.class);
           this.agentOrgService.saveAgent(agentOrg);
       }catch (Exception e){
           log.error("新增代理商异常:{}",e);
           resultVO.setData(null);
           resultVO.setMessage(SystemEnum.ERROR.getMessage());
           resultVO.setCode(SystemEnum.ERROR.getCode());
       }
        return resultVO;
    }

    public static void main(String[] args) {
        AgentOrg agentOrg = new AgentOrg();
        agentOrg.setAgentName("代理商001");
        agentOrg.setCode("001");
        agentOrg.setContactPerson("张三");
        agentOrg.setContactTel("123456789");
        agentOrg.setStatus("1");
        agentOrg.setAreaId(1);
        agentOrg.setCreator("jmeter");
        agentOrg.setCreatorName("jmeter");
        agentOrg.setLevel("1");
        System.out.println(JSON.toJSONString(agentOrg));
    }
}
