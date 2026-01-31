# Sudoku Solver

## 📝 Problem
Fill 9×9 grid so each row, column, and 3×3 box has digits 1-9 exactly once

## 💡 Core Concept
- **Base Case**: row = 9 → solved
- **Skip**: If cell filled → move to next
- **Recursive**: Try digits 1-9 in empty cell
- **Constraint**: Check row, column, and 3×3 box
- **Backtracking**: Place digit → Recurse → Remove if fails
- **Pattern**: Complex constraint satisfaction

## 🔄 Recursion Tree
```
                solveSudoku(0,0)
                /    |    |    \
            Try 1  Try 2  ...  Try 9
              |      |           |
           Valid? Valid?      Valid?
              ✓      ✗           ✗
              |
        solveSudoku(0,1)
        /    |    |    \
    Try 1  Try 2  ...  Try 9
      |      |           |
    Valid? Valid?     Valid?
      ✗      ✓           ✗
             |
       solveSudoku(0,2)
            ...
            |
       All cells filled
            ✓ Solution!
```

## ⚡ Complexity
- **Time**: O(9^(empty_cells)) - worst case
- **Space**: O(1) - modify in-place + O(empty) depth

## 🎯 Key Points
- 3 constraints: row, column, 3×3 box
- Box calculation: (row/3)×3, (col/3)×3
- Try digits 1-9 for each empty cell
- Backtrack if no valid digit found
- Prune early with constraint checking

## 💻 Implementation
```java
public static boolean solveSudoku(char[][] board) {
    return solveSudokuHelper(board, 0, 0);
}

private static boolean solveSudokuHelper(char[][] board, int row, int col) {
    if (row == 9) {
        return true;
    }

    if (col == 9) {
        return solveSudokuHelper(board, row + 1, 0);
    }

    if (board[row][col] != '.') {
        return solveSudokuHelper(board, row, col + 1);
    }

    for (int digit = 1; digit <= 9; digit++) {
        char ch = (char) (digit + '0');
        
        if (isValidPlacement(board, row, col, ch)) {
            board[row][col] = ch;
            
            if (solveSudokuHelper(board, row, col + 1)) {
                return true;
            }
            
            board[row][col] = '.';
        }
    }

    return false;
}

private static boolean isValidPlacement(char[][] board, int row, int col, char digit) {
    for (int j = 0; j < 9; j++) {
        if (board[row][j] == digit) {
            return false;
        }
    }

    for (int i = 0; i < 9; i++) {
        if (board[i][col] == digit) {
            return false;
        }
    }

    int boxRow = (row / 3) * 3;
    int boxCol = (col / 3) * 3;
    
    for (int i = boxRow; i < boxRow + 3; i++) {
        for (int j = boxCol; j < boxCol + 3; j++) {
            if (board[i][j] == digit) {
                return false;
            }
        }
    }

    return true;
}
```