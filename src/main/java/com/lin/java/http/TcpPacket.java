package com.lin.study.java.http;


import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linwenxue on 2015/3/6.
 */
public class TcpPacket {
    public final static ArrayList<String[]> firstLinkPackageList = new ArrayList<String[]>();//存储TCP三次握手建立连接的第一个数据包
    public final static ArrayList<String[]> secondLinkPackageList = new ArrayList<String[]>();//存储TCP三次握手建立连接的第二个数据包
    public final static Map<String,List<String[]>> resultMap = new HashMap<String, List<String[]>>();//按源IP+源端口号为key,数据包List为Value的Map存储TCP普通数据包

    public static void main(String[] args) {
        TcpPacket tcpPacket = new TcpPacket();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始数据包重组：");
            long sum = tcpPacket.readFileByLines("E://tcp.txt");
            System.out.println("共读取：" + sum + "个数据包");
            long success_sum = tcpPacket.fileWriter("E://5.txt", tcpPacket.analysePackage());
            System.out.println("完成数据包重组：重组成功："+success_sum+"条记录，使用"+(System.currentTimeMillis()-startTime)/1000.0+"s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * readFileByLines:读取文件方法
     * @param fileName
     * @throws IOException
     */
    public long readFileByLines(String fileName) throws IOException{
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        long sum = 0;

        while ((tempString = reader.readLine()) != null){
            separatePackage(tempString.split(","));
            sum++;
        }

        reader.close();
        return sum;
    }

    /**
     * fileWriter：将重组结果写入文件
     * @param fileName
     * @param map
     * @throws IOException
     */
    public long fileWriter(String fileName, HashMap<String, String[]> map) throws IOException{
        FileWriter fw = new FileWriter(fileName);

        for(Map.Entry entry: map.entrySet()) {
            String[] tmpArray = (String[])entry.getValue();
            fw.write("数据key:"+entry.getKey().toString()+"数据重组："+tmpArray[0]+"，"+tmpArray[1]+"，"+tmpArray[2]+"，"+tmpArray[3]+"，"+tmpArray[4]);
            fw.write("\n");
        }

        fw.flush();
        fw.close();
        return map.size();
    }

    /**
     * separatePackage:分类存储数据包
     * @param packageinfo
     * @throws IllegalArgumentException
     */
    public void separatePackage(String[] packageinfo) throws IllegalArgumentException{
        if(packageinfo == null) throw new IllegalArgumentException("paramter is null");

        if(packageinfo[7] != null) {
            switch (Integer.parseInt(packageinfo[8])) {
                case 2 : firstLinkPackageList.add(packageinfo); break;
                case 18 : secondLinkPackageList.add(packageinfo); break;
                default: sortPackage(packageinfo);
            }
        }
    }

    /**
     * sortPackage:按照不同的“src_ip + src_port”为key存储为不同的List
     * @param packageinfo
     */
    public void sortPackage(String[] packageinfo) {
        if(!(packageinfo[2].isEmpty() && !packageinfo[4].isEmpty())) {
            if(resultMap == null || resultMap.size() == 0 || resultMap.get(packageinfo[2] + packageinfo[4]) == null) {
                List<String[]> tmpList = new ArrayList<String[]>();
                tmpList.add(packageinfo);
                resultMap.put(packageinfo[2] + packageinfo[4], tmpList);
            } else {
                resultMap.get(packageinfo[2] + packageinfo[4]).add(packageinfo);
            }
        }
    }

    /**
     * analysePackage:进行数据包分析重组
     * @return
     */
    public HashMap<String, String[]> analysePackage() {
        //用户判断重组成功的标志
        boolean flag = false;
        HashMap<String, String[]> sortMap = new HashMap<String, String[]>();
        //首先确定三次握手简历连接的包的重组顺序
        if(firstLinkPackageList != null && firstLinkPackageList.size() > 0
                && secondLinkPackageList != null && secondLinkPackageList.size() > 0) {
            for(String[] tmp : firstLinkPackageList) {//TCP三次握手建立连接的第一个数据包
                if(!tmp[6].isEmpty()) {
                    for(String[] tmp2 : secondLinkPackageList) {
                        //查找TCP三次握手建立连接的第二个数据包且包的ack等于第一个包的seq+1,isTag局部判断条件标志
                        boolean isTag = (new BigInteger(tmp2[7]).compareTo(new BigInteger(tmp[6]).add(new BigInteger("1"))) == 0)
                                    && tmp2[2].equals(tmp[3]) && tmp2[4].equals(tmp[5]);
                        if(isTag) {
                            /*
                            查找TCP三次握手建立连接的第3个数据包和客户端第一个请求包，
                            这个两包的源IP、目的IP、源端口、目的端口、seq、ack均相同，标志位不同
                            （TCP三次握手的第三个数据包标志位“16”，第一个请求包的标志位为“24”）
                            查找resultMap是否存在第3、客户端第一个请求包的List
                             */
                            List<String[]> tmpList =  resultMap.get(tmp2[3] + tmp2[5]);
                            String[] tmpArray = new String[2];
                            if(tmpList != null && tmpList.size() > 0) {
                                for(String[] tmp3 : tmpList) {
                                    isTag = new BigInteger(tmp3[6]).compareTo(new BigInteger(tmp2[7])) == 0
                                            && new BigInteger(tmp3[7]).compareTo(new BigInteger(tmp2[6]).add(new BigInteger("1"))) == 0;

                                    if(isTag) {
                                        if(tmpArray[0] == null && "16".equals(tmp3[8]))
                                            tmpArray[0] = tmp3[0];
                                        else
                                            tmpArray[1] = tmp3[0];
                                    }

                                    if(tmpArray != null && tmpArray[0] != null && tmpArray[1] != null) {
                                        //查找5个数据包，即服务器响应的第一个数据包
                                        List<String[]> tmpList2 =  resultMap.get(tmp3[3] + tmp3[5]);
                                        if(tmpList2 != null && tmpList2.size() > 0) {
                                            for(String[] tmp4 : tmpList2) {
                                                isTag =  tmp4[6].equals(tmp3[7]) && new BigInteger(tmp4[7]).compareTo(new BigInteger(tmp3[6]).add(new BigInteger(tmp3[1]))) == 0;
                                                if(isTag) {
                                                    sortMap.put(tmp[2]+":"+tmp[4], new String[]{tmp[0],tmp2[0],tmpArray[0],tmpArray[1], tmp4[0]});
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        return sortMap;
    }
}

