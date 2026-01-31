# Palindrome Partitioning

## 📝 Problem
Partition into palindromes: "aab" → [["a","a","b"], ["aa","b"]]

## 💡 Core Concept
- **Base Case**: start = length → add partition
- **Recursive**: Try all substrings from start
- **Constraint**: Only proceed if substring is palindrome
- **Pattern**: Substring generation with validation

## 🔄 Recursion Tree
```
                partition("aab", 0, [])
                /                    \
            Try "a"                Try "aa"
         (palindrome ✓)         (palindrome ✓)
              /                        \
    partition("aab", 1, ["a"])    partition("aab", 2, ["aa"])
        /              \                    |
    Try "a"         Try "ab"            Try "b"
  (palindrome ✓)  (not palindrome ✗)  (palindrome ✓)
      /                                    |
partition("aab", 2, ["a","a"])    partition("aab", 3, ["aa","b"])
      |                                    |
   Try "b"                              Reached end
(palindrome ✓)                            ✓ ["aa","b"]
      |
partition("aab", 3, ["a","a","b"])
      |
  Reached end
✓ ["a","a","b"]
```

## ⚡ Complexity
- **Time**: O(n × 2^n) - 2^n partitions, O(n) palindrome check
- **Space**: O(n) - recursion depth

## 🎯 Key Points
- Try all possible cuts
- Only proceed if current substring is palindrome
- Prune invalid paths early
- Can optimize palindrome check with DP

## 💻 Implementation
```java
public static void palindromePartition(String str, List<String> current, List<List<String>> result, int start) {
    if (start == str.length()) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i < str.length(); i++) {
        String substring = str.substring(start, i + 1);
        
        if (isPalindrome(substring)) {
            current.add(substring);
            palindromePartition(str, current, result, i + 1);
            current.remove(current.size() - 1);
        }
    }
}

public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}
```