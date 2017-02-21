package com.lin.study.java.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;

/**
 * Created by wenxuelin on 2017/2/14.
 */
public class JsonParser {

    @Test
    public void parse() {
        String str = "{\"workorder_id\":\"\",\"order_id\":\"\",\"interface_desc\":\"\",\"WorkorderStatusDesc\":\"³É¹¦½á°¸\",\"createdDate\":{\"date\":25,\"day\":0,\"hours\":8,\"minutes\":0,\"month\":11,\"nanos\":0,\"seconds\":12,\"time\":1482624012000,\"timezoneOffset\":-480,\"year\":116},\"CustName\":\"\",\"CustID\":\"\",\"CustMobile\":\"\",\"DriverID\":\"\",\"DriverName\":\"\",\"DriverPhone\":\"\",\"Question1\":\"???\",\"Question2\":\"\",\"Question3\":\"\",\"Question4\":\"\",\"Question5\":\"\",\"Question6\":\"\",\"modifiedDate\":{\"date\":25,\"day\":0,\"hours\":8,\"minutes\":0,\"month\":11,\"nanos\":0,\"seconds\":12,\"time\":1482624012000,\"timezoneOffset\":-480,\"year\":116},\"handleby\":\"³Â¾üÇå\",\"RoleGroupDescription\":\"Ò»Ïß×øÏ¯×é\",\"comment\":\"\"}";
        for(Map.Entry e : ((Map<String, Object>)JSON.parse(str)).entrySet()) {
            System.out.println(e.getKey()+":"+e.getValue());
        }
     }
}
