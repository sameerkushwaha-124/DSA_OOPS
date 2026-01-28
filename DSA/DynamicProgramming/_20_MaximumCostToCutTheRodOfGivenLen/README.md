# Rod Cutting Problem

## Problem Statement
Given a rod of length N and an array of prices where price[i] denotes the price of a rod of length (i+1). Find the maximum value obtainable by cutting the rod and selling the pieces.

**Input:**
- `price[]` - Array where price[i] = price of rod of length (i+1)
- `rodLength` - Length of the rod (N)

**Output:**
- Maximum profit obtainable

**Example:**
```
Input: price = [1, 5, 8, 9], rodLength = 4
Output: 10
Explanation: Cut into two pieces of length 2 each (5 + 5 = 10)
```

## Problem Analysis

**Available cuts:**
```
price[0] = 1  → length 1
price[1] = 5  → length 2
price[2] = 8  → length 3
price[3] = 9  → length 4
```

**Possible ways to cut rod of length 4:**
```
1. No cut: length 4 → price = 9
2. Cut 1+3: → price = 1 + 8 = 9
3. Cut 2+2: → price = 5 + 5 = 10 ✅
4. Cut 1+1+2: → price = 1 + 1 + 5 = 7
5. Cut 1+1+1+1: → price = 1 + 1 + 1 + 1 = 4
```

## Connection to Unbounded Knapsack

This is **Unbounded Knapsack** where:
- Items = Different rod lengths [1, 2, 3, ..., N]
- Values = price[0], price[1], ..., price[N-1]
- Weights = 1, 2, 3, ..., N
- Capacity = rodLength

## Solution Approaches

### 1. Recursion
**Approach:** Try cutting rod at each possible length multiple times.

**Time Complexity:** O(2^N)  
**Space Complexity:** O(N) - recursion stack

**Recursion Tree (price=[1,5,8,9], rodLen=4):**
```
                    f(3, 4)
                   /        \
            f(2, 4)          9+f(3, 0)
           /      \              9
      f(1, 4)  8+f(2, 1)
     /      \      
f(0,4)  5+f(1,2)
  4     5+...
  
Maximum = 10
```

**Dry Run:**
```
price = [1, 5, 8, 9], rodLen = 4, idx = 3

Base case (idx=0):
  return rodLen × price[0]
  = 4 × 1 = 4

f(3, 4):
├─ Not take length 4: f(2, 4)
│  └─ f(2, 4):
│     ├─ Not take: f(1, 4)
│     │  └─ f(1, 4):
│     │     ├─ Not take: f(0, 4) = 4
│     │     └─ Take len 2: 5 + f(1, 2)
│     │        └─ 5 + (5 + f(1, 0)) = 10 ✅
│     └─ Take len 3: 8 + f(2, 1) = 8 + 1 = 9
└─ Take length 4: 9 + f(3, 0) = 9

Final: max(10, 9) = 10 ✅
```

**Key Point:** currRodLen = idx + 1 (because array is 0-indexed)

---

### 2. Memoization (Top-Down DP)
**Approach:** Store maximum profit in dp[idx][rodLen].

**Time Complexity:** O(N × rodLength)  
**Space Complexity:** O(N × rodLength) + O(rodLength) recursion stack

**DP State:** `dp[i][len]` = Maximum profit using rod lengths[0..i] with remaining length = len

**Dry Run:**
```
price = [1, 5, 8, 9], rodLen = 4

dp[3][4]:
├─ Not take: dp[2][4]
│  └─ Eventually evaluates to 10
└─ Take: 9 + dp[3][0] = 9 + 0 = 9

Final: max(10, 9) = 10 ✅
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(N × rodLength)  
**Space Complexity:** O(N × rodLength)

**DP Table (price=[1,5,8,9], rodLen=4):**
```
       0   1   2   3   4
    ┌───┬───┬───┬───┬───┐
  0 │ 0 │ 1 │ 2 │ 3 │ 4 │
    ├───┼───┼───┼───┼───┤
  1 │ 0 │ 1 │ 5 │ 6 │10 │
    ├───┼───┼───┼───┼───┤
  2 │ 0 │ 1 │ 5 │ 8 │10 │
    ├───┼───┼───┼───┼───┤
  3 │ 0 │ 1 │ 5 │ 8 │10 │
    └───┴───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[0][rl] = rl × price[0]
      dp[0][4] = 4 × 1 = 4

i=1 (len=2, price=5), rl=4:
  notTake = dp[0][4] = 4
  take = price[1] + dp[1][2] = 5 + 5 = 10
  dp[1][4] = max(4, 10) = 10 ✅

i=2 (len=3, price=8), rl=4:
  notTake = dp[1][4] = 10
  take = price[2] + dp[2][1] = 8 + 1 = 9
  dp[2][4] = max(10, 9) = 10

i=3 (len=4, price=9), rl=4:
  notTake = dp[2][4] = 10
  take = price[3] + dp[3][0] = 9 + 0 = 9
  dp[3][4] = max(10, 9) = 10 ✅
```

**Important:** For unbounded knapsack:
- Use `dp[i][rl - currRodLen]` (same row)
- currRodLen = i + 1

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr).

**Time Complexity:** O(N × rodLength)  
**Space Complexity:** O(rodLength)

**Dry Run:**
```
price = [1, 5, 8, 9], rodLen = 4

prev = [0, 1, 2, 3, 4]  // after len=1

i=1 (len=2, price=5):
curr[0] = 0
curr[1] = max(prev[1], invalid) = 1
curr[2] = max(prev[2], 5+curr[0]) = max(2, 5) = 5
curr[3] = max(prev[3], 5+curr[1]) = max(3, 6) = 6
curr[4] = max(prev[4], 5+curr[2]) = max(4, 10) = 10 ✅

Result: curr[4] = 10
```

**Key:** Use `curr[rl - currRodLen]` not `prev[rl - currRodLen]`

## Detailed Example

**price = [3, 5, 8, 9, 10, 17, 17, 20], rodLen = 8**

```
Optimal: Cut into two pieces of length 2 and 6
         Price = 5 + 17 = 22

OR: Cut into four pieces of length 2 each
    Price = 5 + 5 + 5 + 5 = 20

Best: 22

DP approach will find: 22
```

## Code Implementation
See [RodCutting.java](RodCutting.java) for complete implementation with all approaches.

## Key Insights
- Classic Unbounded Knapsack variant
- Each rod length can be used multiple times
- currRodLen = idx + 1 (0-indexed array)
- When taking cut, stay at same index (unlimited cuts)
- Greedy doesn't work (need to try all combinations)
- Space optimization reduces memory from O(N²) to O(N)

## Related Problems
- Unbounded Knapsack
- Coin Change (Minimum Coins)
- Coin Change II (Count Ways)
- Maximum Ribbon Cut

## Pattern Recognition
Rod Cutting = Unbounded Knapsack where:
- Items = Rod lengths [1, 2, ..., N]
- Values = Prices
- Weights = Lengths
- Capacity = Total rod length

## Common Mistakes
1. ❌ Using `dp[i-1][rl-len]` instead of `dp[i][rl-len]`
2. ❌ Forgetting currRodLen = i + 1
3. ❌ Not handling base case properly (idx=0)
4. ❌ Using greedy approach (doesn't work!)
