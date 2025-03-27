package cn.itbeien.web.controller.qrcode;

import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.common.utils.poi.ExcelUtil;
import cn.itbeien.business.enums.SystemEnum;
import cn.itbeien.business.qrcode.domain.QrCode;
import cn.itbeien.business.qrcode.service.IQrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 10:43
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/qrcode")
@Slf4j
public class QrCodeController extends BaseController {

    @Value("${system.qrcode.url}")
    private String qrCodeUrl;

    @Value("${qr.number}")
    private Integer number;

    //@Value("${qrcode_location}")
    @Value("${dtpay.profile}")
    private String qrLocation;

    @Autowired
    private IQrCodeService qrCodeService;
    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:qrcode:list')")
    @GetMapping("/list")
    public TableDataInfo list(QrCode qrCode)
    {
        startPage();
        List<QrCode> list = qrCodeService.selectQrCodeList(qrCode);
        return getDataTable(list);
    }

    /**
     * 批量生成二维码
     * number 批量生成的数量
     */
    @PreAuthorize("@dss.hasPermi('dt:qrcode:add')")
    @Log(title = "二维码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public String batchCreateQR(@Validated @RequestBody QrCode qrCode){
        String result = SystemEnum.ERROR.getMessage();
        try{
            Integer qrNumber = qrCode.getNumber() == null ? this.number : qrCode.getNumber();
            qrCodeService.saveQR(qrNumber,qrCodeUrl,qrLocation);
            result =  SystemEnum.SUCCESS.getMessage();
        }catch(Exception e){
            log.error("创建二维码异常:{}",e);
        }
        return result;
    }

    @Log(title = "二维码导出", businessType = BusinessType.EXPORT)
    @PreAuthorize("@dss.hasPermi('dt:qrcode:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, QrCode qrCode){
        List<QrCode> list = qrCodeService.selectQrCodeList(qrCode);
        ExcelUtil<QrCode> util = new ExcelUtil<QrCode>(QrCode.class);
        util.exportExcel(response, list, "二维码数据");
    }

    /**
     * 修改二维码信息
     */
    @PreAuthorize("@dss.hasPermi('dt:qrcode:edit')")
    @Log(title = "修改二维码信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody QrCode qrCode)
    {
        return toAjax(qrCodeService.updateQrCode(qrCode));
    }

    /**
     * 删除二维码信息
     */
    @PreAuthorize("@dss.hasPermi('dt:qrcode:remove')")
    @Log(title = "删除二维码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qrIds}")
    public AjaxResult remove(@PathVariable Integer[] qrIds)
    {
        return toAjax(qrCodeService.deleteQrByIds(qrIds));
    }

    /**
     * 根据二维码Id获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:qrcode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Integer id)
    {
        return success(qrCodeService.selectQrById(id));
    }

}
