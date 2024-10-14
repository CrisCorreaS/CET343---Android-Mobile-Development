// Cristina Correa - 1819211867

package com.cristina.correa.exercisesweek1;

import java.util.Scanner;

public class ExercisesWeek1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean stopCalculations = false;

        System.out.println("Please, enter the first number");
        int number1 = scanner.nextInt();

        System.out.println("Please, enter the second number");
        int number2 = scanner.nextInt();

        BasicIntegerCalculations1 basicIntegerCalculations1 = new BasicIntegerCalculations1();
        BasicIntegerCalculations2 basicIntegerCalculations2 = new BasicIntegerCalculations2(number1, number2);

        int counter = 0;

        do {
            System.out.println("Please choose your calculations. You have 4 tries");
            System.out.println("0 -> Finish");
            System.out.println("1 -> Addition");
            System.out.println("2 -> Subtraction");
            System.out.println("3 -> Multiplication");
            System.out.println("4 -> Division");
            int calculation = scanner.nextInt();

            if (counter == 0) {
                switch (calculation) {
                    case 0:
                        stopCalculations = true;
                        break;
                    case 1:
                        basicIntegerCalculations2.integerAddition();
                        break;
                    case 2:
                        basicIntegerCalculations2.integerSubtraction();
                        break;
                    case 3:
                        basicIntegerCalculations2.integerMultiplication();
                        break;
                    case 4:
                        basicIntegerCalculations2.integerDivision();
                        break;
                    default:
                        System.out.println("You didn't write a valid number");
                        break;
                }
            } else {
                switch (calculation) {
                    case 0:
                        stopCalculations = true;
                        break;
                    case 1:
                        int result1 = basicIntegerCalculations1.integerAddition(number1, number2);
                        System.out.println(number1 + " + " + number2 + " = " + result1);
                        break;
                    case 2:
                        int result2 = basicIntegerCalculations1.integerSubtraction(number1, number2);
                        System.out.println(number1 + " - " + number2 + " = " + result2);
                        break;
                    case 3:
                        int result3 = basicIntegerCalculations1.integerMultiplication(number1, number2);
                        System.out.println(number1 + " * " + number2 + " = " + result3);
                        break;
                    case 4:
                        float result4 = basicIntegerCalculations1.integerDivision(number1, number2);
                        System.out.println(number1 + " / " + number2 + " = " + result4);
                        break;
                    default:
                        System.out.println("You didn't write a valid number");
                        break;
                }
            }

            counter++;
            
            if(counter == 4){
                stopCalculations = true;
            }

        } while (!stopCalculations);

        System.out.println("You finished all your basic calculations with " + number1 + " and " + number2);
        scanner.close();
    }
}
