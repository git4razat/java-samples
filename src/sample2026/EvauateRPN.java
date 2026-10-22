package sample2026;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvauateRPN {

    public int evaluate(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token: tokens) {

            if (token.length() > 1 || Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = 0;
                switch (token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        EvauateRPN eval = new EvauateRPN();
        int result = eval.evaluate(new String[] {"2","1","+","3","*"});
        System.out.println("result:: "+ result);

        result = eval.evaluate(new String[] {"4","13","5","/","+"});
        System.out.println("result:: "+ result);
    }

}
