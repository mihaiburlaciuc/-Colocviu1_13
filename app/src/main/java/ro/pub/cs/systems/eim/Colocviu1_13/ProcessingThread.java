package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;


public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    String commands;
    public ProcessingThread(Context context, String commands) {
        this.context = context;
        this.commands = commands;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());

        sleep();
        sendMessage();

        Log.d(Constants.TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + commands);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

//    public void stopThread() {
//    }
}
