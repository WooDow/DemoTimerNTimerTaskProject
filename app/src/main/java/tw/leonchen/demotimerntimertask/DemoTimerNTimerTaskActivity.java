package tw.leonchen.demotimerntimertask;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DemoTimerNTimerTaskActivity extends AppCompatActivity {

    private TextView tv_msg;
    private Timer timer;
    private MyTimerTask task;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_timer_ntimer_task);

        tv_msg = (TextView)findViewById(R.id.tv_msg);
    }

    public void processTimerStart(View view) {
        if(timer==null) {
            timer = new Timer();
            task = new MyTimerTask();
            handler = new MyHandler();
            //延遲3秒後開始執行 每秒執行一次
            timer.schedule(task, 3000, 1000);
        }
    }

    public void processTimerStop(View view) {
        if (timer!=null) {
            timer.cancel();
            timer = null;
        }
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                tv_msg.setText("Time:" + new Date());
            }
        }
    }

    private class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            //傳送代號為0
            handler.sendEmptyMessage(0);
        }
    }
}
