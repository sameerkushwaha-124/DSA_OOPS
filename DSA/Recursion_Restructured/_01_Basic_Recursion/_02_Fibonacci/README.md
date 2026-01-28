# Fibonacci

## 📝 Problem
Find nth Fibonacci number: 0, 1, 1, 2, 3, 5, 8, 13...

## 💡 Core Concept
- **Base Cases**: n < 2 → return n
- **Recursive Case**: fib(n-1) + fib(n-2)
- **Pattern**: Tree recursion (two recursive calls)

## 🔄 Recursion Tree
```
                fib(5)=5
               /        \
          fib(4)=3      fib(3)=2
         /      \        /      \
     fib(3)=2  fib(2)=1 fib(2)=1 fib(1)=1
     /    \     /    \   /    \
fib(2) fib(1) fib(1) fib(0) fib(1) fib(0)
 /  \    |      |      |      |      |
fib(1) fib(0)  1      1      0      1      0
  |      |
  1      0
```

## ⚡ Complexity
- **Time**: O(2^n) - exponential (many repeated calls)
- **Space**: O(n) - max depth

## 🎯 Key Points
- Each call branches into 2 calls
- Same values calculated multiple times (fib(2), fib(3))
- Inefficient without memoization
- fib(5) makes 15 function calls

## 💻 Implementation
```java
public static int fibonacci(int n) {
    if (n < 2) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```