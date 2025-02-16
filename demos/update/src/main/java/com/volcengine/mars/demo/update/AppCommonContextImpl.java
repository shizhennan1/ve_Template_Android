package com.volcengine.mars.demo.update;

import android.content.Context;
import com.volcengine.mars.update.AbsAppCommonContext;
import com.volcengine.mars.update.DeviceWrapper;
import java.util.Map;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public class AppCommonContextImpl extends AbsAppCommonContext {
    private static final String HOST_ADDRESS = "";
    @Override
    public String getCustomUrl() {
        return HOST_ADDRESS;
    }

    private Context mContext;

    public AppCommonContextImpl(Context context) {
        mContext = context;
    }

    @NotNull
    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public String getStringAppName() {
        return "升级SDK Demo";
    }

    @NotNull
    @Override
    public String getDeviceId() {
//        若接入了applog，或实现了Device接口，可参考注释中的代码获取did，此接口不能返回空字符串
//        DeviceWrapper deviceService = new DeviceWrapper();
//        return deviceService.getDeviceID();
        return "12345678912";
    }
    @Override
    public String getChannel() {
//        return isDebugApk() ? "local_test" : "release";
        return "beta";
    }
    @Override
    public Map<String, String> getCustomKV() {
        Map<String, String> customMap = new HashMap<>();
//        customMap.put("user_id", "1234501");
//        customMap.put("user_department", "DP51214");
//        customMap.put("user_role", "10010015");
        return customMap;
    }
}
