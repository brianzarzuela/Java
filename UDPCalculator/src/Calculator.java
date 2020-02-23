import java.util.StringTokenizer;

/**
 * <p>
 *     The Calculator class makes use of CalculatorInterface and OperationFunctionInterface
 *     to create a simple calculator using lambdas. It accepts two integers and an operator
 *     character as command line arguments.
 *     NOTE: Updated version for use with CalculatorUDP implementation
 * </p>
 * @author Brian Zarzuela
 * @version 2.0, April 2019
 * Error Codes:
 *  1 - Incorrect number of arguments
 *  2 - Invalid number
 *  3 - Invalid operation
 * Note that this version only handles integers.
 */

public class Calculator implements CalculatorInterface{
    private StringTokenizer args;


    Calculator(StringTokenizer st){
        this.args = st;
    }

    public int performOperation(OperationalFunctionalInterface ofi, int num1, int num2){
        return ofi.operation(num1, num2);
    }

    String executable() {
        String result;

        if (args.countTokens() != 3)
            result = CalculatorInterface.helpMessage(1);
        else {
            try {
                int num1 = Integer.parseInt(args.nextToken());
                String operand = args.nextToken();
                int num2 = Integer.parseInt(args.nextToken());

                switch(operand){
                    case "+":
                        result = Integer.toString(performOperation((a,b) -> (a+b), num1, num2));
                        break;
                    case "-":
                        result = Integer.toString(performOperation((a,b) -> (a-b), num1, num2));
                        break;
                    case "/":
                        result = Integer.toString(performOperation((a,b) -> (a/b), num1, num2));
                        break;
                    case "*":
                        result = Integer.toString(performOperation((a,b) -> (a*b), num1, num2));
                        break;
                    default: result = CalculatorInterface.helpMessage(3);
                }

            } catch (NumberFormatException e) {
                result = CalculatorInterface.helpMessage(2);
            }
        }

        return result;
    }
}
