package leetcode;

import java.util.*;

/**
 * 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */

public class LongestWithoutRepeating {

    // ascii
    public int lengthOfLongestSubstring_ASCII(String s){
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j-i+1);
            index[s.charAt(j)] = j+1;
        }
        return ans;
    }

    // 优化滑动窗口
    public int lengthOfLongestSubstring_HashMap(String s){
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }

    // 滑动窗口
    public int lengthOfLongestSubstring_HashSet(String s){
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n){
            if ( !set.contains(s.charAt(j)) ){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    // 暴力法
    public int lengthOfLongestSubstring(String s){
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                if(allUnique(s, i, j)) ans = Math.max(ans, j-i);
            }
        }
        return ans;
    }
    public boolean allUnique(String s, int start, int end){
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if(set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

}
