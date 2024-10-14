// Cristina Correa - 1819211867

package com.cristina.correa.exercisesweek1;

public class BasicIntegerCalculations2 {

    public int number1;
    public int number2;

    public BasicIntegerCalculations2(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public void integerAddition() {
        int result = this.number1 + this.number2;
        System.out.println(this.number1 + " + " + this.number2 + " = " + result);
    }

    public void integerSubtraction() {
        int result = this.number1 - this.number2;
        System.out.println(this.number1 + " - " + this.number2 + " = " + result);
    }

    public void integerMultiplication() {
        int result = this.number1 * this.number2;
        System.out.println(this.number1 + " * " + this.number2 + " = " + result);
    }

    public void integerDivision() {
        float result = (float) this.number1 / this.number2;
        System.out.println(this.number1 + " / " + this.number2 + " = " + result);
    }

}
