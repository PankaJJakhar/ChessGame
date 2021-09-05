package com.jakhar.chessgame.core;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop() {
        identifier = Constants.PIECE_TYPE_BISHOP;
    }

    @Override
    public void findValidMoves(ChessBoard.Square square) {
        super.findValidMoves(square);

        ChessBoard board = GameContext.getInstance().getBoard();

        ArrayList<String> movesList = new ArrayList<>();

        ChessPiece piece = square.piece;

        int startRowIndex = square.rowIndex - 1;
        int startColumnIndex = square.columnIndex - 1;

        int minRowLimit = 0;
        int maxRowLimit = 7;
        int minColumnLimit = 0;
        int maxColumnLimit = 7;

        // Check direction up-left
        while (startRowIndex >= minRowLimit && startColumnIndex >= minColumnLimit) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[startRowIndex][startColumnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, startRowIndex, startColumnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }

            startRowIndex--;
            startColumnIndex--;
        }

        // Reset start indices for up-right direction check.
        startRowIndex = square.rowIndex - 1;
        startColumnIndex = square.columnIndex + 1;

        // Check direction up-right
        while (startRowIndex >= minRowLimit && startColumnIndex <= maxColumnLimit) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[startRowIndex][startColumnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, startRowIndex, startColumnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }

            startRowIndex--;
            startColumnIndex++;
        }

        // Reset start indices for down-left direction check.
        startRowIndex = square.rowIndex + 1;
        startColumnIndex = square.columnIndex - 1;

        // Check direction down-left
        while (startRowIndex <= maxRowLimit && startColumnIndex >= minColumnLimit) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[startRowIndex][startColumnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, startRowIndex, startColumnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }

            startRowIndex++;
            startColumnIndex--;
        }

        // Reset start indices for down-right direction check.
        startRowIndex = square.rowIndex + 1;
        startColumnIndex = square.columnIndex + 1;

        // Check direction down-left
        while (startRowIndex <= maxRowLimit && startColumnIndex <= maxColumnLimit) {
            ChessBoard.Square traversedSquare = board.mChessBoardMatrix[startRowIndex][startColumnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, startRowIndex, startColumnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }

            startRowIndex++;
            startColumnIndex++;
        }

        printValidMoves(square, movesList);
    }
}
