package com.common.lib.demo.network;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * httpclient 工具类
 *
 * @author wx
 */
public class HttpClientUtil {
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(500).setMaxConnPerRoute(500).build();
    private static final CloseableHttpClient HTTP_CLIENT;
    /**
     * 默认超时时间3s
     */
    private static final int DEFAULT_TIMEOUT = 3000;

    static {
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).setSoTimeout(DEFAULT_TIMEOUT).build();
        HTTP_CLIENT = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(20)
                .setDefaultSocketConfig(socketConfig).setSSLSocketFactory(createSSLConnSocketFactory()).build();
    }

    /**
     * @param url url
     * @return String 类型返回内容
     * @throws Exception Exception
     */
    public static String get(String url) throws Exception {
        return get(url, null, getResponseHandler());
    }

    /**
     * @param url url
     * @return String 类型返回内容
     * @throws Exception Exception
     */
    public static String get(String url, Map<String, String> param) throws Exception {
        return get(url, param, getResponseHandler());
    }

    /**
     * @param url url
     * @param url 参数
     * @return String 类型返回内容
     * @throws Exception Exception
     */
    public static <T> T get(String url, ResponseHandler<? extends T> responseHandler) throws Exception {
        return get(url, null, responseHandler);
    }

    /**
     * @param url   url
     * @param param url 参数
     * @return String 类型返回内容
     * @throws Exception Exception
     */
    public static <T> T get(String url, Map<String, String> param, ResponseHandler<? extends T> responseHandler) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        if (null != param && param.size() > 0) {
            param.forEach(builder::addParameter);
        }
        HttpGet httpGet = new HttpGet(builder.build());
        return execute(httpGet, responseHandler);
    }


    /**
     * 带json字符串post
     *
     * @param url         url
     * @param requestBody 请求body为json字符串
     * @return response
     * @throws Exception Exception
     */
    public static String post(String url, String requestBody, String encode) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (Strings.isNotBlank(requestBody)) {
            StringEntity stringEntity;
            if (Strings.isNotBlank(encode)) {
                stringEntity = new StringEntity(requestBody, encode);
            } else {
                stringEntity = new StringEntity(requestBody);
            }
            httpPost.setEntity(stringEntity);
        }
        return execute(httpPost, getResponseHandler());
    }

    public static String post(String url, String requestBody) throws Exception {
        return post(url, requestBody, "UTF-8");
    }

    /**
     * 自定义post请求 发送post
     *
     * @param httpPost
     * @return
     * @throws Exception
     */
    public static String post(HttpPost httpPost) throws Exception {
        return execute(httpPost, getResponseHandler());
    }

    /**
     * 带表单参数post
     *
     * @param url               url
     * @param nameValuePairList 表单参数
     * @param responseHandler   responseHandler
     * @param <T>               responseHandler 参数类型
     * @return T
     * @throws Exception Exception
     */
    public static <T> T post(String url, List<NameValuePair> nameValuePairList, ResponseHandler<? extends T> responseHandler) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (nameValuePairList != null) {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "UTF-8"));
        }
        return execute(httpPost, responseHandler);
    }

    public static <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws Exception {
        return execute(request, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, responseHandler);
    }

    public static <T> T execute(HttpUriRequest request, int connTimeOut, int waitTimeOut, ResponseHandler<? extends T> responseHandler) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connTimeOut).setConnectTimeout(connTimeOut).setSocketTimeout(waitTimeOut).build();
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setRequestConfig(requestConfig);
        /**header 自定义区**/
        request.setHeader("user-agent", UserAgentUtils.getRandomUserAgent());
        request.setHeader("Origin","https://www.jianshu.com");
        request.setHeader("Referer","https://www.jianshu.com/p/07557ca424b0");
        /**header 自定义区**/
        return HTTP_CLIENT.execute(request, responseHandler, localContext);
    }

    public static ResponseHandler<String> getResponseHandler() {
        return (HttpResponse response) -> {
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

    public static ResponseHandler<byte[]> getByteArrayResponseHandler() {
        return (HttpResponse response) -> {
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toByteArray(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

    /**
     * 信任所有HTTPS链接
     *
     * @return SSL
     */
    public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (a, b) -> true).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (GeneralSecurityException e) {
        }
        return sslsf;
    }

    public static String doPost(String url, List<Header> headerlist, Map<String, String> params, Map<String, String> cookieMap) {
        if (Strings.isEmpty(url)) {
            return null;
        } else {
            HttpPost httpPost = null;
            CookieStore cookieStore = new BasicCookieStore();
            CloseableHttpClient httpCookieClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            try {
                httpPost = new HttpPost(url);
                httpPost.setConfig(RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(1000).build());
                if (null != headerlist) {
                    Iterator var4 = headerlist.iterator();

                    while (var4.hasNext()) {
                        Header header = (Header) var4.next();
                        httpPost.addHeader(header);
                    }
                }
                //添加cookie
                if (cookieMap != null && cookieMap.size() > 0) {
                    while (cookieMap.entrySet().iterator().hasNext()) {
                        Map.Entry<String, String> entry = cookieMap.entrySet().iterator().next();
                        String value = entry.getValue();
                        BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), value);
                        cookie.setVersion(0);
                        cookieStore.addCookie(cookie);
                    }
                }

                List<NameValuePair> pairs = null;
                if (params != null && !params.isEmpty()) {
                    pairs = new ArrayList(params.size());
                    Iterator var16 = params.entrySet().iterator();

                    while (var16.hasNext()) {
                        Map.Entry<String, String> entry = (Map.Entry) var16.next();
                        String value = (String) entry.getValue();
                        if (value != null) {
                            pairs.add(new BasicNameValuePair((String) entry.getKey(), value));
                        }
                    }
                }

                if (pairs != null && pairs.size() > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
                }

                CloseableHttpResponse response = httpCookieClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                String result = null;
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                } else {
                    //logger.warn(String.format("Can not get Http Response! url=%s, code=%s", url, statusCode));
                }

                EntityUtils.consume(entity);
                response.close();
                String var9 = result;
                return var9;
            } catch (Exception var13) {
                //logger.error("doPost, url = " + url, var13);
            } finally {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }

            }

            return null;
        }
    }
}