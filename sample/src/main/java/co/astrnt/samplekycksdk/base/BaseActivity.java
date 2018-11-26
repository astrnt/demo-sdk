package co.astrnt.samplekycksdk.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import co.astrnt.demosdk.DemoSDK;
import co.astrnt.demosdk.core.DemoSDKApi;
import co.astrnt.samplekycksdk.AstronautApp;

public class BaseActivity extends AppCompatActivity {
    protected Context context = this;
    protected DemoSDK demoSDK;

    public static DemoSDKApi getApi() {
        return AstronautApp.getApi();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        demoSDK = new DemoSDK();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
