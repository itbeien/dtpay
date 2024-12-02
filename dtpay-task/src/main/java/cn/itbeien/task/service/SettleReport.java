package cn.itbeien.task.service;

import cn.itbeien.task.dao.order.TradeOrderMapper;
import cn.itbeien.task.entity.AgentOrg;
import cn.itbeien.task.entity.MerchantInfo;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author itbeien
 */
@Component
@Slf4j
public class SettleReport {

   @Value("${dtpay.base-rate}")
   private double base;

   @Resource
   private TradeOrderMapper tradeOrderMapper;

   @Resource
   private IAgentService agentService;

   @Resource
   private IMerchantService merchantService;

    /**
     * 1、任务执行模式Bean模式
     * 分片广播任务
     */
    @XxlJob("merchantReport")
    public void merchantReport() throws Exception {
        try{
            //商户结算报表业务逻辑
            log.info("进入到商户结算报表");
            // 分片参数
            int shardIndex = XxlJobHelper.getShardIndex();//当前分片索引
            int shardTotal = XxlJobHelper.getShardTotal();//分片总数(目前固定为2)
            //获取平台所有状态正常的商户信息
            List<MerchantInfo> merchantInfos = this.merchantService.findMerchant();
            for (int i = 0; i < merchantInfos.size(); i++) {
                this.merchantService.generateMerchantReport(merchantInfos.get(i).getMercNo());
            }
        }catch (Exception e){
            log.error("商户结算报表异常信息{0}",e);
        }
    }


    /**
     * 1、任务执行模式Bean模式
     * 分片广播任务
     */
    @XxlJob("agentReport")
    public void agentReport() throws Exception {
        /**
         * 1 获取所有状态为正常的代理商数据(包含代理商编号和结算费率),平台基础费率从哪里获取(维护在配置中心)
         * 2 根据所获取的代理商数据下标对2取模是否等于当前分片索引来进行任务的调度
         * 3 编写统计代理商分润数据的业务逻辑方法
         */
        try{
            // 分片参数
            int shardIndex = XxlJobHelper.getShardIndex();//当前分片索引
            int shardTotal = XxlJobHelper.getShardTotal();//分片总数(目前固定为2)

            List<AgentOrg> agentOrgs = this.agentService.findAgent();

            for (int i = 0; i < agentOrgs.size(); i++) {
                if( i % shardTotal == shardIndex ){
                    //传入代理商编号，获取 当前代理商下所有的商户订单数据
                    AgentOrg ao = agentOrgs.get(i);
                    this.agentService.generateAgentReport(ao.getId(),Double.parseDouble(ao.getRate()));
                }
            }
            //代理商分润报表业务逻辑
            log.info("进入到地代理商分润报表");
        }catch(Exception e){
            log.error("代理商分润报表异常信息{0}",e);
        }
    }

}
