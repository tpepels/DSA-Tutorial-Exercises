package tut_1;

// TODO Choose the most appropriate data structure for the task and import it
// import java.util.Stack;
// import java.util.Queue;

public class BalancedParenthesesChecker {

    public static boolean isBalanced(String expression) {
        // TODO Implement this
    }

    public static void main(String[] args) {
        // TODO Test your implementation
        String expression = "{(([a + b]) * c) - d}";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
        expression = "([(a + b) * c)} - d";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
    }
}
