package com.jakhar.chessgame.core;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    public Knight() {
        identifier = Constants.PIECE_TYPE_KNIGHT;
    }

    @Override
    public void findValidMoves(ChessBoard.Square square) {
        super.findValidMoves(square);

        ChessBoard board = GameContext.getInstance().getBoard();

        ArrayList<String> movesList = new ArrayList<>();

        ChessPiece piece = square.piece;

        int minRowLimit = (square.rowIndex - 2 >= 0) ? square.rowIndex - 2 : 0;
        int maxRowLimit = (square.rowIndex + 2 <= 7) ? square.rowIndex + 2 : 7;
        int minColumnLimit = (square.columnIndex - 2 >= 0) ? square.columnIndex - 2 : 0;
        int maxColumnLimit = (square.columnIndex + 2 <= 7) ? square.columnIndex + 2 : 7;

        // Base on Knight's movement, Knight can not move row-wise, column-wise and diagonally.
        // So if currently being iterated Square does not exist in the same row or column or diagonal
        // then it means it's a valid move if different color piece or no piece is present.
        for (int rowIndex = minRowLimit; rowIndex <= maxRowLimit; rowIndex++) {
            for (int columnIndex = minColumnLimit; columnIndex <= maxColumnLimit; columnIndex++) {
                ChessBoard.Square traversedSquare = board.mChessBoardMatrix[rowIndex][columnIndex];

                if ((traversedSquare.columnIndex == square.columnIndex ||
                        (traversedSquare.rowIndex == square.rowIndex))) {
                    continue;
                } else if ((traversedSquare.rowIndex + traversedSquare.columnIndex ==
                        square.rowIndex + square.columnIndex) ||
                        (Math.abs(traversedSquare.rowIndex - traversedSquare.columnIndex) ==
                                Math.abs(square.rowIndex - square.columnIndex))) {
                    continue;
                } else if (traversedSquare.piece == null ||
                        (traversedSquare.piece.color.equalsIgnoreCase(square.piece.color) == false)) {
                    int userRow = board.mIndexToUserRowMap.get(rowIndex);
                    String userColumn = board.mIndexToUserColumnMap.get(columnIndex);

                    movesList.add(userColumn + userRow);
                }
            }
        }

        printValidMoves(square, movesList);
    }
}
