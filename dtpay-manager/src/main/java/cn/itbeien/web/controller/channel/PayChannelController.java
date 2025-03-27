package cn.itbeien.web.controller.channel;

import cn.itbeien.business.channel.domain.PayChannel;
import cn.itbeien.business.channel.service.IPayChannelService;
import cn.itbeien.common.annotation.Log;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import cn.itbeien.common.core.page.TableDataInfo;
import cn.itbeien.common.enums.BusinessType;
import cn.itbeien.business.enums.SystemEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author beien
 * @date 2024-04-18 13:29
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/channel")
@Slf4j
public class PayChannelController extends BaseController {
    @Autowired
    private IPayChannelService payChannelService;

    @Value("${channel.env}")
    private String channelEnv;

    /**
     *
     * 新增支付渠道
     */
    @PreAuthorize("@dss.hasPermi('dt:channel:add')")
    @Log(title = "支付渠道管理", businessType = BusinessType.INSERT)
    @PostMapping
    public String batchCreateQR(@Validated @RequestBody PayChannel payChannel){
        String result = SystemEnum.ERROR.getMessage();
        try{
            payChannel.setChannelEnv(channelEnv);
            payChannel.setCreateTime(new Date());
            payChannel.setChannelCode(RandomStringUtils.randomAlphanumeric(12));
            this.payChannelService.savePayChannel(payChannel);
            result =  SystemEnum.SUCCESS.getMessage();
        }catch(Exception e){
            log.error("新增支付渠道异常:{}",e);
        }
        return result;
    }

    /**
     * 获取订单列表
     */
    @PreAuthorize("@dss.hasPermi('dt:channel:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayChannel payChannel)
    {
        startPage();
        List<PayChannel> list = payChannelService.selectPayChannelList(payChannel);
        return getDataTable(list);
    }


    /**
     * 删除支付渠道
     */
    @PreAuthorize("@dss.hasPermi('dt:channel:remove')")
    @Log(title = "删除支付渠道信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{channelCode}")
    public AjaxResult remove(@PathVariable String[] channelCode)
    {
        return toAjax(payChannelService.deleteChannelByCodes(channelCode));
    }

    /**
     * 修改支付渠道
     */
    @PreAuthorize("@dss.hasPermi('dt:channel:edit')")
    @Log(title = "修改支付渠道信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody PayChannel payChannel)
    {
        return toAjax(payChannelService.updateChannel(payChannel));
    }

    /**
     * 根据渠道编号获取详细信息
     */
    @PreAuthorize("@dss.hasPermi('dt:channel:query')")
    @GetMapping(value = "/{channelCode}")
    public AjaxResult getInfo(@PathVariable String channelCode)
    {
        return success(payChannelService.findPayChannelByCode(channelCode));
    }
}
