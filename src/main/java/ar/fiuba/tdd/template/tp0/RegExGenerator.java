package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private int maxLength;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {

        if (maxLength == 10) System.out.println("Hola mundo");

        return new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
            }
        };
    }
}