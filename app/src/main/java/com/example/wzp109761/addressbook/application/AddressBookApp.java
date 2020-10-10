package com.example.wzp109761.addressbook.application;

import android.app.Application;

public class AddressBookApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.initialize(this);
    }
}
