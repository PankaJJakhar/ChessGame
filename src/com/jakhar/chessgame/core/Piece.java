package com.jakhar.chessgame.core;

public class Piece implements IPiece {
    private String color;
    private String type;

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
    public String getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }
}
