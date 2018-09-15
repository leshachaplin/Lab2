import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;

public class MatrixUtils {

    public static int[][] matrixFileInput(String nameOfFile) {
        try {
            FileReader fr = new FileReader(nameOfFile);
            BufferedReader isr = new BufferedReader(fr);

            final int m = Integer.parseInt(isr.readLine());
            final int n = Integer.parseInt(isr.readLine());

            int[][] matrix = new int[m][n];

            for (int i = 0; i < m; i++) {
                String matrixLineStr = isr.readLine();
                String[] stringArray = matrixLineStr.split(" ");
                for (int j = 0, size = stringArray.length; j < size; j++) {
                    matrix[i][j] = Integer.parseInt(stringArray[j]);
                }
            }

            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printMatrix(@NotNull int[][] matrix) {
        for (int i = 0, size = matrix.length; i < size; i++) {
            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] copyMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0, size = newMatrix.length; i < size; i++) {
            for (int j = 0, innerSize = newMatrix[i].length; j < innerSize; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    public static int[][] transpone(int[][] matrix) {
        int[][] result = copyMatrix(matrix);
        for (int i = 0, size = matrix.length; i < size; i++) {
            for (int j = 0, innerSize = matrix[i].length; j < innerSize; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
}
