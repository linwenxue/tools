package com.lin.hadoop.learn;

import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.impl.YarnClientImpl;
import org.apache.hadoop.yarn.exceptions.YarnException;

import java.io.IOException;
import java.util.List;

/**
 * Created by elevenlin@lagou.com on 2016/5/17.
 */
public class HadoopYarnLearning {
    private YarnClient yarnClient;

    public HadoopYarnLearning() {
        yarnClient = new YarnClientImpl();
    }

    public void queryMRApplictions() throws YarnException, IOException{
        List<ApplicationReport> applicationReports = yarnClient.getApplications();
        System.out.println("start..........");
        for(ApplicationReport ar : applicationReports) {
            String appId = Integer.toString(ar.getApplicationId().getId());
            String name = ar.getName();
            String user = ar.getUser();
            String appType = ar.getApplicationType();
            String queue = ar.getQueue();
            String originalTrackingUrl = ar.getOriginalTrackingUrl();
            String trackingUrl = ar.getTrackingUrl();
            System.out.print("appId:"+appId+"\tname:"+name+"\tuser:"+user
                +"\tappType:"+appType+"\tqueue:"+queue+"\toriginalTrackingUrl:"
                +originalTrackingUrl+"\ttrackingUrl:"+trackingUrl);
        }
    }

    public void main(String[] args) {
        try {
            new HadoopYarnLearning().queryMRApplictions();
        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
