# Coin Change II (Ways to Make Amount)

## Problem Statement
Given an array of coin denominations and a target amount, find the number of ways to make that amount using the coins. You have infinite supply of each coin.

**Input:**
- `coins[]` - Array of coin denominations
- `amount` - Target amount

**Output:**
- Number of ways to make the amount

**Example:**
```
Input: coins = [1, 2, 5], amount = 5
Output: 4
Explanation: 
5 = 5
5 = 2 + 2 + 1
5 = 2 + 1 + 1 + 1
5 = 1 + 1 + 1 + 1 + 1
```

## Solution Approaches

### 1. Recursion
**Approach:** Try taking each coin multiple times (unbounded knapsack).

**Time Complexity:** O(2^amount)  
**Space Complexity:** O(amount) - recursion stack

**Recursion Tree (coins=[1,2], amount=3):**
```
                    f(1, 3)
                   /        \
            f(0, 3)          f(1, 1)
              3             /      \
                      f(0, 1)    f(1, -1)
                         1          0
                         
Total ways = 3 + 1 = 4
```

**Dry Run:**
```
coins = [1, 2], amount = 3, idx = 1

Base case (idx=0):
  if amount % coins[0] == 0: return 1
  else: return 0

f(1, 3):
├─ Not take coin[1]=2: f(0, 3)
│  └─ 3 % 1 == 0 → return 1 way
└─ Take coin[1]=2: f(1, 1)
   ├─ Not take: f(0, 1) → 1 way
   └─ Take: f(1, -1) → 0 ways
   Result: 1 + 0 = 1 way

Final: 1 + 1 = 2 ways ✅
```

**Key Difference from Minimum Coins:**
- Here we COUNT ways (return 1 or 0)
- In Minimum Coins, we COUNT coins (return count)

---

### 2. Memoization (Top-Down DP)
**Approach:** Store number of ways in dp[idx][amount].

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(n × amount) + O(amount) recursion stack

**DP State:** `dp[i][j]` = Number of ways to make amount j using coins[0..i]

**Dry Run:**
```
coins = [1, 2], amount = 3

dp[1][3]:
├─ Not take: dp[0][3] = 1
└─ Take: dp[1][1]
   ├─ Not take: dp[0][1] = 1
   └─ Take: dp[1][-1] = 0
   Result: 1 + 0 = 1

Final: 1 + 1 = 2 ways ✅
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(n × amount)

**DP Table (coins=[1,2], amount=3):**
```
       0   1   2   3
    ┌───┬───┬───┬───┐
  0 │ 1 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┤
  1 │ 1 │ 1 │ 2 │ 2 │
    └───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[0][target] = 1 if target % coins[0] == 0, else 0

i=1 (coin=2), target=2:
  notTake = dp[0][2] = 1
  take = dp[1][0] = 1
  dp[1][2] = 1 + 1 = 2

i=1 (coin=2), target=3:
  notTake = dp[0][3] = 1
  take = dp[1][1] = 1
  dp[1][3] = 1 + 1 = 2 ✅
```

**Important:** For unbounded knapsack:
- Use `dp[i][target - coins[i]]` (same row)
- This allows reusing the same coin

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr).

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(amount)

**Dry Run:**
```
coins = [1, 2], amount = 3

prev = [1, 1, 1, 1]  // after coin=1

i=1 (coin=2):
curr[0] = 1
curr[1] = prev[1] + 0 = 1
curr[2] = prev[2] + curr[0] = 1 + 1 = 2
curr[3] = prev[3] + curr[1] = 1 + 1 = 2 ✅

Result: curr[3] = 2
```

**Key:** Use `curr[target - coins[i]]` not `prev[target - coins[i]]`

## Detailed Example

**coins = [1, 2, 5], amount = 5**

```
Ways:
1. 5
2. 2 + 2 + 1
3. 2 + 1 + 1 + 1
4. 1 + 1 + 1 + 1 + 1

DP Table:
       0   1   2   3   4   5
    ┌───┬───┬───┬───┬───┬───┐
  0 │ 1 │ 1 │ 1 │ 1 │ 1 │ 1 │
    ├───┼───┼───┼───┼───┼───┤
  1 │ 1 │ 1 │ 2 │ 2 │ 3 │ 3 │
    ├───┼───┼───┼───┼───┼───┤
  2 │ 1 │ 1 │ 2 │ 2 │ 3 │ 4 │
    └───┴───┴───┴───┴───┴───┘
```

## Code Implementation
See [WaysToGetTargetUsingCoinsOfINFAmount.java](WaysToGetTargetUsingCoinsOfINFAmount.java) for complete implementation.

## Key Insights
- Unbounded Knapsack variant (infinite supply)
- Count WAYS not minimum coins
- Base case: If amount divisible by first coin, return 1
- When taking coin, stay at same index (infinite supply)
- Add ways from both take and not-take choices
- Similar to: Minimum Coins, Unbounded Knapsack, Rod Cutting

## Comparison with Minimum Coins

| Aspect | Coin Change II | Minimum Coins |
|--------|---------------|---------------|
| Goal | Count ways | Minimize coins |
| Return | 1 or 0 | Count or INF |
| Combine | Add (take + notTake) | Min (take, notTake) |
| Base | 1 if divisible | amount/coin |
