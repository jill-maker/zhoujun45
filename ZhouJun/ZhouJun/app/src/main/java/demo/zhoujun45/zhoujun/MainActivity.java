package demo.zhoujun45.zhoujun;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String lgName,jsonStr;
    private String signNmae="";
    protected TextView lgNameTx,signNameTx;
    protected ListView mainListView;
    protected MyAdapter myAdapter;
    protected List<DouBanEntity.ListBean> listData =new ArrayList<>();

 //   public MainActivity() {
  //      signNameTx = lgNameTx.findViewById();
  //      lgNameTx = signNameTx.findViewById();
   //     mainListView = mainListView.findViewById();
   // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取登陆界面传递过来的用户名
        lgName = CacheUtil.getPermanent("lgName",String.class,"");
        signNmae = CacheUtil.getPermanent("signNmae",String.class,"");
        jsonStr = CacheUtil.getPermanent("json",String.class,"");
        initView();
        initData();
    }

    public void setContentView(int activity_main) {
    }

    @Override
      void initView() {
        signNameTx.setOnClickListener(new View.OnClickListener()
        {
            @Override
             public void onClick(View view) {
               Intent i = new Intent(MainActivity.this,SignNameActivity.class);
            }
        });
    }

    @Override
    void initData() {
        lgNameTx.setText(lgName);
        if(signNmae.length()>0){
            signNameTx.setText(signNmae);
        }else{
            signNameTx.setText("设置你的个性签名？");
        }
        //JSONObject userJson = JSONObject.parseObject(jsonStr);
        //DouBanEntity data = JSON.toJavaObject(userJson,DouBanEntity.class);
        listData.clear();
        //listData.addAll(data.getList());
        myAdapter = new MyAdapter(MainActivity.this, R.layout.item_main_layout, listData, new MyAdapter.OnBackInfo() {
            @Override
            public void onClickItem(int postion) {
                listData.remove(postion);
                myAdapter.notifyDataSetChanged();
            }
        });
        mainListView.setAdapter(myAdapter);
    }
}

