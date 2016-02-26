import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSeparatorUI;


public class Model {
	
	int[] result;
	String res1;
	
	Model(){
		
	}

	
	public String[] calculate(String mathExpression){
		System.out.println(mathExpression);
		res1 = Infix2(mathExpression);
		System.out.println(res1);
		result = evalPf(res1);
		System.out.println("Result: " +result[0]);
		System.out.println("Cost: " +result[1]);
		return new String[]{result[0] +"", result[1]+""};
	}
	
	
	
	
	
/*	private double evaluate(String postfix) {
		Stack<Double> eval = new Stack<Double>();

		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if(isOperand(c)){
				double number = c -'0';
				eval.push(number);
			}
			else if(isOperator(c)) {
				double opTwo = eval.pop();
				double opOne = eval.pop();
				double result = 0;

				switch (c) {
				case '*':
					result = opOne * opTwo;
					break;
				case '/':
					result = opTwo / opOne;
					break;
				case '+':
					result = opOne + opTwo;
					break;
				case '-':
					result = opTwo - opOne;
					break;
				}
				eval.push(result);
			}
		}	
		return eval.pop();
	}
*/
	static int[] evalPf(String str)
	{
	    Scanner sc = new Scanner(str);
	    Stack<Integer> stack = new Stack<Integer>();
	    int counter = 0;
	    int counter2= 0;
	    System.out.println(str);

	    while (sc.hasNext()) {
	    	System.out.println("c1: " + counter++);

	        while (sc.hasNextInt()) {
	        	System.out.println("c2:" + counter2++);
	            stack.push(sc.nextInt());
	        }
	        if(!sc.hasNextInt()){
	        int b = stack.pop();
	        int a = stack.pop();
	        char op = sc.next().charAt(0);
	        
	        if      (op == '+') stack.push(a + b);
	        else if (op == '-') stack.push(a - b);
	        else if (op == '*') stack.push(a * b);
	        else if (op == '/') stack.push(a / b);
	        else if (op == '%') stack.push(a % b);
	        else if (op == ' ') sc.next();
	        }
	    }

	    sc.close();
	    int y = stack.pop();
	    System.out.println(y);
	    int cost = counter + counter2;
	    
	    System.out.println("Cost of calculation: " + cost);
	    return new int[]{y,cost};
	}
	

public static String Infix2(String input) throws ArrayIndexOutOfBoundsException {
    if (input == null)
    	return "";
    char[] in = input.toCharArray();
    Stack<Character> stack = new Stack<Character>();
    StringBuilder out = new StringBuilder();

    for (int i = 0; i < in.length; i++)
    	switch (in[i]) {
    	case '+':
    	case '-':
    		while (!stack.empty()
    				&& (stack.peek() == '*' || stack.peek() == '/'))
    			out.append(stack.pop()+" ");
    	case '*':
    	case '/':
    	case '(':
    		stack.push(in[i]);
    	case ' ':
    		break;
    	case ')':
    		while (!stack.empty() && stack.peek() != '(')
    			out.append(stack.pop()+" ");
    		if (!stack.empty())
    			stack.pop();
    		break;
    	default:
    		ArrayList<Integer> ar = new ArrayList<Integer>();
    		int result = 0;
    		while(true){
    			if((i < in.length)&&(Character.isDigit(in[i]))){
    				ar.add(in[i]-48);
    				i++;
    			}else{
    				i--;
					for(int j=0;j<ar.size();j++){
						result += Math.pow(10,ar.size()-1-j)*ar.get(j);}
		    		out.append(result + " ");
		    		break;
    			}
    					
			}
    		}
    			/*
    		}
    			try {
					int a;
					a = ((int)(in[i]));

					System.out.println(in[i]+ "= in[i]");
					System.out.println(a + "= a");
					ar.add((a-48));
					i++;
				} catch (ClassCastException|ArrayIndexOutOfBoundsException e) {
					i--;
					for(int j=0;j<ar.size();j++){
						System.out.println(ar.get(j) + " =ar.get(j)");
						System.out.println(ar.size() + " = ar.size()");
						System.out.println(Math.pow(10,ar.size()-1-j)+ "= Math.pow(10,ar.size()-1-j)");
						System.out.println(j + " =j");
						result += Math.pow(10,ar.size()-1-j)*ar.get(j);
						System.out.println(result);
					}
		    		out.append(result + " ");
		    		break;
				}
    		}
			break;*/

    		

    		


    	


    while (!stack.isEmpty())
    	out.append(stack.pop()+" ");

    return out.toString();
}
}




