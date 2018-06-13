package com.juphoon.jrsdk;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JRNumberTool {

    public static String getDialableNumber(String number) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (; i < number.length(); ++i) {
            char c = number.charAt(i);
            if (contactsIsDialable(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String numberWithoutChineseCountryCode(String number) {
        String temp = getDialableNumber(number);
        if (temp.isEmpty()) {
            return null;
        }
        if (validatePhoneNum(temp) && temp.startsWith("+86")) {
            return temp.substring(3);
        }
        return temp;
    }

    public static String numberWithChineseCountryCode(String number) {
        String temp = getDialableNumber(number);
        if (temp.isEmpty()) {
            return null;
        }
        if (validatePhoneNum(temp) && !temp.startsWith("+86")) {
            return "+86" + temp;
        }
        return temp;
    }

    public static boolean isNumberEqual(String firstNumber, String secondNumber) {
        if (getDialableNumber(firstNumber).length() == 0 || getDialableNumber(secondNumber).length() == 0) {
            return false;
        }
        if (numberWithoutChineseCountryCode(firstNumber).equalsIgnoreCase(numberWithoutChineseCountryCode(secondNumber))) {
            return true;
        }
        return false;
    }

    private static boolean validatePhoneNum(String number) {
        String phonePattern = "^(((13\\d{1})|(14[57])|(15([0-3]|[5-9]))|(17[6-8])|(18\\d{1}))\\d{8}|170[0,5,9]\\d{7})$";
        Pattern p = Pattern.compile(phonePattern);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    private static boolean contactsIsDialable(char c) {
        return c == '+' || c == '*' || c == '#' || c == ',' || (c >= '0' && c <= '9');
    }
}
