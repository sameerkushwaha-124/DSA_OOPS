# Coin Toss

## 📝 Problem
All outcomes for n coin tosses: n=3 → ["HHH", "HHT", "HTH", "HTT", "THH", "THT", "TTH", "TTT"]

## 💡 Core Concept
- **Base Case**: n = 0 → add result
- **Recursive**: Choose H OR Choose T
- **Pattern**: Binary tree (2 choices per toss)

## 🔄 Recursion Tree
```
                    coinToss(3, "")
                   /              \
                  H                T
                 /                  \
          coinToss(2,"H")      coinToss(2,"T")
           /          \          /          \
          H            T        H            T
         /              \      /              \
   coinToss(1,"HH") coinToss(1,"HT") coinToss(1,"TH") coinToss(1,"TT")
     /      \         /      \         /      \         /      \
    H        T       H        T       H        T       H        T
   /          \     /          \     /          \     /          \
"HHH"      "HHT" "HTH"      "HTT" "THH"      "THT" "TTH"      "TTT"
```

## ⚡ Complexity
- **Time**: O(2^n) - 2 choices per toss
- **Space**: O(2^n) results + O(n) depth

## 🎯 Key Points
- Perfect binary tree
- Total outcomes = 2^n
- Each path represents one outcome

## 💻 Implementation
```java
public static void coinToss(int n, List<String> result, String current) {
    if (n == 0) {
        result.add(current);
        return;
    }

    coinToss(n - 1, result, current + "H");
    coinToss(n - 1, result, current + "T");
}
```