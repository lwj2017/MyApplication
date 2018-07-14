package com.zucc.lwj31502025.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


public class EditPlanActivity extends AppCompatActivity {
    private BDLocation bdLocation;
    //private List<Book> bookList=new ArrayList<>();
    private RelativeLayout relativeLayout;
    String addr;
    TextView locational;
    public LocationClient mLocationClient = null;
    private TimePicker timePicker;
    private int hour, minutes;
    private MyLocationListener myListener = new MyLocationListener();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);

        locational=(TextView)findViewById(R.id.location);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        final EditText title=(EditText)findViewById(R.id.editTexttitle);
        final EditText content=(EditText)findViewById(R.id.editTextneirong);
        Button baocun=(Button)findViewById(R.id.create_baocun);
        Button quxiao=(Button)findViewById(R.id.create_quxiao);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(0);
        option.setOpenGps(true);

        mLocationClient.setLocOption(option);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        Button button=(Button)findViewById(R.id.button);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minutes) {
                EditPlanActivity.this.hour = hour;
                EditPlanActivity.this.minutes = minutes;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locational.setText(addr);
            }
        });


        quxiao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1=new Intent(EditPlanActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        baocun.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String title1=title.getText().toString();
                String neirong1=content.getText().toString();
                String planDate = getIntent().getStringExtra("planDate");
                String planHour = timePicker.getCurrentHour().toString();
                String planMinutes = timePicker.getCurrentMinute().toString();
                
                finish();
            }
        });

    }


    public class MyLocationListener implements BDLocationListener {
        String addr;
        String country;
        String province;
        String city;
        String district;
        String street;

        @Override
        public void onReceiveLocation(BDLocation location) {

            addr = location.getAddrStr();    //获取详细地址信息
            country = location.getCountry();    //获取国家
            province = location.getProvince();    //获取省份
            city = location.getCity();    //获取城市
            district = location.getDistrict();    //获取区县
            street = location.getStreet();    //获取街道信息
            getInfo(location);
        }
    }
    public void getInfo(BDLocation location){
        addr=location.getAddrStr();
        Snackbar.make(relativeLayout,location.getAddrStr(),Snackbar.LENGTH_LONG).show();
    }


}
