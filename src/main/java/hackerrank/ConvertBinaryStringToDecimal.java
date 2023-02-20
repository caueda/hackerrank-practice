package hackerrank;

public class ConvertBinaryStringToDecimal {
    public static int convert(String binary) {
        int decimal = 0;
        int base = 1;

        for(int i=binary.length()-1; i>=0; i--) {
            if(binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2;
        }

        return decimal;
    }
}
