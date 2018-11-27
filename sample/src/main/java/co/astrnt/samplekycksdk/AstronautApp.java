package co.astrnt.samplekycksdk;

import android.app.Application;

import co.astrnt.demosdk.DemoSDK;
import co.astrnt.demosdk.core.DemoSDKApi;

public class AstronautApp extends Application {

    private static DemoSDK demoSDK;

    public static DemoSDKApi getApi() {
        return demoSDK.getApi();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setUpSDK();
    }

    private void setUpSDK() {
        if (demoSDK == null) {
            demoSDK = new DemoSDK(BuildConfig.API_URL, BuildConfig.DEBUG);
        }
    }
}
