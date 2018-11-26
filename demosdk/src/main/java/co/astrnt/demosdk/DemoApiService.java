package co.astrnt.demosdk;

import java.util.HashMap;

import co.astrnt.demosdk.dao.RegisterApiDao;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by deni rohimat on 26/11/18
 */
public interface DemoApiService {

    @FormUrlEncoded
    @POST("astronaut/kyck/candidate/save")
    Observable<RegisterApiDao> registerCandidate(@FieldMap HashMap<String, String> data);

}