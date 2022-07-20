/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2011-2016 Shanghai Sunny Education, Inc. All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * Shanghai Sunny Education, Inc. and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Shanghai Sunny
 * Education, Inc. and its suppliers and may be covered by patents, patents
 * in process, and are protected by trade secret or copyright law. Dissemination
 * of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Shanghai Sunny Education, Inc.
 */

package com.common.lib.demo.http.client.execute;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ALPS http response data structure. A wrapper of original
 * {@code org.apache.http.client.methods.CloseableHttpResponse}.
 *
 * @author Xiaohai Zhang
 * @version 20151208
 * @since Dec 8, 2015
 */
public class AlpsHttpResponse {

  private final HttpUriRequest httpUriRequest;

  public AlpsHttpResponse(HttpUriRequest httpUriRequest) {
    this.httpUriRequest = Objects.requireNonNull(httpUriRequest);
  }

  private StatusLine statusLine = null;           // response status line
  private Header[] headers = null;                // response all headers
  /**
   * 服务器端返回的contentType，可能为null
   */
  private ContentType contentType = null;         // response content type
  /**
   * 服务器端返回的原始byte数组
   */
  private byte[] content = null;                  // response content byte array
  /**
   * 这个字段用于记录服务器端返回的charset，response中的contentType
   * 也可能为null，如果为null则表示服务器端没有返回contentType或者
   * 返回的contentType中没有带charset
   */
  private Charset responseCharset = null;

  private Exception httpClientException = null;   // set this if http client exception occurs

  public Exception getHttpClientException() {
    return httpClientException;
  }

  public void setHttpClientException(Exception httpClientException) {
    this.httpClientException = httpClientException;
  }

  public void initialize(HttpResponse response) {
    if (response == null) {
      // Should not happen
      return;
    }
    statusLine = response.getStatusLine();
    headers = response.getAllHeaders();
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      contentType = ContentType.get(entity);
      if (contentType != null) {
        responseCharset = contentType.getCharset();
      }
    }

    if (entity != null) {
      try {
        content = EntityUtils.toByteArray(entity);
      } catch (IOException ex) {
        content = null;
      }
    }
  }

  public boolean hasHttpClientException() {
    return httpClientException != null;
  }

  public String getHttpClientExceptionMessage() {
    return httpClientException == null ? null : httpClientException.getMessage();
  }

  public List<Header> getHeaders() {
    if (httpClientException != null) {
      return Collections.emptyList();
    }
    return headers == null ? Collections.emptyList() : Arrays.asList(headers);
  }

  public List<Header> getHeaders(String name) {
    return getHeaders().stream()
        .filter(t -> StringUtils.equals(t.getName(), name))
        .collect(Collectors.toList());
  }

  public Header getFirstHeader(String name) {
    return getHeaders(name).stream().findFirst().orElse(null);
  }

  public Header getLastHeader(String name) {
    List<Header> list = getHeaders(name).stream().collect(Collectors.toCollection(LinkedList::new));
    Collections.reverse(list);
    return list.stream().findFirst().orElse(null);
  }

  /**
   * Return -1 in case of no status code obtained.
   *
   * @return status code, see comments
   */
  public int getStatusCode() {
    if (httpClientException != null) {
      return -1;
    }
    return statusLine == null ? -1 : statusLine.getStatusCode();
  }

  /**
   * Return null if no valid response string.
   * 使用服务器端返回的charset，如果服务器端没有返回charset，则使用UTF-8
   *
   * @return response string with UTF-8 encoding
   * @see #getResponseString(Charset)
   */
  public String getResponseString() {
    return getResponseString(null);
  }

  /**
   * Return null if no valid response string. Force specify charset.
   *
   * @param charset the charset to be used, use UTF-8 in case of not specified
   * @return response string, return null if no response string or exception occurs
   */
  public String getResponseString(Charset charset) {
    if (httpClientException != null) {
      return null;
    }
    if (content == null) {
      return null;
    }
    if (charset == null) {
      // 没有指定用什么字符集编码，则优先尝试使用服务器端的返回值
      charset = responseCharset;
      if (charset == null) {
        // charset still null, it means no charset returned from server response, just try utf-8
        // 服务器端没有返回charset，也没有自己指定，那么使用UTF-8
        charset = Consts.UTF_8;
      }
    }

    try {
      return IOUtils.toString(content, charset.name());
    } catch (Exception ex) {
      return null;
    }
  }

  public byte[] getOriginalResponse() {
    return content;
  }

  public ContentType getContentType() {
    return contentType;
  }

  public Charset getResponseCharset() {
    return responseCharset;
  }


  @Override
  public String toString() {
    return "[statusCode=" + getStatusCode() + "," +
        "responseString=" + getResponseString() + "," +
        "responseContentType=" + getContentType() + "," +
        "exception=" + formatHttpClientException() + "]";
  }

  private String formatHttpClientException() {
    return httpClientException == null ? "" : httpClientException.getClass().getName() + ":" + httpClientException.getMessage();
  }
}
