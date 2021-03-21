package jav;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Write text for unboxing.\nText must have a-z, numbers and [].\nExample: 3[xyz]4[xy]z");
        String inputStr = sc.nextLine();
        if (inputStr.contains("[") && inputStr.contains("]") || inputStr.matches("/^[0-9]{8}[A-Za-z]$/")){
            System.out.println(parseString(inputStr));
        } else {
            System.out.println("The string isn't valid!");
        }
    }
    public static String parseString(String str) {
        int num = 0;
        String result = "";
        String newStr;
        String repeatedStr;

        Pattern pattern = Pattern.compile("\\d\\[(.*?)]");
        Matcher matcher = pattern.matcher(str);
        ArrayList<String> buffArr = new ArrayList<>();

        while (matcher.find()) {
            String matchingStrInside = matcher.group(1);
            if (matchingStrInside.contains("[")) {
                String[] matchingStrInsideArr = matchingStrInside.split("");
                for (int k = 0; k < matchingStrInsideArr.length; k++) {
                    if (matchingStrInsideArr[k].equals("[")) {
                        num = Integer.parseInt(matchingStrInsideArr[k - 1]);
                        continue;
                    }

                    if (!matchingStrInsideArr[k].matches("\\d")) {
                        buffArr.add(matchingStrInsideArr[k]);
                        newStr = String.join("", buffArr);
                        newStr = newStr.repeat(num);
                        matchingStrInside = newStr;
                    }
                }
            }
            num = Integer.parseInt(String.valueOf(matcher.group().charAt(0)));
            repeatedStr = matchingStrInside.repeat(num);
            result = matcher.replaceFirst(repeatedStr);
            if (matcher.find()) {
                return parseString(result);
            }
        }
        return result;
    }
}
