package com.copyright.mall.util;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QRcodeUtil {
    /**
     * 生成二维码
     *
     * @param text   内容，可以是链接或者文本
     * @param path   生成的二维码位置
     * @param width  宽度，默认300
     * @param height 高度，默认300
     * @param format 生成的二维码格式，默认png
     */
    public static void encodeQRCode(String text, HttpServletResponse response, Integer width, Integer height) {
        try {

            // 得到文件对象
            // 宽
            if (width == null) {
                width = 300;
            }
            // 高
            if (height == null) {
                height = 300;
            }
            // 设置字符集编码
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 生成二维码矩阵
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            // 写入文件
            MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 对二维码图片进行解码
     *
     * @param filePath 二维码路径
     * @return 解码后对内容
     */
    public static JSONObject decodeQRCode(String filePath) {

        try {

            // 读取图片
            BufferedImage image = ImageIO.read(new File(filePath));

            // 多步解析
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            // 一步到位
            // BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)))

            // 设置字符集编码
            Map<DecodeHintType, String> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            // 对图像进行解码
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            // 解码内容
            JSONObject content = JSONObject.parseObject(result.getText());

            System.out.println("图片内容：  ");
            System.out.println("content： " + content.toJSONString());
            System.out.println("图片中格式：  ");
            System.out.println("encode： " + result.getBarcodeFormat());

            return content;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
