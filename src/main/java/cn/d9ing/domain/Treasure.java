package cn.d9ing.domain;

import java.util.Date;

public class Treasure {
    private Long tId;

    private String tName;

    private String tNumber;

    private String tRemark;

    private String tKilneye;

    private Integer tDynasty;

    private String tCoverUrl;

    private String t3dUrl;

    private String tCoverUrlReal;

    private String tBottomUrl;

    private String tBottomUrlReal;

    private String tCertificateUrl;

    private String tCertificateUrlReal;

    private Date tCreatetime;

    private Date tUptime;

    private Integer isdelte;

    private String tDesc;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber == null ? null : tNumber.trim();
    }

    public String gettRemark() {
        return tRemark;
    }

    public void settRemark(String tRemark) {
        this.tRemark = tRemark == null ? null : tRemark.trim();
    }

    public String gettKilneye() {
        return tKilneye;
    }

    public void settKilneye(String tKilneye) {
        this.tKilneye = tKilneye == null ? null : tKilneye.trim();
    }

    public Integer gettDynasty() {
        return tDynasty;
    }

    public void settDynasty(Integer tDynasty) {
        this.tDynasty = tDynasty;
    }

    public String gettCoverUrl() {
        return tCoverUrl;
    }

    public void settCoverUrl(String tCoverUrl) {
        this.tCoverUrl = tCoverUrl == null ? null : tCoverUrl.trim();
    }

    public String getT3dUrl() {
        return t3dUrl;
    }

    public void setT3dUrl(String t3dUrl) {
        this.t3dUrl = t3dUrl == null ? null : t3dUrl.trim();
    }

    public String getTCoverUrlReal() {
        return tCoverUrlReal;
    }

    public void setTCoverUrlReal(String tCoverUrlReal) {
        this.tCoverUrlReal = tCoverUrlReal == null ? null : tCoverUrlReal.trim();
    }

    public String gettBottomUrl() {
        return tBottomUrl;
    }

    public void settBottomUrl(String tBottomUrl) {
        this.tBottomUrl = tBottomUrl == null ? null : tBottomUrl.trim();
    }

    public String gettBottomUrlReal() {
        return tBottomUrlReal;
    }

    public void settBottomUrlReal(String tBottomUrlReal) {
        this.tBottomUrlReal = tBottomUrlReal == null ? null : tBottomUrlReal.trim();
    }

    public String gettCertificateUrl() {
        return tCertificateUrl;
    }

    public void settCertificateUrl(String tCertificateUrl) {
        this.tCertificateUrl = tCertificateUrl == null ? null : tCertificateUrl.trim();
    }

    public String gettCertificateUrlReal() {
        return tCertificateUrlReal;
    }

    public void settCertificateUrlReal(String tCertificateUrlReal) {
        this.tCertificateUrlReal = tCertificateUrlReal == null ? null : tCertificateUrlReal.trim();
    }

    public Date gettCreatetime() {
        return tCreatetime;
    }

    public void settCreatetime(Date tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public Date gettUptime() {
        return tUptime;
    }

    public void settUptime(Date tUptime) {
        this.tUptime = tUptime;
    }

    public Integer getIsdelte() {
        return isdelte;
    }

    public void setIsdelte(Integer isdelte) {
        this.isdelte = isdelte;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc == null ? null : tDesc.trim();
    }
}