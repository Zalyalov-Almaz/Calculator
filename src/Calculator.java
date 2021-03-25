import exception.UnsupportedNumberException;
import exception.UnsupportedOperatorException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private String expression;
    private String firstNumber;
    private String secondNumber;
    private String operator;
    private int indexOfOperator;
    private int decimalResult;

    public int calculate() throws RuntimeException {
        this.expression = getExpression();
        this.operator = getOperator(expression);
        this.indexOfOperator = expression.indexOf(operator);
        this.firstNumber = getFirstNumber(expression);
        this.secondNumber = getSecondNumber(expression);

        switch (operator) {
            case "+" : this.decimalResult = getNumber(this.firstNumber) + getNumber(this.secondNumber);
                break;
            case "-" : this.decimalResult = getNumber(this.firstNumber) - getNumber(this.secondNumber);
                break;
            case "*" : this.decimalResult = getNumber(this.firstNumber) * getNumber(this.secondNumber);
                break;
            case "/" : this.decimalResult = getNumber(this.firstNumber) / getNumber(this.secondNumber);
        }
        String finalAnswer = getAnswer(this.decimalResult);
        System.out.println(finalAnswer);
        return this.decimalResult;
    }

    private String getOperator(String expression) throws UnsupportedOperatorException, NullPointerException {
        if (expression.contains("+") || expression.contains("-") || expression.contains("*") || expression.contains("/")) {
            char[] expressionAsCharArray = expression.toCharArray();
            for (char searchingOperator : expressionAsCharArray) {
                if (searchingOperator == '+' || searchingOperator == '-' || searchingOperator == '*' || searchingOperator == '/') {
                    operator = String.valueOf(searchingOperator);
                    break;
                }
            }
        } else {
            throw new UnsupportedOperatorException();
        }
        return operator;
    }

    private String getFirstNumber(String expression) throws UnsupportedNumberException {
        String firstNumberAsString = expression.substring(0, indexOfOperator);
        firstNumber = String.valueOf(getNumber(firstNumberAsString));
        return firstNumber;
    }

    private String getSecondNumber(String expression) throws UnsupportedNumberException {
        String secondNumberAsString = expression.substring(indexOfOperator + 1);
        secondNumber = String.valueOf(getNumber(secondNumberAsString));
        return secondNumber;
    }

    private String getExpression() {
        Scanner in = new Scanner(System.in);
        this.expression = in.nextLine();
        in.close();
        return this.expression;
    }

    private int getNumber(String numberAsString) {
        int number = 0;
        try {
            number = Integer.parseInt(numberAsString);
            if (number < 1 || number > 10) {
                throw new UnsupportedNumberException();
            }
            return number;
        } catch (NumberFormatException e) {
            RomanNumbers [] romanNumbers = RomanNumbers.values();
            for (RomanNumbers rn : romanNumbers) {
                if (rn.toString().equals(numberAsString)) {
                    number = rn.getDecimalOfNumber();
                    if (number < 1 || number > 10) {
                        throw new UnsupportedNumberException();
                    }
                    return number;
                }
            }
        }
        return number;
    }

    public String decimalToRoman(int number) {
        String romanResult = "";
        RomanNumbers [] romanNumbers = RomanNumbers.values();
        for (RomanNumbers romanNumber : romanNumbers) {
            if (romanNumber.getDecimalOfNumber() == number) {
                romanResult = romanNumber.toString();
                break;
            }
        }
        if (romanResult.equals("")) {
            throw new UnsupportedNumberException();
        }
        return romanResult;
    }

    public String getAnswer(int calculatedResult) {
        RomanNumbers [] romanNumbers = RomanNumbers.values();
        String result = String.valueOf(calculatedResult);
        boolean firstNumberIsRoman = false;
        boolean secondNumberIsRoman = false;
        for (RomanNumbers rn : romanNumbers) {
            if (this.firstNumber.equals(rn.toString())) {
                firstNumberIsRoman = true;
                break;
            }
        }
        for (RomanNumbers rn : romanNumbers) {
            if (this.secondNumber.equals(rn.toString())) {
                secondNumberIsRoman = true;
                break;
            }
        }
        if (firstNumberIsRoman == true && secondNumberIsRoman == true) {
            result = decimalToRoman(calculatedResult);
        }
        return result;
    }
}
