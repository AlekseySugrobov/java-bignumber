package ru.asugrobov;

public class BigNumber {
    private int[] digits;
    private int[] digitsToAdd;
    private boolean isGreaterThanTen = false;
    private int digitsLeft;
    private int digitsToAddLeft;
    private String result = "";

    public BigNumber(String stringNumber){
        int stringLength = stringNumber.length();
        digits = new int[stringLength];
        for(char digit:stringNumber.toCharArray()){
            digits[--stringLength] = Character.getNumericValue(digit);
        }
    }

    public int[] getDigits() {
        return digits;
    }

    public BigNumber Add(BigNumber bigNumberToAdd) {
        digitsToAdd = bigNumberToAdd.digits;
        digitsLeft = digits.length;
        digitsToAddLeft = digitsToAdd.length;
        for(int index = 0; index < digitsToAdd.length; index++){
            if(index < digits.length) {
                result = Sum(digits[index], digitsToAdd[index]) + result;
                digitsLeft--;
                digitsToAddLeft--;
            }
        }
        result = addLeftDigits();
        return new BigNumber(result.toString());
    }

    private char Sum(int a, int b) {
        int sum = a + b;
        return Sum(sum);
    }

    private char Sum(int a){
        if (isGreaterThanTen)
            a += 1;
        if (a > 9) {
            isGreaterThanTen = true;
        } else {
            isGreaterThanTen = false;
        }
        String sumString = String.valueOf(a);
        return sumString.charAt(sumString.length() - 1);
    }

    private String addLeftDigits() {
        if(digitsLeft != 0){
            loopThrowLeftDigits(digits, digitsLeft);
            addOneToOlderDigit();
        } else if(digitsToAddLeft != 0){
            loopThrowLeftDigits(digitsToAdd, digitsToAddLeft);
            addOneToOlderDigit();
        } else {
            addOneToOlderDigit();
        }
        return result;
    }

    private void addOneToOlderDigit() {
        if(isGreaterThanTen){
            result = "1" + result;
        }
    }

    private void loopThrowLeftDigits(int[] arrayOfLeftDigits, int leftDigitsCount){
        for(int index = arrayOfLeftDigits.length - leftDigitsCount; index < arrayOfLeftDigits.length; index++){
            result = Sum(arrayOfLeftDigits[index]) + result;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(int index = digits.length - 1; index >= 0; index--){
            result += digits[index];
        }
        return result;
    }
}
