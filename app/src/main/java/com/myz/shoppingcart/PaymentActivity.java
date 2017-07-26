package com.myz.shoppingcart;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myz.shoppingcart.entity.BaseBean;
import com.myz.shoppingcart.entity.WaterGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 下单页面
 * Created by MC.Zeng on 2017-07-08.
 */

public class PaymentActivity extends AppCompatActivity{
    TextView back;
    ExpandableListView expandableListView;
    TextView tvTime;
    RelativeLayout rlPaymentTime;
    TextView allSelecticon;

    private BaseBean<List<WaterGroup>> bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initViews();
    }

    protected void initViews() {
        back = (TextView) findViewById(R.id.tv_payment_back);
        expandableListView = (ExpandableListView) findViewById(R.id.elv_payment);
        allSelecticon = (TextView) findViewById(R.id.tv_payment_all_circle);
        rlPaymentTime = (RelativeLayout) findViewById(R.id.rl_payment_time);
        tvTime = (TextView) findViewById(R.id.tv_payment_time);


        //配送时间点击
        rlPaymentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
                //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
                Calendar selectedDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                startDate.set(2017, 0, 1);//选择开始时间
                Calendar endDate = Calendar.getInstance();
                endDate.set(2019, 11, 28);//选择最大选择时间，，，月份是从0开始计算所以只有0-11月
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(PaymentActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvTime.setText(getTime(date));
                    }
                })//年月日时分秒 的显示与否，不设置则默认全部显示
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .setLabel("年", "月", "日", "点", "分", "")
                        .isCenterLabel(false)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setDate(selectedDate)
                        .setRangDate(startDate, endDate)
                        .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                        .setDecorView(null)
                        .build();

//                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();

            }
        });

        //设置箭头图标
        expandableListView.setGroupIndicator(null);
        //设置ExpandableListView是否可以展开，返回false可以展开，true位不展开
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        bean = JsonUtils("data.json");

        ShopCartExpandAdapter shoppingAdapter = new ShopCartExpandAdapter(PaymentActivity.this, bean);
        expandableListView.setAdapter(shoppingAdapter);
        //设置默认expandableListView全部展开
        for (int i = 0; i < shoppingAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public BaseBean<List<WaterGroup>> JsonUtils(String jsonPath) {
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open(jsonPath),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            isr.close();
            String json = builder.toString();//builder读取了JSON中的数据。
            Gson gson = new Gson();
            Type type = new TypeToken<BaseBean<List<WaterGroup>>>() {
            }.getType();
            BaseBean<List<WaterGroup>> listBaseBean = (BaseBean<List<WaterGroup>>) gson.fromJson(json, type);
            return listBaseBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
