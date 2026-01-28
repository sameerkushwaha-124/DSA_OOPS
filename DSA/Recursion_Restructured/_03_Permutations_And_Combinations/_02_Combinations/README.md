# Combinations

## 📝 Problem
Choose k from n: C(4,2) from [1,2,3,4] → [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]

## 💡 Core Concept
- **Base Case**: current.size = k → add result
- **Recursive**: Try elements from start index onwards
- **Key**: Use start index to avoid duplicates
- **Pattern**: Order doesn't matter

## 🔄 Recursion Tree
```
                combinations([1,2,3,4], k=2, start=0, [])
        /           |           |           \
    Choose 1     Choose 2     Choose 3     Choose 4
       /             |             |             \
comb(1,[1])     comb(2,[2])   comb(3,[3])   comb(4,[4])
  /  |  \         /  |  \       /    \          |
 2   3   4       3   4   ✗     4     ✗         ✗
 |   |   |       |   |         |
[1,2][1,3][1,4] [2,3][2,4]   [3,4]
 ✓   ✓   ✓       ✓   ✓         ✓
```

## ⚡ Complexity
- **Time**: O(C(n,k) × k) = O(n!/(k!(n-k)!) × k)
- **Space**: O(k) - recursion depth

## 🎯 Key Points
- Start index prevents [1,2] and [2,1] duplicates
- Order doesn't matter: [1,2] = [2,1]
- Total combinations = C(n,k) = n!/(k!(n-k)!)
- Different from permutations (order matters there)

## 💻 Implementation

### Array Combinations
```java
public static void combinationsArray(int[] arr, int k, int start, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i < arr.length; i++) {
        current.add(arr[i]);
        combinationsArray(arr, k, i + 1, current, result);
        current.remove(current.size() - 1);
    }
}
```

### Number Combinations
```java
public static void combinationsNumbers(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i <= n; i++) {
        current.add(i);
        combinationsNumbers(n, k, i + 1, current, result);
        current.remove(current.size() - 1);
    }
}
```