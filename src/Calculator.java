import exception.ExpressionFormatException;
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
                break;
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

    private String getFirstNumberAsItEntered(String expression) {
        String firstNumberAsItEntered = expression.substring(0, indexOfOperator);
        return firstNumberAsItEntered;
    }

    private String getSecondNumberAsItEntered(String expression) {
        String secondNumberAsItEntered = expression.substring(indexOfOperator + 1);
        return secondNumberAsItEntered;
    }

    private String getFirstNumber(String expression) throws UnsupportedNumberException {
        String firstNumberAsString = getFirstNumberAsItEntered(expression);
        this.firstNumber = String.valueOf(getNumber(firstNumberAsString));
        return firstNumber;
    }

    private String getSecondNumber(String expression) throws UnsupportedNumberException {
        String secondNumberAsString = getSecondNumberAsItEntered(expression);
        this.secondNumber = String.valueOf(getNumber(secondNumberAsString));
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

    private String decimalToRoman(int number) {
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
        String firstNumberAsItEntered = getFirstNumberAsItEntered(this.expression);
        String secondNumberAsItEntered = getSecondNumberAsItEntered(this.expression);
        boolean firstEnteredNumberIsRoman = false;
        boolean secondEnteredNumberIsRoman = false;
        for (RomanNumbers rn : romanNumbers) {
            if (firstNumberAsItEntered.equals(rn.toString())) {
                firstEnteredNumberIsRoman = true;
                break;
            }
        }
        for (RomanNumbers rn : romanNumbers) {
            if (secondNumberAsItEntered.equals(rn.toString())) {
                secondEnteredNumberIsRoman = true;
                break;
            }
        }
        if (firstEnteredNumberIsRoman == true && secondEnteredNumberIsRoman == true) {
            result = decimalToRoman(calculatedResult);
            return result;
        }
        if ((firstEnteredNumberIsRoman == true && secondEnteredNumberIsRoman == false) || (firstEnteredNumberIsRoman == false && secondEnteredNumberIsRoman == true)) {
            throw new ExpressionFormatException("In one expression, entered numbers must be or roman, or arabic only.");
        }
        return result;
    }
}
