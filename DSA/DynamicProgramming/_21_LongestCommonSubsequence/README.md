# Longest Common Subsequence (LCS)

## Problem Statement
Given two strings, find the length of the longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.

**Input:**
- `str1` - First string
- `str2` - Second string

**Output:**
- Length of longest common subsequence

**Example:**
```
Input: str1 = "abcde", str2 = "ace"
Output: 3
Explanation: LCS is "ace" with length 3
```

## Solution Approaches

### 1. Recursion
**Approach:** Compare characters from end of both strings.

**Time Complexity:** O(2^(m+n))  
**Space Complexity:** O(m+n) - recursion stack

**Recursion Tree (str1="aed", str2="acd"):**
```
                    f(2, 2)
                   /        \
            1+f(1,1)         max(f(1,2), f(2,1))
           /      \         /                    \
      1+f(0,0)  max()   f(1,1)                f(2,0)
         2                1                      1
         
LCS length = 2
```

**Dry Run:**
```
str1 = "aed", str2 = "acd", idx1 = 2, idx2 = 2

f(2, 2): str1[2]='d', str2[2]='d' → Match!
  └─ 1 + f(1, 1)
     └─ f(1, 1): str1[1]='e', str2[1]='c' → No match
        ├─ f(0, 1): str1[0]='a', str2[1]='c' → No match
        │  ├─ f(-1, 1) = 0
        │  └─ f(0, 0): str1[0]='a', str2[0]='a' → Match!
        │     └─ 1 + f(-1, -1) = 1
        │  Result: max(0, 1) = 1
        └─ f(1, 0): str1[1]='e', str2[0]='a' → No match
           ├─ f(0, 0) = 1
           └─ f(1, -1) = 0
           Result: max(1, 0) = 1
        Result: max(1, 1) = 1
  Total: 1 + 1 = 2 ✅
```

**Logic:**
- If characters match: 1 + LCS(i-1, j-1)
- If don't match: max(LCS(i-1, j), LCS(i, j-1))

---

### 2. Memoization (Top-Down DP)
**Approach:** Store LCS length in dp[idx1][idx2].

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n) + O(m+n) recursion stack

**DP State:** `dp[i][j]` = LCS length of str1[0..i] and str2[0..j]

**Dry Run:**
```
str1 = "aed", str2 = "acd"

dp[2][2]: 'd' == 'd' → 1 + dp[1][1]
dp[1][1]: 'e' != 'c' → max(dp[0][1], dp[1][0])
dp[0][1]: 'a' != 'c' → max(dp[-1][1], dp[0][0]) = max(0, 1) = 1
dp[1][0]: 'e' != 'a' → max(dp[0][0], dp[1][-1]) = max(1, 0) = 1
dp[1][1] = max(1, 1) = 1
dp[2][2] = 1 + 1 = 2 ✅
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively using 1-based indexing.

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n)

**DP Table (str1="aed", str2="acd"):**
```
       ''  a   c   d
    ┌───┬───┬───┬───┐
 '' │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┤
 a  │ 0 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┤
 e  │ 0 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┤
 d  │ 0 │ 1 │ 1 │ 2 │
    └───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[i][0] = 0, dp[0][j] = 0

i=1, j=1: str1[0]='a', str2[0]='a' → Match
  dp[1][1] = 1 + dp[0][0] = 1

i=1, j=2: str1[0]='a', str2[1]='c' → No match
  dp[1][2] = max(dp[0][2], dp[1][1]) = max(0, 1) = 1

i=2, j=2: str1[1]='e', str2[1]='c' → No match
  dp[2][2] = max(dp[1][2], dp[2][1]) = max(1, 1) = 1

i=3, j=3: str1[2]='d', str2[2]='d' → Match
  dp[3][3] = 1 + dp[2][2] = 1 + 1 = 2 ✅
```

**Note:** Using 1-based indexing to avoid negative indices.

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr).

**Time Complexity:** O(m × n)  
**Space Complexity:** O(n)

**Dry Run:**
```
str1 = "aed", str2 = "acd"

prev = [0, 0, 0, 0]  // initial

i=1 (str1[0]='a'):
curr = [0, 1, 1, 1]  // 'a' matches at j=1
prev = curr

i=2 (str1[1]='e'):
curr = [0, 1, 1, 1]  // no new matches
prev = curr

i=3 (str1[2]='d'):
curr = [0, 1, 1, 2]  // 'd' matches at j=3
prev = curr

Result: prev[3] = 2 ✅
```

**Key:** Only need previous row to compute current row.

## Detailed Example

**str1 = "AGGTAB", str2 = "GXTXAYB"**

```
LCS = "GTAB" (length 4)

DP Table:
       ''  G   X   T   X   A   Y   B
    ┌───┬───┬───┬───┬───┬───┬───┬───┐
 '' │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │ 0 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 A  │ 0 │ 0 │ 0 │ 0 │ 0 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 G  │ 0 │ 1 │ 1 │ 1 │ 1 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 G  │ 0 │ 1 │ 1 │ 1 │ 1 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 T  │ 0 │ 1 │ 1 │ 2 │ 2 │ 2 │ 2 │ 2 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 A  │ 0 │ 1 │ 1 │ 2 │ 2 │ 3 │ 3 │ 3 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
 B  │ 0 │ 1 │ 1 │ 2 │ 2 │ 3 │ 3 │ 4 │
    └───┴───┴───┴───┴───┴───┴───┴───┘
```

## Code Implementation
See [LCS.java](LCS.java) for complete implementation with all approaches.

## Key Insights
- Foundation for many string DP problems
- Use 1-based indexing in tabulation to avoid negative indices
- Two choices at each step: match or skip from either string
- Space optimization reduces memory from O(m×n) to O(n)
- Can be extended to print the actual LCS (see Print LCS problem)

## Related Problems
- Print LCS
- Longest Common Substring
- Longest Palindromic Subsequence
- Shortest Common Supersequence
- Edit Distance
- Minimum Insertions to Make Palindrome

## Pattern Recognition
Use LCS when:
- ✅ Two strings/sequences involved
- ✅ Need to find common elements
- ✅ Order matters but not contiguity
- ✅ Maximize/minimize based on matching

## Common Mistakes
1. ❌ Using 0-based indexing without handling negative indices
2. ❌ Forgetting base cases (empty string scenarios)
3. ❌ Not using 1-based indexing in tabulation
4. ❌ Confusing with Longest Common Substring (contiguous)
