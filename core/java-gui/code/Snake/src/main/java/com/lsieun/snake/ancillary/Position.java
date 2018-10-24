package com.lsieun.snake.ancillary;

public class Position {
    public int row;
    public int col;

    public Position() {
        this(0, 0);
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(Position p) {
        this(p.row, p.col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position pt = (Position)obj;
            return (row == pt.row) && (col == pt.col);
        }
        return false;
    }

    public String toString() {
        return getClass().getName() + "[row=" + row + ",col=" + col + "]";
    }
}
