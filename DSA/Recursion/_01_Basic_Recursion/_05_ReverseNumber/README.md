# Reverse Number

## 📝 Problem
Reverse digits: 1234 → 4321

## 💡 Core Concept
- **Base Case**: n = 0 → return reversed
- **Recursive**: reverseNumber(n/10, reversed×10 + n%10)
- **Pattern**: Accumulator (build result during recursion)

## 🔄 Recursion Tree
```
reverseNumber(1234, 0)
├─ reverseNumber(123, 4)
│  ├─ reverseNumber(12, 43)
│  │  ├─ reverseNumber(1, 432)
│  │  │  ├─ reverseNumber(0, 4321)
│  │  │  │  └─ return 4321
│  │  │  └─ 4321
│  │  └─ 4321
│  └─ 4321
└─ 4321
```

## ⚡ Complexity
- **Time**: O(log₁₀ n) - number of digits
- **Space**: O(log₁₀ n) - stack depth

## 🎯 Key Points
- Accumulator pattern (no calculation on return)
- Build result forward, not backward
- Tail recursion (can be optimized)

## 💻 Implementation
```java
public static int reverseNumber(int n, int reversed) {
    if (n == 0) {
        return reversed;
    }
    return reverseNumber(n / 10, reversed * 10 + n % 10);
}
```