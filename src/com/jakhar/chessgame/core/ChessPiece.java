package com.jakhar.chessgame.core;

import java.util.ArrayList;
import java.util.Collections;

public class ChessPiece implements IPiece {
    public String color;
    public String type;
    public String identifier;

    public int rowIndex;
    public int columnIndex;

    /**
     * This is going to help us to track the next move for our chess pieces
     */
    public class MoveStats {
        public String move;
        public boolean shouldBreak;

        public MoveStats() {
            shouldBreak = false;
        }
    }

    public MoveStats getMoveStats(ChessBoard.Square traversedSquare, ChessBoard.Square square, int rowIndex, int columnIndex) {
        ChessBoard board = GameContext.getInstance().getBoard();

        MoveStats moveStats = new MoveStats();

        int userRow = board.mIndexToUserRowMap.get(rowIndex);
        String userColumn = board.mIndexToUserColumnMap.get(columnIndex);

        if (traversedSquare.piece == null) {
            moveStats.move = userColumn + userRow;
        } else if (traversedSquare.piece != null &&
                (traversedSquare.piece.color.equalsIgnoreCase(square.piece.color) == false)) {
            // Piece exist at square but of different color - Legal move.
            moveStats.move = userColumn + userRow;

            moveStats.shouldBreak = true;
        } else if (traversedSquare.piece != null &&
                (traversedSquare.piece.color.equalsIgnoreCase(square.piece.color) == true)) {
            // Piece exist at square but of different color - Illegal move.
            moveStats.shouldBreak = true;
        }

        return moveStats;
    }

    @Override
    public void printValidMoves(ChessBoard.Square square, ArrayList<String> movesList) {
        Collections.sort(movesList);

        System.out.println(identifier + " on " + square.userPosition + ": " + String.join(" ", movesList));
    }

    @Override
    public void findValidMoves(ChessBoard.Square square) {

    }
}
