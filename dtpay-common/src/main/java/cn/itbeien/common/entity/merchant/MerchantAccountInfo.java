package cn.itbeien.common.entity.merchant;

import java.io.Serializable;
import java.util.Date;

public class MerchantAccountInfo implements Serializable {

    private String id;

    private String mercNo;

    private String mercNickName;

    private String fileName;

    private String filePath;

    private String fileSize;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public MerchantAccountInfo withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMercNo() {
        return mercNo;
    }

    public MerchantAccountInfo withMercNo(String mercNo) {
        this.setMercNo(mercNo);
        return this;
    }

    public void setMercNo(String mercNo) {
        this.mercNo = mercNo == null ? null : mercNo.trim();
    }

    public String getMercNickName() {
        return mercNickName;
    }

    public MerchantAccountInfo withMercNickName(String mercNickName) {
        this.setMercNickName(mercNickName);
        return this;
    }

    public void setMercNickName(String mercNickName) {
        this.mercNickName = mercNickName == null ? null : mercNickName.trim();
    }

    public String getFileName() {
        return fileName;
    }


    public MerchantAccountInfo withFileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }


    public String getFilePath() {
        return filePath;
    }


    public MerchantAccountInfo withFilePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }


    public String getFileSize() {
        return fileSize;
    }


    public MerchantAccountInfo withFileSize(String fileSize) {
        this.setFileSize(fileSize);
        return this;
    }


    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }


    public Date getStartDate() {
        return startDate;
    }


    public MerchantAccountInfo withStartDate(Date startDate) {
        this.setStartDate(startDate);
        return this;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public MerchantAccountInfo withEndDate(Date endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public MerchantAccountInfo withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public MerchantAccountInfo withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mercNo=").append(mercNo);
        sb.append(", mercNickName=").append(mercNickName);
        sb.append(", fileName=").append(fileName);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MerchantAccountInfo other = (MerchantAccountInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMercNo() == null ? other.getMercNo() == null : this.getMercNo().equals(other.getMercNo()))
            && (this.getMercNickName() == null ? other.getMercNickName() == null : this.getMercNickName().equals(other.getMercNickName()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMercNo() == null) ? 0 : getMercNo().hashCode());
        result = prime * result + ((getMercNickName() == null) ? 0 : getMercNickName().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}