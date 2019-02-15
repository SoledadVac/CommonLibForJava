package com.common.lib.demo.dataprocess;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/8/22
 * \* Time: 下午10:26
 * \* Description: j8 stream常用操作
 * \
 */
public class StreamTest {


    /**
     * 常用的stream方法
     */
    @Test
    public void buildStreamTest() {
        //创建stream
        Stream<String> song = Stream.of("gently", "down", "the");//使用of方法创建
        Stream<String> emptyStream = Stream.empty();//创建空的stream
        Stream<String> echos = Stream.generate(() -> "echos");//创建一个含有常量的stream
        Stream<Double> randoms = Stream.generate(Math::random);//一个包含随机数的stream
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));//创建一个0，1，2，。。。无限序列
    }

    /**
     * 流转换
     */
    @Test
    public void transformStreamTest() {
        List<String> wordList = Arrays.asList("a", "APPLE", "mmmm");
        Stream<String> wordStream = wordList.stream();
        Stream<String> longWords = wordStream.filter(w -> w.length() > 1);
        Stream<String> lowercaseWords = wordStream.map(String::toLowerCase);

        Stream<Stream<Character>> result = wordList.stream().map(w -> characterStream(w));
        Stream<Character> resul = wordList.stream().flatMap(w -> characterStream(w));//使用flatMap展开结果

    }

    private Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }

    /**
     * choose stream
     */
    @Test
    public void chooseElementTest() {
        //skip
        Stream<String> wordStream = Stream.of("apple", "lhc", "test", "lallala");
        //Stream<String> skipStream = wordStream.skip(2);
        //System.out.println(skipStream.collect(Collectors.toList()));

        //limit
        //System.out.println(skipStream.limit(1).collect(Collectors.toList()));

        //concat
        Stream<String> wordStream2 = Stream.of("baobaobaobao");
        Stream<String> concatStream = Stream.concat(wordStream, wordStream2);
        //System.out.println(concatStream.collect(Collectors.toList()));

        //peek:真正访问每个元素时候会调用，可以用于调试时候使用
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("fetch" + e))
                .limit(20)
                .toArray();
        //System.out.println(JSONObject.toJSONString(powers));
    }

    /**
     * distinct stream
     */
    @Test
    public void distinctTest() {
        Stream<String> wordStream = Stream.of("aa", "cc", "aa", "bb", "bb", "cc", "cc").distinct();
        System.out.println(wordStream.collect(Collectors.toList()));
    }

    /**
     * count,min,max --stream
     */
    @Test
    public void aggragateTest() {
        Stream<String> wordStream = Stream.of("aa", "cc", "aa", "bb", "bb", "cc", "cc");
        //count
        // System.out.println(wordStream.count());

        //max
        Stream<Integer> intStream = Stream.of(1, 2, 3);
        //Optional<Integer> max=intStream.max(Integer::compareTo);
        //System.out.println(max.get());

        //min
        Optional<Integer> min = intStream.min(Integer::compareTo);
        System.out.println(min.get());
    }


    @Test
    public void allMatchTest() {
        List<User> users = Lists.newArrayList();
        users.add(new User("1", false));
        users.add(new User("2", false));
        users.add(new User("3", false));
        users.add(new User("4", false));
        users.add(new User("5", false));
        users.add(new User("6", false));
        boolean flag = users.stream().filter(User::isFlag).allMatch(User::isFlag);
        System.out.println("flag=" + flag);  //总返回true
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class User {
        private String name;
        private boolean flag;
    }

}
