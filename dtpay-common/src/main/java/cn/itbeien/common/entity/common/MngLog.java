package cn.itbeien.common.entity.common;

import java.io.Serializable;
import java.util.Date;

public class MngLog implements Serializable {
    private String id;

    private String oprName;

    private String oprFrom;

    private Date oprTime;

    private String oprType;

    private String oprStatus;

    private String logAction;

    private String logTitle;

    private String logDetail;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName == null ? null : oprName.trim();
    }

    public String getOprFrom() {
        return oprFrom;
    }

    public void setOprFrom(String oprFrom) {
        this.oprFrom = oprFrom == null ? null : oprFrom.trim();
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType == null ? null : oprType.trim();
    }

    public String getOprStatus() {
        return oprStatus;
    }

    public void setOprStatus(String oprStatus) {
        this.oprStatus = oprStatus == null ? null : oprStatus.trim();
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction == null ? null : logAction.trim();
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle == null ? null : logTitle.trim();
    }

    public String getLogDetail() {
        return logDetail;
    }

    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail == null ? null : logDetail.trim();
    }
}