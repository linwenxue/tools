package com.lin.java.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by wenxuelin on 2017/2/14.
 */
public class FtpClient {
    public static void main(String[] args) {
        String url = "106.38.102.71";
        String user = "orderlist";
        String pwd = "orderlist";
        FTPClient ftp = new FTPClient();
        //ftp.setControlEncoding("GBK");
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        //conf.setServerLanguageCode("zh");
        ftp.configure(conf);
        try {
            ftp.connect(url);
            if (ftp.login(user, pwd)) {
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftp.disconnect();
                    System.out.println("disconnect");
                } else {
                    ftp.enterLocalPassiveMode();
                    ftp.setFileType(FTP.BINARY_FILE_TYPE);

                    File dir = new File("down");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String[] names = ftp.listNames();
                    for (String name : names) {
                        File file = new File(dir.getPath() + File.separator + name);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        long pos = file.length();
                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        raf.seek(pos);
                        ftp.setRestartOffset(pos);

                        InputStream is = ftp.retrieveFileStream(name);
                        if (is == null) {
                            System.out.println("no such file:" + name);
                        } else {
                            System.out.println("start getting file:" + name);

                            int b;
                            while ((b = is.read()) != -1) {
                                raf.write(b);
                            }
                            if (ftp.getReply() == FTPReply.CLOSING_DATA_CONNECTION) {
                                System.out.println("done!");
                            }
                            is.close();
                        }
                        raf.close();
                    }
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
