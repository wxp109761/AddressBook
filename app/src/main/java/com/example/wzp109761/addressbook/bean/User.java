package com.example.wzp109761.addressbook.bean;


import java.io.Serializable;

/**
 * 当前用户
 */
public class User implements Serializable {

    /**
     * id : 1
     * name : 张世宇
     * nickName : 。
     * phone : 18852895041
     * password : zsy2713.
     * signature : 张
     * sex : 0
     * birthday : 1998-06-02
     * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602348299624&di=ef22f46c385574884aa7efde497aff50&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F70%2F91%2F01300000261284122542917592865.jpg
     * createTime : 1597395547000
     * modifyTime : 1602338232000
     * isDeleted : 0
     */

    private int id;
    private String name;
    private String nickName;
    private long phone;
    private String password;
    private String signature;
    private int sex;
    private String birthday;
    private String url;
    private long createTime;
    private long modifyTime;
    private int isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
