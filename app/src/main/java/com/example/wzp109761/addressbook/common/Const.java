package com.example.wzp109761.addressbook.common;


public class Const {

    //用户相关
    public static class USERINFO_KEY {
        public static final String USER_INFO = "mUserInfo";  //用户信息
        public static final String IS_LOGIN = "mIsLogin";    //登录状态
        public static final String AES = "mLABSs";//用户信息密钥
    }

    //事件Action
    public static class EVENT_ACTION {
        public static final String MAIN = "main";
        public static final String ADDRESSBOOK = "address_book";
        public static final String MINE = "mine";

        public static final String SEARCH = "search";
    }

    //Intent传值
    public static class BUNDLE_KEY {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String OBJ = "obj";
        public static final String TYPE = "type";

    }
    //当前页面状态
    public static class PAGE_STATE {
        public static final int STATE_REFRESH = 0; //刷新
        public static final int STATE_LOAD_MORE = 1;//加载更多
    }




}
