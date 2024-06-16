package cn.itbeien.web.controller.merchant;

import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.business.merchant.domain.ExtMerchantInfo;
import cn.itbeien.business.merchant.service.IChannelMerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/dt/channelMerchant")
@Slf4j
public class ChannelMerchantController extends BaseController {

    @Autowired
    private IChannelMerchantService channelMerchantService;

    @PreAuthorize("@dss.hasPermi('dt:channelMerchant:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExtMerchantInfo extMerchantInfo)
    {
        startPage();
        List<ExtMerchantInfo> list = channelMerchantService.selectChannelMerchantList(extMerchantInfo);
        return getDataTable(list);
    }


    /**
     * 修改状态
     * @param extMerchantInfo
     * @return
     */
    @PreAuthorize("@dss.hasPermi('dt:channelMerchant:audit')")
    //@GetMapping("/edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExtMerchantInfo extMerchantInfo){
        return toAjax(channelMerchantService.auditMerchant(extMerchantInfo));
    }

    /**
     * 根据商户编号获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:channelMerchant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(channelMerchantService.findMercInfoById(id));
    }

    /**
     * 删除商户信息
     */
    @PreAuthorize("@dss.hasPermi('dt:channelMerchant:remove')")
    @Log(title = "删除订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long[] id)
    {
        return toAjax(channelMerchantService.deleteMercByIds(id));
    }

}
