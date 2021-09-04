package com.jakhar.chessgame.core;

public interface IPiece {
    public void setColor(String color);
    public void setType(String color);
    public void setRowIndex(int rowIndex);
    public void setColumnIndex(int columnIndex);
    public void setPosition(Piece.Position position);

    public String getColor();
    public String getType();

    public int getRowIndex();
    public int getColumnIndex();

    public Piece.Position getPosition();
}
