package humble.slave.pts_b.features.model;

import android.content.Context;
import android.widget.Toast;

import humble.slave.pts_b.common.RequestCompleteListener;
import humble.slave.pts_b.features.model.data.serverData.HomeServerResponse;
import humble.slave.pts_b.features.presenter.services.ReceiveAndSendSMS;
import humble.slave.pts_b.network.API;
import humble.slave.pts_b.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallServerImpl implements CallServer{
    @Override
    public void getTrafficAndTemperature(Context context, RequestCompleteListener<HomeServerResponse> callback) {


            API api = new RetrofitClient().getRetrofit("http://192.168.1.10:8080/").create(API.class);
            Toast.makeText(context, "CALLING THE SERVER", Toast.LENGTH_SHORT).show();
            Call<HomeServerResponse> call = api.apiHomeServerResponse();
            call.enqueue(new Callback<HomeServerResponse>() {
                @Override
                public void onResponse(Call<HomeServerResponse> call, Response<HomeServerResponse> response) {
                    Toast.makeText(context, "RECEIVED", Toast.LENGTH_SHORT).show();
                    if(response.body()!=null){
//TODO : CANNOT USE THE DEGREE SIGN HERE SINCE IT IS NOT WITHIN THE DESIGNATED ASCII VALUES WHICH THE sendTextMessage ALLOWS TO PASS
//                        Toast.makeText(context, message[0], Toast.LENGTH_SHORT).show();
                        callback.onRequestSuccess(response.body());
                    }
                }


                @Override
                public void onFailure(Call<HomeServerResponse> call, Throwable t) {
//                    message[0] = t.getLocalizedMessage();
//                    Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    callback.onRequestFailed(t.getLocalizedMessage());
                }
            });

    }
}
