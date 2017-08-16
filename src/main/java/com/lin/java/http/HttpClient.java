package com.lin.java.http;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by wenxuelin on 2017/4/13.
 */
public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static void main(String[] args) {
        try {
            System.out.println(post("http://www.itstrike.cn/Question/4daf85aa-017c-4115-ae7d-b3b43ead9084.html", new HashMap<String, String>()));
            System.out.println("--------------------------------------------");
            System.out.println(get("https://www.baidu.com/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String url, Map<String, String> params) throws Exception{
        if(StringUtils.isBlank(url))
            throw new IllegalArgumentException("url参数不能为空");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<>();
        Iterator iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
            paramsList.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
        }
        try {
            if(paramsList.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, "utf-8");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("状态码："+statusCode);
                if(statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity, "UTF-8");
                    }
                } else{
                    logger.error("执行http查询错误，url:{},params:{},http状态码:{}", url, params.toString(), statusCode);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("执行http查询错误，url:{},params:{},错误信息:{}", url, params.toString(), e.getMessage());
            throw new Exception(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭http连接错误，错误信息:{}", e.getMessage());
                throw new IOException(e);
            }
        }
        return StringUtils.EMPTY;
    }


    public static String get(String url) throws Exception{
        if(StringUtils.isBlank(url))
            throw new IllegalArgumentException("url参数不能为空");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity, "UTF-8");
                    }
                } else{
                    logger.error("执行http查询错误，url:{},http状态码:{}", url, statusCode);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("执行http查询错误，url:{},错误信息:{}", url,e.getMessage());
            throw new Exception(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("关闭http连接错误，错误信息:{}", e.getMessage());
                throw new IOException(e);
            }
        }
        return StringUtils.EMPTY;
    }
}
