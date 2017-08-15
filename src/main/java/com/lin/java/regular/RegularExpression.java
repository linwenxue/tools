package com.lin.study.java.regular;

/**
 * Created by elevenlin@lagou.com on 2016/5/17.
 */
public class RegularExpression {
    public static void main(String[] args) {
        /*Pattern p = Pattern.compile("\\(?（?(北京|天津)?[省|市|州]?\\)?）?");
        Matcher m = p.matcher("(ads北京)ss）ff");
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                if (m.group(i) != null) {
                    System.out.println(i + ":" + m.group(i));
                }
            }
        }

        String baikeRegular = "网秦天下科技有限公司.{0,20}(位于|位置|成立|总部|地点|所在地|经营范围|所在地).{0,20}(成都|北京|上海|常州|江苏)[省|市|县|州]?";
        Pattern baikePattern = Pattern.compile(baikeRegular);
        String text = "核实北京网秦天下科技有限公司成立时间2005-10-21所在地中国北京注册资本5000万元人民币法定代表人史文勇企业类型有限责任公司睿峰科技睿峰科技有限公司总部设于成都CBD核心区域，";
        Matcher baikeMacher = baikePattern.matcher(text);
        while (baikeMacher.find()) {
            for (int i = 1; i <= baikeMacher.groupCount(); i++) {
                if (baikeMacher.group(i) != null) {//查找第二个分组，即地点"AddressUtil.getAllPattern().toString()"
                    String key = baikeMacher.group(2);
                    System.out.println("百度百科正则匹配:" + key);
                }
            }
        }*/
    }
}
