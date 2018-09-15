import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class Lab2 {

    public int getMatrixMinNorma(@NotNull int[][] matrix) {
        int norma = Integer.MAX_VALUE;
        for (int i = 0, size = matrix.length; i < size; i++) {
            int sumPerLine = 0;

            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {
                sumPerLine += matrix[i][j];
            }

            if (norma > sumPerLine) {
                norma = sumPerLine;
            }
        }

        return norma;
    }

    public void removeLocalMaximums(int[][] matrix) {
        for (int i = 0, size = matrix.length; i < size; i++) {
            loop:
            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {
                final int currentItem = matrix[i][j];
                // check all neighbours
                for (int k = Math.max(0, i - 1); k <= Math.min(i + 1, size - 1); k++) {
                    for (int m = Math.max(0, j - 1); m <= Math.min(j + 1, innerSize - 1); m++) {
                        if (k == i && m == j) {
                            continue; // ignore current item
                        }
                        if (currentItem <= matrix[k][m]) {
                            continue loop; // not a local maximum, go to next item
                        }
                    }
                }
                // as we reach here, it means it is a local maximum
                matrix[i][j] = 0;
            }
        }
    }

    public boolean checkIsSymmetricMatrix(int[][] matrix) {
        for (int i = 0, size = matrix.length; i < size; i++) {
            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {
                if (i == j) {
                    continue; // skip main diagonal
                }

                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getColumnCharacteristic(int[][] matrix, int column) {
        int characteristic = 0;

        for (int i = 0, size = matrix.length; i < size; i++) {
            if (i % 2 != 0) {
                continue; // skip even position in column
            }
            int currentItem = matrix[i][column];
            int itemCharacteristic = currentItem < 0 ? Math.abs(currentItem) : 0;
            characteristic += itemCharacteristic;
        }

        return characteristic;
    }

    public int[][] sortByCharacteristics(int[][] matrix) {
        int c0 = getColumnCharacteristic(matrix, 0);
        int c1 = getColumnCharacteristic(matrix, 1);
        int c2 = getColumnCharacteristic(matrix, 2);

        // 1. [column, column characteristic] - type A
        // 2. A[3] - B
        // 3. sort(B)
        int[][] map = {
                {0, c0},
                {1, c1},
                {2, c2}
        };
        Arrays.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // re-ordering of matrix columns
        int[][] result = MatrixUtils.copyMatrix(matrix);
        for (int i = 0, size = map.length; i < size; i++) {
            int[] mapItem = map[i];
            int oldColumnIndex = mapItem[0];
            int newColumnIndex = i;

            for (int j = 0; j < matrix.length; j++) {
                result[j][newColumnIndex] = matrix[j][oldColumnIndex];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = MatrixUtils.matrixFileInput("matrix.txt");

        if (matrix == null) {
            System.out.println("Error while reading from file");
            return; // early exit
        }

        System.out.println("Matrix:");
        MatrixUtils.printMatrix(matrix);

        Lab2 lab = new Lab2();

        // 12
        System.out.println("Matrix norma:");
        System.out.println(lab.getMatrixMinNorma(matrix));

        // 26
        System.out.println("Matrix without local maximums:");
        int[][] matrixCopy = MatrixUtils.copyMatrix(matrix);
        lab.removeLocalMaximums(matrixCopy);
        MatrixUtils.printMatrix(matrixCopy);
        System.out.println("Matrix symmetric:");
        boolean isSymmetric = lab.checkIsSymmetricMatrix(matrixCopy);
        System.out.println(isSymmetric);

        // 40
        System.out.println();
        System.out.println("Source matrix characteristics:");
        MatrixUtils.printMatrix(matrix);
        System.out.println("| | |");
        int c0 = lab.getColumnCharacteristic(matrix, 0);
        int c1 = lab.getColumnCharacteristic(matrix, 1);
        int c2 = lab.getColumnCharacteristic(matrix, 2);
        System.out.println(String.format("%d %d %d", c0, c1, c2));
        System.out.println();
        int[][] sortedByCharacteristicMatrix = lab.sortByCharacteristics(matrix);
        MatrixUtils.printMatrix(sortedByCharacteristicMatrix);
    }
}
