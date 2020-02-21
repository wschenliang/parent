package com.rongqi.manage.pojo;

import java.io.Serializable;

/**
 * @author chenliang
 * @version 1.0.0
 * Modification  History:
 * Date         Author        Version        Description
 * ------------------------------------------------------
 * 2020-1-6   chenliang     1.0.0          create
 */
public class CompanyDetail implements Serializable {
    private static final long serialVersionUID = 820888937612392147L;

    //公司序号
    private Integer id;

    //公司名称
    private String companyName;

    //公司详情描述
    private String companyDescr;

    //公司地址
    private String companyAddress;

    //公司电话
    private String companyTelephone;

    //联系人
    private String linkman;

    //是否营业 0没有。1有
    private Integer isOpen;

    //工商局注册号
    private String companyRegisterNumber;

    //企业网站备案号
    private String netRecordNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescr() {
        return companyDescr;
    }

    public void setCompanyDescr(String companyDescr) {
        this.companyDescr = companyDescr;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public Integer getOpen() {
        return isOpen;
    }

    public void setOpen(Integer open) {
        isOpen = open;
    }

    public String getCompanyRegisterNumber() {
        return companyRegisterNumber;
    }

    public void setCompanyRegisterNumber(String companyRegisterNumber) {
        this.companyRegisterNumber = companyRegisterNumber;
    }

    public String getNetRecordNumber() {
        return netRecordNumber;
    }

    public void setNetRecordNumber(String netRecordNumber) {
        this.netRecordNumber = netRecordNumber;
    }
}
