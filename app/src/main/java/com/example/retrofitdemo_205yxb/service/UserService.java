package com.example.retrofitdemo_205yxb.service;
import com.example.retrofitdemo_205yxb.bean.LoginBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("userLogin.do")
    Call<LoginBean> login(@Query("username") String username, @Query("userpass") String userpass);
}
