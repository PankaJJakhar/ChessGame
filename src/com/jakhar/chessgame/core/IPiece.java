package com.jakhar.chessgame.core;

import java.util.ArrayList;

public interface IPiece {
    public void findValidMoves(ChessBoard.Square square);
    public void printValidMoves(ChessBoard.Square square, ArrayList<String> movesList);
}
