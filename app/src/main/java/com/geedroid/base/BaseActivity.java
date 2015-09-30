package com.geedroid.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geedroid.base.common.LogUtil;
import com.geedroid.base.common.SPreferenceUtil;
import com.geedroid.base.common.SystemBarTintManager;

/**
 * Created by james.li on 2015/9/29.
 * 继承此类，可以使用getView(R.id.test);找到对应的控件，无需在使用findViewById()
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initStatusBar();
        initTheme();
        initLayout();
    }


    /**
     * 获取布局view
     * @return
     */
    protected abstract View getContentView();


    /**
     * 初始化标题栏
     */
    public void initLayout(){

        View view = getContentView();

        ViewGroup titleLayout = (ViewGroup)view.findViewById(R.id.title_bar_layout);
        TextView title = (TextView)view.findViewById(R.id.title_bar_title);
        ImageButton leftBtn = (ImageButton)view.findViewById(R.id.title_bar_back);
        ImageButton rightBtn = (ImageButton)view.findViewById(R.id.title_bar_right);
        View shadow = view.findViewById(R.id.title_bar_shadow);

        initTitleBar(titleLayout,title,leftBtn,rightBtn,shadow);
        setContentView(view);
    }

    /**
     * 初始化标题栏各项参数
     * @param titleLayout
     * @param title
     * @param leftBtn
     * @param rightBtn
     * @param shadow
     */
    public abstract void initTitleBar(ViewGroup titleLayout, TextView title, ImageButton leftBtn, ImageButton rightBtn, View shadow);

    /**
     * 设置主题
     */
    private void initTheme() {

        int theme = (Integer) SPreferenceUtil.get(mContext, "theme", 0);
        switch (theme) {
            case 0:
                setTheme(R.style.BlueTheme);
                break;
            default:
                break;
        }
    }

    /**
     * 沉浸状态栏
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initStatusBar() {
        //获取主题
        int theme = (Integer) SPreferenceUtil.get(mContext, "theme", 0);
        int color = R.color.theme_0;
        switch (theme) {
            case 0:
                color = R.color.theme_0;
                break;
            default:
                break;
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        setStatusBarColor(tintManager,color);
    }

    protected void setStatusBarColor(SystemBarTintManager tintManager,int color){
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(getResources().getColor(color));
    }

    /**
     * 切换Fragment
     *
     * @param id       要切换的布局id
     * @param fragment 要切换的Fragment
     */
    protected void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    /**
     * 隐藏软键盘 hideSoftInputView
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



    /**
     * 返回按钮
     * @param view
     */
    public void back(View view){
        onBackPressed();
    }


    /**
     * 可以使用getView(R.id.test);找到对应的控件，无需在使用findViewById()
     * @param id
     * @param <E>
     * @return
     */
    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            LogUtil.e("Could not cast View to concrete class.");
            throw ex;
        }
    }

}
