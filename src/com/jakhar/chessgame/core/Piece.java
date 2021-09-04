package com.jakhar.chessgame.core;

public class Piece implements IPiece {
    private String color;
    private String type;
    private Position position;

    private int rowIndex;
    private int columnIndex;

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public class Position {
        private String position;
        private int xIndex;
        private int yIndex;
    }
}
