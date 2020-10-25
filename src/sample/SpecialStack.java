package sample;

import java.util.Stack;

// Design a stack with getMin function
// And getMin should always be o(1) ,
// no other data structure should be used
public class SpecialStack {
	
	private Stack<Integer> s = new Stack<>();
	
	private int min;
	
	public void pop() {
		if (s.isEmpty()) {
			System.out.println("Stack is empty now");
			return;
		}
		int t = s.pop();
		if (t >= min) {
			System.out.println("Pop Element::"+ t);
		} else {
			min = 2 * min - t;
			System.out.println("Pop Element::"+ min);
		}
	}
	
	public void push(int x) {
		if (s.empty()) {
			s.push(x);
			min = x;
		} else if (x >= min) {
			s.push(x);
		} else {
			int a = (2 * x - min);
			s.push(a);
			min = x;
		}
	}
	
	public int getMin() {
		return min;
	}
	
	public static void main(String[] args) {
		SpecialStack st = new SpecialStack();
		st.push(5);
		st.push(6);
		st.push(2);
		st.push(8);
		System.out.println("minimum element::" + st.getMin());
		st.pop();
		st.pop();
		System.out.println("minimum element after pop::" + st.getMin());
	}

}
