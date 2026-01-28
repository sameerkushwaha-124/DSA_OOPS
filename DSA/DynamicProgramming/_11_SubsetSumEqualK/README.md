# Subset Sum Equal to K

## Problem Statement
Given an array of non-negative integers and a target sum K, determine if there exists a subset of the array whose sum equals K.

**Input:**
- `arr[]` - Array of integers
- `k` - Target sum

**Output:**
- `true` if subset exists, `false` otherwise

**Example:**
```
Input: arr = [100, 2, 3, 1, 2], k = 100
Output: true
Explanation: Subset {100} has sum = 100
```

## Solution Approaches

### 1. Recursion
**Approach:** Try all possible subsets by including/excluding each element.

**Time Complexity:** O(2^n)  
**Space Complexity:** O(n) - recursion stack

**Recursion Tree (arr=[2,3,1], k=3):**
```
                    f(2, 3)
                   /        \
            f(1, 3)          f(1, 2)
           /      \         /      \
      f(0, 3)  f(0, 2)  f(0, 3)  f(0, 1)
         ❌      ❌       ❌       ✅
```

**Dry Run:**
```
arr = [2, 3, 1], k = 3, idx = 2
├─ Take arr[2]=1: f(1, 2)
│  ├─ Take arr[1]=3: f(0, -1) → false
│  └─ Not take: f(0, 2) → arr[0]=2 → true ✅
└─ Not take: f(1, 3)
   ├─ Take arr[1]=3: f(0, 0) → true ✅
   └─ Not take: f(0, 3) → false
```

---

### 2. Memoization (Top-Down DP)
**Approach:** Store results of subproblems in dp[idx][k] to avoid recomputation.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(n × k) + O(n) recursion stack

**DP State:** `dp[i][j]` = Can we achieve sum j using elements from index 0 to i?

**Dry Run:**
```
arr = [2, 3, 1], k = 3
dp[2][3] → Check if sum 3 possible with arr[0..2]
├─ Take arr[2]=1: dp[1][2]
│  └─ Take arr[1]=3: dp[0][-1] → false
│  └─ Not take: dp[0][2] → true (arr[0]=2)
└─ Result: true
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(n × k)

**DP Table (arr=[2,3,1], k=3):**
```
       0   1   2   3
    ┌───┬───┬───┬───┐
  0 │ T │ F │ T │ F │
    ├───┼───┼───┼───┤
  1 │ T │ F │ T │ T │
    ├───┼───┼───┼───┤
  2 │ T │ T │ T │ T │
    └───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[i][0] = true (sum 0 always possible)
      dp[0][2] = true (arr[0] = 2)

i=1, target=3:
  notTake = dp[0][3] = false
  take = dp[0][0] = true (3-3=0)
  dp[1][3] = true

i=2, target=3:
  notTake = dp[1][3] = true
  take = dp[1][2] = true (3-1=2)
  dp[2][3] = true ✅
```

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr) instead of 2D table.

**Time Complexity:** O(n × k)  
**Space Complexity:** O(k)

**Optimization:** Since dp[i] only depends on dp[i-1], we only need previous row.

**Dry Run:**
```
arr = [2, 3, 1], k = 3

prev = [T, F, T, F]  // after arr[0]=2

i=1 (arr[1]=3):
curr = [T, F, T, T]  // can make 3 now
prev = curr

i=2 (arr[2]=1):
curr = [T, T, T, T]  // can make 1,2,3
prev = curr

Result: prev[3] = true ✅
```

## Code Implementation
See [SubsetSum.java](SubsetSum.java) for complete implementation with all approaches.

## Key Insights
- Classic 0/1 Knapsack variant
- Each element can be taken or not taken (binary choice)
- Base cases: k=0 always true, idx=0 check if arr[0]==k
- Space optimization reduces memory from O(n×k) to O(k)
