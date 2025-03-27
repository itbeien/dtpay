package cn.itbeien.web.controller.merchant;

import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.business.merchant.domain.MerchantInfo;
import cn.itbeien.business.merchant.service.IMerchantManagement;
import cn.itbeien.business.merchant.service.IMerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 10:31
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/merchant")
@Slf4j
public class MerchantController extends BaseController {

    //@Value("${yscard.agent.clientCode}")
    private String clientCode;

    //@Value("${yscard.agent.version}")
    private String version;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMerchantManagement merchantManagement;

    @PreAuthorize("@dss.hasPermi('dt:merchant:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantInfo merchantInfo)
    {
        startPage();
        List<MerchantInfo> list = merchantService.selectMerchantInfoList(merchantInfo);
        return getDataTable(list);
    }


    /**
     * 审核并发起进件（修改状态）
     * @param merchantInfo
     * @return
     */
    @PreAuthorize("@dss.hasPermi('dt:merchant:audit')")
    @GetMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody MerchantInfo merchantInfo){
        return toAjax(merchantService.auditMerchant(merchantInfo));
    }

    /**
     * 根据商户编号获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:merchant:query')")
    @GetMapping(value = "/{mercNo}")
    public AjaxResult getInfo(@PathVariable String mercNo)
    {
        return success(merchantService.findMercInfoByMercNo(mercNo));
    }

    /**
     * 删除商户信息
     */
    @PreAuthorize("@dss.hasPermi('dt:merchant:remove')")
    @Log(title = "删除订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{mercNo}")
    public AjaxResult remove(@PathVariable String[] mercNo)
    {
        return toAjax(merchantService.deleteMercByNos(mercNo));
    }

}
