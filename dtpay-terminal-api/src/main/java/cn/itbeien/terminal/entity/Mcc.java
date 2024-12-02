package cn.itbeien.terminal.entity;

public class Mcc {
    private String mccId;

    private String mccName;

    private String mccParent;

    private String mccFlag;

    private String mccRemark;

    public String getMccId() {
        return mccId;
    }

    public void setMccId(String mccId) {
        this.mccId = mccId == null ? null : mccId.trim();
    }

    public String getMccName() {
        return mccName;
    }

    public void setMccName(String mccName) {
        this.mccName = mccName == null ? null : mccName.trim();
    }

    public String getMccParent() {
        return mccParent;
    }

    public void setMccParent(String mccParent) {
        this.mccParent = mccParent == null ? null : mccParent.trim();
    }

    public String getMccFlag() {
        return mccFlag;
    }

    public void setMccFlag(String mccFlag) {
        this.mccFlag = mccFlag == null ? null : mccFlag.trim();
    }

    public String getMccRemark() {
        return mccRemark;
    }

    public void setMccRemark(String mccRemark) {
        this.mccRemark = mccRemark == null ? null : mccRemark.trim();
    }
}