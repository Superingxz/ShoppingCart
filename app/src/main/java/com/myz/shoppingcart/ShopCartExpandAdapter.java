package com.myz.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myz.shoppingcart.entity.BaseBean;
import com.myz.shoppingcart.entity.WaterGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */

public class ShopCartExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;

    private BaseBean<List<WaterGroup>> bean;

    public ShopCartExpandAdapter(Context context, BaseBean<List<WaterGroup>> bean) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bean = bean;
    }

    /*第二级Child */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return bean.getData().get(groupPosition).getWater_group().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_item_payment_product, parent, false);
        String name = bean.getData().get(groupPosition).getWater_group().get(childPosition).getGoods_name();
        TextView itemName = (TextView) convertView.findViewById(R.id.tv_payment_name_item);
        itemName.setText(name);
        TextView checkBox = (TextView) convertView.findViewById(R.id.tv_check_box_item_item);
        final TextView num = (TextView) convertView.findViewById(R.id.tv_payment_num);
        ImageView subtract = (ImageView) convertView.findViewById(R.id.img_payment_subtract);
        ImageView add = (ImageView) convertView.findViewById(R.id.img_payment_add);
        //减少数量
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将得到的num的数据String转化为int
                int number = Integer.parseInt(num.getText().toString());
                if (number > 1) {
                    number--;
                    num.setText(number + "");

                } else {
                    Toast.makeText(context, "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增加数量
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(num.getText().toString());
                number++;
                num.setText(number + "");
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return bean.getData().get(groupPosition).getWater_group().size();
    }

    /*第一级Group */
    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return getGroup(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return bean.getData().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String name = bean.getData().get(groupPosition).getBrand_name();
        convertView = inflater.inflate(R.layout.item_payment_product, parent, false);
        TextView productName = (TextView) convertView.findViewById(R.id.tv_payment_name);
        productName.setText(name);
        TextView checkBox = (TextView) convertView.findViewById(R.id.tv_check_box_item);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }
}
