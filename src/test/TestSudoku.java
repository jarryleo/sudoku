package test;

import cn.leo.sudoku.SudokuChecker;
import cn.leo.sudoku.SudokuEval;
import cn.leo.sudoku.SudokuFactory;

public class TestSudoku {
    public static void main(String[] args) {
        //testCreate();
        testSolution();
    }

    private static void testCreate() {
        SudokuFactory sudokuFactory = new SudokuFactory();
        byte[][] map = sudokuFactory.getMap(0);
        SudokuChecker.showSudokuMap(map);
    }

    private static void testSolution() {
        String s = "050003100000010007800405000406000900090000020008000503000501008300090000009800010";
        SudokuEval sudokuEval = new SudokuEval();
        SudokuEval input = sudokuEval.input(s);
        System.out.println("题目如下:");
        SudokuChecker.showSudokuMap(input.getMap());
        System.out.println("解题中...");
        byte[][] map = input
                .solution()
                .getMap();
        SudokuChecker.showSudokuMap(map);
        long time = sudokuEval.getSolutionTime();
        System.out.println("解题完毕！耗时：" + time + "ms");
    }
}
