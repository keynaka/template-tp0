package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private int maxLength = 10;
    private static char ANYCHAR = '.';
    private static char ESCAPE = '\\';
    private static char ZERO_SAMECHAR = '?';
    String oneMatch = "";
    Random randomNumber;
    int position;

    public RegExGenerator(int maxLength) {
        this.randomNumber = new Random();
        this.maxLength = maxLength;
        this.position = 0;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> matchedStrings = new ArrayList<String>();

        if (maxLength == 10) {
            System.out.println("todavia no lo use");
        }

        for (position = 0 ; position < regEx.length() ; position++) {
            this.analyzingDot(regEx);
            this.analyzingBackslash(regEx);
            this.analyzingZeroOrSameCharSymbol(regEx);
        }
        matchedStrings.add(oneMatch);

        return matchedStrings;
    }

    private void analyzingDot(String regEx) {
        if (regEx.charAt(position) == ANYCHAR) {
            int oneRandom = randomNumber.nextInt(256);
            oneMatch = oneMatch.concat(String.valueOf(Character.toChars(oneRandom)));
        }
    }

    private void analyzingBackslash(String regEx) {
        if (regEx.charAt(position) == ESCAPE) {
            position ++;
            oneMatch = oneMatch.concat(String.valueOf(regEx.charAt(position)));
        }
    }

    private void analyzingZeroOrSameCharSymbol(String regEx) {
        if (regEx.charAt(position) == ZERO_SAMECHAR) {
            int oneRandom = randomNumber.nextInt(2);
            if (oneRandom == 1){
                oneMatch = oneMatch.concat(String.valueOf(regEx.charAt(position-1)));
            }

        }
    }
}