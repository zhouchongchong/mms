package cn.d9ing.domain;

import java.util.Date;

public class User {
    private Integer uId;

    private Integer uRule;

    private String uUsername;

    private String uPassword;

    private String uRealname;

    private String uPic;

    private String uPhone;

    private String uEmail;

    private String uQq;

    private String uWecat;

    private String uDisc;

    private Date uCreatetime;

    private Date uUptime;

    private Integer isdelete;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getuRule() {
        return uRule;
    }

    public void setuRule(Integer uRule) {
        this.uRule = uRule;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername == null ? null : uUsername.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuRealname() {
        return uRealname;
    }

    public void setuRealname(String uRealname) {
        this.uRealname = uRealname == null ? null : uRealname.trim();
    }

    public String getuPic() {
        return uPic;
    }

    public void setuPic(String uPic) {
        this.uPic = uPic == null ? null : uPic.trim();
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public String getuQq() {
        return uQq;
    }

    public void setuQq(String uQq) {
        this.uQq = uQq == null ? null : uQq.trim();
    }

    public String getuWecat() {
        return uWecat;
    }

    public void setuWecat(String uWecat) {
        this.uWecat = uWecat == null ? null : uWecat.trim();
    }

    public String getuDisc() {
        return uDisc;
    }

    public void setuDisc(String uDisc) {
        this.uDisc = uDisc == null ? null : uDisc.trim();
    }

    public Date getuCreatetime() {
        return uCreatetime;
    }

    public void setuCreatetime(Date uCreatetime) {
        this.uCreatetime = uCreatetime;
    }

    public Date getuUptime() {
        return uUptime;
    }

    public void setuUptime(Date uUptime) {
        this.uUptime = uUptime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}