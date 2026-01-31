# Sum of Digits

## 📝 Problem
Sum all digits: 1234 → 1+2+3+4 = 10

## 💡 Core Concept
- **Base Case**: n = 0 → return 0
- **Recursive**: (n % 10) + sumOfDigits(n / 10)
- **Pattern**: Extract last digit, recurse on remaining

## 🔄 Recursion Tree
```
sumOfDigits(1234)
├─ 4 + sumOfDigits(123)
│      ├─ 3 + sumOfDigits(12)
│      │      ├─ 2 + sumOfDigits(1)
│      │      │      ├─ 1 + sumOfDigits(0)
│      │      │      │      └─ 0
│      │      │      └─ 1
│      │      └─ 3
│      └─ 6
└─ 10
```

## ⚡ Complexity
- **Time**: O(log₁₀ n) - number of digits
- **Space**: O(log₁₀ n) - stack depth

## 🎯 Key Points
- `n % 10` extracts last digit
- `n / 10` removes last digit
- Process digits right to left

## 💻 Implementation
```java
public static int sumOfDigits(int n) {
    if (n == 0) {
        return 0;
    }
    return (n % 10) + sumOfDigits(n / 10);
}
```