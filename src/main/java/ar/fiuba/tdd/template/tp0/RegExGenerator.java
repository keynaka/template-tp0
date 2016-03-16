package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private int maxLength = 10;
    private static char DOT = '.';
    private static char BACKSLASH = '\\';
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
        }
        matchedStrings.add(oneMatch);

        return matchedStrings;
    }

    private void analyzingDot(String regEx) {
        if (regEx.charAt(position) == DOT) {
            int oneRandom = randomNumber.nextInt(256);
            oneMatch = oneMatch.concat(String.valueOf(Character.toChars(oneRandom)));
        }
    }

    private void analyzingBackslash(String regEx) {
        if (regEx.charAt(position) == BACKSLASH) {
            oneMatch = oneMatch.concat(String.valueOf(regEx.charAt(position + 1)));
            position ++;
        }
    }
}