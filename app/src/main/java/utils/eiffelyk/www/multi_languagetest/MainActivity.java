package utils.eiffelyk.www.multi_languagetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends BaseActivity {
    private TextView textView1;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        setContentView(R.layout.activity_main);
        initView();
        initOnclick();
    }

    private void initOnclick() {
        //获取当前系统语言
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(getLocaleLanguageString());
                //switchLanguage("zh_TW");
                //finish();
                //Intent it = new Intent(MainActivity.this, MainActivity.class);
                //startActivity(it);
            }
        });
        //跳转到语言切换页面
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switchLanguage("zh_CN");
                //finish();
                Intent it = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(it);
            }
        });
    }

    /**
     * 获取当前系统的语言；
     * @return  String 三种展现形式
     */
    private String getLocaleLanguageString() {
        Locale locale = getResources().getConfiguration().locale;
        return ("getLanguage==" + locale.getDefault().getLanguage() + "\n") + "getDisplayLanguage==" + locale.getDefault().getDisplayLanguage() + "\n" + "toString==" + locale.getDefault().toString() + "\n";
    }

    private void initView() {
        textView1 = (TextView) this.findViewById(R.id.textView);
        button1 = (Button) this.findViewById(R.id.button);
        button2 = (Button) this.findViewById(R.id.button2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
