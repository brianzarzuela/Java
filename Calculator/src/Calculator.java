/**
 * <p>
 *     The Calculator class makes use of CalculatorInterface and OperationFunctionInterface
 *     to create a simple calculator using lambdas. It accepts two integers and an operator
 *     character as command line arguments
 * </p>
 * @author Brian Zarzuela
 * @version 1.0, 26 March 2019
 * Exit Codes:
 *  1 - Incorrect number of arguments
 *  2 - Invalid number
 *  3 - Invalid operation
 * Note that this version only handles integers.
 */
public class Calculator implements CalculatorInterface{

    public int performOperation(OperationalFunctionalInterface ofi, int num1, int num2){
        return ofi.operation(num1, num2);
    }

    public static void main(String args[]) {
        if (args.length != 3)
            CalculatorInterface.helpMessage(1);
        try {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[2]);

            switch(args[1]){
                case "+":
                    System.out.println(new Calculator().performOperation((a,b) -> (a+b), num1, num2));
                    break;
                case "-":
                    System.out.println(new Calculator().performOperation((a,b) -> (a-b), num1, num2));
                    break;
                case "/":
                    System.out.println(new Calculator().performOperation((a,b) -> (a/b), num1, num2));
                    break;
                case "*":
                    System.out.println(new Calculator().performOperation((a,b) -> (a*b), num1, num2));
                    break;
                default: CalculatorInterface.helpMessage(3);
            }

        } catch (NumberFormatException e) {
            CalculatorInterface.helpMessage(2);
        }
    }
}
