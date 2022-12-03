package com.focusrite.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bingo {
    private final int[] numbers;
    private final List<BingoBoard> boards = new ArrayList<>();
    private boolean dirty;

    public Bingo(String puzzleInput) {
        var scanner = new Scanner(puzzleInput);
        numbers = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        while (scanner.hasNext()) {
            var board = new int[5][5];
            for (var y = 0; y < 5; y++) {
                for (var x = 0; x < 5; x++) {
                    board[y][x] = scanner.nextInt();
                }
            }
            boards.add(new BingoBoard(board));
        }
    }

    public boolean solvePart1() {
        if (dirty) {
            boards.forEach(BingoBoard::reset);
        }
        dirty = true;
        for (var number : numbers) {
            for (var board : boards) {
                board.addNumber(number);
                if (board.hasWon()) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public BingoBoard solvePart2() {
        if (dirty) {
            boards.forEach(BingoBoard::reset);
        }
        dirty = true;
        for (var number : numbers) {
            for (var board : boards) {
                board.addNumber(number);
                if (boards.stream().anyMatch(BingoBoard::hasWon)) {
                    return board;
                }
            }
        }
        return null;
    }

}