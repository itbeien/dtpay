package cn.itbeien.business.merchant.domain;

public class ExtMerchantInfo {
    private Long id;

    private String merchantNo;

    private String payingMercNo;

    private String payingMercName;

    private String orgId;

    private String orgMerCode;

    private String orgTermNo;

    private String privateKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getPayingMercNo() {
        return payingMercNo;
    }

    public void setPayingMercNo(String payingMercNo) {
        this.payingMercNo = payingMercNo == null ? null : payingMercNo.trim();
    }

    public String getPayingMercName() {
        return payingMercName;
    }

    public void setPayingMercName(String payingMercName) {
        this.payingMercName = payingMercName == null ? null : payingMercName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgMerCode() {
        return orgMerCode;
    }

    public void setOrgMerCode(String orgMerCode) {
        this.orgMerCode = orgMerCode == null ? null : orgMerCode.trim();
    }

    public String getOrgTermNo() {
        return orgTermNo;
    }

    public void setOrgTermNo(String orgTermNo) {
        this.orgTermNo = orgTermNo == null ? null : orgTermNo.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }
}