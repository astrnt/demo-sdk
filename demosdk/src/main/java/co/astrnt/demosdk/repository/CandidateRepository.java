package co.astrnt.demosdk.repository;

import android.graphics.Bitmap;

import java.util.HashMap;

import co.astrnt.demosdk.core.DemoSDKApi;
import co.astrnt.demosdk.dao.BaseApiDao;
import co.astrnt.demosdk.dao.RegisterApiDao;
import co.astrnt.demosdk.utils.Utils;
import io.reactivex.Observable;

/**
 * Created by deni rohimat on 26/11/18
 */
public class CandidateRepository extends BaseRepository {
    private final DemoSDKApi mDemoSDKApi;

    public CandidateRepository(DemoSDKApi demoSDKApi) {
        mDemoSDKApi = demoSDKApi;
    }

    public Observable<RegisterApiDao> registerCandidate(String name, String email) {

        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);

        return mDemoSDKApi.getApiService().registerCandidate(map);
    }

    public Observable<BaseApiDao> saveIdCard(String candidateId, Bitmap bitmapImg) {

        HashMap<String, String> map = new HashMap<>();
        map.put("candidate_identifier", candidateId);
        map.put("file", Utils.imageToBase64(bitmapImg));

        return mDemoSDKApi.getApiService().saveIdCard(map);
    }

}
