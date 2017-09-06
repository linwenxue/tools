package com.lin.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by linwenxue on 2015/7/28.
 */
public class NetTool {
    InetAddress myIPaddress=null;
    InetAddress myServer=null;

    public static void main( String[] args){
        NetTool mytool;
        mytool=new NetTool();
        String url;
        if(args.length>0) {
            url=args[0];
        } else {
            url="lh4.ggpht.com";//测试域名
        }
        System.out.println("Your host IP is: " + mytool.getMyIP());
        System.out.println("The Server IP is :" +mytool.getServerIP(url));

    }

    /**
     * 取得LOCALHOST的IP地址
     * @return InetAddress
     */
    public InetAddress getMyIP() {
        try { myIPaddress=InetAddress.getLocalHost();}
        catch (UnknownHostException e) {}
        return (myIPaddress);
    }
    /**
     * 取得域名对应的IP地址
     * @return InetAddress
     */
    public InetAddress getServerIP(String url){
        try {myServer=InetAddress.getByName(url);}
        catch (UnknownHostException e) {}
        return (myServer);
    }
}
