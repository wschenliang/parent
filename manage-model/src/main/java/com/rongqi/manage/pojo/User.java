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
public class User implements Serializable {
    private static final long serialVersionUID = 1006985677276786947L;

    private Integer id;
    //姓名
    private String name;
    //用户名
    private String account;
    //密码
    private String password;
    //是否激活 Y表示激活， N表示不激活
    private String activeStatus;
    public static final String STATUS_VALID = "Y";
    public static final String STATUS_INVALID = "N";
    //电话
    private String telephone;
    //qq号
    private String qqNumber;
    //邮箱地址
    private String email;
    //现居住地址
    private String addressNow;
    //籍贯
    private String addressOriginal;
    //性别： 0 男 1女
    private Integer sex;
    //身份证
    private String manId;
    //等级 0 管理员
    private Integer rank;
    //备注信息
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressNow() {
        return addressNow;
    }

    public void setAddressNow(String addressNow) {
        this.addressNow = addressNow;
    }

    public String getAddressOriginal() {
        return addressOriginal;
    }

    public void setAddressOriginal(String addressOriginal) {
        this.addressOriginal = addressOriginal;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getManId() {
        return manId;
    }

    public void setManId(String manId) {
        this.manId = manId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
}
