package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

class ProcessingThread extends Thread{
    String my_s;

    Context context;
    public ProcessingThread(Context context, String s) {
        this.context = context;
        this.my_s = s;
    }

    @Override
    public void run() {
        try {
            sleep(5000);
            sendMessage(my_s);
        } catch (Exception e) {

        }
    }

    private void sendMessage(String to_view) {
        Intent intent = new Intent();
        intent.setAction("action");
        intent.putExtra("extra",
                new Date(System.currentTimeMillis()) + " " + to_view);
        context.sendBroadcast(intent);
    }
}