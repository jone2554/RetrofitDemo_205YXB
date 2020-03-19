package com.example.retrofitdemo_205yxb.activies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitdemo_205yxb.R;
import com.example.retrofitdemo_205yxb.bean.LoginBean;
import com.example.retrofitdemo_205yxb.listener.RetrofitListener;
import com.example.retrofitdemo_205yxb.model.UserModel;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements RetrofitListener<LoginBean> {
    private EditText username, userpass;
    private Button btndl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initEvents();
    }

    private void initViews() {
        username = findViewById(R.id.etuser);
        userpass = findViewById(R.id.etpass);
        btndl = findViewById(R.id.button);

    }
    private void initEvents(){
        btndl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().equals("")||userpass.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    login();
                }
            }
        });
    }
    public void login()
    {
        UserModel userModel = new UserModel();
        userModel.userLogin(username.getText().toString().trim(), userpass.getText().toString().trim(), (RetrofitListener) this);
    }
    @Override
    public void onSuccess(LoginBean userId, int flag) {

        if(userId!=null &&((LoginBean)userId).getUserid()!=null)
            Toast.makeText(LoginActivity.this, "登录成功："+((LoginBean)userId).getUserid(), Toast.LENGTH_SHORT).show();
        else Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onFail() {
        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }



    public void saveUser(String user_id) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("username", username.getText().toString());
        int userid = Integer.parseInt(user_id);
        editor.putInt("user_id", userid);
        editor.putString("userpass", userpass.getText().toString());
        editor.commit();//提交修改
    }
}
