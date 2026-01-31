# Generate Parentheses

## 📝 Problem
Valid parentheses for n pairs: n=3 → ["((()))", "(()())", "(())()", "()(())", "()()()"]

## 💡 Core Concept
- **Base Case**: length = 2n → add result
- **Constraint 1**: Add '(' if open < n
- **Constraint 2**: Add ')' if close < open
- **Pattern**: Constraint-based generation

## 🔄 Recursion Tree
```
                    gen(n=2, "", o=0, c=0)
                    /                    \
               Add '('                 Can't add ')'
                  /                    (c < o fails)
            gen("(", o=1, c=0)
            /                \
       Add '('            Add ')'
          /                    \
    gen("((", o=2, c=0)    gen("()", o=1, c=1)
         |                  /              \
      Add ')'          Add '('          Add ')'
         |                |                  |
    gen("(()", o=2, c=1) gen("()(", o=2, c=1) gen("()()", o=1, c=2)
         |                    |                     ✓ "()()"
      Add ')'              Add ')'
         |                    |
    gen("(())", o=2, c=2)  gen("()()", o=2, c=2)
         ✓ "(())"              ✓ "()()"
```

## ⚡ Complexity
- **Time**: O(4^n / √n) - Catalan number
- **Space**: O(n) - recursion depth

## 🎯 Key Points
- Can add '(' if haven't used all n
- Can add ')' only if it won't make invalid (close < open)
- Result count = Catalan number: C(n) = (2n)!/(n+1)!n!
- n=1→1, n=2→2, n=3→5, n=4→14

## 💻 Implementation
```java
public static void generateParentheses(int n, List<String> result, String current, int open, int close) {
    if (current.length() == 2 * n) {
        result.add(current);
        return;
    }

    if (open < n) {
        generateParentheses(n, result, current + "(", open + 1, close);
    }

    if (close < open) {
        generateParentheses(n, result, current + ")", open, close + 1);
    }
}
```