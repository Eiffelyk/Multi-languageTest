多语言切换的demo
关键：
    1.需要保存切换后的语言sharedpreference中
    2.需要重启所有Activity才能生效

NOTE:
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