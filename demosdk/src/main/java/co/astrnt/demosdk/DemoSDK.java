package co.astrnt.demosdk;

import co.astrnt.demosdk.core.DemoSDKApi;
import timber.log.Timber;

public class DemoSDK {

    private static DemoSDKApi mDemoSDKApi;
    private static String mApiUrl;
    private boolean isDebuggable;

    public DemoSDK(String apiUrl, boolean debug) {
        mApiUrl = apiUrl;
        isDebuggable = debug;

        if (debug) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public DemoSDK() {
    }

    public String getApiUrl() {
        return mApiUrl;
    }

    public DemoSDKApi getApi() {
        if (mDemoSDKApi == null) {
            mDemoSDKApi = new DemoSDKApi(getApiUrl(), isDebuggable);
        }
        return mDemoSDKApi;
    }

}
