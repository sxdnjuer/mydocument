package com.example.phonecapture;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

//����һ�������쳣��������Ҫ��ʱ�����
public class CrashHandler implements UncaughtExceptionHandler {
 
        private final static String TAG = "UncaughtExceptionHandler";
        private Thread.UncaughtExceptionHandler mDefaultHandler;
        private static CrashHandler mInstance;
        private Context mContext;
 
        private CrashHandler() {
        }
 
        /** ��ȡCrashHandlerʵ�� ,����ģʽ */
        public static CrashHandler getInstance() {
                if (mInstance == null)
                        mInstance = new CrashHandler();
                return mInstance;
        }
 
        public void uncaughtException(Thread thread, Throwable throwable) {
                // if (!handleException(throwable) && mDefaultHandler != null) {
                // // ����û�û�д�������ϵͳĬ�ϵ��쳣������������
                // mDefaultHandler.uncaughtException(thread, throwable);
                // } else {
                // // Sleepһ����������
                // // �����߳�ֹͣһ����Ϊ����ʾToast��Ϣ���û���Ȼ��Kill����
                // try {
                // Thread.sleep(300);
                // } catch (InterruptedException e) {
                Log.e("Error : ", "e");
                // }
                ActivityManager activityMgr = (ActivityManager) mContext
                                .getSystemService(Context.ACTIVITY_SERVICE);
 
                activityMgr.restartPackage(mContext.getPackageName());
 
                activityMgr.killBackgroundProcesses(mContext.getPackageName());
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                // }
        }
 
        private boolean handleException(Throwable ex) {
                if (ex == null) {
                        return true;
                }
                final String msg = ex.getLocalizedMessage();
                // ʹ��Toast����ʾ�쳣��Ϣ
                new Thread() {
                        @Override
                        public void run() {
                                // Toast ��ʾ��Ҫ������һ���̵߳���Ϣ������
                                Looper.prepare();
                                Toast.makeText(mContext, "Exception:" + msg, Toast.LENGTH_LONG)
                                                .show();
                                Looper.loop();
                        }
                }.start();
                return true;
        }
 
        public void init(Context context) {
                mContext = context;
                mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(this);
        }
 
}