package org.as.devtechsolution.json;

public class ReverseWordsInString {
    public static void main(String[] args) {
        String str = "a good  example";
        String[] words = str.trim().split(" ");
        String reversedString = "";
        for (int i = words.length - 1; i >= 0; i--) {
            reversedString += words[i];// + " ";
            if (i != 0) {
                //output.append(" ");
                reversedString+= " ";
            }
        }
        System.out.println("Original string: " + str);
        System.out.println("Reversed string: " + reversedString.trim());


        System.out.println("***************************");
        //String input = "   Hello  world!   ";
        String input= str;
        String[] wordss = input.trim().split("\\s+"); // Split the string by one or more whitespace characters
        StringBuilder output = new StringBuilder();

        for (int i = wordss.length - 1; i >= 0; i--) {
            output.append(wordss[i]);
            if (i != 0) {
                output.append(" ");
            }
        }

        System.out.println(output.toString()); // Prints "world! Hello"
    }
}