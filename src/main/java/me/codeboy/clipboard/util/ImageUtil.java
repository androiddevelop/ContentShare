package me.codeboy.clipboard.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;

/**
 * 图片保存
 * Created by yuedong.li on 2019-07-19
 */
public class ImageUtil {

    private static Random random = new Random();

    /**
     * base64字符串转换成图片
     *
     * @param imgStr base64字符串
     * @return 文件路径
     */
    public static String saveBase64ToFile(String imgStr) {

        if (imgStr == null) // 图像数据为空
            return null;

        File dir = new File("images");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String imageFilePath = System.currentTimeMillis() + "" + random.nextLong() + ".png";
        imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
        try {
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(new File(dir, imageFilePath));
            out.write(b);
            out.flush();
            out.close();
            return "/images/" + imageFilePath;
        } catch (Exception e) {
            return null;
        }
    }
}
