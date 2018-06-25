package ink.liangxin.launchmodetest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsActivity extends Activity{
    private LinearLayout linearLayout;
    private TextView tv;


    private void printLog(String str){
        System.out.println(getClass().getSimpleName() + " -> " + str);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printLog("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        printLog("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printLog("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printLog("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printLog("onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        printLog("onWindowFocusChanged");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        printLog("onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printLog("onPause");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        printLog("onCreate 2个参数");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        printLog("onCreate 1个参数");

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        List<Class<? extends Activity>> myActivity = ((MyApplication) getApplication()).getMyActivity();

        for(Class<? extends Activity> c : myActivity){
            final Class<? extends Activity> myClass = c;
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
            button.setText("启动Activity："+c.getSimpleName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AbsActivity.this,myClass);
                    startActivity(intent);
                }
            });
            linearLayout.addView(button);
        }

        tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(tv);

        setContentView(linearLayout);
    }

    public void setInfo(String info){
        tv.setText(info);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        printLog("onSaveInstanceState 2个参数");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        printLog("onSaveInstanceState 1个参数");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        printLog("onRestoreInstanceState");
    }

}