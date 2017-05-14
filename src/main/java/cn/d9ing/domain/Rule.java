package cn.d9ing.domain;

import java.util.Date;

public class Rule {
    private Integer rId;

    private String rName;

    private Date rCreatetime;

    private Date rUptime;

    private Integer isdelete;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public Date getrCreatetime() {
        return rCreatetime;
    }

    public void setrCreatetime(Date rCreatetime) {
        this.rCreatetime = rCreatetime;
    }

    public Date getrUptime() {
        return rUptime;
    }

    public void setrUptime(Date rUptime) {
        this.rUptime = rUptime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}