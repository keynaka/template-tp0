package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private int maxLength = 10;
    private static int DOT = '.';


    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {

        Random randomNumber = new Random();
        ArrayList<String> matchedStrings = new ArrayList<String>();

        if (maxLength == 10) {
            System.out.println("todavia no lo use");
        }

        String oneMatch = "";
        for (int i = 0 ; i < regEx.length() ; i++) {
            if (regEx.charAt(i) == DOT) {
                int oneRandom = randomNumber.nextInt(256);
                oneMatch = oneMatch.concat(String.valueOf(Character.toChars(oneRandom)));
            }
        }
        matchedStrings.add(oneMatch);

        return matchedStrings;
    }

/*        return new ArrayList<String>() {
           {
                add("a");
                add("b");
                add("c");
            }
        };
    }*/
}