package leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */
public class towSum {

    // 一次哈希
    public int[] twoSum_mapOnce(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i){
                return new int[] { i, map.get(complement) };
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("no answer");
    }

    // 两遍哈希
    public int[] twoSum_map(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i){
                return new int[] { i, map.get(complement) };
            }
        }

        throw new IllegalArgumentException("no answer");
    }


    //暴力破解
    public int[] twoSum_violence(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(target == nums[i]+nums[j]){
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("no answer");
    }
}