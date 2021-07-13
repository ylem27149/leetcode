public class LongestPalindrome {
    public String firstVersion(String s) {
        //嗯，其实我这个跟动态转义的思路很接近了，但是那个是用空间换时间，记录了中间部分的计算结果
        //但是感觉我这也不是单纯的暴力解法啊。我这是先找可能的最长的，并且此后只会找长度有可能超过的。
        //性能应该还是比暴力好一点的
        int strLen = 1;
        String palindrome = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // 从左向右查看开头元素
            int tail = s.lastIndexOf(s.charAt(i));//结尾字符的Index,子字符串包括该字符
            while (tail - i + 1 > strLen) {
                //从右向左查看跟开头元素相同的元素，当之间的长度比当前最长的回文串长才会进行进一步查找
                boolean flag = true;
                for (int j = 1; j < tail - i; j++)
                    //查看中间的开头结尾之间的元素是否都符合回文
                    if (s.charAt(i + j) != s.charAt(tail - j)) {
                        flag = false;
                        break;
                    }
                if (flag) {
                    strLen = tail - i + 1;
                    palindrome = s.substring(i, tail + 1);
                } else
                    tail = s.lastIndexOf(s.charAt(i), tail - 1);

            }

        }

        return palindrome;
    }

    public String dynamicProgramming(String s) {
        //https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
        //一列一列计算的。即0-1，0-2，1-2，0-3，1-3，2-3，。。。
        boolean[][] booleans = new boolean[s.length()][s.length()];
        int strLen = 1;
        int start = 0;
        //按照可能的尾元素（i)枚举
        for (int i = 1; i < s.length(); i++)
            for (int j = 0; j < i; j++) {
                if (i - j < 3)
                    booleans[j][i] = s.charAt(i) == s.charAt(j);
                else
                    booleans[j][i] = s.charAt(i) == s.charAt(j) && booleans[j - 1][i - 1];
                if (booleans[j][i] && j - i + 1 > strLen) {
                    strLen = j - i + 1;
                    start = i;
                }


            }

        return s.substring(start, start + strLen + 1);
    }
}