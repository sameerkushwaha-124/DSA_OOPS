# Path Finding in Grid

## 📝 Problem
Paths from (0,0) to (m-1,n-1): Moves allowed - Down(V), Right(H), Diagonal(D)

## 💡 Core Concept
- **Base Case**: reached (m-1, n-1) → add path
- **Recursive**: Try 3 moves - V, H, D
- **Pattern**: Grid traversal with multiple directions

## 🔄 Recursion Tree (2×2 grid)
```
                    findPaths(0,0, "")
                /         |         \
            Move V     Move H     Move D
              /           |           \
        (1,0,"V")    (0,1,"H")    (1,1,"D")
          /    \        /    \         |
      Move H  Move D  Move V Move D  Reached
        /        \      /       \       ✓ "D"
    (1,1,"VH") (1,1,"VD") (1,1,"HV") (1,1,"HD")
       ✓ "VH"    ✓ "VD"    ✓ "HV"    ✓ "HD"
```

## ⚡ Complexity
- **Time**: O(3^(m+n)) - 3 choices per cell
- **Space**: O(m+n) - max depth

## 🎯 Key Points
- 3 possible moves from each cell
- Without diagonal: C(m+n-2, m-1) paths
- With diagonal: many more paths
- Can optimize with DP

## 💻 Implementation

### Find All Paths
```java
public static void findAllPaths(List<String> result, int m, int n, int i, int j, String path) {
    if (i == m - 1 && j == n - 1) {
        result.add(path);
        return;
    }

    if (i < m - 1) {
        findAllPaths(result, m, n, i + 1, j, path + "V");
    }

    if (j < n - 1) {
        findAllPaths(result, m, n, i, j + 1, path + "H");
    }

    if (i < m - 1 && j < n - 1) {
        findAllPaths(result, m, n, i + 1, j + 1, path + "D");
    }
}
```

### Count Paths
```java
public static int countPaths(int m, int n, int i, int j) {
    if (i == m - 1 && j == n - 1) {
        return 1;
    }

    if (i >= m || j >= n) {
        return 0;
    }

    int paths = 0;
    paths += countPaths(m, n, i + 1, j);
    paths += countPaths(m, n, i, j + 1);
    paths += countPaths(m, n, i + 1, j + 1);

    return paths;
}
```