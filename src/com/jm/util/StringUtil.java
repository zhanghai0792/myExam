package com.jm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;

public class StringUtil {
	public static String datePattern="yyyy-MM-dd kk:mm:ss";
	public static SimpleDateFormat dateFormate=new SimpleDateFormat(datePattern);
	//如果字符串为null或""返回true
 public static boolean isEmpty(String name){
	  if(name==null||"".equals(name))
		  return true;
	  return false;
 }
 
 public static boolean isNotEmpty(String name){
	 return isEmpty(name)!=true;
 }
 public static Date StringToDate(String date) throws Exception{
	 if(isNotEmpty(date)){
		date=date.replaceAll("/", "-").replaceAll(".", "-").replaceAll("\\","-");
		synchronized (dateFormate) {
		 return	dateFormate.parse(date);
		}
	 }
	 return null;
 }
 
 public static String DateToString(Date date){
	 if(date!=null){
		 synchronized (dateFormate) {
			return dateFormate.format(date);
		}
	 }
	 return null;
 }
 
 public static String getPY(String name){
	  if(isNotEmpty(name)){
		  String convert = "";
	        for (int j = 0; j < name.length(); j++) {
	            char word = name.charAt(j);
	            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
	            if (pinyinArray != null) {
	                convert += pinyinArray[0].charAt(0);
	            } else {
	                convert += word;
	            }
	        }
	        return convert.toUpperCase();
	  }
	  return null;
 }

 

 
}
