package wahl.adriano.whoistheclosest;

import java.util.List;

public class WhoIsTheClosest1 implements WhoIsTheClosest {
    // n = length of s, m = length of queries, k = number of occurrences of the same character

    public List<Integer> findClosest(String s, List<Integer> queries) {//0(m log n)
        return queries.stream().map((query) -> findClosestChar(s, query)).toList();//0(m)
    }

    private Integer findClosestChar(String s, Integer queryIndex) {// O(log n)
        var characterQueried = s.charAt(queryIndex);
        var left = queryIndex - 1;
        var right = queryIndex + 1;
        while (left >= 0 || right < s.length()) {
            if (left >= 0 && s.charAt(left) == characterQueried) {
                return left;
            } else if (right < s.length() && s.charAt(right) == characterQueried) {
                return right;
            }
            left--;
            right++;
        }
        return -1;
    }
}
