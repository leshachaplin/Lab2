import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;

public class Lab2 {
    private static int[][] matrixFileInput(String nameOfFile) {
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

    public static void main(String[] args) {
        int[][] matrix = matrixFileInput("matrix.txt");

        if (matrix == null) {
            System.out.println("Error while reading from file");
            return; // early exit
        }

        printMatrix(matrix);
    }
}
