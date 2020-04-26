package util.calculation;

import java.util.Random;

public class Matrix {

    private final double MOD = 1_000_000_007;

    private int row;

    private int column;

    public double[][] num;

    public int getRow() {
        return row;
    }

//    public void setRow(int row) {
//        this.row = row;
//    }

    public int getColumn() {
        return column;
    }

//    public void setColumn(int column) {
//        this.column = column;
//    }

    public Matrix(int row, int column) {
        this.row = row;
        this.column = column;
        num = new double[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                num[i][j] = 0;
            }
        }
    }

    public Matrix(int row, int column, Matrix mat, boolean setRandom) {
        if(mat.getRow() > row || mat.getColumn() > column) {
            throw new IllegalArgumentException("The row and column cannot be smaller than the matrix's");
        }
        this.row = row;
        this.column = column;
        this.num = new double[row][column];
        Random random = new Random();
        for (int i = 0; i < mat.getRow(); ++i) {
            for (int j = 0; j < mat.getColumn(); ++j) {
                this.num[i][j] = mat.num[i][j];
            }
            for (int j = mat.getColumn(); j < column; ++j) {
                if(setRandom) {
                    this.num[i][j] = (random.nextInt(99999998) + 1) * 0.00000001;
                } else {
                    this.num[i][j] = 0;
                }
            }
        }
        for (int i = mat.getRow(); i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                if(setRandom) {
                    this.num[i][j] = (random.nextInt(99999998) + 1) * 0.00000001;
                } else {
                    this.num[i][j] = 0;
                }
            }
        }
    }

    public Matrix(int row, int column, boolean setRandom) {
        this.row = row;
        this.column = column;
        num = new double[row][column];
        if(setRandom) {
            Random random = new Random();
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < column; ++j) {
                    this.num[i][j] = (random.nextInt(99999998) + 1) * 0.00000001;
                }
            }
        } else {
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < column; ++j) {
                    this.num[i][j] = 0;
                }
            }
        }
    }

    public Matrix add(Matrix mat) {
        if(this.column != mat.getColumn() || this.row != mat.getRow()) {
            throw new IllegalArgumentException("This matrix cannot be the argument of add");
        }
        Matrix answer = new Matrix(row, mat.getColumn());
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                answer.num[i][j] = num[i][j] + mat.num[i][j];
            }
        }
        return answer;
    }

    public Matrix substract(Matrix mat) {
        if(this.column != mat.getColumn() || this.row != mat.getRow()) {
            throw new IllegalArgumentException("This matrix cannot be the argument of substract");
        }
        Matrix answer = new Matrix(row, mat.getColumn());
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                answer.num[i][j] = num[i][j] - mat.num[i][j];
            }
        }
        return answer;
    }

    public Matrix multiply(Matrix mat) {
        if(this.column != mat.getRow()) {
            throw new IllegalArgumentException("This matrix cannot be the argument of multiply");
        }
        Matrix answer = new Matrix(row, mat.getColumn());
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < mat.getColumn(); ++j) {
                answer.num[i][j] = 0;
                for (int k = 0; k < column; ++k) {
                    answer.num[i][j] += num[i][k] * mat.num[k][j];
                }
            }
        }
        return answer;
    }

    public double[] multiply(double[] arr) {
        if(arr.length != this.column) {
            throw new IllegalArgumentException("The length of this array does not equal to the column of matrix("
                    + column + ")" );
        }
        double[] answer = new double[this.row];
        for(int i = 0; i < this.row; ++i) {
            answer[i] = 0;
            for(int j = 0; j < arr.length; ++j) {
                answer[i] += this.num[i][j] * arr[j];
            }
        }
        return answer; // length = this.row
    }

    private Matrix multiplyWithMod(Matrix mat) {
        if(this.column != mat.getRow()) {
            throw new IllegalArgumentException("This matrix cannot be the argument of multiply");
        }
        Matrix answer = new Matrix(row, mat.getColumn());
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < mat.getColumn(); ++j) {
                answer.num[i][j] = 0;
                for (int k = 0; k < column; ++k) {
                    answer.num[i][j] = (num[i][k] * mat.num[k][j] + answer.num[i][j]) % MOD;
                }
            }
        }
        return answer;
    }

    /**
     * calculate mat^b;
     *
     * @param mat pojo.Matrix
     * @param b   exponent
     * @return ansewr(pojo.Matrix)
     */
    public static Matrix qpow(Matrix mat, int b) {
        if(mat.getColumn() != mat.getRow()) {
            throw new IllegalArgumentException("This matrix cannot be the argument of qpow");
        }
        Matrix answer = new Matrix(mat.getRow(), mat.getRow());
        for (int i = 0; i < mat.getRow(); ++i) {
            answer.num[i][i] = 1;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                answer = answer.multiplyWithMod(mat);
            }
            mat = mat.multiplyWithMod(mat);
            b >>= 1;
        }
        return answer;
    }

    public Matrix transpose() {
        Matrix answer = new Matrix(column, row);
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < column; ++j) {
                answer.num[j][i] = num[i][j];
            }
        }
        return answer;
    }

    public double norm2() {
        return Math.sqrt(norm2Pow());
    }

    public double norm2Pow() {
        double answer = 0;
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < column; ++j) {
                answer += num[i][j] * num[i][j];
            }
        }
        return answer;
    }

    public double norm1() {
        double answer = 0;
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < column; ++j) {
                answer += num[i][j];
            }
        }
        return answer;
    }

    public void print() {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix addColumn(double[] newArr) {
        if(newArr.length != this.row) {
            throw new IllegalArgumentException("The length of this array does not equal to the column of matrix("
                    + column + ")" );
        }
        Matrix answer = new Matrix(this.row, this.column + 1, this, false);
        for(int i = 0; i < this.row; ++i) {
            answer.num[i][this.column - 1] = newArr[i];
        }
        return answer;
    }
}