package wahl.adriano.whoistheclosest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhoIsTheClosest6 implements WhoIsTheClosest {
    // n = length of s, m = length of queries, k = number of occurrences of the same character

    public List<Integer> findClosest(String s, List<Integer> queries) {// O(n + m) speed
        Map<Character, List<Integer>> lettersToIndexesMap = mapLettersToIndexes(s);// O(n)
        var answersArray = createAndInitializeIntegerArray(s.length(), -1);
        for (List<Integer> indexesOfSameCharacter : lettersToIndexesMap.values()) {// O(n)
            if (indexesOfSameCharacter.size() < 2) {
                continue;
            }
            for (int index = 0; index < indexesOfSameCharacter.size(); index++) {
                int queryIndex = indexesOfSameCharacter.get(index);
                int closestIndex = findClosestIndex(indexesOfSameCharacter, index, queryIndex);
                answersArray[queryIndex] = closestIndex;
            }
        }
        return getAnswersToQueries(queries, answersArray);
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

    private int findClosestIndex(List<Integer> indexesOfSameCharacter, int startingPosition, int queryIndex) {// O(1)
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

    private List<Integer> getAnswersToQueries(List<Integer> queries, Integer[] answersArray) {
        var answersToQueries = new ArrayList<Integer>();
        for (Integer query : queries) {// O(m)
            answersToQueries.add(answersArray[query]);
        }
        return answersToQueries;
    }
}
