package wahl.adriano.whoistheclosest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WhoIsTheClosest5 implements WhoIsTheClosest {

    protected static final int VALUE_FOR_ANSWER_NOT_COMPUTED = -2;

    // n = length of s, m = length of queries, k = number of occurrences of the same character

    public List<Integer> findClosest(String s, List<Integer> queries) {// O(n + m * log k) speed
        Map<Character, List<Integer>> lettersToIndexesMap = mapLettersToIndexes(s);// O(n)
        var answersToQueries = new ArrayList<Integer>();
        var answersArray = createAndInitializeIntegerArray(s.length(), VALUE_FOR_ANSWER_NOT_COMPUTED);
        queries.forEach(queryIndex -> {
            Integer closestChar;
            if (answersArray[queryIndex] != VALUE_FOR_ANSWER_NOT_COMPUTED) {// if it's repeated, answer is O(1)
                closestChar = answersArray[queryIndex];
            } else {
                closestChar = findClosestChar(s, lettersToIndexesMap, queryIndex);
                answersArray[queryIndex] = closestChar;
            }
            answersToQueries.add(closestChar);
        });// O(m)
        return answersToQueries;
    }

    private Map<Character, List<Integer>> mapLettersToIndexes(String s) {// O(n)
        Map<Character, List<Integer>> lettersToIndexesMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lettersToIndexesMap.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }
        return lettersToIndexesMap;
    }

    private static Integer[] createAndInitializeIntegerArray(int length, int initialValue) {
        var answersArray = new Integer[length];
        Arrays.fill(answersArray, initialValue);
        return answersArray;
    }

    private Integer findClosestChar(String s, Map<Character, List<Integer>> lettersToIndexesMap, Integer queryIndex) {// O(log k)
        var characterQueried = s.charAt(queryIndex);
        List<Integer> indexesOfSameCharacter = lettersToIndexesMap.get(characterQueried);
        if (indexesOfSameCharacter == null || indexesOfSameCharacter.size() < 2) {
            return -1;
        } else {
            int startingPosition = findIndexOfValueInListThroughBinarySearch(queryIndex, indexesOfSameCharacter);
            var left = startingPosition - 1;
            var right = startingPosition + 1;
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

    private static int findIndexOfValueInListThroughBinarySearch(Integer value, List<Integer> listContainingValue) {// O(log k)
        int low = 0;
        int high = listContainingValue.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (Objects.equals(listContainingValue.get(middle), value)) {
                return middle;
            } else if (listContainingValue.get(middle) < value) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }
}
