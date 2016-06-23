package com.example.cjfr83.nirboundservice;

import android.os.IBinder;

interface IMainAidlInterface {

   void registerCallBacks(in IBinder cb , String packageName);
}

