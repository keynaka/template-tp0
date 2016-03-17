package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private int maxLength = 10;
    private static char ANYCHAR = '.';
    private static char ESCAPE = '\\';
    private static char ZERO_ONECHAR = '?';
    //private static char ONE_LOTSCHAR = '+';
    //private static char ZERO_LOTSCHAR = '*';
    private static char CHARSETOPEN = '[';
    private static char CHARSETCLOSE = ']';

    private String oneMatch = "";
    private String allAscii = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random randomNumber;
    private int position;
    private String charsIncluded = "";
    private boolean isSet;
    private boolean usedQuantifier;

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
                charsIncluded = String.valueOf(charsIncluded.charAt(0)); //probando
                position ++;
            }
        } else {
            //caso en el que es un caracter solo
            charsIncluded = charsIncluded.concat(String.valueOf(regEx.charAt(position)));
        }
    }

    private void quantifierZeroOrSameCharSymbol(String regEx) {
        if (regEx.charAt(position + 1) == ZERO_ONECHAR) {
            position ++;
            usedQuantifier = true;
            int oneRandom = randomNumber.nextInt(2);
            if (oneRandom == 1) {
                //oneMatch = oneMatch.concat(String.valueOf(regEx.charAt(position-1)));
                this.addOneChar();
            }
        }
    }

    private void addOneChar() {
        if (! isSet) {
            oneMatch = oneMatch.concat(charsIncluded); //ultimo que hice, intentando agregar al match, el caracter.
        } else {
            int oneRandom = randomNumber.nextInt(charsIncluded.length());
            oneMatch = oneMatch.concat(String.valueOf(charsIncluded.charAt(oneRandom)));
        }
    }

    private void writing(String regEx) {
        usedQuantifier = false;
        //aca van todos los cuantificadores uno abajo del otro
        this.quantifierZeroOrSameCharSymbol(regEx);

        if (! usedQuantifier) {
            this.addOneChar();
        }
    }

}//nota antes de empezar, esta todo en estado OK el build, hay q ver como hacer con el tema de los quantificadores
//en que momento escribir las cosas, o sea en q momento hacer el concat al oneMatch