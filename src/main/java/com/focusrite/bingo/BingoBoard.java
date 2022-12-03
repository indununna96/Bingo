package com.focusrite.bingo;

import java.util.Arrays;

public class BingoBoard {
    private boolean won = false;
    private int lastNumber = -1;
    private final int[][] board;
    private final boolean[][] marks = new boolean[5][5];

    public BingoBoard(int[][] board) {
        this.board = board;
    }

    public boolean hasWon() {
        return won;
    }

    public void reset() {
        won = false;
        for (var y = 0; y < 5; y++) {
            Arrays.fill(marks[y], false);
        }
    }

    public void addNumber(int n) {
        if (hasWon()) {
            return;
        }
        lastNumber = n;
        for (var y = 0; y < 5; y++) {
            for (var x = 0; x < 5; x++) {
                if (board[y][x] == n) {
                    marks[y][x] = true;
                }
            }
        }
        for (var i = 0; i < 5; i++) {
            boolean row = true;
            boolean col = true;
            for (var j = 0; j < 5; j++) {
                row &= marks[i][j];
                col &= marks[j][i];
            }
            if (row || col) {
                won = true;
                return;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
