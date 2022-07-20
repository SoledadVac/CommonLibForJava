package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/24
 * \* Time: 26:32 PM
 * \* Description:  找出第 k 小的距离对
 * \
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * <p>
 * 示例 1:
 * <p>
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 * <p>
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
public class A719_FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int tempDist = 0;
        int tempK = 0;


        return -1;
    }

    @Test
    public void test() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<uwword fileformat=\"1.1\" props=\"document-endnote-place-endsection:0;document-footnote-initial:1;document-footnote-restart-page:0;document-footnote-restart-section:0;document-endnote-restart-section:0;document-endnote-place-enddoc:1;document-footnote-type:numeric;document-endnote-initial:1;document-endnote-type:numeric\" template=\"false\" xid_max=\"222\">\n" +
                "    <metadata>\n" +
                "        <m key=\"dc.format\">application/x-uwword</m>\n" +
                "        <m key=\"uwword.generator\">uwWord</m>\n" +
                "    </metadata>\n" +
                "    <styles>\n" +
                "        <s followedby=\"Current Settings\" name=\"Normal\" props=\"margin-top:0pt;margin-left:0pt;bgcolor:transparent;widows:2;margin-bottom:0pt;ef:test;margin-right:0pt\" type=\"P\"/>\n" +
                "    </styles>\n" +
                "    <pagesize height=\"29.700000\" orientation=\"portrait\" page_scale=\"1.000000\" pagetype=\"A4\" units=\"cm\" width=\"21.000000\"/>\n" +
                "    <section footer=\"108\" header=\"109\" props=\"width:9.84in;page-margin-footer:1.00cm;page-margin-right:2.50cm;page-margin-top:4.81cm;page-margin-left:2.50cm;ef:element..基本信息;page-margin-bottom:0.9236in;page-margin-header:0.00cm;height:13.59in;page-margin-evendiff:0;et:9\" strux_image_dataid=\"NoPageBackground\" xid=\"220\">\n" +
                "        <p>\n" +
                "            <c props=\"font-weight:bold\">患者姓名</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_创业表1.RB_创业表1-姓名;text-decoration:underline;en:3.1\">彭灵燕</c>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">性别</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_就诊记录.RB_就诊记录-性别;text-decoration:underline;en:4.1\">女</c>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">年龄</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_就诊记录.RB_就诊记录-年龄;text-decoration:underline;en:15.1\">38岁</c>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">科别</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_创业表1.RB_创业表1-科室;text-decoration:underline;en:13.1\">综合三病房加</c>\n" +
                "            <c/>\n" +
                "        </p >\n" +
                "        <p>\n" +
                "            <c props=\"font-weight:bold\">病室</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_创业表1.RB_创业表1-病区;text-decoration:underline;en:14.1\">综合三病区加</c>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">床号</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"et:6;ef:RA_创业表1.RB_创业表1-床位号;text-decoration:underline;en:7.1\">JC04</c>\n" +
                "        </p >\n" +
                "        <p props=\"line-height:2.5000\" style=\"Normal\" xid=\"3\">\n" +
                "            <c props=\"font-weight:bold\">临床诊断</c>\n" +
                "            <c>：</c>\n" +
                "            <c>结肠癌</c>\n" +
                "        </p >\n" +
                "        <p props=\"line-height:2.5000\" style=\"Normal\" xid=\"4\">\n" +
                "            <c props=\"font-weight:bold\">病理诊断</c>\n" +
                "            <c>：</c>\n" +
                "            <c>腺癌</c>\n" +
                "        </p >\n" +
                "        <p props=\"line-height:2.5000\" style=\"Normal\" xid=\"5\">\n" +
                "            <c props=\"font-weight:bold\">拟行化学治疗方案</c>\n" +
                "            <c>：</c>\n" +
                "            <c>伊立替康  200mg 静滴 第1天 /21天重复*1周期</c>\n" +
                "        </p >\n" +
                "        <p props=\"line-height:2.5000\" style=\"Normal\" xid=\"6\">\n" +
                "            <c props=\"font-weight:bold\">化学治疗风险及并发症</c>\n" +
                "            <c>：</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"7\">\n" +
                "            <c>化学治疗 ( 简称化疗 ) 作为治疗肿瘤的主要手段之一已广泛应用于临床，并对多种肿瘤具有良好的疗效，部分肿瘤经过化疗可以得到根治，更多的病人经过化疗可以延长生存时间改善生活质量。但是，不是所有病人所有肿瘤都适宜做化疗，也不是所有肿瘤都对化疗敏感。化疗药物在杀死肿瘤细胞的同时对人体某些生长代谢旺盛的正常组织细胞 ( 如骨髓、消化道上皮细胞等 ) 也有一定的毒性。化疗的毒副作用主要表现为恶心呕吐、腹泻、血象降低、脱发、口腔黏膜炎、静脉炎等，某些药物还可以对人体的心、肺、肝肾功能及神经系统造成不同程度的损害。另外有些化疗药物局部刺激性较强，外渗或外漏后可造成局部组织损伤，严重者可引起组织坏死。化疗的毒副作用随所用药物及病人的耐受能力而异，大多数为可逆性，停药或对症治疗后能缓解，极少数也可以发生严重并发症，甚至死亡。</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"8\">\n" +
                "            <c>我明确知道，基于本人疾病状况及诊断，有必要实施化疗以达到治疗目的，我知道将由</c>\n" +
                "            <c props=\"text-decoration:underline\"/>\n" +
                "            <c props=\"text-decoration:underline\">王金万、</c>\n" +
                "            <c props=\"text-decoration:underline\"/>\n" +
                "            <c>等医生为我实施化疗，</c>\n" +
                "            <c props=\"text-decoration:underline\"/>\n" +
                "            <c>等医生已向我解释过接受化疗必要性及存在医疗上难以预知和 / 或无法避免的风险。</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"9\">\n" +
                "            <c>我理解化疗具有一定风险，在化疗过程中有可能出现一些不可预料的情况可导致危及生命和健康的严重后果，医生将会根据医学专业知识和经验尽可能提供合理的化疗方案，预期达到最佳疗效，并积极预防和处理化疗的毒副作用，我理解有疾病和医疗上不可确定的因素导致本次化疗无法达到预期目的和效果的可能性。</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"10\">\n" +
                "            <c>中国医学科学院肿瘤医院是一家三级甲等肿瘤专科医院，所从事的医疗活动有义务严格遵循国家有关的法律、法规和诊疗护理的规范要求。我同意在这家医院诊疗工作中引起的任何争议将按照国家有关的法律、法规所提供的途径解决。</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"11\">\n" +
                "            <c>我明确表示，同意接受本次化学治疗。</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"12\"/>\n" +
                "        <p style=\"Normal\" xid=\"13\">\n" +
                "            <c props=\"font-weight:bold\">患者签名</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"text-decoration:underline\"/>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">日期：</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">年</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">月</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">日</c>\n" +
                "            <c/>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"14\">\n" +
                "            <c props=\"font-size:9pt\">(应由患者本人签名，如果患者本人无法签名，可由患者法定代理人、近亲属及关系人签名，同时注明与患者关系、并附上授权委托书。 )</c>\n" +
                "        </p >\n" +
                "        <p style=\"Normal\" xid=\"15\">\n" +
                "            <c props=\"font-weight:bold\">医生签名</c>\n" +
                "            <c>：</c>\n" +
                "            <c props=\"text-decoration:underline\"/>\n" +
                "            <c/>\n" +
                "            <c props=\"font-weight:bold\">日期：</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">年</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">月</c>\n" +
                "            <c props=\"font-weight:bold;text-decoration:underline\"/>\n" +
                "            <c props=\"font-weight:bold\">日</c>\n" +
                "            <c/>\n" +
                "            <c props=\"font-size:9pt\">( 应由与患者及其代理人进行谈话的本院主治医生以上的医师签名。 )</c>\n" +
                "        </p >\n" +
                "    </section>\n" +
                "    <section id=\"109\" listid=\"0\" parentid=\"0\" props=\"et:9;ef:element..hdrftr_header\" type=\"header\" xid=\"123\">\n" +
                "        <p/>\n" +
                "        <p props=\"text-align:center\" xid=\"194\"/>\n" +
                "        <p props=\"text-align:center\" xid=\"199\"/>\n" +
                "        <p props=\"text-align:center\" xid=\"200\"/>\n" +
                "        <p props=\"text-align:center\" xid=\"201\"/>\n" +
                "        <p props=\"text-align:center\" xid=\"202\">\n" +
                "            <c props=\"font-family:黑体;font-size:16pt\">化学治疗知情同意书</c>\n" +
                "        </p >\n" +
                "        <p>\n" +
                "            <c props=\"text-decoration:underline;font-weight:bold\">病案号:</c>\n" +
                "            <c props=\"et:6;ef:RA_创业表1.RB_创业表1-病案号;text-decoration:underline;en:1.1;font-weight:bold\">1396336</c>\n" +
                "            <c props=\"text-decoration:underline;font-weight:bold\"/>\n" +
                "        </p >\n" +
                "    </section>\n" +
                "    <section id=\"108\" listid=\"0\" parentid=\"0\" props=\"et:9;ef:element..hdrftr_footer\" type=\"footer\" xid=\"107\">\n" +
                "        <p props=\"text-align:center\" xid=\"108\"/>\n" +
                "        <p props=\"text-align:center\" xid=\"189\">\n" +
                "            <c>第</c>\n" +
                "            <c style=\"1\">（</c>\n" +
                "            <field style=\"1\" type=\"page_number\" xid=\"198\"/>\n" +
                "            <c>）页</c>\n" +
                "        </p >\n" +
                "    </section>\n" +
                "</uwword>";
        String key1 = "";
        String key2 = "";
        String stopwords = null;
        System.out.println(evaluate(str, key1, key2, stopwords));
    }


    public static String evaluate(String str, String key1, String key2, String stopwords) {
        if (str == null)
            return "";
        if (key1 == null)
            return str;
        if (key2 == null)
            return str;
        String[] stopword = null;
        if (stopwords != null)
            stopword = stopwords.split(",");

        if (key1.indexOf(",") > 0) {
            String[] ks = key1.split(",");
            for (String k : ks) {
                if (str.indexOf(k) >= 0) {
                    str = str.substring(str.indexOf(k) + k.length());
                    // break;
                }
            }
        } else if (str.indexOf(key1) >= 0) {
            str = str.substring(str.indexOf(key1) + key1.length());
        }
        /*
         * if (str.indexOf(key2) >= 0 && !key2.equals("")) { str = str.substring(0,
         * str.indexOf(key2)); } else if (key2.indexOf(",") > 0) {
         */
        String[] ks = key2.split(",");
        for (String k : ks) {
            if (str.indexOf(k) >= 0 && !k.equals("")) {
                String sw = null;
                if (stopword != null) {
                    for (String s : stopword) {
                        if (s.indexOf(k) >= 0)
                            sw = s;
                    }
                }
                if (sw == null) {
                    str = str.substring(0, str.indexOf(k));
                } else {
                    if (str.indexOf(sw) > 0) {
                        String tmp = str.substring(str.lastIndexOf(sw) + sw.length(), str.length());
                        if (tmp.indexOf(k) > 0) {
                            tmp = tmp.substring(0, tmp.indexOf(k));
                        }
                        str = str.substring(0, str.lastIndexOf(sw)) + sw + tmp;
                    } else {
                        str = str.substring(0, str.indexOf(k));
                    }
                }
            }
        }
        // }
        return str;
    }

}
