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
        String s = "000000090943007580016090730020000000004010200000000050057060840061500329080000000";
        SudokuEval sudokuEval = new SudokuEval();
        byte[][] map = sudokuEval.input(s)
                .solution()
                .getMap();
        SudokuChecker.showSudokuMap(map);
        long time = sudokuEval.getSolutionTime();
        System.out.println("解题耗时：" + time + "ms");
    }
}
