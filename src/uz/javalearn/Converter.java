package uz.javalearn;


import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Converts between integers and a string of roman numerals.
 *
 * @author Andy Oliver
 * @see <a href="http://frequal.com/RomanNumerals/">RomanNumerals</a>
 */
public class Converter {

    private static final List<Integer> listValues = Arrays.asList(1000, 500, 100, 50, 10, 5, 1);
    private static final List<Character> listNumerals = Arrays.asList('M', 'D', 'C', 'L', 'X', 'V', 'I');
    private final boolean bStrictMode;

    public Converter() {
        bStrictMode = false;
    }

    /**
     * Strict mode forces rejection of illegal Roman numeral sequences.
     */
    public Converter(boolean bStrictMode) {
        this.bStrictMode = bStrictMode;
    }

    /**
     * Convert Roman numerals to an integer.
     *
     * @param strRomanNumerals The Roman numerals in string format.
     * @return The integer version of the Roman numerals.
     * @throws ParseException If the Roman numerals are in illegal form according to the mode.
     */
    public int toNumber(String strRomanNumerals) throws ParseException {
        int total = 0;

        for (int i = 0; i < strRomanNumerals.length(); i++) {
            int currentNumeralIndex = listNumerals.indexOf(strRomanNumerals.charAt(i));

            if (currentNumeralIndex == -1) {
                throw new ParseException("Illegal character: " + strRomanNumerals.charAt(i), i);
            }

            boolean bAddNumeral = true;

            if (i < strRomanNumerals.length() - 1) {
                int nextNumeralIndex = listNumerals.indexOf(strRomanNumerals.charAt(i + 1));
                boolean bNextNumeralLarger = nextNumeralIndex < currentNumeralIndex;
                bAddNumeral = !bNextNumeralLarger;

                if (bStrictMode && bNextNumeralLarger) {
                    if ((currentNumeralIndex - nextNumeralIndex) > 2) {
                        throw new ParseException("Cannot increase by more than one numeral at a time in strict mode", i);
                    }

                    if ((currentNumeralIndex % 2) != 0) {
                        throw new ParseException("Cannot subtract V, L, D, or other 5-based numerals in strict mode", i);
                    }
                }

                if (bNextNumeralLarger && (i < strRomanNumerals.length() - 2)) {
                    int nextNextNumeralIndex = listNumerals.indexOf(strRomanNumerals.charAt(i + 2));
                    boolean bNextNextNumeralLarger = nextNextNumeralIndex < nextNumeralIndex;
                    // Throw exception if in strict mode
                    if (bNextNextNumeralLarger) {
                        throw new ParseException("Cannot have two increasing numerals interface a row", i);
                    }
                }
            }

            if (bAddNumeral) {
                total += listValues.get(listNumerals.indexOf(strRomanNumerals.charAt(i)));
            } else {
                total -= listValues.get(listNumerals.indexOf(strRomanNumerals.charAt(i)));
            }
        }

        return total;
    }
}
