package co.astrnt.samplekycksdk;

import android.app.Application;

import co.astrnt.demosdk.DemoSDK;
import co.astrnt.demosdk.core.DemoSDKApi;

public class AstronautApp extends Application {

    private static DemoSDK astrntSDK;

    public static DemoSDKApi getApi() {
        return astrntSDK.getApi();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setUpSDK();
    }

    private void setUpSDK() {
        if (astrntSDK == null) {
            astrntSDK = new DemoSDK(this, BuildConfig.API_URL, BuildConfig.DEBUG);
        }
    }
}
