package com.common.lib.demo.test.yiqixue.outputdata;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/11
 * \* Time: 4:47 下午
 * \* Description:
 * \
 */
@Getter
@Setter
public class CheckPointLabel implements Serializable {
    private static final long serialVersionUID = -681071872283170625L;
    private String _id;                          // 模板id
    private Date createAt;                      // 创建时间
    private Date updateAt;                      // 修改时间
    private String labelName;                   // 标签名称
    private String labelNote;
    private String labelNameColor;              // 标签名称颜色
    private String labelBackGround;             //标签背景色
    private Boolean disabled = false;           // 默认false，删除true
}
