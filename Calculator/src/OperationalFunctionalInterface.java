interface OperationalFunctionalInterface {
    int operation(int a, int b);
}

interface CalculatorInterface {
    int performOperation(OperationalFunctionalInterface ofi, int num1, int num2);
    static void helpMessage(int exitCode)
    {
        System.out.println("Usage: <num> <operation> <num>");
        System.exit(exitCode);
    }
}