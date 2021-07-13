public class findMedianSortedArrays {
    public double binarySearch(int[] nums1, int[] nums2) {
        //参考二分查找
        int k = (nums1.length+nums2.length+1)/2;  // 中位数是第K个元素
        int i = (nums1.length+1)/2;  //第一个数组分割线右边的元素索引
        int j = k-i; //第二个数组分割线右边的元素索引。在这种写法下，左边的元素个数大于等于右边
        int left = 0; //分割线左边最大的元素
        while(true)
        {
            if(nums1.length == 0 || nums2.length == 0)
                break;
            if(i==0 && nums1[i] >= nums2[j - 1])
                break;
            if(i==nums1.length && nums1[i - 1] <= nums2[j])
                break;
            if (j==0 && nums1[i - 1]<= nums2[j])
                break;
            if(j == nums2.length && nums2[j - 1] <= nums1[i])
                break;

            if(i != 0 && i != nums1.length && j != 0 && j!= nums2.length && nums1[i - 1] <= nums2[j] && nums1[i] >= nums2[j - 1])
                break;

            if (i!= 0 && j != nums2.length&&nums1[i - 1] > nums2[j]) {
                i--;
                j++;
            }

            if (i!= nums1.length && j != 0 && nums1[i] < nums2[j - 1]) {
                i++;
                j--;
            }


        }
        if(i == 0)
            left = nums2[j-1];
        if(j == 0)
            left = nums1[i-1];
        // 不能合并到上面的循环里。不然从 == nums.length出来并且另一个==0的情况会被漏掉

        if(i != 0 && j!= 0)
            left = Math.max(nums1[i-1],nums2[j-1]);

        if ((nums1.length + nums2.length) % 2 == 0) {
            //偶数
            int right = 0;
            if (i == nums1.length)
                right = nums2[j];
            if(j == nums2.length)
                right = nums1[i];
            if(i != nums1.length && j != nums2.length)
                right = Math.min(nums1[i],nums2[j]);
            return (left+right)/2.0;
        }
        else
            return left;

    }

    public double mergeSort(int[] nums1, int[] nums2) {
        // 参考归并排序，从左向右查找在两个数组中处于中间位置的数
        int i = 0;
        int j = 0;
        double median = 0.0;
        // 分这么多情况主要是怕索引过节=-=
        // 另外总数字个数为奇数/偶数需要用不同公式

        // 一个数组为空，直接找另一个数组的中位数
        if (nums1.length == 0 && nums2.length != 0) {
            //nums1 为空
            if (nums2.length % 2 == 0)
                return (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0;
            else
                return (double) nums2[nums2.length/2];
        }
        if (nums2.length == 0 && nums1.length != 0) {
            //nums2为空
            if (nums1.length % 2 == 0)
                return (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0;
            else
                return (double) nums1[nums1.length/2];
        }
        //都不为空。
        if ((nums1.length + nums2.length) % 2 == 0) {
            //偶数
            for (int current = 0; current < (nums1.length + nums2.length) / 2.0; current++) {
                //一个数组用完了
                if (i == nums1.length) {
                    median = nums2[j];
                    j++;
                }
                else if (j == nums2.length)
                {
                    median = nums1[i];
                    i++;

                }
                //两个数组都没有用完
                else if (nums1[i] <= nums2[j]) {
                    median = nums1[i];
                    i++;
                } else {
                    median = nums2[j];
                    j++;
                }
            }
            //寻找参与计算中位数的第二个数字
            if (i < nums1.length && j < nums2.length)
            // 第一个数字不在任意一个数组的末尾
            {if(nums1[i] <= nums2[j])
                median = (median + nums1[i]) / 2.0;
            else
                median = (median + nums2[j]) / 2.0;}
            //第一个数字在其中一个数组的末尾
            if (i == nums1.length)
                median = (median + nums2[j]) / 2.0;
            if (j == nums2.length)
                median = (median + nums1[i]) / 2.0;
        }
        else {
            //两数组都不为空，总长度为奇数
            for (int current = 0; current < (nums1.length + nums2.length) / 2.0; current++) {
                //一个数组用完了
                if (i == nums1.length) {
                    median = nums2[j];
                    j++;
                }
                else if (j == nums2.length)
                {
                    median = nums1[i];
                    i++;

                }
                //两个数组都没有用完
                else if (nums1[i] <= nums2[j]) {
                    median = nums1[i];
                    i++;
                } else {
                    median = nums2[j];
                    j++;
                }
            }
        }
        return median;
    }
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3};
        findMedianSortedArrays find = new findMedianSortedArrays();
        System.out.print(find.binarySearch(nums1,nums2));
    }
}


