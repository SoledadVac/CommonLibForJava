package com.common.lib.demo.bitcoincopycat;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.jruby.RubyProcess;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/11/20
 * \* Time: 5:26 下午
 * \* Description:区块定义
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block implements Serializable {
    private static final long serialVersionUID = -7756067036594010159L;
    private Integer index;//区块索引号
    private String hash;//当前区块的hash值，区块唯一标识
    private Long timestamp;//生成区块的时间戳
    private List<Transaction> transactions;//区块上面记录的交易
    private Integer nonce;//工作量证明，计算正确哈希值的次数
    private String prevHash;//前一个区块的hash值





}
