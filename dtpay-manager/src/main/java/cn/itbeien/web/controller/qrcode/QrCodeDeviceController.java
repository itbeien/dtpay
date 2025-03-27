package cn.itbeien.web.controller.qrcode;

import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.business.qrcode.domain.QrCodeMerchant;
import cn.itbeien.business.qrcode.service.IQrCodeDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 10:43
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/device")
@Slf4j
public class QrCodeDeviceController extends BaseController {

    @Autowired
    private IQrCodeDeviceService qrCodeDeviceService;
    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrCodeMerchant qrCodeMerchant)
    {
        startPage();
        List<QrCodeMerchant> list = qrCodeDeviceService.selectQrCodeDeviceList(qrCodeMerchant);
        return getDataTable(list);
    }


    /**
     * 修改二维码信息
     */
    @PreAuthorize("@dss.hasPermi('dt:device:edit')")
    @Log(title = "修改二维码信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody QrCodeMerchant qrCodeMerchant)
    {
        return toAjax(qrCodeDeviceService.updateQrCodeDevice(qrCodeMerchant));
    }

    /**
     * 删除二维码信息
     */
    @PreAuthorize("@dss.hasPermi('dt:device:remove')")
    @Log(title = "删除二维码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qrIds}")
    public AjaxResult remove(@PathVariable Integer[] qrIds)
    {
        return toAjax(qrCodeDeviceService.deleteQrDeviceByIds(qrIds));
    }

    /**
     * 根据二维码Id获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:device:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Integer id)
    {
        return success(qrCodeDeviceService.selectQrDeviceById(id));
    }

}
