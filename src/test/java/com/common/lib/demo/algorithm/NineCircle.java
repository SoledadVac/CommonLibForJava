package com.common.lib.demo.algorithm;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;

public class NineCircle {

    private int step;
    private String[] circleArray; //观测操作步骤

    //九连环玩法：1，卸载下环N，需要环N和N-1都在杠杠上，且1-[N-2]都不在杠杠上；
    public void moveDn(int n) throws InterruptedException {    //装上
        step++;
        if (n > 2)
            moveDn(n - 2);
        circleArray[n - 1] = "-";
        System.out.println(JSONObject.toJSONString(circleArray));
        if (n > 2)
            moveUp(n - 2);
        if (n > 1)
            moveDn(n - 1);
        Thread.sleep(100);
    }

    public void moveUp(int n) throws InterruptedException {    //取下
        step++;
        if (n > 1)
            moveUp(n - 1);
        if (n > 2)
            moveDn(n - 2);
        circleArray[n - 1] = "O";
        System.out.println(JSONObject.toJSONString(circleArray));
        if (n > 2)
            moveUp(n - 2);
        Thread.sleep(100);
    }

    @Test
    public void test() throws InterruptedException {
        System.out.println("宝子们，请注意---》前方低能！！！！！！");
        Thread.sleep(4000);
        System.out.println(" 下面是一段灵力加持，为的是给代码开光~~~~~~~");
        System.out.println("                            _ooOoo_\n" +
                "                           o8888888o\n" +
                "                           88\" . \"88\n" +
                "                           (| -_- |)\n" +
                "                            O\\ = /O\n" +
                "                        ____/`---'\\____\n" +
                "                        .   ' \\\\| |// `.\n" +
                "                       / \\\\||| : |||// \\\n" +
                "                     / _||||| -:- |||||- \\\n" +
                "                       | | \\\\\\ - /// | |\n" +
                "                     | \\_| ''\\---/'' | |\n" +
                "                      \\ .-\\__ `-` ___/-. /\n" +
                "                   ___`. .' /--.--\\ `. . __\n" +
                "                .\"\" '< `.___\\_<|>_/___.' >'\"\".\n" +
                "               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /\n" +
                "         ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                            `=---='\n" +
                "\n" +
                "         .............................................\n" +
                "                  佛祖保佑  本宝宝代码永无bug");
        Thread.sleep(5000);
        System.out.println("好的，开光完成，下面开始吧~ \n");
        System.out.println("O 表示在杠杠上的环；   - 表示环被从杠杠上取下； ");
        Thread.sleep(4000);
        System.out.println(" 跟紧程序输出的魔鬼步伐~~~~");
        Thread.sleep(4000);
        circleArray = new String[9];
        Arrays.fill(circleArray,"O");
        moveDn(9);
        System.out.println("解开9所需步骤数目：" + step);
    }
}
