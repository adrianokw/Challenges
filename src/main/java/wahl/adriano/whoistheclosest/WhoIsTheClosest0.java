package wahl.adriano.whoistheclosest;

import java.util.List;

public class WhoIsTheClosest0 implements WhoIsTheClosest {
    // n = length of s, m = length of queries, k = number of occurrences of the same character

    public List<Integer> findClosest(String s, List<Integer> queries) {//0(n * m)
        return queries.stream().map((query) -> findClosestChar(s, query)).toList();//0(m)
    }

    private Integer findClosestChar(String s, Integer queryIndex) {// O(n)
        var characterQueried = s.charAt(queryIndex);
        int distance = Integer.MAX_VALUE;
        int indexOfClosest = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == characterQueried && i != queryIndex && Math.abs(i - queryIndex) < distance) {
                distance = Math.abs(i - queryIndex);
                indexOfClosest = i;
            }
        }
        return indexOfClosest;
    }
}
