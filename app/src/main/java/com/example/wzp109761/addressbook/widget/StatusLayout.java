package com.example.wzp109761.addressbook.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.wzp109761.addressbook.R;


/**
 * author: 康栋普
 * date: 2018/2/7
 * 加载各种状态的布局(empty、loading、error、content)
 */

public class StatusLayout extends FrameLayout {
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mContentView;

    public StatusLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater mInflater = LayoutInflater.from(context);
        mLoadingView = mInflater.inflate(R.layout.layout_loading, this, false);
        mErrorView = mInflater.inflate(R.layout.layout_error, this, false);
        mEmptyView = mInflater.inflate(R.layout.layout_empty, this, false);
        addView(mLoadingView);
        addView(mErrorView);
        addView(mEmptyView);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            int count = getChildCount();
            mContentView = getChildAt(count - 1);
        }
    }

    //loading
    public void showLoding() {
        mLoadingView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        if (mContentView != null)
            mContentView.setVisibility(GONE);
    }

    //error
    public void showError() {
        mErrorView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        if (mContentView != null)
            mContentView.setVisibility(GONE);
    }

    //empty
    public void showEmpty() {
        mEmptyView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        if (mContentView != null)
            mContentView.setVisibility(GONE);
    }

    //content
    public void showContent() {
        if (mContentView != null)
            mContentView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
    }


}
