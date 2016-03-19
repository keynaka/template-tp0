package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {

    private boolean validate(String regEx, int numberOfResults) {
        int maxLength = 10;
        RegExGenerator generator = new RegExGenerator(maxLength);
        // TODO: Uncomment parameters
        List<String> results = generator.generate(regEx, numberOfResults);
        // force matching the beginning and the end of the strings
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        return results
                .stream()
                .reduce(true,
                        (acc, item) -> {
                            Matcher matcher = pattern.matcher(item);
                            return acc && matcher.find();
                        },
                        (item1, item2) -> item1 && item2);
    }

    @Test
    public void testAnyCharacter() {
        assertTrue(validate(".", 1));
    }

    @Test
    public void testMultipleCharacters() {
        assertTrue(validate("...", 1));
    }

    @Test
    public void testLiteral() {
        assertTrue(validate("\\@", 1));
    }

    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 1));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 1));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 1));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 1));
    }

    @Test
    public void testCharacterSetWithQuantifierOneOrMoreChars() {
        assertTrue(validate("[abc]*", 1));
    }

    @Test
    public void testEscapedDot() {
        assertTrue(validate("\\.", 1));
    }

    @Test
    public void testJustLiterals() {
        assertTrue(validate("helloworld", 1));
    }

    @Test
    public void testDotPlus() {
        assertTrue(validate(".+", 1));
    }

    @Test
    public void testDotAsterisk() {
        assertTrue(validate(".*", 1));
    }

    @Test
    public void testLiteralSetInterrogation() {
        assertTrue(validate("hello[abc]?", 1));
    }

    @Test
    public void testLiteralPlus() {
        assertTrue(validate("k+", 1));
    }

    @Test
    public void testLiteralAsterisk() {
        assertTrue(validate("k*", 1));
    }

    @Test
    public void testEscapedSquareBracket() {
        assertTrue(validate("\\[", 1));
    }

    @Test
    public void testEscapedAsterisk() {
        assertTrue(validate("\\*", 1));
    }

    @Test
    public void testEscapedPlus() {
        assertTrue(validate("\\+", 1));
    }

    @Test
    public void testEscapedInterrogation() {
        assertTrue(validate("\\?", 1));
    }

    @Test
    public void testAll() {
        assertTrue(validate("\\*..+a?[xyz]*good", 1));
    }

    @Test
    public void testMultipleCharactersFifteenMatches() {
        assertTrue(validate("...", 15));
    }

    @Test
    public void testAnyCharacterFiveMatches() {
        assertTrue(validate(".", 5));
    }

    @Test
    public void testAnyCharacterTwentyMatches() {
        assertTrue(validate(".", 20));
    }

    @Test
    public void testZeroOrOneCharacterFiveMatches() {
        assertTrue(validate("\\@.h?", 5));
    }

    @Test
    public void testCharacterSetThreeMatches() {
        assertTrue(validate("[abc]", 3));
    }

    @Test
    public void testLiteralAsteriskTenMatches() {
        assertTrue(validate("k*", 10));
    }

    @Test
    public void testLiteralPlusTenMatches() {
        assertTrue(validate("k+", 10));
    }

    @Test
    public void testDotAsterisk20Matches() {
        assertTrue(validate(".*", 20));
    }

    @Test
    public void testLiteralSetInterrogationFourMatches() {
        assertTrue(validate("hello[abc]?", 4));
    }

    @Test
    public void testAllTenMatches() {
        assertTrue(validate("\\*..+a?[xyz]*good", 10));
    }
}
