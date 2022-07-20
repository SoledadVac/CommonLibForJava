/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2006-2016 Shanghai Sunny Education, Inc. All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * Shanghai Sunny Education, Inc. and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Shanghai Sunny
 * Education, Inc. and its suppliers and may be covered by patents, patents
 * in process, and are protected by trade secret or copyright law. Dissemination
 * of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Shanghai Sunny Education, Inc.
 */

package com.common.lib.demo.concurrent;



import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code ReentrantLock} support/helper.
 *
 * @author Xiaohai Zhang
 * @see ReentrantLock
 * @since 201508.45
 */
public class ReentrantLocker {

  protected final ReentrantLock reentrantLock;

  public ReentrantLock getReentrantLock() {
    return reentrantLock;
  }

  public ReentrantLocker() {
    reentrantLock = newReentrantLock();
  }

  protected ReentrantLock newReentrantLock() {
    return new ReentrantLock();
  }

  public <T> T withinLock(LockCallback<T> callback) {
    Objects.requireNonNull(callback);
    reentrantLock.lock();
    try {
      return callback.callback();
    } finally {
      reentrantLock.unlock();
    }
  }

  public void withinLockWithoutResult(LockCallbackWithoutResult callback) {
    Objects.requireNonNull(callback);
    reentrantLock.lock();
    try {
      callback.callback();
    } finally {
      reentrantLock.unlock();
    }
  }

  /**
   * @deprecated will be removed in the future release, use {@link #withinLock(LockCallback)}.
   */
  @Deprecated
  public <T> T doInLock(LockCallback<T> callback) {
    Objects.requireNonNull(callback);
    return withinLock(callback::callback);
  }

  /**
   * @deprecated will be removed in the future release, use {@link #withinLockWithoutResult(LockCallbackWithoutResult)}
   */
  @Deprecated
  public void doInLockWithoutResult(LockCallbackWithoutResult callback) {
    Objects.requireNonNull(callback);
    withinLockWithoutResult(callback::callback);
  }
}
