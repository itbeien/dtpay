package cn.itbeien.terminal.entity;

public class YlArea {
    private String ylId;

    private String ylCode;

    private String ylName;

    private String ylPr;

    private String ylPrName;

    public String getYlId() {
        return ylId;
    }

    public void setYlId(String ylId) {
        this.ylId = ylId == null ? null : ylId.trim();
    }

    public String getYlCode() {
        return ylCode;
    }

    public void setYlCode(String ylCode) {
        this.ylCode = ylCode == null ? null : ylCode.trim();
    }

    public String getYlName() {
        return ylName;
    }

    public void setYlName(String ylName) {
        this.ylName = ylName == null ? null : ylName.trim();
    }

    public String getYlPr() {
        return ylPr;
    }

    public void setYlPr(String ylPr) {
        this.ylPr = ylPr == null ? null : ylPr.trim();
    }

    public String getYlPrName() {
        return ylPrName;
    }

    public void setYlPrName(String ylPrName) {
        this.ylPrName = ylPrName == null ? null : ylPrName.trim();
    }
}