# Minimum Coins (Coin Change)

## Problem Statement
Given an array of coin denominations and a target amount, find the minimum number of coins needed to make that amount. You have infinite supply of each coin. Return -1 if amount cannot be made.

**Input:**
- `coins[]` - Array of coin denominations
- `amount` - Target amount

**Output:**
- Minimum number of coins needed, or -1 if impossible

**Example:**
```
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1 (3 coins)
```

## Solution Approaches

### 1. Recursion
**Approach:** Try taking each coin multiple times (unbounded knapsack).

**Time Complexity:** O(2^amount) - exponential  
**Space Complexity:** O(amount) - recursion stack

**Recursion Tree (coins=[1,2], amount=4):**
```
                    f(1, 4)
                   /        \
            f(0, 4)          1+f(1, 2)
           /      \         /        \
      f(-1,4)  1+f(0,3)  f(0,2)   1+f(1,0)
        ∞      1+...     1+...       1+0
        
Minimum = 2 coins (2+2)
```

**Dry Run:**
```
coins = [1, 2], amount = 4, idx = 1

Base case (idx=0):
  if amount % coins[0] == 0: return amount/coins[0]
  else: return ∞

f(1, 4):
├─ Not take coin[1]=2: f(0, 4) = 4/1 = 4 coins
└─ Take coin[1]=2: 1 + f(1, 2)
   ├─ Not take: f(0, 2) = 2/1 = 2 coins
   └─ Take: 1 + f(1, 0) = 1 + 0 = 1 coin
   Result: min(2, 1) = 1
   Total: 1 + 1 = 2 coins

Final: min(4, 2) = 2 ✅
```

**Key Point:** When taking a coin, stay at same index (idx) because infinite supply.

---

### 2. Memoization (Top-Down DP)
**Approach:** Store minimum coins needed in dp[idx][amount].

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(n × amount) + O(amount) recursion stack

**DP State:** `dp[i][j]` = Minimum coins needed using coins[0..i] to make amount j

**Dry Run:**
```
coins = [1, 2], amount = 4

dp[1][4]:
├─ Not take: dp[0][4] = 4
└─ Take: 1 + dp[1][2]
   └─ dp[1][2]:
      ├─ Not take: dp[0][2] = 2
      └─ Take: 1 + dp[1][0] = 1
      Result: min(2, 1) = 1
   Total: 1 + 1 = 2

Final: min(4, 2) = 2 ✅
```

---

### 3. Tabulation (Bottom-Up DP)
**Approach:** Build dp table iteratively from base cases.

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(n × amount)

**DP Table (coins=[1,2], amount=4):**
```
       0   1   2   3   4
    ┌───┬───┬───┬───┬───┐
  0 │ 0 │ 1 │ 2 │ 3 │ 4 │
    ├───┼───┼───┼───┼───┤
  1 │ 0 │ 1 │ 1 │ 2 │ 2 │
    └───┴───┴───┴───┴───┘
```

**Dry Run:**
```
Base: dp[0][target] = target/coins[0] if divisible, else ∞

i=1 (coin=2), target=4:
  notTake = dp[0][4] = 4
  take = 1 + dp[1][2] = 1 + 1 = 2
  dp[1][4] = min(4, 2) = 2 ✅

Note: dp[1][2] uses dp[1][0] (same row), not dp[0][0]
```

**Important:** For unbounded knapsack, when taking item:
- Use `dp[i][target - coins[i]]` (same row)
- NOT `dp[i-1][target - coins[i]]` (previous row)

---

### 4. Space Optimized
**Approach:** Use only two 1D arrays (prev, curr).

**Time Complexity:** O(n × amount)  
**Space Complexity:** O(amount)

**Dry Run:**
```
coins = [1, 2], amount = 4

prev = [0, 1, 2, 3, 4]  // after coin=1

i=1 (coin=2):
curr[0] = 0
curr[1] = min(prev[1], ∞) = 1
curr[2] = min(prev[2], 1+curr[0]) = min(2, 1) = 1
curr[3] = min(prev[3], 1+curr[1]) = min(3, 2) = 2
curr[4] = min(prev[4], 1+curr[2]) = min(4, 2) = 2 ✅

Result: curr[4] = 2
```

**Key:** Use `curr[target - coins[i]]` not `prev[target - coins[i]]`

## Code Implementation
See [MinCoin.java](MinCoin.java) for complete implementation with all approaches.

## Key Insights
- Unbounded Knapsack variant (infinite supply of coins)
- Greedy approach doesn't work (e.g., coins=[1,3,4], amount=6)
- Use INF (10^9) to mark impossible states
- When taking coin, stay at same index (infinite supply)
- Return -1 if result >= INF
- Similar problems: Rod Cutting, Unbounded Knapsack
