package cn.itbeien.common.entity;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable {
    private Date hyDate;

    private String weekDay;

    private String isWorkDay;

    private String holiday;

    private static final long serialVersionUID = 1L;

    public Date getHyDate() {
        return hyDate;
    }

    public void setHyDate(Date hyDate) {
        this.hyDate = hyDate;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay == null ? null : weekDay.trim();
    }

    public String getIsWorkDay() {
        return isWorkDay;
    }

    public void setIsWorkDay(String isWorkDay) {
        this.isWorkDay = isWorkDay == null ? null : isWorkDay.trim();
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }
}