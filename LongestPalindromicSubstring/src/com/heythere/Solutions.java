package com.heythere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solutions {
    public String longestPalindrome(String s) {
        List<CharOccurrence> charsInString=new ArrayList<>();
        HashMap<Character,Integer> charsAndOccurrence=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!charsAndOccurrence.containsKey(s.charAt(i))){
                charsAndOccurrence.put(s.charAt(i),1);
            }else{

            }
        }

        return "";
    }
}
