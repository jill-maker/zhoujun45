package demo.zhoujun45.zhoujun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;



public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText username,password;
    private CheckBox chk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        restoreInfo();

    }
    private void initView(){
        btn_login=(Button)findViewById(R.id.btn_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        chk=(CheckBox)findViewById(R.id.chk);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk.isChecked()){
                    String usr=username.getText().toString();
                    String pwd=password.getText().toString();
                    memInfo(usr,pwd);
                }else{
                    SharedPreferences.Editor et=getSharedPreferences("data",0).edit();
                    et.clear();
                    et.commit();
                }
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void memInfo(String usr,String pwd){
        SharedPreferences.Editor editor=getSharedPreferences("data",0).edit();
        editor.putString("username",usr);
        editor.putString("password",pwd);
        editor.commit();
    }
    private void restoreInfo(){
        SharedPreferences sp=getSharedPreferences("data",0);
        username.setText(sp.getString("username",""));
        password.setText(sp.getString("password",""));
    }
}
