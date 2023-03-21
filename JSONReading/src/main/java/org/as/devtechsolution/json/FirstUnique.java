package org.as.devtechsolution.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aditya Srivastva on 20-03-2023
 */
public class FirstUnique {
    public static void main(String[] args) {
        String s = "leetcode";
        int firstUniqueChar = findFirstUniqueChar(s);
        System.out.println("The first unique character in " + s + " is " + firstUniqueChar + s.charAt(firstUniqueChar));
    }

    public static int findFirstUniqueChar(String s) {
        // create a hashmap to store character frequencies
        Map<Character, Integer> charFreq = new HashMap<>();

        // iterate through the string and update the frequency count
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }

        // iterate through the string again and return the first character with frequency 1
        int nthPlace=4;
        int counter=0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charFreq.get(c) == 1 ) {
                if(counter==nthPlace){
                    return i;
                }else {
                    counter++;

                }

                //return i;
            }
        }

        // if no unique character is found, return null or throw an exception
        return -1;
    }

}
