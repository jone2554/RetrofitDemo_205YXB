package com.example.retrofitdemo_205yxb.model;
import com.example.retrofitdemo_205yxb.bean.LoginBean;
import com.example.retrofitdemo_205yxb.listener.RetrofitListener;
import com.example.retrofitdemo_205yxb.service.UserService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.retrofitdemo_205yxb.common.Constants.BASE_URL;

public class UserModel
{
    private UserService service;
        private Retrofit retrofit;
        public UserModel() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(UserService.class);
        }
        public void userLogin(String username, String password, final RetrofitListener listener) {
            retrofit2.Call call=service.login(username,password);
            Callback<LoginBean> callback = new Callback<LoginBean>() {
                @Override
                public void onResponse(retrofit2.Call<LoginBean> call, Response<LoginBean> response) {
                    listener.onSuccess(response.body(),1);
                }
                @Override
                public void onFailure(retrofit2.Call<LoginBean> call, Throwable t) {
                    listener.onFail();
                }
            };
            call.enqueue(callback);
        }
}
