# Target Sum (Ways to Achieve Target)

## Problem Statement
Given an array of integers and a target value, assign '+' or '-' sign to each element such that the sum equals target. Count the number of ways to do this.

**Input:**
- `nums[]` - Array of integers
- `target` - Target sum

**Output:**
- Number of ways to achieve target

**Example:**
```
Input: nums = [1, 1, 1, 1, 1], target = 3
Output: 5
Explanation: 
+1+1+1+1-1 = 3
+1+1+1-1+1 = 3
+1+1-1+1+1 = 3
+1-1+1+1+1 = 3
-1+1+1+1+1 = 3
```

## Problem Transformation

### Mathematical Insight
Let's say:
- S1 = sum of elements with '+' sign
- S2 = sum of elements with '-' sign

Then:
```
S1 - S2 = target          ... (1)
S1 + S2 = totalSum        ... (2)

Adding (1) and (2):
2×S1 = target + totalSum
S1 = (target + totalSum) / 2
```

**Problem reduces to:** Count subsets with sum = (totalSum - target) / 2

## Solution Approaches

### Mathematical Validation
```
totalSum = sum of all elements
requiredSum = (totalSum - target) / 2

Conditions for valid solution:
1. totalSum - target >= 0
2. (totalSum - target) % 2 == 0
```

**Example:**
```
nums = [1, 1, 1, 1, 1], target = 3
totalSum = 5
requiredSum = (5 - 3) / 2 = 1

Count subsets with sum = 1
Subsets: {1}, {1}, {1}, {1}, {1}
Answer: 5 ways ✅
```

### Recursion Tree (nums=[1,1,2], target=0)
```
totalSum = 4, requiredSum = (4-0)/2 = 2

                    f(2, 2)
                   /        \
            f(1, 0)          f(1, 1)
           /      \         /      \
      f(0, 0)  f(0,-1)  f(0, 0)  f(0, 0)
        2       0        2       2
        
Total = 2 + 2 + 2 = 6 ways
```

**Dry Run:**
```
nums = [1, 1, 2], target = 0
totalSum = 4, k = 2

f(2, 2):
├─ Pick nums[2]=2: f(1, 0)
│  ├─ Pick nums[1]=1: f(0, -1) → 0
│  └─ Not pick: f(0, 0) → 2 (nums[0]=0 case)
└─ Not pick: f(1, 2)
   ├─ Pick nums[1]=1: f(0, 1) → 1
   └─ Not pick: f(0, 2) → 0

Total = 2 + 1 = 3 ways
```

### Time & Space Complexity

**1. Memoization:**
- Time: O(n × k) where k = (totalSum - target) / 2
- Space: O(n × k) + O(n) recursion stack

**2. Tabulation:**
- Time: O(n × k)
- Space: O(n × k)

**3. Space Optimized:**
- Time: O(n × k)
- Space: O(k)

### DP Table (nums=[1,1,2], k=2)
```
       0   1   2
    ┌───┬───┬───┐
  0 │ 1 │ 1 │ 0 │
    ├───┼───┼───┤
  1 │ 1 │ 2 │ 1 │
    ├───┼───┼───┤
  2 │ 1 │ 2 │ 3 │
    └───┴───┴───┘
```

**Dry Run (Tabulation):**
```
Base: dp[0][0] = 1, dp[0][1] = 1 (nums[0]=1)

i=1, target=2:
  notPick = dp[0][2] = 0
  pick = dp[0][1] = 1 (2-1=1)
  dp[1][2] = 0 + 1 = 1

i=2, target=2:
  notPick = dp[1][2] = 1
  pick = dp[1][0] = 1 (2-2=0)
  dp[2][2] = 1 + 1 = 2 ✅
```

### Space Optimized Dry Run
```
nums = [1, 1, 2], k = 2

prev = [1, 1, 0]  // after nums[0]=1

i=1 (nums[1]=1):
curr = [1, 2, 1]
prev = curr

i=2 (nums[2]=2):
curr[2] = prev[2] + prev[0] = 1 + 1 = 2 ✅
prev = curr

Result: prev[2] = 2
```

## Code Implementation
See [TargetSum.java](TargetSum.java) for complete implementation with all approaches.

## Key Insights
- Transform problem to "Count Subsets with Sum K"
- Mathematical formula: S1 = (totalSum - target) / 2
- Check validity: (totalSum - target) must be non-negative and even
- Handle zeros carefully in base case
- Space optimization reduces memory from O(n×k) to O(k)
- Classic example of problem transformation in DP

## Related Problems
- Partition Equal Subset Sum
- Count Partition with Given Difference
- Subset Sum Equal to K
