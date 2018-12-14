package com.lin.spark.streaming

import java.time.format.DateTimeFormatter.ofPattern
import java.time.{LocalDate, ZoneId}

/**
  * Created by wenxuelin on 2016/12/15.
  */
object Test {
  def main(args: Array[String]): Unit = {
    val runTimeStr = "2018080706"
    val yesterday = LocalDate.parse(runTimeStr, ofPattern("yyyyMMddHH")).minusDays(1).format(ofPattern("yyyyMMdd"))
    val today = LocalDate.parse(runTimeStr, ofPattern("yyyyMMddHH")).plusDays(1).format(ofPattern("yyyyMMdd"))
    //val d14AgoSecond = LocalDate.parse(runTimeStr, ofPattern("yyyyMMdd")).minusDays(14).atStartOfDay(ZoneId.systemDefault()).toEpochSecond()


    println(yesterday +"===="+today)
  }

}
