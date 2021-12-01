package com.volcengine.mars.demo.update;

import android.Manifest;
import android.Manifest.permission;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.volcengine.mars.R;
import com.volcengine.mars.update.OnUpdateStatusChangedListener;
import com.volcengine.mars.update.VEUpdate;
import com.volcengine.mars.update.UpdateConfig;
import com.volcengine.mars.update.UpdateDialogStyle;
import com.volcengine.mars.update.UpdateStrategyInfo;
import com.volcengine.mars.permissions.PermissionsManager;
import java.lang.ref.WeakReference;

public class UpdateDemoActivity extends AppCompatActivity {
    private static final String TAG = "UpdateHomeActivity";
    private Button mButton;
    private UpdateStrategyInfo updateStrategyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                permission.ACCESS_FINE_LOCATION,
                permission.ACCESS_COARSE_LOCATION,
                permission.REQUEST_INSTALL_PACKAGES
        };
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions,null);
        setContentView(R.layout.activity_update);
        mButton = findViewById(R.id.updateButton);
        UpdateConfig updateConfig = getUpdateConfig();
        VEUpdate.initialize(updateConfig);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                VEUpdate.checkUpdate(UpdateDialogStyle.STYLE_BIG_INNER, new OnUpdateStatusChangedListener() {
                    @Override
                    public void onUpdateStatusChanged(final int status) {
                        boolean isUpdateAvailable = VEUpdate.isRealCurrentVersionOut();
                        Log.d(TAG, "onUpdateStatusChanged status = " + status);
                    }

                    @Override
                    public void saveDownloadInfo(final int size, final String etag, final boolean pre) {
                        Log.d(TAG, "onUpdateStatusChanged saveDownloadInfo = " + pre);
                    }

                    @Override
                    public void updateProgress(final int byteSoFar, final int contentLength, final boolean pre) {
                        Log.d(TAG, "onUpdateStatusChanged updateProgress = " + pre);
                    }

                    @Override
                    public void downloadResult(final boolean isSuccess, final boolean pre) {
                        Log.d(TAG, "onUpdateStatusChanged downloadResult = " + isSuccess);
                    }

                    @Override
                    public void onPrepare(final boolean pre) {
                        Log.d(TAG, "onUpdateStatusChanged onPrepare = " + pre);
                    }
                },false);
            }
        });

    }
    public UpdateConfig getUpdateConfig() {
        int drawableRes = R.drawable.btn_appupdate_bind_check;

        String authority = "com.mars.android.update";

        if (updateStrategyInfo == null) {
            updateStrategyInfo = new UpdateStrategyInfo();
        }
        updateStrategyInfo.currentActivity = new WeakReference<>(this);
        return new UpdateConfig.Builder()
                .setAppCommonContext(new AppCommonContextImpl(UpdateDemoActivity.this.getBaseContext()))
                .setIUpdateForceExit(new UpdateForceExitImpl())
                .setNotifyIcon(drawableRes)
                .setFormalAuthority(authority)
                .setUpdateStrategyInfo(updateStrategyInfo)
                .build();

    }
}
