package com.example.wzp109761.addressbook.bean;

import java.io.Serializable;
import java.util.List;

public class TestBean implements Serializable{


    private List<StuInfoBean> stu_info;

    public List<StuInfoBean> getStu_info() {
        return stu_info;
    }

    public void setStu_info(List<StuInfoBean> stu_info) {
        this.stu_info = stu_info;
    }

    public static class StuInfoBean {
        /**
         * id : 4058
         * name : test001
         * sex : 男
         * age : 18
         * addr : 北京市昌平区
         * grade : 7
         * phone : 15062431255
         * gold : 215928
         */

        private int id;
        private String name;
        private String sex;
        private int age;
        private String addr;
        private String grade;
        private String phone;
        private int gold;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
    }
}