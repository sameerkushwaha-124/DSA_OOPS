# Print Longest Common Subsequence

## Problem Statement
Given two strings, find and print the longest common subsequence. If multiple LCS exist, print any one.

**Input:**
- `str1` - First string
- `str2` - Second string

**Output:**
- The actual LCS string (not just length)

**Example:**
```
Input: str1 = "aed", str2 = "acd"
Output: "ad"
Explanation: LCS is "ad" with length 2
```

## Approach

### Step 1: Build DP Table (Same as LCS)
First, build the standard LCS dp table.

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n)

### Step 2: Backtrack to Construct LCS
Traverse the dp table from bottom-right to top-left:
- If characters match: Include in LCS, move diagonally
- Else: Move to cell with larger value

**Time Complexity:** O(m + n)  
**Space Complexity:** O(min(m, n)) for result string

## Algorithm

```
1. Build dp table (same as LCS)
2. Start from dp[n][m]
3. While i > 0 and j > 0:
   - If str1[i-1] == str2[j-1]:
       * Add character to result
       * Move diagonally (i--, j--)
   - Else if dp[i-1][j] > dp[i][j-1]:
       * Move up (i--)
   - Else:
       * Move left (j--)
4. Reverse the result string
```

## Detailed Example

**str1 = "aed", str2 = "acd"**

### DP Table:
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

### Backtracking Process:
```
Start: i=3, j=3, result=""

Step 1: str1[2]='d', str2[2]='d' → Match!
  result = "d"
  Move diagonal: i=2, j=2

Step 2: str1[1]='e', str2[1]='c' → No match
  dp[1][2]=1, dp[2][1]=1 → Equal, move left
  i=2, j=1

Step 3: str1[1]='e', str2[0]='a' → No match
  dp[1][1]=1, dp[2][0]=0 → Move up
  i=1, j=1

Step 4: str1[0]='a', str2[0]='a' → Match!
  result = "da"
  Move diagonal: i=0, j=0

Reverse result: "ad" ✅
```

## Dry Run with Visualization

**str1 = "AGGTAB", str2 = "GXTXAYB"**

### DP Table:
```
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

### Backtracking Path:
```
(6,7) 'B'=='B' → Match → result="B" → (5,6)
(5,6) 'A'!='Y' → dp[4][6]=3 > dp[5][5]=3 → (4,6)
(4,6) 'T'!='Y' → dp[3][6]=2 > dp[4][5]=3 → (4,5)
(4,5) 'T'!='A' → dp[3][5]=2 > dp[4][4]=2 → (3,5)
(3,5) 'G'!='A' → dp[2][5]=1 < dp[3][4]=2 → (3,4)
(3,4) 'G'!='X' → dp[2][4]=1 == dp[3][3]=1 → (2,4)
(2,4) 'G'!='X' → dp[1][4]=0 < dp[2][3]=1 → (2,3)
(2,3) 'G'!='T' → dp[1][3]=0 < dp[2][2]=1 → (2,2)
(2,2) 'G'!='X' → dp[1][2]=0 < dp[2][1]=1 → (2,1)
(2,1) 'G'=='G' → Match → result="BG" → (1,0)
(1,0) i=1, j=0 → Stop

Wait, let me recalculate...

Actually: (6,7) → B → (5,6) → A → (4,5) → T → (3,4) → G → (2,3)
Result (reversed): "GTAB" ✅
```

## Code Walkthrough

```java
// After building dp table
int i = n, j = m;
String result = "";

while (i > 0 && j > 0) {
    if (str1.charAt(i-1) == str2.charAt(j-1)) {
        result += str1.charAt(i-1);  // Add to result
        i--; j--;                     // Move diagonal
    } 
    else if (dp[i-1][j] > dp[i][j-1]) {
        i--;  // Move up
    } 
    else {
        j--;  // Move left
    }
}

// Reverse the result
result = new StringBuilder(result).reverse().toString();
```

## Code Implementation
See [PrintLCS.java](PrintLCS.java) for complete implementation.

## Key Insights
- Two-phase approach: Build table, then backtrack
- Backtracking follows the path of optimal choices
- Characters are added in reverse order (need to reverse at end)
- When dp values are equal, can move either direction (multiple LCS possible)
- Space can be optimized but makes backtracking harder

## Time & Space Complexity

| Phase | Time | Space |
|-------|------|-------|
| Build DP | O(m × n) | O(m × n) |
| Backtrack | O(m + n) | O(min(m, n)) |
| **Total** | **O(m × n)** | **O(m × n)** |

## Related Problems
- Longest Common Subsequence (Length only)
- Shortest Common Supersequence (Print)
- Longest Palindromic Subsequence
- Edit Distance (Print operations)

## Common Mistakes
1. ❌ Forgetting to reverse the result string
2. ❌ Using wrong comparison (str1[i] instead of str1[i-1])
3. ❌ Not handling equal dp values correctly
4. ❌ Going out of bounds (i < 0 or j < 0)

## Variations
- Print all LCS (use BFS/DFS when dp values are equal)
- Print lexicographically smallest LCS
- Print LCS with minimum ASCII sum
