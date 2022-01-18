package com.common.lib.demo.concurrent.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/12/27
 * \* Time: 2:09 下午
 * \* Description:
 * \
 */
public class ForkTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        List<Integer> sourceData = Stream.generate(() -> 1).limit(10000).collect(Collectors.toList());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> forkJoinTask = forkJoinPool.submit(new FormateLetterTask(sourceData, 0, sourceData.size() - 1));
        int result = forkJoinTask.get();
        System.out.println("result=" + result);
        forkJoinPool.shutdown();
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class FormateLetterTask extends RecursiveTask<Integer> {
        List<Integer> sourceData;
        int begin;
        int end;

        @Override
        protected Integer compute() {
            if (end - begin <= 10) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += sourceData.get(i);
                }
                return sum;
            }
            int mid = (end + begin) / 2;
            FormateLetterTask left = new FormateLetterTask(sourceData, begin, mid);
            FormateLetterTask right = new FormateLetterTask(sourceData, mid + 1, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
