/**
 * <p>
 *     Interface for the Calculator class and Functional Interface for the operation function
 *     used within the Calculator class through the use of lambdas.
 *     NOTE: Updated version for use with CalculatorUDP implementation
 * </p>
 * @author Brian Zarzuela
 * @version 2.0, 10 April 2019
 */

interface OperationalFunctionalInterface {
    int operation(int a, int b);
}

interface CalculatorInterface {
    int performOperation(OperationalFunctionalInterface ofi, int num1, int num2);
    static String helpMessage(int errorCode)
    {
        String message = "";
        switch(errorCode){
            case 1: message = ("Error Code (" + errorCode + ") : Incorrect number of arguments"); break;
            case 2: message = ("Error Code (" + errorCode + ") : Number format exception, invalid number"); break;
            case 3: message = ("Error Code (" + errorCode + ") : Invalid operation"); break;
            default: break;
        }
        return message;
    }
}