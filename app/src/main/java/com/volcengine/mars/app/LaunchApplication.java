package com.volcengine.mars.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.bytedance.lego.init.InitScheduler;
import com.bytedance.lego.init.config.TaskConfig;
import com.bytedance.lego.init.model.InitPeriod;
import com.bytedance.lego.init.monitor.InitMonitor;
import com.bytedance.lynx.webview.TTWebSdk;
import com.bytedance.veh5.VEH5;
import com.bytedance.veh5.config.VEH5Config;
import com.volcengine.mars.activity.ActivityStack;
import com.volcengine.mars.utils.AppUtils;
import com.volcengine.onekit.OneKitApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

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
        OneKitApp.initialize(this);
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
        initializeH5Service();
        ActivityStack.init(this);
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_ONCREATE2SUPER);
        super.onCreate();
        MPLaunch.INSTANCE.onPeriod(sApplication, InitPeriod.APP_SUPER2ONCREATEEND);
    }

    private void initializeH5Service() {
        List<Pattern> cachePrefixes = new ArrayList<>();
        cachePrefixes.add(Pattern.compile("https://mars-jsbridge.goofy-web.bytedance.com/"));
        cachePrefixes.add(Pattern.compile("https://sf6-scmcdn-tos.pstatp.com/goofy/"));

        List<String> internalCacheDirs = new ArrayList<>();
        internalCacheDirs.add("offline-resources");

        VEH5.INSTANCE.initialize(
                this,
                new VEH5Config.Builder()
                        .enableDebug(true)
                        .setDeviceId("7135843788734")
                        .setOfflineResourceAccessKey("d3d71ae74f5383ec33b4fd85b298f50f")
                        .setOfflineResourceUrlPrefixes(cachePrefixes)
                        .setInternalCacheDirs(internalCacheDirs)
                        .enableKernel(true)
                        .setKernelInitListener(new TTWebSdk.InitListener() {
                            @Override
                            public void onDownloadFinished() {
                                super.onDownloadFinished();
                                Toast.makeText(LaunchApplication.this, "内核下载完成", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSoFileUpdateFinished() {
                                super.onSoFileUpdateFinished();
                                Toast.makeText(LaunchApplication.this, "内核下载完成", Toast.LENGTH_SHORT).show();
                            }
                        })
        );
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
