import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;

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

    /*
    for (int i = 0, size = matrix.length; i < size; i++) {
            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {

            }
        }
    */

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

    public static void main(String[] args) {
        int[][] matrix = MatrixUtils.matrixFileInput("matrix.txt");

        if (matrix == null) {
            System.out.println("Error while reading from file");
            return; // early exit
        }

        System.out.println("Matrix:");
        MatrixUtils.printMatrix(matrix);

        Lab2 lab = new Lab2();

        System.out.println("Matrix norma:");
        System.out.println(lab.getMatrixMinNorma(matrix));

        System.out.println("Matrix without local maximums:");
        int[][] matrixCopy = MatrixUtils.copyMatrix(matrix);
        lab.removeLocalMaximums(matrixCopy);
        MatrixUtils.printMatrix(matrixCopy);
        System.out.println("Matrix symmetric:");
        boolean isSymmetric = lab.checkIsSymmetricMatrix(matrixCopy);
        System.out.println(isSymmetric);
    }
}
