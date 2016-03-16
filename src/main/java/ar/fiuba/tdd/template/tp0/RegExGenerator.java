package ar.fiuba.tdd.template.tp0;

import java.util.*;

public class RegExGenerator {

    private int maxLength = 10;
    private static char ANYCHAR = '.';
    private static char ESCAPE = '\\';
    //private static char ZERO_ONECHAR = '?';
    //private static char ONE_LOTSCHAR = '+';
    //private static char ZERO_LOTSCHAR = '*';
    //private static char CHARSETOPEN = '[';
    //private static char CHARSETCLOSE = ']';

    String oneMatch = "";
    String allAscii = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random randomNumber;
    int position;
    //ArrayList<Character> quantifiers;
    String charsIncluded = "";

    public RegExGenerator(int maxLength) {
        this.randomNumber = new Random();
        this.maxLength = maxLength;
        this.position = 0;
        //this.quantifiers = new ArrayList<Character>();
        //this.quantifiers.add(ZERO_ONECHAR);
        //this.quantifiers.add(ZERO_LOTSCHAR);
        //this.quantifiers.add(ONE_LOTSCHAR);
    }

    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> matchedStrings = new ArrayList<String>();

        if (maxLength == 10) {
            System.out.println("todavia no lo use");
        }

        for (position = 0 ; position < regEx.length() ; position++) {
            this.analyzingEscape(regEx);
            //this.analyzingAnyCharSymbol(regEx);
            //this.analyzingZeroOrSameCharSymbol(regEx);
            //this.analyzingCharacterSetOrCharacter(regEx);
        }
        matchedStrings.add(oneMatch);

        return matchedStrings;
    }

    private void analyzingAnyCharSymbol(String regEx) {
        if (regEx.charAt(position) == ANYCHAR) {
            Character oneRandomChar = allAscii.charAt(randomNumber.nextInt(allAscii.length()));
            charsIncluded = charsIncluded.concat(String.valueOf(oneRandomChar)); //ultimo q hice
        }else{
            charsIncluded = charsIncluded.concat(String.valueOf(regEx.charAt(position)));
        }
    }

    private void analyzingEscape(String regEx) {
        charsIncluded = "";
        if (regEx.charAt(position) == ESCAPE) {
            position ++;
        }
        this.analyzingAnyCharSymbol(regEx);
        oneMatch = oneMatch.concat(charsIncluded);
    }
/*
    private void analyzingZeroOrSameCharSymbol(String regEx) {
        if (regEx.charAt(position) == ZERO_ONECHAR) {
            int oneRandom = randomNumber.nextInt(2);
            if (oneRandom == 1){
                oneMatch = oneMatch.concat(String.valueOf(regEx.charAt(position-1)));
            }

        }
    }

    private void analyzingCharacterSetOrCharacter(String regEx) {
        if (regEx.charAt(position) == CHARSETOPEN) {
            position ++;
            while (regEx.charAt(position) != CHARSETCLOSE) {
                charsIncluded.add(String.valueOf(regEx.charAt(position)));
                position ++;
            }
        }else {
            //caso en el que es un caracter solo
            charsIncluded.add(String.valueOf(regEx.charAt(position)));
        }
        if (!charsIncluded.isEmpty()) {
            this.processCharacterSetOrLiteral(regEx, charsIncluded);
        }

    }

    private void processCharacterSetOrLiteral(String regEx, ArrayList<String> charsIncluded) {
        String oneRandomChar = charsIncluded.get(randomNumber.nextInt(charsIncluded.size()));
        oneMatch = oneMatch.concat(oneRandomChar);
        //en las 2 lineas de arriba, ya se hizo en el caso que no es un cuantificador lo q le sigue
        //entonces si le sigue un quantificador, dependiendo cual, hay q repetirlo, sino ya no hace nada mas

        if (this.quantifiers.contains(regEx.charAt(position + 1))) {
            //hay q hacer cada quantificador
        }
        position ++;
    }*/
}