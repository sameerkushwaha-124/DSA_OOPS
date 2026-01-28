# Rat in a Maze

## 📝 Problem
Find paths in maze (1=path, 0=blocked): Moves - Down(D), Left(L), Right(R), Up(U)

## 💡 Core Concept
- **Base Case**: reached (n-1, n-1) → add path
- **Pruning**: out of bounds, blocked, or visited → return
- **Backtracking**: Mark visited → Recurse → Unmark
- **Pattern**: 4-directional maze traversal

## 🔄 Recursion Tree
```
Maze:
1 0 0 0
1 1 0 1
0 1 0 0
1 1 1 1

                findPaths(0,0, "", visited)
                /      |      |      \
               D       L      R      U
              /        ✗      ✗      ✗
        (1,0,"D")
         /    |    |    \
        D     L    R     U
        ✗     ✗    |     ✗
              (1,1,"DR")
               /    |    |    \
              D     L    R     U
              |     ✗    ✗     ✗
          (2,1,"DRD")
              |
              D
              |
          (3,1,"DRDD")
              |
              R
              |
          (3,2,"DRDDR")
              |
              R
              |
          (3,3,"DRDDRR") ✓
```

## ⚡ Complexity
- **Time**: O(4^(n²)) - 4 choices per cell
- **Space**: O(n²) - visited array + depth

## 🎯 Key Points
- Track visited to avoid cycles
- Backtrack: mark visited → recurse → unmark
- 4 directions: D(↓), L(←), R(→), U(↑)
- Check boundaries and obstacles

## 💻 Implementation
```java
public static void findPaths(int[][] maze, int row, int col, String path, boolean[][] visited, List<String> paths) {
    int n = maze.length;
    
    if (row < 0 || row >= n || col < 0 || col >= n || maze[row][col] == 0 || visited[row][col]) {
        return;
    }
    
    if (row == n - 1 && col == n - 1) {
        paths.add(path);
        return;
    }
    
    visited[row][col] = true;
    
    findPaths(maze, row + 1, col, path + "D", visited, paths);
    findPaths(maze, row, col - 1, path + "L", visited, paths);
    findPaths(maze, row, col + 1, path + "R", visited, paths);
    findPaths(maze, row - 1, col, path + "U", visited, paths);
    
    visited[row][col] = false;
}
```