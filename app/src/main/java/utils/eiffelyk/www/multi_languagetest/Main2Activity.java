package utils.eiffelyk.www.multi_languagetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * NOTE:
 一般，从用户体验角度讲，语言设置功能入口会放在App的前几层，如果入口太深，导致用户无法快速找到语言设置入口，并且如果要讲应用重启的话，用户行为操作记录会比较麻烦。
 重启对应Activity有几种方式：
 如果用户进入语言设置需要太多的层级，或者在操作语言设置之前操作的其他行为，APP想保存的，那可以通过广播的方式(sendBroadcast())，语言改变时发送广播，所有activity接受到广播后(BroadcastReceiver)，都进行重启操作；
 如果允许用户设置语言后，app回到主目录，这样就简单很多，直接调用上面的restart()方法即可。
 重启singleTask activity：

 如果你的启动activity是singleTask，向上面那样重启，语言还是不生效的。这种情况如何呢？可以通过了解、利用其生命周期来解决，在切回singleTask属性的activity时，activity会调用onNewIntent()方法。 重写该方法就可以。以下是一种解决方法，先finish自己，然后重启自己。

 @Override protected void onNewIntent(Intent intent) {
 if (intent.getAction() == null) {
 finish();
 Intent i = new Intent(this, MainActivity.class);
 startActivity(i);
 // overridePendingTransition(0, 0);
 } else {
 //其他逻辑
 }
 }
 */
public class Main2Activity extends BaseActivity {
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        setContentView(R.layout.activity_main2);
        initView();
         arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        arrayAdapter.add("跟随系统");
        arrayAdapter.add("zh_CN");
        arrayAdapter.add("zh_TW");
        arrayAdapter.add("ar");
        arrayAdapter.add("de");
        arrayAdapter.add("es");
        arrayAdapter.add("fr");
        arrayAdapter.add("ja");
        arrayAdapter.add("ko");
        arrayAdapter.add("ru");
        listView.setAdapter(arrayAdapter);
        initItemClick();
    }

    private void initItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("馋猫",arrayAdapter.getItem(position).toString());
                switchLanguage(arrayAdapter.getItem(position).toString());
                removeAllActivity();
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        listView = (ListView) this.findViewById(R.id.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
