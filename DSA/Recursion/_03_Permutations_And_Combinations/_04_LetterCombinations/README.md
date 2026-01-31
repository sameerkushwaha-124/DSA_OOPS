# Letter Combinations (Phone Keypad)

## 📝 Problem
Phone keypad combinations: "23" → ["ad","ae","af","bd","be","bf","cd","ce","cf"]

## 💡 Core Concept
- **Mapping**: 2→abc, 3→def, 4→ghi, 5→jkl, 6→mno, 7→pqrs, 8→tuv, 9→wxyz
- **Base Case**: index = digits.length → add result
- **Recursive**: Try all letters for current digit
- **Pattern**: Multiple branching based on keypad

## 🔄 Recursion Tree
```
                    letterComb("23", 0, "")
                    /        |        \
                   a         b         c
                  /          |          \
        letterComb(1,"a") letterComb(1,"b") letterComb(1,"c")
         /    |    \       /    |    \       /    |    \
        d     e     f     d     e     f     d     e     f
        |     |     |     |     |     |     |     |     |
      "ad"  "ae"  "af"  "bd"  "be"  "bf"  "cd"  "ce"  "cf"
```

## ⚡ Complexity
- **Time**: O(4^n) - worst case (7 and 9 have 4 letters)
- **Space**: O(n) - recursion depth

## 🎯 Key Points
- Each digit maps to 3-4 letters
- Total combinations = product of letter counts
- "23" → 3×3 = 9 combinations
- "234" → 3×3×3 = 27 combinations

## 💻 Implementation
```java
private static final String[] KEYPAD = {
    "",     // 0
    "",     // 1
    "abc",  // 2
    "def",  // 3
    "ghi",  // 4
    "jkl",  // 5
    "mno",  // 6
    "pqrs", // 7
    "tuv",  // 8
    "wxyz"  // 9
};

public static void letterCombinations(String digits, List<String> result) {
    if (digits == null || digits.length() == 0) {
        return;
    }
    letterCombinationsHelper(digits, 0, "", result);
}

private static void letterCombinationsHelper(String digits, int index, String current, List<String> result) {
    if (index == digits.length()) {
        result.add(current);
        return;
    }

    char digit = digits.charAt(index);
    String letters = KEYPAD[digit - '0'];

    for (int i = 0; i < letters.length(); i++) {
        letterCombinationsHelper(digits, index + 1, current + letters.charAt(i), result);
    }
}
```