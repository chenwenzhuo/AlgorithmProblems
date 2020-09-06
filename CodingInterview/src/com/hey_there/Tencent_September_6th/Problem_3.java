package com.hey_there.Tencent_September_6th;

import java.util.*;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);

        //map存储字符串及其出现次数
        HashMap<String, Integer> map = new HashMap<>();
        //输入字符串并统计出现次数
        for (int i = 0; i < N; i++) {
            String curLine = sc.nextLine();
            map.put(curLine, map.getOrDefault(curLine, 0) + 1);
        }

        ArrayList<String> mostKStrs = new ArrayList<>();
        ArrayList<String> leastKStrs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = map.get(key);

            if (mostKStrs.size() == 0) {
                mostKStrs.add(key);
            } else if (mostKStrs.size() < K) {
                int initSize = mostKStrs.size();
                for (int i = 0; i < mostKStrs.size(); i++) {
                    if (value < map.get(mostKStrs.get(i))) {
                        mostKStrs.add(i, key);
                        break;
                    } else if (value == map.get(mostKStrs.get(i))) {
                        int idx = i;
                        while (value == map.get(mostKStrs.get(idx))) {
                            if (key.compareTo(mostKStrs.get(idx)) < 0) {
                                mostKStrs.add(idx, key);
                            }
                            idx++;
                        }
                        break;
                    }
                }
                if (mostKStrs.size() == initSize) mostKStrs.add(key);
            } else if (mostKStrs.size() == K) {
                int initSize = mostKStrs.size();
                for (int i = 0; i < mostKStrs.size(); i++) {
                    if (value < map.get(mostKStrs.get(i))) {
                        mostKStrs.add(i, key);
                        break;
                    } else if (value == map.get(mostKStrs.get(i))) {
                        int idx = i;
                        while (value == map.get(mostKStrs.get(idx))) {
                            if (key.compareTo(mostKStrs.get(idx)) < 0) {
                                mostKStrs.add(idx, key);
                            }
                            idx++;
                        }
                        break;
                    }
                }
                if (mostKStrs.size() == initSize) mostKStrs.add(key);
                mostKStrs.remove(0);
            }

            if (leastKStrs.size() == 0) {
                leastKStrs.add(key);
            } else if (leastKStrs.size() < K) {
                int initSize = leastKStrs.size();
                for (int i = 0; i < leastKStrs.size(); i++) {
                    if (value < map.get(leastKStrs.get(i))) {
                        leastKStrs.add(i, key);
                        break;
                    } else if (value == map.get(leastKStrs.get(i))) {
                        int idx = i;
                        while (value == map.get(leastKStrs.get(idx))) {
                            if (key.compareTo(leastKStrs.get(idx)) < 0) {
                                leastKStrs.add(idx, key);
                            }
                            idx++;
                        }
                        break;
                    }
                }
                if (leastKStrs.size() == initSize) leastKStrs.add(key);
            } else if (leastKStrs.size() == K) {
                int initSize = leastKStrs.size();
                for (int i = 0; i < leastKStrs.size(); i++) {
                    if (value < map.get(leastKStrs.get(i))) {
                        leastKStrs.add(i, key);
                        break;
                    } else if (value == map.get(leastKStrs.get(i))) {
                        int idx = i;
                        while (value == map.get(leastKStrs.get(idx))) {
                            if (key.compareTo(leastKStrs.get(idx)) < 0) {
                                leastKStrs.add(idx, key);
                            }
                            idx++;
                        }
                        break;
                    }
                }
                if (leastKStrs.size() == initSize) leastKStrs.add(key);
                leastKStrs.remove(leastKStrs.size() - 1);
            }
        }
        for (int i = mostKStrs.size() - 1; i >= 0; i--) {
            System.out.println(mostKStrs.get(i) + " " + map.get(mostKStrs.get(i)));
        }
        for (int i = 0; i < leastKStrs.size(); i++) {
            System.out.println(leastKStrs.get(i) + " " + map.get(leastKStrs.get(i)));
        }
    }
}
