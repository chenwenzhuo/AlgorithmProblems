package com.hey_there.Qunar_September_23rd;

import java.util.*;

public class Problem_3 {
    private static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCards = Integer.parseInt(sc.nextLine());
        String[] cards = sc.nextLine().split(" ");
        sc.close();

        map.put("A", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("10", 10);
        map.put("J", 11);
        map.put("Q", 12);
        map.put("K", 13);

        Arrays.sort(cards, (s1, s2) -> {
            String type1 = s1.substring(0, 1);
            String type2 = s2.substring(0, 1);
            //花色不同时按花色排序
            if (!type1.equals(type2)) return type1.compareTo(type2);
            //花色相同时按点数排序
            String num1 = s1.substring(1);
            String num2 = s2.substring(1);
            return map.get(num1) - map.get(num2);
        });

        if (isHuangJiaTongHuaShun(cards)) {
            System.out.println("HuangJiaTongHuaShun");
            return;
        }
        if (isTongHuaShun(cards)) {
            System.out.println("TongHuaShun");
            return;
        }
        if (isSiTiao(cards)) {
            System.out.println("SiTiao");
            return;
        }
        if (isHuLu(cards)) {
            System.out.println("HuLu");
            return;
        }
        if (isTongHua(cards)) {
            System.out.println("TongHua");
            return;
        }
        if (isShunZi(cards)) {
            System.out.println("ShunZi");
            return;
        }
        if (isSanTiao(cards)) {
            System.out.println("SanTiao");
            return;
        }
        if (isLiangDui(cards)) {
            System.out.println("LiangDui");
            return;
        }
        if (isYiDui(cards)) {
            System.out.println("YiDui");
            return;
        }
        System.out.println("GaoPai");
    }

    private static boolean isHuangJiaTongHuaShun(String[] cards) {
        if (cards.length < 5) return false;

        HashMap<String, ArrayList<String>> typeAndNum = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (typeAndNum.containsKey(type)) {
                typeAndNum.get(type).add(num);
            } else {
                ArrayList<String> nums = new ArrayList<>();
                nums.add(num);
                typeAndNum.put(type, nums);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : typeAndNum.entrySet()) {
            ArrayList<String> nums = entry.getValue();
            if (nums.size() < 5) continue;//一种花色的牌少于5张，不能构成皇家同花顺

            //检查是否有10，J，Q，K，A这五张牌
            boolean contains10 = false;
            boolean containsJ = false;
            boolean containsQ = false;
            boolean containsK = false;
            boolean containsA = false;
            if (nums.contains("10")) contains10 = true;
            if (nums.contains("J")) containsJ = true;
            if (nums.contains("Q")) containsQ = true;
            if (nums.contains("K")) containsK = true;
            if (nums.contains("A")) containsA = true;

            if (contains10 && containsJ && containsQ && containsK && containsA) return true;
        }
        return false;
    }

    private static boolean isTongHuaShun(String[] cards) {
        if (cards.length < 5) return false;

        HashMap<String, ArrayList<String>> typeAndNum = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (typeAndNum.containsKey(type)) {
                typeAndNum.get(type).add(num);
            } else {
                ArrayList<String> nums = new ArrayList<>();
                nums.add(num);
                typeAndNum.put(type, nums);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : typeAndNum.entrySet()) {
            ArrayList<String> nums = entry.getValue();
            if (nums.size() < 5) continue;//一种花色的牌少于5张，不能构成同花顺

            ArrayList<Integer> numsInt = new ArrayList<>(nums.size());
            for (String n : nums) {
                numsInt.add(map.get(n));
            }
            Collections.sort(numsInt);

            int lianXuShuLiang = 1;
            for (int i = 1; i < numsInt.size(); i++) {
                if (numsInt.get(i) == numsInt.get(i - 1) + 1) {
                    lianXuShuLiang++;
                    if (lianXuShuLiang >= 5) return true;
                } else
                    lianXuShuLiang = 1;
            }
        }
        return false;
    }

    private static boolean isSiTiao(String[] cards) {
        HashMap<String, ArrayList<String>> numAndType = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (numAndType.containsKey(num)) {
                numAndType.get(num).add(type);
            } else {
                ArrayList<String> types = new ArrayList<>();
                types.add(type);
                numAndType.put(num, types);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : numAndType.entrySet()) {
            ArrayList<String> types = entry.getValue();
            if (types.size() >= 4) return true;
        }
        return false;
    }

    private static boolean isHuLu(String[] cards) {
        HashMap<String, ArrayList<String>> numAndType = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (numAndType.containsKey(num)) {
                numAndType.get(num).add(type);
            } else {
                ArrayList<String> types = new ArrayList<>();
                types.add(type);
                numAndType.put(num, types);
            }
        }

        boolean sanZhang = false;
        boolean liangZhang = false;
        for (Map.Entry<String, ArrayList<String>> entry : numAndType.entrySet()) {
            ArrayList<String> types = entry.getValue();
            if (types.size() == 3) sanZhang = true;
            else if (types.size() == 2) liangZhang = true;
        }

        return sanZhang && liangZhang;
    }

    private static boolean isTongHua(String[] cards) {
        HashMap<String, ArrayList<String>> typeAndNum = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (typeAndNum.containsKey(type)) {
                typeAndNum.get(type).add(num);
            } else {
                ArrayList<String> nums = new ArrayList<>();
                nums.add(num);
                typeAndNum.put(type, nums);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : typeAndNum.entrySet()) {
            ArrayList<String> nums = entry.getValue();
            if (nums.size() >= 5) return true;
        }
        return false;
    }

    private static boolean isShunZi(String[] cards) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (String card : cards) {
            String num = card.substring(1);
            Integer numInt = map.get(num);
            if (!nums.contains(numInt)) nums.add(numInt);
        }
        Collections.sort(nums);

        int lianXuShuLiang = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == nums.get(i - 1) + 1) {
                lianXuShuLiang++;
                if (lianXuShuLiang == 5) return true;
            } else
                lianXuShuLiang = 1;
        }
        return false;
    }

    private static boolean isSanTiao(String[] cards) {
        HashMap<String, ArrayList<String>> numAndType = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (numAndType.containsKey(num)) {
                numAndType.get(num).add(type);
            } else {
                ArrayList<String> types = new ArrayList<>();
                types.add(type);
                numAndType.put(num, types);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : numAndType.entrySet()) {
            ArrayList<String> types = entry.getValue();
            if (types.size() == 3) return true;
        }
        return false;
    }

    private static boolean isLiangDui(String[] cards) {
        HashMap<String, ArrayList<String>> numAndType = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (numAndType.containsKey(num)) {
                numAndType.get(num).add(type);
            } else {
                ArrayList<String> types = new ArrayList<>();
                types.add(type);
                numAndType.put(num, types);
            }
        }

        int numDuiZi = 0;
        for (Map.Entry<String, ArrayList<String>> entry : numAndType.entrySet()) {
            ArrayList<String> types = entry.getValue();
            if (types.size() == 2) numDuiZi++;
        }
        return numDuiZi >= 2;
    }

    private static boolean isYiDui(String[] cards) {
        HashMap<String, ArrayList<String>> numAndType = new HashMap<>();
        for (String card : cards) {
            String type = card.substring(0, 1);
            String num = card.substring(1);
            if (numAndType.containsKey(num)) {
                numAndType.get(num).add(type);
            } else {
                ArrayList<String> types = new ArrayList<>();
                types.add(type);
                numAndType.put(num, types);
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : numAndType.entrySet()) {
            ArrayList<String> types = entry.getValue();
            if (types.size() == 2) return true;
        }
        return false;
    }
}
