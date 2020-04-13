package ro.pub.cs.systems.eim.Colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Colocviu1_13Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String commands = intent.getStringExtra(Constants.SERVICE_COMMAND_INTENT);
        Log.d(Constants.TAG, "Services started by receiving: " + commands);
        processingThread = new ProcessingThread(this, commands);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
//        processingThread.stopThread();
    }
}
