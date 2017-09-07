package com.lin.algorithm;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.

 * Examples:

 * Given "abcabcbb", the answer is "abc", which the length is 3.

 * Given "bbbbb", the answer is "b", with the length of 1.

 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring {

    /**
     * The naive approach is very straightforward. But it is too slow. So how can we optimize it?
     *
     * In the naive approaches, we repeatedly check a substring to see if it has duplicate character. But it is unnecessary. If a substring s_{ij}
     * from index ii to j - 1 is already checked to have no duplicate characters. We only need to check if s[j] is already in the substring s_{ij}
     * .
     *
     * To check if a character is already in the substring, we can scan the substring, which leads to an O(n^2)
     * algorithm. But we can do better.
     *
     * By using HashSet as a sliding window, checking if a character in the current can be done in O(1)O(1).
     *
     * A sliding window is an abstract concept commonly used in array/string problems. A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j) (left-closed, right-open). A sliding window is a window "slides" its two boundaries to the certain direction. For example, if we slide [i, j) to the right by 1 element, then it becomes [i+1, j+1) (left-closed, right-open).
     *
     * Back to our problem. We use HashSet to store the characters in current window [i, j) (j=i initially). Then we slide the index j to the right. If it is not in the HashSet, we slide j further. Doing so until s[j] is already in the HashSet. At this point, we found the maximum size of substrings without duplicate characters start with index i. If we do this for all i, we get our answer.

     * Complexity Analysis

     * Time complexity : O(2n)=O(n). In the worst case each character will be visited twice by i and j.

     * Space complexity : O(min(m, n)). Same as the previous approach. We need O(k) space for the sliding window, where k is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    /**
     * Sliding Window Optimized [Accepted]
     * The above solution requires at most 2n steps. In fact, it could be optimized to require only n steps. Instead of using a set to tell if a character exists or not, we could define a mapping of the characters to its index. Then we can skip the characters immediately when we found a repeated character.
     *
     * The reason is that if s[j] have a duplicate in the range [i, j) with index j,
     * we don't need to increase i little by little. We can skip all the elements in the range [i, j]
     * and let i to be j + 1 directly.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    /**
     * Java (Assuming ASCII 128)
     *
     * The previous implements all have no assumption on the charset of the string s.
     *
     * If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
     *
     * Commonly used tables are:
     *
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     *
     * Complexity Analysis
     *
     * Time complexity : O(n). Index j will iterate n times.
     *
     * Space complexity (HashMap) : O(min(m, n)). Same as the previous approach.
     *
     * Space complexity (Table): O(m). m is the size of the charset.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstring().lengthOfLongestSubstring3("abcdeeeaf"));
    }
}
