package cn.leo.sudoku;

import java.util.List;

/**
 * 数独解题类
 */
public class SudokuEval {

    private byte[][] map = new byte[9][9];

    public SudokuEval input(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = (byte) (chars[i * 9 + j] - 48);
            }
        }
        return this;
    }

    public SudokuEval input(byte[] s) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = s[i * 9 + j];
            }
        }
        return this;
    }

    public SudokuEval input(byte[][] s) {
        map = s;
        return this;
    }

    public SudokuEval input(int[] s) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = (byte) s[i * 9 + j];
            }
        }
        return this;
    }

    public SudokuEval input(int[][] s) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = (byte) s[i][j];
            }
        }
        return this;
    }

    public SudokuEval solution() {
        planA();
        return this;
    }

    public byte[][] getMap() {
        return map;
    }

    /**
     * 唯一数解法
     * 一个空的候选数只有1个数即确定
     */
    private void planA() {
        boolean p1 = oneNum();
        while (p1) {
            p1 = oneNum();
        }
    }

    private boolean oneNum() {
        boolean flag = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Integer> inputNum = SudokuChecker.findCellCanInputNum(map, i, j);
                if (inputNum.size() == 1) {
                    map[i][j] = inputNum.get(0).byteValue();
                    flag = true;
                }
            }
        }
        return flag;
    }
}
