package util.generate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static util.TestConstants.WRIKE_EMAIL_DOMAIN;

public abstract class StringGeenerator {
    enum Symbol {

        LETTERS_U("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        LETTERS_L(LETTERS_U.getValue().toLowerCase()),
        NUMBERS("0123456789"),
        SYMBOLS("_");

        private String value;

        public String getValue() { return this.value; }

        Symbol(String action) { this.value = action; }
    }

    private static final Random random = new Random();


    private static String generateString(int minSize, int maxSize, List<Symbol> acceptedSymbols){
        // Result string should contain some symbols
        if (minSize < 1 || maxSize < 1 || minSize > maxSize) {
            throw new  IllegalArgumentException();
        }
        // Get a list of available symbols for result string
        StringBuilder acceptedString = new StringBuilder();
        for (Symbol oneValue : acceptedSymbols){
            acceptedString.append(oneValue.value);
        }
        if (acceptedString.length() < 1) {
            throw new IllegalArgumentException();
        }
        // Find out length of result string
        int size = minSize + random.nextInt(maxSize - minSize);
        // Generate result string
        StringBuilder resultString = new StringBuilder();
        for (int symbolCounter = 0; symbolCounter < size; symbolCounter++){
            resultString.append(acceptedString.charAt(random.nextInt(acceptedString.length())));
        }
        return resultString.toString();
    }

    public static String generateEmail(){
        return generateString(5,12,
                Arrays.asList(Symbol.LETTERS_U, Symbol.LETTERS_L, Symbol.NUMBERS, Symbol.SYMBOLS)) + WRIKE_EMAIL_DOMAIN;
    }

    public static String generateRandomString() {
        return generateString(5,12,
                Arrays.asList(Symbol.LETTERS_U, Symbol.LETTERS_L));
    }
}
