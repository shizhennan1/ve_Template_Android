/**
 * manage all dependencies bound to the baseline version
 * "_plugin" suffix for gradle plugin
 * "_processor" suffix for annotation processor
 * created by luoqiaoyou on 2020/9/15.
 */
public class BaselineDeps {

    /***********************
     ** h5 framework
     ***********************/
    public static String h5_service = "com.volcengine:h5-service:1.1.0";


    /***********************
     ** service manager
     ***********************/
    public static String service_manager_plugin = "com.bytedance.news.common:service-manager-plugin:1.1.0.4";
    public static String service_manager_processor = "com.bytedance.news.common:service-manager-processor:1.0.9";
    public static String service_manager = "com.bytedance.news.common:service-manager:1.0.9.3";
    public static String service_manager_ext = "com.bytedance.news.common:service-manager-ext:1.0.9.3";

    /***********************
     ** init scheduler
     ***********************/
    private static String INIT_VERSION = "1.0.6-alpha.95";
    public static String init_task_plugin = "com.bytedance.lego.plugin:init-scheduler:" + INIT_VERSION;
    public static String init_task_scheduler = "com.bytedance.lego:init-scheduler:" + INIT_VERSION;
    public static String init_task_api = "com.bytedance.lego:init-api:" + INIT_VERSION;
    /***********************
     ** jvm/system bugfix component
     ***********************/
    private static String GODZILLA_VERSION = "3.0.3-rc.34";
    public static String godzilla_lib = "com.bytedance.platform:godzilla-lib:" + GODZILLA_VERSION;
    public static String godzilla_anr = "com.bytedance.platform:godzilla-anr:" + GODZILLA_VERSION;
    public static String godzilla_crash = "com.bytedance.platform:godzilla-crash:" + GODZILLA_VERSION;
    public static String godzilla_sysopt = "com.bytedance.platform:godzilla-sysopt:" + GODZILLA_VERSION;

    /***********************
     ** onekit sdk
     ***********************/
    private static String ONEKIT_VERSION = "0.0.1-rc.18";
    public static String onekit_sdk = "com.volcengine.onekit:onekit:" + ONEKIT_VERSION;
    public static String onekit_plugin = "com.volcengine.onekit:onekit-plugin:" + ONEKIT_VERSION;

    /***********************
     ** hotfix
     ***********************/
    private static String HOTFIX_VERSION = "2.3.3";
    public static String hotfix_sdk = "com.volcengine:hotfix-frankie:" + HOTFIX_VERSION;
    public static String hotfix_plugin = "com.volcengine:hotfix-gradle-tools:" + HOTFIX_VERSION;
    /*******************
     * okHttp
     *******************/
    public static String okhttp = "com.squareup.okhttp3:okhttp:3.14.9";

    /*******************
     * 二维码
     *******************/
    public static String qrcode = "com.volcengine.mars:qrcode:0.0.1-rc.6";

    /*******************
     * 工具库（沉浸式、权限、截屏、屏幕适配）
     *******************/
    public static String utility = "com.volcengine.mars:utility:0.0.1-rc.6  ";

    /*******************
     * 下载库
     *******************/
    public static String downloader = "com.bytedance.ies.social.base:downloader:1.3.3.6-rc.1";

    /*******************
     * kotlin
     *******************/
    private static String KOTLIN_VERSION = "1.3.61";
    public static String kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:" + KOTLIN_VERSION;
    public static String kotlin_stdlib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" + KOTLIN_VERSION;
    public static String kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + KOTLIN_VERSION;

    /*******************
     * androidx
     *******************/
    public static String lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:2.2.0";
    public static String navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:2.2.0";
    public static String navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:2.2.0";
    public static String x_annotation = "androidx.annotation:annotation:1.1.0";
    public static String support_compat = "androidx.core:core:1.2.0";
    public static String support_v4 = "androidx.legacy:legacy-support-v4:1.0.0";
    public static String appcompat = "androidx.appcompat:appcompat:1.1.0";
    public static String activity = "androidx.activity:activity:1.1.0";
    public static String recyclerview = "androidx.recyclerview:recyclerview:1.1.0";
    public static String constraint_layout = "androidx.constraintlayout:constraintlayout:1.1.3";

    /*******************
     * android plugin
     *******************/
    public static String android_gradle_plugin = "com.android.tools.build:gradle:3.4.1";

    /*******************
     * material design
     *******************/
    public static String material = "com.google.android.material:material:1.1.0";

    /*******************
     * test
     *******************/
    public static String junit = "junit:junit:4.12";
    public static String runner = "androidx.test.ext:junit:1.1.1";
    public static String espresso_core = "androidx.test.espresso:espresso-core:3.1.0";

}