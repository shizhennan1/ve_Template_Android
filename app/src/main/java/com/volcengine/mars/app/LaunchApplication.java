package com.volcengine.mars.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import com.bytedance.lego.init.InitScheduler;
import com.bytedance.lego.init.config.TaskConfig;
import com.bytedance.lego.init.model.InitPeriod;
import com.bytedance.lego.init.monitor.InitMonitor;
import com.volcengine.mars.activity.ActivityStack;
import com.volcengine.mars.utils.AppUtils;
import java.util.List;

public class LaunchApplication extends Application {

    private static final String TAG = "LaunchApplication";
    public static Application sApplication;
    public static String sProcessName;
    public static boolean sIsMainProcess;

    private static Context sBaseContext;

    public static Context getContext() {
        return sBaseContext;
    }

    public static Application getInstance() {
        return sApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        // start init monitor
        InitMonitor.INSTANCE.onAttachBase();
        sBaseContext = base;
        sApplication = this;
        sProcessName = getProcessName(base);
        sIsMainProcess = base.getPackageName().equals
                (sProcessName);

        TaskConfig config = new TaskConfig.Builder(base, sIsMainProcess, sProcessName)
                .isDebug(AppUtils.isDebug(sBaseContext))
                .setTimeOut(60 * 1000)
                .build();
        InitScheduler.config(config);

        super.attachBaseContext(base);
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_ATTACHBASE2SUPER);
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_SUPER2ATTACHBASEEND);
    }

    public static String getProcessName(Context cxt) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return "";
    }

    @Override
    public void onCreate() {
        ActivityStack.init(this);
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_ONCREATE2SUPER);
        super.onCreate();
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_SUPER2ONCREATEEND);
    }

    /**
     * In attachBaseContext phase, getApplicationContext will return null and cannot create LocalBroadcastManager
     * instance
     */
    @Override
    public Context getApplicationContext() {
        return this;
    }
}
