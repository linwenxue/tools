package com.lin.java.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by elevenlin@lagou.com on 2016/5/17.
 */
public class ImageUtils {
    public static BufferedImage readURLImage(String in) {
        try {
            URL e = new URL(in);
            BufferedImage sourceImage = ImageIO.read(e.openStream());
            return sourceImage;
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(readURLImage("http://192.168.101.66:8080/tomcat.png"));
    }
}
