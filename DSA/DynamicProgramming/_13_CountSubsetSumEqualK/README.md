# Count Subsets with Sum Equal to K

## Problem Statement
Given an array of integers and a target sum K, count the number of subsets whose sum equals K.

**Input:**
- `arr[]` - Array of integers (can contain 0)
- `k` - Target sum

**Output:**
- Count of subsets with sum = k

**Example:**
```
Input: arr = [1, 2, 3], k = 3
Output: 2
Explanation: Subsets {1,2} and {3} both have sum = 3
```

## Solution Approaches

### 1. Recursion
**Approach:** Count all possible subsets by including/excluding each element.

**Time Complexity:** O(2^n)  
**Space Complexity:** O(n) - recursion stack

**Recursion Tree (arr=[1,2,3], k=3):**
```
                    f(2, 3)
                   /        \
            f(1, 0)          f(1, 2)
           /      \         /      \
      f(0, 0)  f(0,-1)  f(0, 0)  f(0, 1)
        1       0        1       0
        
Total = 1 + 1 = 2 subsets
```

**Dry Run:**
```
arr = [1, 2, 3], k = 3, idx = 2
├─ Pick arr[2]=3: f(1, 0)
│  ├─ Pick arr[1]=2: f(0, -2) → 0
│  └─ Not pick: f(0, 0) → 1 ✅
└─ Not pick: f(1, 3)
   ├─ Pick arr[1]=2: f(0, 1)
   │  └─ arr[0]=1 → 1 ✅
   └─ Not pick: f(0, 3) → 0

Total count = 1 + 1 = 2
```

**Special Case (arr[0] = 0):**
```
If k=0 and arr[0]=0: return 2
  - One subset: {} (empty)
  - Another subset: {0}
```

---

### 2. Memoization (Top-Down DP)
**Approach:** Store count of subsets in dp[idx][k] to avoid recomputation.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(n × k) + O(n) recursion stack

**DP State:** `dp[i][j]` = Number of subsets using arr[0..i] with sum = j

**Dry Run:**
```
arr = [1, 2, 3], k = 3
dp[2][3] → Count subsets with sum 3 using arr[0..2]
├─ Pick arr[2]=3: dp[1][0] = 1
└─ Not pick: dp[1][3]
   ├─ Pick arr[1]=2: dp[0][1] = 1
   └─ Not pick: dp[0][3] = 0
Result: 1 + 1 = 2
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(n × k)

**DP Table (arr=[1,2,3], k=3):**
```
       0   1   2   3
    ┌───┬───┬───┬───┐
  0 │ 1 │ 1 │ 0 │ 0 │
    ├───┼───┼───┼───┤
  1 │ 1 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┤
  2 │ 1 │ 1 │ 1 │ 2 │
    └───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[i][0] = 1 (empty subset)
      dp[0][1] = 1 (arr[0] = 1)

i=1, target=3:
  notPick = dp[0][3] = 0
  pick = dp[0][1] = 1 (3-2=1)
  dp[1][3] = 0 + 1 = 1

i=2, target=3:
  notPick = dp[1][3] = 1
  pick = dp[1][0] = 1 (3-3=0)
  dp[2][3] = 1 + 1 = 2 ✅
```

**Special Base Case Handling:**
```
if arr[0] == 0:
    dp[0][0] = 2  // {} and {0}
else:
    dp[0][0] = 1  // {}
    if arr[0] <= k:
        dp[0][arr[0]] = 1
```

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr) instead of 2D table.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(k)

**Dry Run:**
```
arr = [1, 2, 3], k = 3

prev = [1, 1, 0, 0]  // after arr[0]=1

i=1 (arr[1]=2):
curr = [1, 1, 1, 1]  // can make 0,1,2,3
prev = curr

i=2 (arr[2]=3):
curr[3] = prev[3] + prev[0] = 1 + 1 = 2 ✅
prev = curr

Result: prev[3] = 2
```

## Code Implementation
See [CountSubsetSum.java](CountSubsetSum.java) for complete implementation with all approaches.

## Key Insights
- Extension of Subset Sum problem - count instead of boolean
- Handle zero elements carefully (special base case)
- Add counts from both pick and not-pick choices
- Space optimization reduces memory from O(n×k) to O(k)
- Important for problems like Target Sum, Partition problems
