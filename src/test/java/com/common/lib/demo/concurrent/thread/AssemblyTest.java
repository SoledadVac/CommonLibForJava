package com.common.lib.demo.concurrent.thread;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import com.sun.scenario.effect.ImageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import netscape.javascript.JSObject;
import org.junit.Test;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.print.PageFormat;
import java.io.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/12/16
 * \* Time: 11:41 上午
 * \* Description:
 * \
 */
public class AssemblyTest {

    String filePath = "/Users/liuhuichao/Downloads/WechatIMG83.jpg";

    @Test
    public void test() throws Exception {
        getImagePixel("/Users/liuhuichao/Downloads/WechatIMG83.jpg");
    }


    private BufferedImage changeColorSpace(BufferedImage targetImage) throws IOException {

        //Iss空间管理配置文件 这个文件我是放在项目resouce里面的 后面会贴上
        final ICC_Profile ip = ICC_Profile.getInstance("ISOcoated_v2_300_eci.icc");

        //转化色值空间
        final ColorConvertOp cco = new ColorConvertOp(targetImage.getColorModel().getColorSpace(), new ICC_ColorSpace(ip), null);
        BufferedImage cmykImage = cco.filter(targetImage, null);
        ColorModel c = cmykImage.getColorModel();
        int ty = cmykImage.getColorModel().getColorSpace().getType();//为了查看这个图片是不是CMYK的
        return cmykImage;
    }


    public void getImagePixel(String image) throws Exception {
        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //很重要的方法---将图片的RGB色域空间转化为CMYK色域空间
        BufferedImage cmykImg = changeColorSpace(bi);
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
       /* System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");*/
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");
                float[] abc = {rgb[0], rgb[1], rgb[2]};//获取RGB色域空间数组
                bi.setRGB(123, 123, 123);

                //将此像素RGB色值转化为cmyk色值
                float[] cmykRes = cmykImg.getColorModel().getColorSpace().fromRGB(abc);
                //把cmyk色值添加到色域空间
                cmykImg.getColorModel().getColorSpace().toRGB(cmykRes);
                //System.out.println("結果:" + cmykRes[0] + "," + cmykRes[1] + "," + cmykRes[2] + "," + cmykRes[3]);
            }
        }
        //生成这个cmyk tiff 文件
        JAI.create("filestore", cmykImg, "/Users/liuhuichao/Downloads/CMYK_IMAGE1.TIF", "TIFF");
    }


    @Test
    public void test1() throws IOException, DocumentException {
        RandomAccessFileOrArray myTiffFile = new RandomAccessFileOrArray("/Users/liuhuichao/Downloads/CMYK_IMAGE1.TIF");
        int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);
        Document tifftoPDF = new Document();
        PdfWriter.getInstance(tifftoPDF, new FileOutputStream("/Users/liuhuichao/Downloads/tiff2Pdf.pdf"));
        tifftoPDF.open();
        for (int i = 1; i <= numberOfPages; i++) {
            Image tempImage = TiffImage.getTiffImage(myTiffFile, i);
          /*  Rectangle pageSize = new Rectangle(tempImage.getWidth(),
                    tempImage.getHeight());
            tifftoPDF.setPageSize(pageSize);*/
            PdfImage pdfImage = new PdfImage(tempImage, "aa", null);
            tifftoPDF.add(tempImage);
        }
        tifftoPDF.close();
        //PdfImage
    }


    @Test
    public void test2() throws Exception {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<Future<String>> futureQueue = new ConcurrentLinkedQueue<>();
        List<String> idList = Lists.newArrayList("a", "b", "c");
        ExecutorService rateService = new ThreadPoolExecutor(4, 8, 240, TimeUnit.SECONDS, new LinkedBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());
        long start = System.currentTimeMillis();
        for (String id : idList) {
            GetThreadReturnResult t = new GetThreadReturnResult(id);
            Future<String> stringFuture = rateService.submit(t);
            futureQueue.offer(stringFuture);
        }
        while (!futureQueue.isEmpty()) {
            if (futureQueue.peek().isDone() && !futureQueue.peek().isCancelled()) {
                queue.offer(futureQueue.poll().get());
            }
        }
        System.out.println(JSONObject.toJSONString(queue));
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class GetThreadReturnResult implements Callable<String>, Serializable {
        private static final long serialVersionUID = 2277437296426921203L;
        private String id;

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            return id;
        }
    }


    @Test
    public void test3() throws Exception {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        List<String> idList = Lists.newArrayList("a", "b", "c");
        CountDownLatch countDownLatch = new CountDownLatch(idList.size());
        long start = System.currentTimeMillis();
        for (String id : idList) {
            ListenableFuture<String> stringFuture = executorService.submit(new GetThreadReturnResult(id));
            Futures.addCallback(stringFuture, new FutureCallback<String>() {
                @Override
                public void onSuccess(@Nullable String result) {
                    queue.offer(result);
                    countDownLatch.countDown();
                }
                @Override
                public void onFailure(Throwable t) {
                    countDownLatch.countDown();
                    System.out.println(JSONObject.toJSONString(t));
                }
            }, executorService);
        }
        countDownLatch.await();
        System.out.println(JSONObject.toJSONString(queue));
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }





}
