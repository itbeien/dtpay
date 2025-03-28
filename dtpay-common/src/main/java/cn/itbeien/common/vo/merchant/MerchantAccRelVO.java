package cn.itbeien.common.vo.merchant;

import lombok.Data;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Data
public class MerchantAccRelVO {
    private String acctBal;
    private String freezeBal;
    private String acctAvaiBal;
    private String amountInWay;
    private String mercSettAcctNm;
    private String mercSettAcct;
    private String payFeeFlag;
    private String payFeeValue;
    private String updateTime;
    private String mercSettBank;
}
