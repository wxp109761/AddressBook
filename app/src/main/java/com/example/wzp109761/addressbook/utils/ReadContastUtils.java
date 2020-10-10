package com.example.wzp109761.addressbook.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

import com.example.wzp109761.addressbook.bean.PhoneListBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadContastUtils {
    // 获所有手机号码
    public static List<PhoneListBean> getPhoneContant(Context context) {
        // 取得ContentResolver
        List<PhoneListBean> list = new ArrayList<PhoneListBean>();
        ContentResolver content = context.getContentResolver();

        // 联系人的URI
        Cursor cursor = content
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        // int contactCount = cursor.getCount(); // 获得联系人数目
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columId = cursor
                        .getColumnIndex(ContactsContract.Contacts._ID);// id下标
                int displayNameColum = cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);// 名称下标
                // 个数
                int phoneNo = cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);// 电话号码列
                // 获得联系人的ID号
                String contactId = cursor.getString(columId);
                // 获得联系人姓名
                String disPlayName = cursor.getString(displayNameColum);
                String phonenum = cursor.getString(phoneNo);// 号码
                PhoneListBean p = new PhoneListBean(phonenum, disPlayName);
                list.add(p);
                // 电话号码的个数

            }
        }
        Set<PhoneListBean> set = new HashSet<>(list);
        return new ArrayList<>(set);

    }

    @SuppressWarnings("deprecation")
    public static List<PhoneListBean> getSimContant(Context context) {

        // 取得ContentResolver
        List<PhoneListBean> list = new ArrayList<PhoneListBean>();
        // 联系人的URI
        Uri uri = Uri.parse("content://icc/adn");

        Cursor cursor = context.getContentResolver().query(uri, null, null,
                null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columId = cursor.getColumnIndex(Contacts.People._ID);// id下标
                int displayNameColum = cursor.getColumnIndex(Contacts.People.NAME);// 名称下标
                // 个数
                int phoneNo = cursor.getColumnIndex(Contacts.People.NUMBER);// 电话号码列
                // 获得联系人的ID号
                String contactId = cursor.getString(columId);
                // 获得联系人姓名
                String disPlayName = cursor.getString(displayNameColum);
                String phonenum = cursor.getString(phoneNo);// 号码
                PhoneListBean p = new PhoneListBean(phonenum, disPlayName);
                list.add(p);
            }
        }
        Set<PhoneListBean> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }
}
