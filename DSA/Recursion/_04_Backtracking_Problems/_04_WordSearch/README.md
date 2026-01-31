# Word Search

## 📝 Problem
Find if word exists in 2D board by moving horizontally/vertically (no diagonal)

## 💡 Core Concept
- **Base Case**: index = word.length → found
- **Pruning**: out of bounds, wrong char, visited → false
- **Backtracking**: Mark visited → Try 4 directions → Unmark
- **Pattern**: 2D grid search with backtracking

## 🔄 Recursion Tree
```
Board:
A B C E
S F C S
A D E E

Search "SEE":
                search(1,0,'S',0)
                       |
                   Match 'S'
                       |
                Try 4 directions
        /       |       |       \
      Up      Down    Left    Right
      ✗        |       ✗       ✗
          search(2,0,'E',1)
               |
           Match 'E'
               |
        Try 4 directions
    /       |       |       \
  Up      Down    Left    Right
  ✗        ✗       ✗       |
                      search(2,1,'E',2)
                           |
                       Match 'E'
                           |
                      index=3
                           ✓
```

## ⚡ Complexity
- **Time**: O(m×n×4^L) - m×n cells, 4 directions, L=word length
- **Space**: O(L) - recursion depth

## 🎯 Key Points
- Start from each cell matching first character
- Mark cell as visited ('#') during search
- Restore cell after backtracking
- Try all 4 directions: up, down, left, right

## 💻 Implementation
```java
public static boolean exist(char[][] board, String word) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == word.charAt(0)) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
    }
    return false;
}

private static boolean search(char[][] board, String word, int row, int col, int index) {
    if (index == word.length()) {
        return true;
    }

    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || 
        board[row][col] != word.charAt(index)) {
        return false;
    }

    char temp = board[row][col];
    board[row][col] = '#';

    boolean found = search(board, word, row + 1, col, index + 1) ||
                   search(board, word, row - 1, col, index + 1) ||
                   search(board, word, row, col + 1, index + 1) ||
                   search(board, word, row, col - 1, index + 1);

    board[row][col] = temp;
    return found;
}
```