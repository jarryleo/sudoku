package cn.leo.sudoku;

import java.util.List;
import java.util.Random;

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

    /**
     * 开始解题
     */
    public SudokuEval solution() {
        boolean a = planA();
        boolean b = planB();
        while (a || b) {
            a = planA();
            b = planB();
        }
        planC();
        return this;
    }

    public byte[][] getMap() {
        return map;
    }

    /**
     * 唯一数解法
     * 一个空的候选数只有1个数即确定
     */
    private boolean planA() {
        boolean flag = false;
        boolean p1 = oneNum();
        while (p1) {
            flag = true;
            p1 = oneNum();
        }
        return flag;
    }

    //填写唯一数
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

    /**
     * 排除法
     * 目标方格所处九宫的另外两行和两列都有这个数，
     * 且本行本列其它空不能填这个数
     */
    private boolean planB() {
        boolean flag = false;
        boolean p2 = exclude();
        while (p2) {
            flag = true;
            p2 = exclude();
        }
        return flag;
    }

    //排除确定数字
    private boolean exclude() {
        boolean flag = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Integer> inputNum = SudokuChecker.findCellCanInputNum(map, i, j);
                if (inputNum.size() > 1) {
                    for (Integer num : inputNum) {
                        int x1 = (i / 3) * 3;
                        int y1 = (j / 3) * 3;
                        int h = 0;
                        int v = 0;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 9; l++) {
                                if (map[x1 + k][l] == num) v++;
                                if (map[l][y1 + k] == num) h++;
                            }
                        }
                        if (h + v == 4) {
                            map[i][j] = num.byteValue();
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 暴力破解
     */
    private boolean planC() {
        boolean flag = false;
        byte[][] bytes = copyMap();
        boolean violence = violence(bytes);
        while (!violence) {
            bytes = copyMap();
            violence = violence(bytes);
        }
        flag = true;
        map = copyMap();
        return flag;
    }

    //暴力破解
    private boolean violence(byte[][] copyMap) {
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Integer> inputNum = SudokuChecker.findCellCanInputNum(copyMap, i, j);
                if (inputNum.size() == 0) return false;
                int n = r.nextInt(inputNum.size());
                copyMap[i][j] = inputNum.get(n).byteValue();
            }
        }
        return true;
    }

    //复制数组
    private byte[][] copyMap() {
        byte[][] copy = new byte[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}
