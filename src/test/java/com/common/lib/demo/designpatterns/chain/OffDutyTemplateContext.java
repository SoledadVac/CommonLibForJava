package com.common.lib.demo.designpatterns.chain;

import lombok.Getter;
import lombok.Setter;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/11
 * \* Time: 上午11:49
 * \* Description: data context in off duty processer
 * \
 */
@Getter
@Setter
public class OffDutyTemplateContext implements ITemplateContext {
    private Long id;
    private String name;
    //and so on....
}
