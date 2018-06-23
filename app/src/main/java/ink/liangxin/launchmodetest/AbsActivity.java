package ink.liangxin.launchmodetest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

public abstract class AbsActivity extends AppCompatActivity{
    private LinearLayout linearLayout;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
