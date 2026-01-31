# Generate Subsequences

## 📝 Problem
Generate all subsequences: "abc" → ["", "c", "b", "bc", "a", "ac", "ab", "abc"]

## 💡 Core Concept
- **Base Case**: index = length → add to result
- **Recursive**: Include OR Exclude each character
- **Pattern**: Binary choice tree (2^n outcomes)

## 🔄 Recursion Tree
```
                    ("", 0)
                   /        \
              Include 'a'   Exclude 'a'
                 /              \
            ("a", 1)          ("", 1)
           /        \        /        \
      Include 'b' Exclude  Include  Exclude
         /            \      /          \
    ("ab", 2)      ("a", 2) ("b", 2)  ("", 2)
     /    \         /    \    /    \    /    \
   Inc   Exc      Inc  Exc  Inc  Exc  Inc  Exc
    |     |        |    |    |    |    |    |
  "abc" "ab"     "ac" "a"  "bc" "b"  "c"  ""
```

## ⚡ Complexity
- **Time**: O(2^n) - each element has 2 choices
- **Space**: O(2^n) for results + O(n) depth

## 🎯 Key Points
- Each level: include or exclude decision
- Total subsequences = 2^n
- Fundamental pattern for subset problems

## 💻 Implementation

### String Subsequences
```java
public static void generateSubsequences(String str, int index, String current, List<String> result) {
    if (index == str.length()) {
        result.add(current);
        return;
    }

    generateSubsequences(str, index + 1, current + str.charAt(index), result);
    generateSubsequences(str, index + 1, current, result);
}
```

### Array Subsets
```java
public static void generateSubsets(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    if (index == arr.length) {
        result.add(new ArrayList<>(current));
        return;
    }

    current.add(arr[index]);
    generateSubsets(arr, index + 1, current, result);
    current.remove(current.size() - 1);

    generateSubsets(arr, index + 1, current, result);
}
```