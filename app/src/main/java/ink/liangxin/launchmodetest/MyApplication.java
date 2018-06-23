package ink.liangxin.launchmodetest;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangxin on 2018/6/23.
 */

public class MyApplication extends Application {
    public List<Class<? extends Activity>> getMyActivity() {
        return myActivity;
    }

    private List<Class<? extends Activity>> myActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        getActivitiesClass(getPackageName());
        for(Class<? extends Activity> my : myActivity){
            System.out.println(my.getSimpleName());
        }
    }

    private void getActivitiesClass(String packageName){

        List<Class<? extends Activity>> returnClassList = new ArrayList<>();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if(packageInfo.activities!=null){
                for(ActivityInfo ai: packageInfo.activities){
                    Class c;
                    try {
                        c = Class.forName(ai.name);
                        if(Activity.class.isAssignableFrom(c)){
                            returnClassList.add(c);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        myActivity = returnClassList;
    }
}
