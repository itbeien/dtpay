package cn.itbeien.business.channel.domain;

import java.util.Date;

public class PayChannel {
    private String channelCode;

    private String channelName;

    private String chnNickName;

    private String orgId;

    private String orgTermNo;

    private String status;

    private String privateKey;

    private String channelEnv;

    private Date createTime;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChnNickName() {
        return chnNickName;
    }

    public void setChnNickName(String chnNickName) {
        this.chnNickName = chnNickName == null ? null : chnNickName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgTermNo() {
        return orgTermNo;
    }

    public void setOrgTermNo(String orgTermNo) {
        this.orgTermNo = orgTermNo == null ? null : orgTermNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public String getChannelEnv() {
        return channelEnv;
    }

    public void setChannelEnv(String channelEnv) {
        this.channelEnv = channelEnv == null ? null : channelEnv.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}