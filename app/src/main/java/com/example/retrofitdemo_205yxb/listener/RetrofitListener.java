package com.example.retrofitdemo_205yxb.listener;

public interface RetrofitListener<T> {
    public void onSuccess(T t, int flag);
    public void onFail();
}
