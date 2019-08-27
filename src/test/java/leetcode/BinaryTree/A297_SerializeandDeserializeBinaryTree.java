package leetcode.BinaryTree;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/27
 * \* Time: 28:23 PM
 * \* Description: 二叉树的序列化与反序列化
 * \
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A297_SerializeandDeserializeBinaryTree {
    public class Codec {

        /**
         * 层次遍历
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "null";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            StringBuilder sb = new StringBuilder("");
            while (!queue.isEmpty()) {
                int size = queue.size();
                Boolean isLastCeil = true; //是否是最后一层
                for (int i = 0; i < size; i++) {
                    TreeNode n = queue.poll();
                    if (n == null) {
                        sb.append("null,");
                        continue;
                    } else {
                        sb.append(n.val + ",");
                    }
                    if (n.left != null || n.right != null) {
                        isLastCeil = false;
                    }
                    queue.add(n.left);
                    queue.add(n.right);
                }
                if (isLastCeil) {
                    break;
                }
            }
            String result = sb.toString();
            return result.substring(0, result.length() - 1);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("null".equals(data)) {
                return null;
            }
            String[] source = data.split(",");
            TreeNode root = new TreeNode(Integer.valueOf(source[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int puls = 1;
            int sourceIndex = 1;
            while (!queue.isEmpty() && (sourceIndex < source.length - 1)) {
                int size = (int) Math.pow(2, puls);
                for (int i = 0; i < size; i++) {
                    if (sourceIndex >= source.length - 1) {
                        return root;
                    }
                    TreeNode n = queue.poll();
                    if ("null".equals(source[sourceIndex])) {
                        n.left = null;
                    } else {
                        TreeNode left = new TreeNode(Integer.valueOf(source[sourceIndex]));
                        n.left = left;
                        queue.add(left);
                    }
                    sourceIndex++;
                    if ("null".equals(source[sourceIndex])) {
                        n.right = null;
                    } else {
                        TreeNode left = new TreeNode(Integer.valueOf(source[sourceIndex]));
                        n.right = left;
                        queue.add(left);
                    }
                    sourceIndex++;
                    puls++;
                }
            }
            return root;

        }

    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        Codec codec = new Codec();
        String nodeStr = codec.serialize(n1);
        System.out.println("string = " + nodeStr);
        TreeNode n = codec.deserialize(nodeStr);
        System.out.println("node value = " + n.val);

     /*   TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n1 = new TreeNode(1);
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        Codec codec = new Codec();
        String nodeStr = codec.serialize(n3);
        System.out.println("string = " + nodeStr);*/
    }
}
