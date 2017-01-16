package com.lin.study.spark.streaming

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
  }
}
