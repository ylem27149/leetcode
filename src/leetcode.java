import java.math.BigInteger;
import java.util.HashMap;
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length;i++)
            map.put(nums[i],i);
        for (int i =0; i < nums.length;i++)
        {if (map.containsKey(target-nums[i]) && i != map.get(target-nums[i])) {
            result[0] = i;
            result[1] = map.get(target - nums[i]);
            return result;
        }

        }
        return result;
        /*
        int[] nums = new int[]{3,2,4};
        int target = 6;
        Solution solution = new Solution();
        System.out.print(solution.twoSum(nums,target));
        */
    }

    public int myAtoi(String s) {
        // 官方解法里面的有限状态机的思路很有趣诶，之前没见过（不过懒得试了啊哈哈）
        int start = 0;
        int length = 0;

        for (int i = 0; i<s.length();i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                //当前字符为数字
                length++;
            else if(length != 0)
                //当前字符为非数字，并且之前已经读取到数字或正负号的情况下
                break;
            else //当前字符为非数字，并且之前没有读取到数字或正负号
            {
                if (s.charAt(i) == ' ')
                    start++;
                else if (s.charAt(i) == '+' ||s.charAt(i) == '-')

                length++;
                else
                    break;
            }

        }
        if (length == 0)
            return 0;
        String substring = s.substring(start,start+length);
        if(substring.equals("+")||substring.equals("-"))
            return 0;
        BigInteger num = new BigInteger(substring);
        if (num.compareTo(new BigInteger("-2147483648")) != 1)
            return -2147483648;
        if (num.compareTo(new BigInteger("2147483647")) != -1)
            return 2147483647;

        else
            return num.intValue();
    }

        public String longestCommonPrefix(String[] strs) {

            int maxprefix = 0;

            boolean flag = true;

            for(int i =0; i<strs[0].length();i++)
            // 第一个字符串的每一个元素
            {for(int j =0;j<strs.length;j++)
                //数组中的每一个字符串
                if(strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i))
                {flag = false;
                    break;
                }
                if (flag == false)

                    break;

                else
                    maxprefix = i+1;
            }
            if (maxprefix == 0)
                return "";
            else
                return strs[0].substring(0,maxprefix);




        }

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<List<Integer>>();


        for (int i = 0; i < nums.length - 2; i++)
        //从前到后,第一个元素
        {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length - 1; j++)
            //第二个元素
            {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                int third = Arrays.binarySearch(nums, j + 1, nums.length, -nums[i] - nums[j]);
                if (third >= 0)
                //找到了
                {
                    List<Integer> answer = new ArrayList(Arrays.asList(nums[i], nums[j], nums[third]));
                    answers.add(answer);
                }

            }
        }
    return answers;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0]+nums[1]+nums[2];
        if (sum>target)
            return sum;
        for(int i=0;i<nums.length-2;i++)
            for(int j = i+1;j<nums.length-1;j++)
                for(int k = j+1;k<nums.length;k++)
                    {if (Math.abs(nums[i] + nums[j] + nums[k]-target) <= Math.abs(sum-target))
                        sum = nums[i] + nums[j] + nums[k];
                    else if(nums[i] + nums[j] + nums[k]>target)
                        break;
                    }
    return sum;


    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        for(int i = 0;i<s.length();i++)
            {if(map.containsKey(s.charAt(i)))
                stack.push(s.charAt(i));
            else if(stack.isEmpty()||map.get(stack.pop())!=s.charAt(i))
                return false;}

        if (stack.isEmpty())
            return true;
        else
            return false;

    }

    }





public class leetcode{
    public static void main(String[] args) {

        String s = "()";
        Solution solution = new Solution();
        solution.isValid(s);

    }
}