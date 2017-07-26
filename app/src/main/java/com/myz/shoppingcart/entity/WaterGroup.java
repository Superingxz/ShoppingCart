package com.myz.shoppingcart.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */

public class WaterGroup {
    /**
     * water_group : [{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-06-16 14:43:11","image_url":"20170522112435976007.jpg","address_id":"2f2f2360-4135-11e7-a5eb-00163e062c7a","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":26,"update_time":"2017-07-13 17:51:53","total_num":16,"is_del":"0","id":"1108a740-525f-11e7-b4f7-00163e062c7a"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-07-22 09:36:51","image_url":"20170522112435976007.jpg","address_id":"8c716090-445c-11e7-8ace-b0d59d46982f","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":20,"total_num":50,"is_del":"0","id":"3c59c420-6e7e-11e7-9f46-00e066f08ac3"}]
     * brand_name : 农夫山泉
     */

    private String brand_name;
    private List<Water> water_group;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public List<Water> getWater_group() {
        return water_group;
    }

    public void setWater_group(List<Water> water_group) {
        this.water_group = water_group;
    }
}
