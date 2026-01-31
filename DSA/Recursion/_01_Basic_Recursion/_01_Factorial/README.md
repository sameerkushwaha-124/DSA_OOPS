# Factorial

## 📝 Problem
Calculate n! = n × (n-1) × (n-2) × ... × 1

## 💡 Core Concept
- **Base Case**: n ≤ 1 → return 1
- **Recursive Case**: n × factorial(n-1)
- **Pattern**: Linear recursion (single recursive call)

## 🔄 Recursion Tree
```
factorial(5)
│
├─ 5 × factorial(4)
│      ├─ 4 × factorial(3)
│      │      ├─ 3 × factorial(2)
│      │      │      ├─ 2 × factorial(1)
│      │      │      │      └─ return 1
│      │      │      └─ return 2
│      │      └─ return 6
│      └─ return 24
└─ return 120
```

## ⚡ Complexity
- **Time**: O(n) - n calls
- **Space**: O(n) - stack depth

## 🎯 Key Points
- Stack builds up during calls
- Results multiply during return
- Each call waits for next to complete

## 💻 Implementation
```java
public static int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}
```