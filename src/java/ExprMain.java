import java.util.Scanner;
import java.util.Stack;

public class ExprMain {

    public static Integer evalRPN(String[] expr) {
        if (expr == null || expr.length == 0) {
            return 0;
        }
        if (expr.length == 1) {
            return Integer.valueOf(expr[0]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expr.length; i++) {
            String temp = expr[i];
            if (temp.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if (temp.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if (temp.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (temp.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.add(Integer.valueOf(temp));
            }
        }
        return stack.pop();

    }


    public static void main(String[] args) {
        System.out.print("输入逆波兰表达式，回车结束：如:2 3 4 + * \n");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        String[] expr = read.split(" ");
        System.out.println("计算结果为：" + evalRPN(expr));

    }
}
