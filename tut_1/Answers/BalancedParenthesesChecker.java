package tut_1.Answers;

import java.util.Stack;

public class BalancedParenthesesChecker {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expression = "((a + b) * c)) - d";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
        expression = "((a + b) * c) - d";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
    }
}
