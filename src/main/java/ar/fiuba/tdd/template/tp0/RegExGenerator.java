package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private static char ANYCHAR = '.';
    private static char ESCAPE = '\\';
    private static char ZERO_ONECHAR = '?';
    private static char ONE_LOTSCHAR = '+';
    private static char ZERO_LOTSCHAR = '*';
    private static char CHARSETOPEN = '[';
    private static char CHARSETCLOSE = ']';
    private int maxLength;
    private int maxRepetition = 10;
    private String oneMatch = "";
    private String allAscii = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random randomNumber;
    private int position;
    private String charsIncluded = "";
    private boolean isSet;
    private boolean usedQuantifier;
    private boolean lastPosition;

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
            charsIncluded = "";
            this.analyzingEscape(regEx);
            this.analyzingAnyCharSymbol(regEx);
            this.writing(regEx);
        }
        matchedStrings.add(oneMatch);

        return matchedStrings;
    }

    private void analyzingEscape(String regEx) {
        if (regEx.charAt(position) == ESCAPE) {
            position ++;
        }
    }

    private void analyzingAnyCharSymbol(String regEx) {
        if (regEx.charAt(position) == ANYCHAR) {
            isSet = true;
            charsIncluded = allAscii;
        } else {
            this.analyzingCharacterSetOrCharacter(regEx);
        }
    }

    private void analyzingCharacterSetOrCharacter(String regEx) {
        isSet = false;
        if (regEx.charAt(position) == CHARSETOPEN) {
            isSet = true;
            position ++;
            while (regEx.charAt(position) != CHARSETCLOSE) {
                charsIncluded = charsIncluded.concat(String.valueOf(regEx.charAt(position)));
                position ++;
            }
        } else {
            charsIncluded = charsIncluded.concat(String.valueOf(regEx.charAt(position)));
        }
    }

    private void quantifierZeroOrSameCharSymbol(String regEx) {
        if (! lastPosition) {
            if (regEx.charAt(position + 1) == ZERO_ONECHAR) {
                usedQuantifier = true;
                int oneRandom = randomNumber.nextInt(2);
                if (oneRandom == 1) {
                    this.addOneChar();
                }
            }
        }
    }

    private void quantifierOneLotsCharsOrZeroLotsCharsSymbols(String regEx) {
        if (! lastPosition) {
            if ((regEx.charAt(position + 1) == ONE_LOTSCHAR) || (regEx.charAt(position + 1) == ZERO_LOTSCHAR)) {
                usedQuantifier = true;
                int numberOfRepetitions = randomNumber.nextInt(maxRepetition);
                if (regEx.charAt(position + 1) == ONE_LOTSCHAR) {
                    numberOfRepetitions ++;
                }
                for (int repetition = 0 ; repetition < numberOfRepetitions ; repetition ++) {
                    this.addOneChar();
                }
            }
        }
    }

    private void writing(String regEx) {
        usedQuantifier = false;
        lastPosition = (position == (regEx.length() - 1));
        this.quantifierZeroOrSameCharSymbol(regEx);
        this.quantifierOneLotsCharsOrZeroLotsCharsSymbols(regEx);

        if (usedQuantifier) {
            position ++;
        } else {
            this.addOneChar();
        }
    }

    private void addOneChar() {
        if (! isSet) {
            oneMatch = oneMatch.concat(charsIncluded);
        } else {
            int oneRandom = randomNumber.nextInt(charsIncluded.length());
            oneMatch = oneMatch.concat(String.valueOf(charsIncluded.charAt(oneRandom)));
        }
    }
}