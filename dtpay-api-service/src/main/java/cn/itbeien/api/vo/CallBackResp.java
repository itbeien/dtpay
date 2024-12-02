package cn.itbeien.api.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-03-25 12:11
 * Copyright© 2024 beien
 */
@Data
public class CallBackResp {
    private String sign;

    private CallBackData data;

    private String sysRetcode;
    private String sysRetmsg;
}
