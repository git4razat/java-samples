package sample;

// In fibonacci series, next number is the sum of previous two numbers 
// for example 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 etc. 
// The first two numbers of fibonacci series are 0 and 1.

class Fibonacci { 
    static int fib(int n) 
    { 
        if (n <= 1) 
            return n; 
        return fib(n - 1) + fib(n - 2); 
    } 
  
    public static void main(String args[]) 
    { 
        int n = 9; 
        System.out.println(fib(n)); 
    } 
} 
