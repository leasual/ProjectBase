package com.geedroid.base;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUIViews();
    }


    public void initUIViews(){
        TextView testText = getView(R.id.testText);
        testText.setText("TestActivity");
    }

    @Override
    protected View getContentView() {
        return View.inflate(mContext,R.layout.activity_test,null);
    }

    @Override
    public void initTitleBar(ViewGroup titleLayout, TextView title, ImageButton leftBtn, ImageButton rightBtn, View shadow) {
        title.setText("TestActivity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
