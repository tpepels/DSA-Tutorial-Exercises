package tut_1.Answers;

import java.util.Stack;

public class BalancedParenthesesChecker {

    // A simple algorithm to check if an expression has balanced parentheses
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            // Check if the character is an opening bracket
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Check if the character is a closing bracket
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing bracket
                }
                char topElement = stack.pop(); // Get the most recent opening bracket

                // Check if the popped opening bracket matches the current closing bracket
                if ((ch == ')' && topElement != '(') ||
                        (ch == ']' && topElement != '[') ||
                        (ch == '}' && topElement != '{')) {
                    return false; // Mismatched bracket types
                }
            }
        }

        // Check if any unmatched opening bracket remains
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // TODO Test your implementation
        String expression = "{(([a + b]) * c) - d}";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
        expression = "([(a + b) * c)} - d";
        System.out.println("Is the expression balanced? " + isBalanced(expression));
    }
}
