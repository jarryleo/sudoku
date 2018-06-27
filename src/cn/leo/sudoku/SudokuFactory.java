package cn.leo.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 数独题目工厂
 */
public class SudokuFactory {
    private byte[][] map = new byte[9][9];

    /**
     * 生成数独终盘
     *
     * @return 是否生成成功
     */
    private boolean createMap() {
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<Integer> inputNum = SudokuChecker.findCellCanInputNum(map, i, j);
                if (inputNum.size() == 0) return false;
                int n = r.nextInt(inputNum.size());
                map[i][j] = inputNum.get(n).byteValue();
            }
        }
        return true;
    }

    /**
     * 根据等级获取数独题目
     *
     * @param level 等级 1-4 对应 初级 中级 高级 骨灰级
     * @return 返回 数独二维数组
     */
    public byte[][] getMap(int level) {
        clearMap();
        boolean create = createMap();
        while (!create) {
            clearMap();
            create = createMap();
        }
        return map;
    }

    /**
     * 清空数独二维数组
     */
    private void clearMap() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = 0;
            }
        }
    }


}
