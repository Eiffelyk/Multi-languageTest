package utils.eiffelyk.www.multi_languagetest;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by 馋猫 on 2015/6/17.
 */
public class BaseActivity extends Activity{
    public static ArrayList<Activity> activities = new ArrayList<>();


    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        //activities.remove(activity);
        activity.finish();
    }
    public  static void removeAllActivity(){
        for (Activity activity :activities){
            if (activity!=null)
                removeActivity(activity);
        }
    }
    public static void removeAllActivityNoMe(Activity activityMe){
        for (Activity activity : activities) {
            if (activityMe.equals(activity))
                removeActivity(activity);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchLanguage(SharedPreferencesUtils.getInstance(BaseActivity.this).getString("key","zh"));
    }


    protected void switchLanguage(String language){
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        switch (language){
            case "zh_TW":
                configuration.locale = Locale.TRADITIONAL_CHINESE;
                break;
            case "zh_CN":
                configuration.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case "en":
                configuration.locale =Locale.ENGLISH;
                break;
            case "de":
                configuration.locale = Locale.GERMAN;
                break;
            case "fr":
                configuration.locale = Locale.FRENCH;
                break;
            case "ja":
                configuration.locale = Locale.JAPANESE;
                break;
            case "ko":
                configuration.locale = Locale.KOREAN;
                break;
            case "es":
                configuration.locale = new Locale("es");
                break;
            case "ar":
                configuration.locale = new Locale("ar");
                break;
            case "ru":
                configuration.locale = new Locale("ru");
                break;
            default:
                configuration.locale = Locale.getDefault();
                break;
        }
        resources.updateConfiguration(configuration,displayMetrics);
        SharedPreferencesUtils.getInstance(BaseActivity.this).putExtra("key",language);
    }
}
