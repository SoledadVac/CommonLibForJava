package com.common.lib.demo.javasource.spi;

import javax.annotation.concurrent.ThreadSafe;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * load spi impl class
 */
@ThreadSafe
abstract public class InstalledServices {

    private InstalledServices() {
    }

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Map<Class<?>, List<?>> buffer = new IdentityHashMap<>();

    public static <T> T installedFirstOrElseMock(Class<T> service) {
        return installedList(service).stream()
                .findFirst()
                .orElse(newInstance(service));
    }

    public static <T> T installedFirst(Class<T> service) {
        return installedList(service).stream().findFirst().orElse(null);
    }


    public static <T> List<T> installedList(Class<T> service) {
        Objects.requireNonNull(service);
        if (!service.isInterface()) {
            throw new IllegalArgumentException("Interface is required");
        }
        List<T> buffered;
        lock.readLock().lock();
        try {
            buffered = (List<T>) buffer.get(service);
        } finally {
            lock.readLock().unlock();
        }
        if (buffered != null) {
            return buffered;
        }

        lock.writeLock().lock();
        try {
            buffered = (List<T>) buffer.get(service);
            if (buffered != null) {
                return buffered;
            }
            List<T> list = new LinkedList<>();
            ServiceLoader.load(service, Thread.currentThread().getContextClassLoader()).forEach(list::add);
            List<?> result = list.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingInt(InstalledServices::getOrder))
                    .collect(Collectors.toList());
            result = Collections.unmodifiableList(result);
            buffer.put(service, result);
            return (List<T>) result;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private static <T> T newInstance(Class<T> interfaceClass) {
        Objects.requireNonNull(interfaceClass);
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException();
        }
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{interfaceClass},
                (proxy, method, args) -> {
                    throw new UnsupportedOperationException();
                });
    }

    private static int getOrder(Object obj) {
        if (obj instanceof org.springframework.core.Ordered) {
            return ((org.springframework.core.Ordered) obj).getOrder();
        }
        return 0;
    }

}
