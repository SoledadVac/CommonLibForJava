package com.common.lib.demo.util.jmx;

import javax.annotation.concurrent.ThreadSafe;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static com.common.lib.demo.util.jmx.JmxType.DOMAIN;

@ThreadSafe
public class ObjectNameGenerator {

    private static final ReentrantLock locker = new ReentrantLock();

    private static final Map<Holder, AtomicInteger> counter = new HashMap<>();

    public static ObjectName generate(String type, String name) {
        Objects.requireNonNull(type, "JMX type must not be null");
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        locker.lock();
        try {
            Holder holder = new Holder(type, name.trim());
            if (!counter.containsKey(holder)) {
                counter.put(holder, new AtomicInteger(0));
            }
            AtomicInteger atomicInteger = counter.get(holder);
            int next = atomicInteger.getAndIncrement();
            String nameForUse = holder.name;
            if (next > 0) {
                nameForUse = nameForUse + "_" + next;
            }
            try {
                return new ObjectName(getJmxNameBase(type) + nameForUse);
            } catch (MalformedObjectNameException ex) {
                throw new RuntimeException("Should not reach here.", ex);
            }
        } finally {
            locker.unlock();
        }
    }

    private static String getJmxNameBase(String type) {
        return DOMAIN + ":type=" + type + ",name=";
    }


    static class Holder {

        @NotNull private final String type;
        @NotNull private final String name;

        public Holder(@NotNull String type, @NotNull String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Holder holder = (Holder) o;
            return type.equals(holder.type) &&
                    name.equals(holder.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, name);
        }
    }
}
