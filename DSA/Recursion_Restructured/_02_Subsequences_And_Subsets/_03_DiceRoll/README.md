# Dice Roll

## 📝 Problem
Ways to get target sum: dice(6, 4) → ["1111", "112", "121", "13", "211", "22", "31", "4"]

## 💡 Core Concept
- **Base Case**: target = 0 → valid solution
- **Pruning**: target < 0 → invalid
- **Recursive**: Try all dice values (1 to faces)
- **Pattern**: Multiple branching (faces choices)

## 🔄 Recursion Tree
```
                    dice(6, 4, "")
        /      /      |      \      \      \
       1      2       3       4      5      6
      /       |       |       |      ✗      ✗
dice(6,3) dice(6,2) dice(6,1) dice(6,0)
  /|\|\    /|\|\     /|\|\      ✓
 1 2 3 4  1 2 3 4   1 2 3 4   "4"
  | | | |  | | | |   | | | |
"1111"   "112"     "13"
"121"    "211"     "31"
         "22"
```

## ⚡ Complexity
- **Time**: O(faces^target) - worst case
- **Space**: O(target) - max depth

## 🎯 Key Points
- Each call branches into 'faces' calls
- Prune when target becomes negative
- Similar to coin change problem

## 💻 Implementation
```java
public static void dice(int faces, int target, String current) {
    if (target == 0) {
        System.out.println(current);
        return;
    }
    
    if (target < 0) {
        return;
    }

    for (int i = 1; i <= faces; i++) {
        dice(faces, target - i, current + i);
    }
}
```