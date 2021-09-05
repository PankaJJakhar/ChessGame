package com.jakhar.chessgame.core;

import java.util.ArrayList;

public class Rook extends ChessPiece {
    public Rook() {
        identifier = Constants.PIECE_TYPE_ROOK;
    }

    @Override
    public void findValidMoves(ChessBoard.Square square) {
        super.findValidMoves(square);

        ChessBoard board = GameContext.getInstance().getBoard();

        ArrayList<String> movesList = new ArrayList<>();

        ChessPiece piece = square.piece;

        // Check vertically up.
        for (int rowIndex = square.rowIndex - 1; rowIndex >= 0; rowIndex--) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[rowIndex][square.columnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, rowIndex, square.columnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }
        }

        // Check vertically down.
        for (int rowIndex = square.rowIndex + 1; rowIndex <= 7; rowIndex++) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[rowIndex][square.columnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, rowIndex, square.columnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }
        }

        // Check horizontally left.
        for (int columnIndex = square.columnIndex - 1; columnIndex >= 0; columnIndex--) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[square.rowIndex][columnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, square.rowIndex, columnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }
        }

        // Check horizontally right.
        for (int columnIndex = square.columnIndex + 1; columnIndex <= 7; columnIndex++) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[square.rowIndex][columnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, square.rowIndex, columnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }
        }

        printValidMoves(square, movesList);
    }
}
