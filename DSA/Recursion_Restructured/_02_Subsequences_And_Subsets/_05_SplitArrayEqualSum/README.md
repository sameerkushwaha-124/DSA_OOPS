# Split Array into Equal Sum Subsets

## 📝 Problem
Split array into 2 subsets with equal sum: [1,2,3,4,5,6] → [1,2,6] and [3,4,5]

## 💡 Core Concept
- **Base Case**: index = length → check if sum1 = sum2
- **Recursive**: Add to list1 OR Add to list2
- **Pattern**: Partition with constraint checking

## 🔄 Recursion Tree
```
                split([1,2,3], 0, sum1=0, sum2=0)
                /                              \
         Add 1 to list1                   Add 1 to list2
              /                                  \
    split(1, sum1=1, sum2=0)          split(1, sum1=0, sum2=1)
        /              \                    /              \
   Add 2 to L1    Add 2 to L2         Add 2 to L1    Add 2 to L2
      /              \                    /              \
split(2,2,0)    split(2,1,2)      split(2,2,1)    split(2,0,3)
  /    \          /    \              /    \          /    \
Add 3  Add 3    Add 3  Add 3        Add 3  Add 3    Add 3  Add 3
L1     L2       L1     L2           L1     L2       L1     L2
|      |        |      |            |      |        |      |
5≠0    2≠3     4≠2    1≠5          5≠1    2≠4      3≠3    0≠6
✗      ✗       ✗      ✗            ✗      ✗        ✓      ✗
                                                [1,2]=[3]
```

## ⚡ Complexity
- **Time**: O(2^n) - each element goes to list1 or list2
- **Space**: O(n) - recursion depth

## 🎯 Key Points
- Each element has 2 choices: list1 or list2
- Check sum equality at the end
- Both lists must be non-empty
- Related to partition equal subset sum

## 💻 Implementation
```java
public static void splitArray(int[] arr, int index, int sum1, int sum2, 
                              List<Integer> list1, List<Integer> list2) {
    if (index == arr.length) {
        if (sum1 == sum2 && !list1.isEmpty() && !list2.isEmpty()) {
            System.out.println(list1 + " = " + list2);
        }
        return;
    }

    list1.add(arr[index]);
    splitArray(arr, index + 1, sum1 + arr[index], sum2, list1, list2);
    list1.remove(list1.size() - 1);

    list2.add(arr[index]);
    splitArray(arr, index + 1, sum1, sum2 + arr[index], list1, list2);
    list2.remove(list2.size() - 1);
}
```