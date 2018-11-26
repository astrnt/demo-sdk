package co.astrnt.demosdk.core;

import com.google.gson.Gson;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import co.astrnt.demosdk.DemoSDK;
import co.astrnt.demosdk.dao.BaseApiDao;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by deni rohimat on 26/11/18
 */
public abstract class MyObserver<T extends BaseApiDao> implements Observer<T> {

    protected DemoSDK demoSDK = new DemoSDK();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public final void onComplete() {
        onApiResultCompleted();
    }

    @Override
    public final void onError(Throwable e) {
        onComplete();
        if (e instanceof retrofit2.HttpException) {
            try {
                ResponseBody body = ((retrofit2.HttpException) e).response().errorBody();
                assert body != null;
                BaseApiDao apiDao = new Gson().fromJson(body.string(), BaseApiDao.class);
                onApiResultError(apiDao.getMessage(), apiDao.getStatus());
            } catch (Exception e2) {
                e2.printStackTrace();
                onApiResultError("Something wrong, please contact developer@astrnt.co", "exception");
            }
        } else if (e instanceof UnknownHostException) {
            onApiResultError("Connection lost, please try again", "exception");
        } else if (e instanceof SocketTimeoutException) {
            onApiResultError("Connection lost, please try again", "exception");
        } else {
            System.err.println(e.getMessage());
            e.printStackTrace();
            onApiResultError("Something wrong, please contact help@astrnt.co", "exception");
        }
    }

    @Override
    public final void onNext(T t) {
        if (t.getStatus() != null && (t.getStatus().contains("error") || t.getStatus().contains("ERROR"))) {
            onApiResultError(t.getMessage(), t.getStatus());
        } else {
            onApiResultOk(t);
        }
    }

    public abstract void onApiResultCompleted();

    public abstract void onApiResultError(String message, String code);

    public abstract void onApiResultOk(T t);
}