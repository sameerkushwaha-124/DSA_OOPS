# N-Queens

## 📝 Problem
Place N queens on N×N board so no two attack each other (same row/column/diagonal)

## 💡 Core Concept
- **Base Case**: row = N → solution found
- **Recursive**: Try each column in current row
- **Constraint**: Check if placement is safe
- **Backtracking**: Place Q → Recurse → Remove Q
- **Pattern**: Constraint satisfaction

## 🔄 Recursion Tree (4-Queens)
```
                    solveNQueens(row=0)
        /           |           |           \
    Col 0       Col 1       Col 2       Col 3
    Q...        .Q..        ..Q.        ...Q
     |           |           |           |
  Row 1       Row 1       Row 1       Row 1
  (all ✗)   Try cols    (all ✗)     (all ✗)
              |
           Col 3 ✓
           .Q..
           ...Q
              |
           Row 2
           Try cols
              |
           Col 0 ✓
           .Q..
           ...Q
           Q...
              |
           Row 3
           Try cols
              |
           Col 2 ✓
           .Q..
           ...Q
           Q...
           ..Q.  ✓ Solution!
```

## ⚡ Complexity
- **Time**: O(N!) - with pruning
- **Space**: O(N²) - board + O(N) depth

## 🎯 Key Points
- Place one queen per row
- Check 3 constraints: column, left diagonal, right diagonal
- Prune invalid placements early
- 4-Queens: 2 solutions, 8-Queens: 92 solutions

## 💻 Implementation
```java
public static List<List<String>> solveNQueens(int n) {
    List<List<String>> solutions = new ArrayList<>();
    char[][] board = new char[n][n];
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            board[i][j] = '.';
        }
    }
    
    solveNQueensHelper(board, 0, solutions);
    return solutions;
}

private static void solveNQueensHelper(char[][] board, int row, List<List<String>> solutions) {
    if (row == board.length) {
        solutions.add(constructSolution(board));
        return;
    }

    for (int col = 0; col < board.length; col++) {
        if (isSafe(board, row, col)) {
            board[row][col] = 'Q';
            solveNQueensHelper(board, row + 1, solutions);
            board[row][col] = '.';
        }
    }
}

private static boolean isSafe(char[][] board, int row, int col) {
    int n = board.length;

    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }

    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }

    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }

    return true;
}
```