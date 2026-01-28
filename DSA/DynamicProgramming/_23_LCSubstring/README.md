# Longest Common Substring

## Problem Statement
Given two strings, find the length of the longest common substring. A substring is a contiguous sequence of characters within a string.

**Input:**
- `str1` - First string
- `str2` - Second string

**Output:**
- Length of longest common substring

**Example:**
```
Input: str1 = "abcjklp", str2 = "acjkp"
Output: 3
Explanation: Longest common substring is "cjk" with length 3
```

## Difference from LCS

| Aspect | LCS (Subsequence) | LCSubstring |
|--------|-------------------|-------------|
| Contiguous | No | Yes |
| "abc" in "aXbXc" | Valid | Invalid |
| Example | "ace" in "abcde" | "abc" in "xabcy" |
| When mismatch | Continue | Reset to 0 |

## Solution Approach

### Tabulation (Bottom-Up DP)
**Approach:** Build dp table, reset to 0 on mismatch.

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n)

**DP State:** `dp[i][j]` = Length of common substring ending at str1[i-1] and str2[j-1]

**Key Difference:**
- LCS: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])` when mismatch
- LCSubstring: `dp[i][j] = 0` when mismatch

## Algorithm

```
1. Create dp table of size (n+1) × (m+1)
2. Initialize ans = 0
3. For each i from 1 to n:
   For each j from 1 to m:
     If str1[i-1] == str2[j-1]:
       dp[i][j] = 1 + dp[i-1][j-1]
       ans = max(ans, dp[i][j])
     Else:
       dp[i][j] = 0
4. Return ans
```

## Detailed Example

**str1 = "abcjklp", str2 = "acjkp"**

### DP Table:
```
       ''  a   c   j   k   p
    ┌───┬───┬───┬───┬───┬───┐
 '' │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 a  │ 0 │ 1 │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 b  │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 c  │ 0 │ 0 │ 1 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 j  │ 0 │ 0 │ 0 │ 2 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 k  │ 0 │ 0 │ 0 │ 0 │ 3 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 l  │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┤
 p  │ 0 │ 0 │ 0 │ 0 │ 0 │ 1 │
    └───┴───┴───┴───┴───┴───┘

Maximum value = 3 ✅
```

### Dry Run:
```
i=1, j=1: str1[0]='a', str2[0]='a' → Match
  dp[1][1] = 1 + dp[0][0] = 1
  ans = max(0, 1) = 1

i=1, j=2: str1[0]='a', str2[1]='c' → No match
  dp[1][2] = 0

i=3, j=2: str1[2]='c', str2[1]='c' → Match
  dp[3][2] = 1 + dp[2][1] = 1 + 0 = 1
  ans = max(1, 1) = 1

i=4, j=3: str1[3]='j', str2[2]='j' → Match
  dp[4][3] = 1 + dp[3][2] = 1 + 1 = 2
  ans = max(1, 2) = 2

i=5, j=4: str1[4]='k', str2[3]='k' → Match
  dp[5][4] = 1 + dp[4][3] = 1 + 2 = 3 ✅
  ans = max(2, 3) = 3

i=7, j=5: str1[6]='p', str2[4]='p' → Match
  dp[7][5] = 1 + dp[6][4] = 1 + 0 = 1
  ans = max(3, 1) = 3

Final answer: 3
```

## Visualization of Matching

```
str1: a b c j k l p
str2: a   c j k   p

Substrings:
- "a" (length 1)
- "c" (length 1)
- "cjk" (length 3) ✅ Maximum
- "p" (length 1)
```

## Another Example

**str1 = "OldSite:GeeksforGeeks.org", str2 = "NewSite:GeeksQuiz.com"**

```
Common substrings:
- "Site" (length 4)
- "Geeks" (length 5) ✅ Maximum
- ".o" (length 2)

Answer: 5
```

## Space Optimization

Can optimize to O(n) space using only two rows:

```java
int[] prev = new int[m + 1];
int ans = 0;

for (int i = 1; i <= n; i++) {
    int[] curr = new int[m + 1];
    for (int j = 1; j <= m; j++) {
        if (str1.charAt(i-1) == str2.charAt(j-1)) {
            curr[j] = 1 + prev[j-1];
            ans = Math.max(ans, curr[j]);
        } else {
            curr[j] = 0;
        }
    }
    prev = curr;
}
```

**Space Complexity:** O(m)

## Code Implementation
See [LongestCommonSubstring.java](LongestCommonSubstring.java) for complete implementation.

## Key Insights
- Must be contiguous (consecutive characters)
- Reset to 0 on mismatch (key difference from LCS)
- Track maximum value seen so far
- Can't use recursion easily (need to track current length)
- Space optimization possible with two arrays

## Comparison Table

**str1 = "abcde", str2 = "abfce"**

| Problem | Answer | Explanation |
|---------|--------|-------------|
| LCS | 4 ("abce") | Can skip characters |
| LCSubstring | 2 ("ab") | Must be contiguous |

## Related Problems
- Longest Common Subsequence
- Longest Repeating Substring
- Minimum Window Substring
- Longest Palindromic Substring

## Pattern Recognition
Use LCSubstring when:
- ✅ Two strings involved
- ✅ Need contiguous match
- ✅ Can't skip characters
- ✅ Looking for actual substring

## Common Mistakes
1. ❌ Using max(dp[i-1][j], dp[i][j-1]) on mismatch
2. ❌ Forgetting to track maximum value
3. ❌ Not resetting to 0 on mismatch
4. ❌ Confusing with LCS (subsequence)

## Time & Space Complexity

| Approach | Time | Space |
|----------|------|-------|
| Tabulation | O(m × n) | O(m × n) |
| Space Optimized | O(m × n) | O(m) |
