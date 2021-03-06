package com.sample.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 1. Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * 2. The solution set must not contain duplicate triplets.
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 */
public class ThreeAndFourSum {

    public static void main(String[] args) {

        Integer[] input = { -1, 0, 1, 2, -1, -4 };

        int[] array = { 1, 8, 30, 40, 100 };
        threeSumBetter();
        fourSumBetter();
        twoDifference(array);

        threeSum(input);
        threeSumII(input);
        threeSumIII(input);

        Integer[] input1 = { 1, 0, -1, 0, -2, 2 };
        fourSum(input1);
        fourSumII(input1);
    }

    private static void twoDifference(int[] array) {
        Arrays.sort(array);
        int difference = 60;
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            int diff = array[j] - array[i];
            if (diff == difference) {
                System.out.println(array[i] + " , " + array[j]);
                i++;
                j--;
            }
            if (diff > difference) {
                i++;
            } else {
                j--;
            }
        }
    }

    private static void threeSumIII(Integer[] input) {
        Arrays.sort(input);
        int target = 0;
        int sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int cnt = 0; cnt < input.length - 3; cnt++) {
            int left = cnt + 1, right = input.length - 1;
            while (left < right) {
                sum = input[cnt] + input[left] + input[right];
                if (sum == target) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(input[cnt]);
                    triplet.add(input[left]);
                    triplet.add(input[right]);
                    result.add(triplet);
                    left++;
                    right--;
                }
                if (sum > target) {
                    right--;
                }
                if (sum < target) {
                    left++;
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void fourSumII(Integer[] input) {
        Arrays.sort(input);
        int sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int cnt = 0; cnt < input.length; cnt++) {
            List<Integer> indexes = indexMap.get(input[cnt]);
            if (indexes == null) {
                indexes = new ArrayList<>();
                indexes.add(cnt);
                indexMap.put(input[cnt], indexes);
            } else {
                indexes.add(cnt);
            }
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    int reminder = sum - (input[i] + input[j] + input[k]);
                    if (indexMap.containsKey(reminder)) {
                        List<Integer> indexes = indexMap.get(reminder);
                        for (Integer index : indexes) {
                            if (index > k) {
                                List<Integer> quartlet = new ArrayList<>();
                                quartlet.add(input[i]);
                                quartlet.add(input[j]);
                                quartlet.add(input[k]);
                                quartlet.add(reminder);
                                result.add(quartlet);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void fourSum(Integer[] input) {
        int sum = 0;
        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    for (int l = k + 1; l < input.length; l++) {
                        if (sum == (input[i] + input[j] + input[k] + input[l])) {
                            List<Integer> quartlet = new ArrayList<>();
                            quartlet.add(input[i]);
                            quartlet.add(input[j]);
                            quartlet.add(input[k]);
                            quartlet.add(input[l]);
                            result.add(quartlet);
                        }
                    }
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void threeSum(Integer[] input) {
        Arrays.sort(input);
        int sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < input.length - 2; i++) {
            for (int j = i + 1; j < input.length - 1; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    if (sum == input[i] + input[j] + input[k]) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(input[i]);
                        triplet.add(input[j]);
                        triplet.add(input[k]);
                        result.add(triplet);
                    }
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void threeSumII(Integer[] input) {
        int sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> inputIndexMap = new HashMap<>();
        for (int cnt = 0; cnt < input.length; cnt++) {
            if (inputIndexMap.containsKey(input[cnt])) {
                List<Integer> indexes = inputIndexMap.get(input[cnt]);
                indexes.add(cnt);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(cnt);
                inputIndexMap.put(input[cnt], indexes);
            }
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int reminder = sum - input[i] - input[j];
                if (inputIndexMap.containsKey(reminder)) {
                    List<Integer> indexList = inputIndexMap.get(reminder);
                    for (Integer index : indexList) {
                        if (index > j) {
                            List<Integer> triplet = new ArrayList<>();
                            triplet.add(input[i]);
                            triplet.add(input[j]);
                            triplet.add(reminder);
                            result.add(triplet);
                        }
                    }
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void threeSumBetter() {
        int[] input = { -1, 0, 1, 2, -1, 2, 2, 2, 2, 2, -4 };
        int target = 0;
        if (input == null || input.length <= 2)
            return;

        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < input.length - 2; i++) {
            if (i > 0 && input[i] == input[i - 1])
                continue;
            int l = i + 1, r = input.length - 1;
            while (l < r) {
                int sum = input[i] + input[l] + input[r];
                if (sum == target) {
                    List<Integer> aresult = new ArrayList<>();
                    aresult.add(input[i]);
                    aresult.add(input[l]);
                    aresult.add(input[r]);
                    result.add(aresult);

                    while (l < r && input[l] == input[l + 1])
                        l++;
                    while (l < r && input[r] == input[r - 1])
                        r--;
                    l++;
                    r--;
                } else if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                }
            }
        }
        System.out.println(result.toString());
    }

    private static void fourSumBetter() {
        int[] input = { 1, 0, -1, 0, -2, 2 };
        int target = 0;
        if (input == null || input.length <= 3)
            return;
        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < input.length - 3; i++) {
            if (i > 0 && input[i] == input[i - 1])
                continue;
            for (int j = i + 1; j < input.length - 2; j++) {
                if (j > i + 1 && input[j] == input[j - 1])
                    continue;
                int l = j + 1, r = input.length - 1;
                while (l < r) {
                    int sum = input[i] + input[j] + input[l] + input[r];
                    if (sum == target) {
                        List<Integer> aresult = new ArrayList<>();
                        aresult.add(input[i]);
                        aresult.add(input[j]);
                        aresult.add(input[l]);
                        aresult.add(input[r]);
                        result.add(aresult);

                        while (l < r && input[l] == input[l + 1])
                            l++;
                        while (l < r && input[r] == input[r - 1])
                            r--;
                        l++;
                        r--;
                    } else if (sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        System.out.println(result.toString());
    }
}
