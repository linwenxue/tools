package com.lin.study.spark.streaming

import java.util.function.Consumer

import com.google.common.base.Splitter
import org.joda.time._
import org.joda.time.format.DateTimeFormat

/**
  * Created by wenxuelin on 2016/12/15.
  */
object Test {
  def main(args: Array[String]): Unit = {
    "bbb" match {
      case "aaa" | "bbb" => println("match ..")
      case _ => println("not match")
    }
    val str = "21,12".split(",").map { x =>
      x.toInt
    }.toList.sortWith(_ < _).slice(0,3)
    str.foreach(println)

    val dt1 = DateTime.parse("2017010415", DateTimeFormat.forPattern("yyyyMMddHH"))
    val dt2 = DateTime.parse("2017010115", DateTimeFormat.forPattern("yyyyMMddHH"))
    println("时间相差：")
    println(Days.daysBetween(dt1, dt2).getDays() + " 天 ")
    println(Hours.hoursBetween(dt1, dt2).getHours() + " 小时 ")
    println(Minutes.minutesBetween(dt1, dt2).getMinutes() + " 分钟 ")
    println(Seconds.secondsBetween(dt1, dt2).getSeconds()+ " 秒")

    val str2 = "23,45,34,36".split(",").toList.slice(0,6)
    str2.foreach(println)
    println(str2.length)

    val splitter = Splitter.on("    ")

    val toSplit="""2017-06-07 14:53:50    140.206.88.220    -    124.250.26.190    rBEBI_6iN1m4OQAAHm9YAA--    -    30508665    {"CONTENT_TYPE":"application\/x-www-form-urlencoded; charset=UTF-8","USER_AGENT":"aWeidao\/8.0.1 (OPPO R9s; Android 6.0.1)","ACCEPT_ENCODING":"gzip","USER_LOCATION":"31.19778,121.44475","AUTHORIZATION":"Bearer mivOxBeMrM4jkjgITT1A-j8ZTYQamgPo17FAuEOzE2c","SIGN":"2996707276BFBD2B517DD27EA1F392DE","HOST":"ycagent.yongche.com","CONNECTION":"Keep-Alive"}    GET    200    /user?nonce=088fc4379c3600844c9eb25254fe5b27    32.430171966553    1    {"nonce":"088fc4379c3600844c9eb25254fe5b27"}    1    {"ret_code":200,"ret_msg":"","result":{"user_id":30508665,"cellphone":"18621917857","name":"","create_time":1462942626,"status":1,"total_km":1022,"total_hours":40.1,"is_activated":true,"active_time":1464060849,"amount":"683.26","head_image":"","head_image_thumb":"","invoiceable_amount":"1300.00","gender":"F","bind_card_status":0,"bind_card_count":0,"card_num_suffix":"","coupon_amount":"0","coupon_cnt":"0","has_corporate":0,"favor_number":0,"favor":{"slow":0,"air_condition":0,"fm":0,"emergency_light":0,"aromatherapy":0,"chat":0,"front_seat":0,"no_call":0},"collect_driver_count":2,"scan_times":0,"finish_order_count":125,"memberInfo":{"level_id":1,"level_flag":517,"growth_points":120,"level_name":"\u94f6\u5361","level_en_name":"SILVER CARD","next_level_id":2,"next_level_name":"\u91d1\u5361","level_bg_image":"https:\/\/i1.yongche.name\/media\/g1\/M04\/06\/37\/rBEAQ1Zmq7-IYejRAAAxM7rqgnQAACvJAP010MAADFL159.jpg","growth_points_desc":"","level_flags":"4,1"},"birth_date":"0","industryInfo":{"industry_id":0,"industry_name":""},"company":"","profession":"","tags":[],"combo_cnt":"\u5feb\u6765\u5c1d\u9c9c","corporate_list":[],"i_id":1,"i_id_2":"1","i_id_3":"","l_id":100,"l_id_2":"100","l_id_3":"","f_id:":2.12,"f_id_2":"2.1231","f_id_3":""}}"""
//    println(splitter.split(toSplit).iterator.next)

    println("xxxxxxxx")
    splitter.split(toSplit).forEach(new Consumer[String] {
      override def accept(t: String): Unit = println(t)
    })

    val map = Map("a"->1,"b"->2)
    //println(map.getOrElse("c","10"))
  }
}
