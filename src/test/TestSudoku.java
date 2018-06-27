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
        String s =
                        "080630900" +
                        "360070008" +
                        "000104305" +
                        "700005100" +
                        "008020600" +
                        "002900004" +
                        "203408000" +
                        "800060031" +
                        "004051090";
        SudokuEval sudokuEval = new SudokuEval();
        byte[][] map = sudokuEval.input(s)
                .solution()
                .getMap();
        SudokuChecker.showSudokuMap(map);

    }
}
