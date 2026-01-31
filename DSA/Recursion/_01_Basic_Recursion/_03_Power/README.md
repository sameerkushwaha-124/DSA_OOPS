# Power (base^exponent)

## 📝 Problem
Calculate base^exponent using recursion

## 💡 Core Concept

### Basic Approach
- **Base Case**: exp = 0 → return 1
- **Recursive**: base × power(base, exp-1)
- **Time**: O(n)

### Optimized (Divide & Conquer)
- **Base Case**: exp = 0 → return 1
- **Recursive**: 
  - result = power(base, exp/2)
  - result = result × result
  - if exp is odd: result × base
- **Time**: O(log n)

## 🔄 Recursion Tree

### Basic: power(2, 4)
```
power(2,4) → 2 × power(2,3)
              → 2 × 2 × power(2,2)
                   → 2 × 2 × 2 × power(2,1)
                        → 2 × 2 × 2 × 2 × power(2,0)
                             → 2 × 2 × 2 × 2 × 1 = 16
```

### Optimized: power(2, 4)
```
power(2,4)
├─ power(2,2) → 4
│  └─ power(2,1) → 2
│     └─ power(2,0) → 1
└─ 4 × 4 = 16
```

## ⚡ Complexity Comparison
| Method | Time | Calls for 2^10 |
|--------|------|----------------|
| Basic | O(n) | 10 |
| Optimized | O(log n) | 4 |

## 🎯 Key Points
- Optimized halves the problem each time
- Even exp: (base^(n/2))²
- Odd exp: (base^(n/2))² × base

## 💻 Implementation

### Basic Approach
```java
public static long power(int base, int exponent) {
    if (exponent == 0) {
        return 1;
    }
    return base * power(base, exponent - 1);
}
```

### Optimized Approach
```java
public static long powerOptimized(int base, int exponent) {
    if (exponent == 0) {
        return 1;
    }
    
    long result = powerOptimized(base, exponent / 2);
    result = result * result;
    
    if (exponent % 2 == 1) {
        result = result * base;
    }
    
    return result;
}
```