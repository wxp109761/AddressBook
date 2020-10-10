package com.example.wzp109761.addressbook.bean;

import java.io.Serializable;
import java.util.List;

public class PhoneListBean implements Serializable {
    public PhoneListBean(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    /**
     * phone : 123
     * name : 123
     */

    private String phone;
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 针对userCode重写hashCode()方法
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phone == null || name==null) ? 0 : (phone.hashCode()+name.hashCode()));
        return result;
    }
    /**
     * 针对userCode重写equals()方法
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhoneListBean other = (PhoneListBean) obj;
        if (phone == null||name==null) {
            if (other.phone != null&&other.name!=null)
                return false;
        } else if (!phone.equals(other.phone)&&!name.equals(other.name))
            return false;
        return true;
    }


}
