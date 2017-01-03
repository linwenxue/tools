package com.lin.study.java.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
	private DateTimeUtil() {
	}

	public static final String FORMAT_PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_PATTERN_DATE_ONLY = "yyyy-MM-dd";
	public static final String FORMAT_PATTERN_TIME_ONLY = "HH:mm:ss";
	public static final String FORMAT_PATTERN_DAY_IN_WEEK = "E";

	public static Date now() {
		return new Date();
		// DateTime.now().toDate();
	}
	public static int getMonthDay(String source) {
		// String source = "2007年12月";
		source = source.substring(0, 4);
		int count = 30;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		try {
			Date date = format.parse(source);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public static String nowString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(new Date());
	}

	public static String DateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(date);
	}

	public static String nowString(String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);// 设置日期格式
		return df.format(new Date());
	}

	public static String getYesterDay(String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);// 设置日期格式
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		return df.format(date);
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static int dateCompare(String DATE1, String DATE2, String formatStr) {

		DateFormat df = new SimpleDateFormat(formatStr);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	/**
	 * 判断日期是否相等  默认格式为 yyyyMMdd
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static boolean dateCompare(String DATE1, String DATE2){
		return dateCompare(DATE1,DATE2,"yyyyMMdd")>=0? true:false;
	}
	
	public static long dateSub(String beginDateStr, String endDateStr, String formatStr, String type) {

		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date beginDate;
		Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			if (type.equals("Y")) {
				day = beginDate.getYear() - endDate.getYear();
			} else if (type.equals("M")) {
				day = (beginDate.getYear() - endDate.getYear()) * 12 + (endDate.getMonth() - beginDate.getMonth());
			} else if (type.equals("D")) {
				day = (beginDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
			} else if (type.equals("H")) {
				day = (beginDate.getTime() - endDate.getTime()) / (60 * 60 * 1000);
			} else if (type.equals("MI")) {
				day = (beginDate.getTime() - endDate.getTime()) / (60 * 1000);
			} else if (type.equals("S")) {
				day = (beginDate.getTime() - endDate.getTime()) / (1000);
			}
			// System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return day;
	}

	public static void main(String arg[]){

		System.out.println(DateTimeUtil.dateCompare("2015-08-13 10:00:00", "2015-08-13 10:00:00", DateTimeUtil.FORMAT_PATTERN_DATE_TIME));
	}
}
