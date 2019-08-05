package leetcode.BinarySearch;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/8
 * \* Time: 29:26 PM
 * \* Description:找到 K 个最接近的元素
 * \
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5], k=4, x=-1
 * 输出: [1,2,3,4]
 * <p>
 * <p>
 * 说明:
 * <p>
 * k 的值为正数，且总是小于给定排序数组的长度。
 * 数组不为空，且长度不超过 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 * <p>
 * <p>
 * 更新(2017/9/19):
 * 这个参数 arr 已经被改变为一个整数数组（而不是整数列表）。 请重新加载代码定义以获取最新更改。
 */
public class A658_FindKClosestElements {

    /**
     * @param arr
     * @param k   取的个数
     * @param x   目标值
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr.length == 1) {
            result.add(arr[0]);
            return result;
        }
        int left = 0;
        int right = arr.length - 1;
        int leftIndex = 0; //结果的左边界
        int rightIndex = 0; //结果的右边界
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                leftIndex = mid;
                rightIndex = mid;
                while (leftIndex >= 0 && rightIndex <= arr.length - 1) {
                    if (leftIndex != 0 && rightIndex != arr.length - 1) {
                        if (Math.abs(arr[leftIndex - 1] - x) <= Math.abs(arr[rightIndex + 1] - x)) {
                            leftIndex--;
                            if (rightIndex - leftIndex + 1 == k) {
                                break;
                            }
                        } else {
                            rightIndex++;
                            if (rightIndex - leftIndex + 1 == k) {
                                break;
                            }
                        }
                    } else {
                        if (leftIndex != 0) {
                            leftIndex--;
                        }
                        if (rightIndex - leftIndex + 1 == k) {
                            break;
                        }
                        if (rightIndex != arr.length - 1) {
                            rightIndex++;
                        }
                        if (rightIndex - leftIndex + 1 == k) {
                            break;
                        }
                    }
                }
                for (int i = leftIndex; i <= rightIndex; i++) {
                    result.add(arr[i]);
                }
                return result;
            } else if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (arr[left] < x && arr[right] > x) {
            if (Math.abs(arr[leftIndex] - x) <= Math.abs(arr[rightIndex] - x)) {
                leftIndex = left;
                rightIndex = left;
            } else {
                leftIndex = right;
                rightIndex = right;
            }
            while (leftIndex >= 0 && rightIndex <= arr.length - 1) {
                if (leftIndex != 0 && rightIndex != arr.length - 1) {
                    if (Math.abs(arr[leftIndex - 1] - x) <= Math.abs(arr[rightIndex + 1] - x)) {
                        leftIndex--;
                        if (rightIndex - leftIndex + 1 == k) {
                            break;
                        }
                    } else {
                        rightIndex++;
                        if (rightIndex - leftIndex + 1 == k) {
                            break;
                        }
                    }
                } else {
                    if (leftIndex != 0) {
                        leftIndex--;
                    }
                    if (rightIndex - leftIndex + 1 == k) {
                        break;
                    }
                    if (rightIndex != arr.length - 1) {
                        rightIndex++;
                    }
                    if (rightIndex - leftIndex + 1 == k) {
                        break;
                    }
                }
            }
        } else {
            if (arr[left] >= x) {
                leftIndex = 0;
                rightIndex = k - 1;
            }
            if (arr[right] <= x) {
                rightIndex = arr.length - 1;
                leftIndex = rightIndex - (k - 1);
            }
        }
        for (int i = leftIndex; i <= rightIndex; i++) {
            result.add(arr[i]);
        }
        return result;
    }


    @Test
    public void test() {
        //[0,0,1,2,3,3,4,7,7,8] 3,5
        //[0,1,1,1,2,3,6,7,8,9] 9,4
        //[0,0,0,1,3,5,6,7,8,8] 2,2
        //[0,1,2,2,2,3,6,8,8,9] 5,9
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        System.out.println(JSONObject.toJSONString(findClosestElements(arr, k, x)));
    }

}
