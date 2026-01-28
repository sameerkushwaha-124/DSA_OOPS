# Subset Sum

## 📝 Problem
Find subsets with target sum: [1,2,3], target=3 → [[1,2], [3]]

## 💡 Core Concept
- **Base Case**: target = 0 → found solution
- **Pruning**: target < 0 or index ≥ length → stop
- **Recursive**: Include OR Exclude current element
- **Pattern**: Include/exclude with target reduction

## 🔄 Recursion Tree
```
                findSubsets([1,2,3], 3, 0, [])
                /                           \
         Include 1                       Exclude 1
              /                                \
    findSubsets(3-1=2, 1, [1])      findSubsets(3, 1, [])
        /              \                  /              \
   Include 2        Exclude 2        Include 2        Exclude 2
      /                  \              /                  \
findSubsets(0,2,[1,2]) findSubsets(2,2,[1]) findSubsets(1,2,[2]) findSubsets(3,2,[])
     ✓ [1,2]              /      \              ✗                  /      \
                    Include 3  Exclude 3                    Include 3  Exclude 3
                        ✗          ✗                            ✓ [3]      ✗
```

## ⚡ Complexity
- **Time**: O(2^n) - include/exclude each element
- **Space**: O(n) - recursion depth

## 🎯 Key Points
- Reduce target when including element
- Stop early if target becomes negative
- Similar to 0/1 knapsack pattern

## 💻 Implementation
```java
public static void findSubsetsWithSum(int[] arr, int target, int index, List<Integer> current, List<List<Integer>> result) {
    if (target == 0) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    if (index >= arr.length || target < 0) {
        return;
    }

    current.add(arr[index]);
    findSubsetsWithSum(arr, target - arr[index], index + 1, current, result);
    current.remove(current.size() - 1);

    findSubsetsWithSum(arr, target, index + 1, current, result);
}
```