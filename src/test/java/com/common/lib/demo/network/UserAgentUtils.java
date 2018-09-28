package com.common.lib.demo.network;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/28
 * \* Time: 下午4:46
 * \* Description: 获取常用UA信息
 * \
 */
public  class UserAgentUtils {

    /**
     * 获取随机ip
     *
     * @return
     */
     public static  String reandomIpAddress() {
        return new Random().nextInt(254) + "." + new Random().nextInt(254) + "." + new Random().nextInt(254) + "." + new Random().nextInt(254);
    }

    /**
     * 随机获取UA
     *
     * @return
     */
    public static String getRandomUserAgent() {
        List<String> UaList = Arrays.asList("Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;Trident/5.0)",
                "Mozilla/4.0(compatible;MSIE8.0;WindowsNT6.0;Trident/4.0)",
                "Mozilla/4.0(compatible;MSIE7.0;WindowsNT6.0)",
                "Opera/9.80(WindowsNT6.1;U;en)Presto/2.8.131Version/11.11",
                "Mozilla/5.0(WindowsNT6.1;rv:2.0.1)Gecko/20100101Firefox/4.0.1",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.3.4000 Chrome/30.0.1599.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36"
        );
        return UaList.stream().findAny().orElse("Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;Trident/5.0)");
    }

}
