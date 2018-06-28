package test;

import cn.leo.sudoku.SudokuChecker;
import cn.leo.sudoku.SudokuEval;
import cn.leo.sudoku.SudokuFactory;

public class TestSudoku {
    public static void main(String[] args) {
        testCreate();
        //testSolution();
    }

    private static void testCreate() {
        SudokuFactory sudokuFactory = SudokuFactory.create(SudokuFactory.ENTRY_LEVEL);
        SudokuChecker.showSudokuMap(sudokuFactory.getTitle());
    }

    private static void testSolution() {
        String s = "005300000800000020070010500400005300010070006003200080060500009004000030000009700";
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
