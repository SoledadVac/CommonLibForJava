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

package com.common.lib.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class IOUtils extends org.apache.commons.io.IOUtils {

  /**
   * Get request body from specified servlet request {@link InputStream} and without
   * destroying original input stream.
   *
   * @param inputStream original servlet request input stream.
   * @return the request body, return null means no request body available
   * @see PushbackInputStream
   */
  public static InputStream getRequestBody(InputStream inputStream) throws IOException {
    if (inputStream == null) {
      return null;
    }
    if (inputStream.markSupported()) {
      inputStream.mark(1);
      if (inputStream.read() == -1) {
        return null;
      }
      inputStream.reset();
      return inputStream;
    } else {
      PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
      int b = pushbackInputStream.read();
      if (b == -1) {
        return null;
      } else {
        pushbackInputStream.unread(b);
      }
      return pushbackInputStream;
    }
  }
}
