# Unbounded Knapsack

## Problem Statement
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value. You can take unlimited quantity of each item (unbounded).

**Input:**
- `val[]` - Array of values
- `wt[]` - Array of weights
- `capacity` - Maximum weight capacity

**Output:**
- Maximum value achievable

**Example:**
```
Input: val = [6, 1, 7, 7], wt = [1, 3, 4, 5], capacity = 8
Output: 48
Explanation: Take item 0 eight times (8 × 6 = 48)
```

## Difference from 0/1 Knapsack

| Aspect | 0/1 Knapsack | Unbounded Knapsack |
|--------|--------------|-------------------|
| Item usage | Once | Unlimited times |
| After taking | Move to next item | Stay at same item |
| Recurrence | `f(i-1, w-wt[i])` | `f(i, w-wt[i])` |

## Solution Approaches

### 1. Recursion
**Approach:** Try taking each item multiple times.

**Time Complexity:** O(2^capacity)  
**Space Complexity:** O(capacity) - recursion stack

**Recursion Tree (val=[6,1], wt=[1,3], capacity=4):**
```
                    f(1, 4)
                   /        \
            f(0, 4)          1+f(1, 1)
           /      \         /        \
      f(-1,4)  6+f(0,3)  f(0,1)   1+f(1,-2)
        0      6+...     6+...       0
        
Maximum = 24 (take item 0 four times)
```

**Dry Run:**
```
val = [6, 1], wt = [1, 3], capacity = 4, idx = 1

Base case (idx=0):
  return (capacity / wt[0]) × val[0]
  = (4 / 1) × 6 = 24

f(1, 4):
├─ Not take wt[1]=3: f(0, 4) = 24
└─ Take wt[1]=3: val[1] + f(1, 1)
   └─ f(1, 1):
      ├─ Not take: f(0, 1) = 6
      └─ Take: val[1] + f(1, -2) = invalid
      Result: max(6, invalid) = 6
   Total: 1 + 6 = 7

Final: max(24, 7) = 24 ✅
```

**Key Point:** When taking item, stay at same index (idx) for unlimited supply.

---

### 2. Memoization (Top-Down DP)
**Approach:** Store maximum value in dp[idx][capacity].

**Time Complexity:** O(n × capacity)  
**Space Complexity:** O(n × capacity) + O(capacity) recursion stack

**DP State:** `dp[i][w]` = Maximum value using items[0..i] with capacity w

**Dry Run:**
```
val = [6, 1], wt = [1, 3], capacity = 4

dp[1][4]:
├─ Not take: dp[0][4] = 24
└─ Take: 1 + dp[1][1]
   └─ dp[1][1]:
      ├─ Not take: dp[0][1] = 6
      └─ Take: 1 + dp[1][-2] = invalid
      Result: 6
   Total: 1 + 6 = 7

Final: max(24, 7) = 24 ✅
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(n × capacity)  
**Space Complexity:** O(n × capacity)

**DP Table (val=[6,1], wt=[1,3], capacity=4):**
```
       0   1   2   3   4
    ┌───┬───┬───┬───┬───┐
  0 │ 0 │ 6 │12 │18 │24 │
    ├───┼───┼───┼───┼───┤
  1 │ 0 │ 6 │12 │18 │24 │
    └───┴───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[0][w] = (w / wt[0]) × val[0]
      dp[0][4] = (4 / 1) × 6 = 24

i=1 (val=1, wt=3), weight=4:
  notTake = dp[0][4] = 24
  take = val[1] + dp[1][1] = 1 + 6 = 7
  dp[1][4] = max(24, 7) = 24 ✅

Note: dp[1][1] uses dp[1][0] (same row), not dp[0][0]
```

**Important:** For unbounded knapsack:
- Use `dp[i][weight - wt[i]]` (same row)
- NOT `dp[i-1][weight - wt[i]]` (previous row)

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr).

**Time Complexity:** O(n × capacity)  
**Space Complexity:** O(capacity)

**Dry Run:**
```
val = [6, 1], wt = [1, 3], capacity = 4

prev = [0, 6, 12, 18, 24]  // after item 0

i=1 (val=1, wt=3):
curr[0] = 0
curr[1] = max(prev[1], invalid) = 6
curr[2] = max(prev[2], invalid) = 12
curr[3] = max(prev[3], 1+curr[0]) = max(18, 1) = 18
curr[4] = max(prev[4], 1+curr[1]) = max(24, 7) = 24 ✅

Result: curr[4] = 24
```

**Key:** Use `curr[weight - wt[i]]` not `prev[weight - wt[i]]`

## Detailed Example

**val = [15, 14, 10, 45, 30], wt = [2, 5, 1, 3, 4], capacity = 7**

```
Optimal: Take item 3 twice (45 + 45 = 90) with weight 6
         Take item 2 once (10) with weight 1
         Total: 90 + 10 = 100, weight = 7

DP Table (partial):
       0   1   2   3   4   5   6   7
    ┌───┬───┬───┬───┬───┬───┬───┬───┐
  0 │ 0 │ 0 │15 │15 │30 │30 │45 │45 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
  1 │ 0 │ 0 │15 │15 │30 │30 │45 │45 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
  2 │ 0 │10 │20 │30 │40 │50 │60 │70 │
    ├───┼───┼───┼───┼───┼───┼───┼───┤
  3 │ 0 │10 │20 │45 │55 │65 │90 │100│
    └───┴───┴───┴───┴───┴───┴───┴───┘
```

## Code Implementation
See [UKnapsack.java](UKnapsack.java) for complete implementation with all approaches.

## Key Insights
- Unbounded = Unlimited supply of each item
- When taking item, stay at same index (not i-1)
- Base case: Take first item as many times as possible
- Greedy doesn't work (need to try all combinations)
- Space optimization reduces memory from O(n×W) to O(W)

## Related Problems
- Coin Change (Minimum Coins)
- Coin Change II (Count Ways)
- Rod Cutting
- Minimum Cost to Cut Rod

## Pattern Recognition
If problem has:
- ✅ Unlimited supply of items
- ✅ Maximize/minimize/count something
- ✅ Capacity constraint

Then use Unbounded Knapsack pattern!
