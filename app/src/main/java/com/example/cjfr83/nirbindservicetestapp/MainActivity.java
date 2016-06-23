package com.example.cjfr83.nirbindservicetestapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cjfr83.nirboundservice.IMainAidlInterface;


public class MainActivity extends AppCompatActivity {


    IMainAidlInterface mIMainAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate", "bind to service");
        Intent i = new Intent();
        i.setClassName("com.example.cjfr83.nirboundservice", "com.example.cjfr83.nirboundservice.KeepTrackService");
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            mIMainAidlInterface = IMainAidlInterface.Stub.asInterface(service);
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            mIMainAidlInterface = null;
        }
    };


    public void registerCallBack(View view) {
        try {
            IBinder mBinder = new Binder();
            mIMainAidlInterface.registerCallBacks(mBinder,getPackageName());
            Log.e("register CallBack ", "registered with no exceptions");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void crashApp(View view) {
        throw new RuntimeException();
    }

}
