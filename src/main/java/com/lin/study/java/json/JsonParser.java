package com.lin.study.java.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxuelin on 2017/2/14.
 */
public class JsonParser {

    public static void main(String[] args) throws IOException{
        String input = "[{\"car_type_id\":2,\"estimated_price\":95,\"deadhead_price\":3.5,\"minimum_amount\":95,\"coupon_info\":\"\",\"estimated_cost\":[{\"name\":\"单价\",\"desc\":\"\",\"value\":\"0.5元/分钟+3.5元/公里\"},{\"name\":\"最低消费\",\"desc\":\"\",\"value\":\"95元\"}],\"show_few_car_tip\":0,\"car_type_xhdpi\":{\"image\":\"https://i3.yongche.name/media/g1/M01/03/1E/rBAApVWbQ-WIeUZkAAEHeyq6ep8AABW2gO-VigAAQeT103.png\",\"url\":\"\",\"desc\":\"\"},\"activity_desc\":{\"line_up\":{\"font_content\":\"参加充返\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"31\",\"font_color\":\"\",\"font_size\":\"\"}},\"line_bottom\":{\"font_content\":\"本单最多可节省31元\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"31\",\"font_color\":\"#ec4949\",\"font_size\":\"17\"}},\"url\":\"https://h5.yongche.com/Touch/Rechargerebate/index\"},\"estimate_rebate_info\":{\"font_content\":\"参加充返后约63元\",\"special_font\":63}},{\"car_type_id\":5,\"estimated_price\":130,\"deadhead_price\":5,\"minimum_amount\":130,\"coupon_info\":\"\",\"estimated_cost\":[{\"name\":\"单价\",\"desc\":\"\",\"value\":\"0.7元/分钟+5元/公里\"},{\"name\":\"最低消费\",\"desc\":\"\",\"value\":\"130元\"}],\"show_few_car_tip\":0,\"car_type_xhdpi\":{\"image\":\"https://i3.yongche.name/media/g1/M01/03/1E/rBAArVWbRA6IcWBLAAEoouL2huAAABW2gPKsNkAASi6120.png\",\"url\":\"\",\"desc\":\"\"},\"activity_desc\":{\"line_up\":{\"font_content\":\"参加充返\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"43\",\"font_color\":\"\",\"font_size\":\"\"}},\"line_bottom\":{\"font_content\":\"本单最多可节省43元\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"43\",\"font_color\":\"#ec4949\",\"font_size\":\"17\"}},\"url\":\"https://h5.yongche.com/Touch/Rechargerebate/index\"},\"estimate_rebate_info\":{\"font_content\":\"参加充返后约86元\",\"special_font\":86}},{\"car_type_id\":3,\"estimated_price\":142,\"deadhead_price\":5.5,\"minimum_amount\":142,\"coupon_info\":\"\",\"estimated_cost\":[{\"name\":\"单价\",\"desc\":\"\",\"value\":\"0.8元/分钟+5.5元/公里\"},{\"name\":\"最低消费\",\"desc\":\"\",\"value\":\"142元\"}],\"show_few_car_tip\":0,\"car_type_xhdpi\":{\"image\":\"https://i3.yongche.name/media/g1/M04/03/22/rBAArVWnTpSIC1_eAAETUeWJErEAABYOwP53hQAARNp744.png\",\"url\":\"\",\"desc\":\"\"},\"activity_desc\":{\"line_up\":{\"font_content\":\"参加充返\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"47\",\"font_color\":\"\",\"font_size\":\"\"}},\"line_bottom\":{\"font_content\":\"本单最多可节省47元\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"47\",\"font_color\":\"#ec4949\",\"font_size\":\"17\"}},\"url\":\"https://h5.yongche.com/Touch/Rechargerebate/index\"},\"estimate_rebate_info\":{\"font_content\":\"参加充返后约94元\",\"special_font\":94}},{\"car_type_id\":26,\"estimated_price\":189,\"deadhead_price\":5.5,\"minimum_amount\":189,\"coupon_info\":\"\",\"estimated_cost\":[{\"name\":\"单价\",\"desc\":\"\",\"value\":\"1.1元/分钟+5.5元/公里\"},{\"name\":\"最低消费\",\"desc\":\"\",\"value\":\"189元\"}],\"show_few_car_tip\":1,\"car_type_xhdpi\":{\"image\":\"https://i2.yongche.name/media/g1/M01/03/1E/rBAApVWbTUuIMWaPAADrDSLt8oMAABW3AB8CkEAAOsl045.png\",\"url\":\"\",\"desc\":\"\"},\"activity_desc\":{\"line_up\":{\"font_content\":\"参加充返\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"62\",\"font_color\":\"\",\"font_size\":\"\"}},\"line_bottom\":{\"font_content\":\"本单最多可节省62元\",\"font_size\":\"12\",\"font_color\":\"#585858\",\"special_font\":{\"font_content\":\"62\",\"font_color\":\"#ec4949\",\"font_size\":\"17\"}},\"url\":\"https://h5.yongche.com/Touch/Rechargerebate/index\"},\"estimate_rebate_info\":{\"font_content\":\"参加充返后约126元\",\"special_font\":126}}]";
        String message = "{\"message\":"+input+"}";
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> node = mapper.readValue(input, List.class);
        for(Map m : node) {
            System.out.println(m.get("car_type_id"));
        }
    }

    @Test
    public void parse() {
        File file = new File("/Users/wenxuelin/Downloads/test.txt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                Map<String, Object> values = mapper.readValue(tempString, Map.class);
                Map<String, Object> aa = (Map<String, Object>)values.get("createdDate");
                System.out.println("time :" + aa.get("time"));

                for(Map.Entry<String, Object> e : values.entrySet()) {
                    System.out.println(e.getKey()+":"+e.getValue());
                }
                System.out.println("line " + line + ": " + tempString);
                System.out.println("____________________________________");
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
     }
}
