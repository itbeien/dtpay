package cn.itbeien.business.order.domain.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-25 16:06
 * Copyright© 2024 beien
 */
@Data
public class RefundReq {
   private String orgId;
   private String orgMercode;
   private String orgTermno;
   private String orgTrace;
   private String sign;
   private String signType;

   private String prodTrace;

   private String payAmount;

   private RefundReqBizData bizData;
}
