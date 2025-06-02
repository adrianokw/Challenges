package wahl.adriano.whoistheclosest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WhoIsTheClosest3 implements WhoIsTheClosest {
    // n = length of s, m = length of queries, k = number of occurrences of the same character

    public List<Integer> findClosest(String s, List<Integer> queries) {// O(n + m * k) speed
        Map<Character, List<Integer>> lettersToIndexesMap = mapLettersToIndexes(s);// O(n)
        var answersToQueries = new ArrayList<Integer>();
        queries.forEach(queryIndex -> answersToQueries.add(findClosestChar(s, lettersToIndexesMap, queryIndex)));// O(m)
        return answersToQueries;
    }

    private Map<Character, List<Integer>> mapLettersToIndexes(String s) {// O(n)
        Map<Character, List<Integer>> lettersToIndexesMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lettersToIndexesMap.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }
        return lettersToIndexesMap;
    }

    private Integer findClosestChar(String s, Map<Character, List<Integer>> lettersToIndexesMap, Integer queryIndex) {// O(k)
        var characterQueried = s.charAt(queryIndex);
        List<Integer> indexesOfSameCharacter = lettersToIndexesMap.get(characterQueried);
        if (indexesOfSameCharacter == null || indexesOfSameCharacter.size() < 2) {
            return -1;
        } else {
            int startingPosition = findIndexOfValueInList(queryIndex, indexesOfSameCharacter);
            int left = startingPosition - 1;
            int right = startingPosition + 1;
            if (left < 0) {
                return indexesOfSameCharacter.get(right);
            } else if (right >= indexesOfSameCharacter.size()) {
                return indexesOfSameCharacter.get(left);
            }

            int leftDistance = Math.abs(indexesOfSameCharacter.get(left) - queryIndex);
            int rightDistance = Math.abs(indexesOfSameCharacter.get(right) - queryIndex);
            return leftDistance <= rightDistance ? indexesOfSameCharacter.get(left) : indexesOfSameCharacter.get(right);
        }
    }

    private static int findIndexOfValueInList(Integer value, List<Integer> listContainingValue) {// O(k)
        for (int i = 0; i < listContainingValue.size(); i++) {
            if (Objects.equals(listContainingValue.get(i), value)) {
                return i;
            }
        }
        return -1;
    }
}
