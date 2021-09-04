package com.jakhar.chessgame.core;

import java.util.*;

public class Board {
    private HashMap<Integer, Integer> userRowToIndexMap;
    private HashMap<String, Integer> userColumnToIndexMap;
    private HashMap<Integer, Integer> indexToUserRowMap;
    private HashMap<Integer, String> indexToUserColumnMap;

    public Square[][] chessBoardMatrix;

    public class Square {
        public int rowIndex;
        public int columnIndex;

        public String userPosition;

        public Piece piece;
    }

    public Board() {
        initialize();
    }

    private void initialize() {
        initializeTheBoard();
        createUserInputToBoardMapping();
    }

    private void createUserInputToBoardMapping() {
        userRowToIndexMap = new HashMap<>();
        userColumnToIndexMap = new HashMap<>();
        indexToUserRowMap = new HashMap<>();
        indexToUserColumnMap = new HashMap<>();

        userRowToIndexMap.put(8, 0);
        userRowToIndexMap.put(7, 1);
        userRowToIndexMap.put(6, 2);
        userRowToIndexMap.put(5, 3);
        userRowToIndexMap.put(4, 4);
        userRowToIndexMap.put(3, 5);
        userRowToIndexMap.put(2, 6);
        userRowToIndexMap.put(1, 7);

        indexToUserRowMap.put(0, 8);
        indexToUserRowMap.put(1, 7);
        indexToUserRowMap.put(2, 6);
        indexToUserRowMap.put(3, 5);
        indexToUserRowMap.put(4, 4);
        indexToUserRowMap.put(5, 3);
        indexToUserRowMap.put(6, 2);
        indexToUserRowMap.put(7, 1);

        userColumnToIndexMap.put(Constants.COLUMN_A, 0);
        userColumnToIndexMap.put(Constants.COLUMN_B, 1);
        userColumnToIndexMap.put(Constants.COLUMN_C, 2);
        userColumnToIndexMap.put(Constants.COLUMN_D, 3);
        userColumnToIndexMap.put(Constants.COLUMN_E, 4);
        userColumnToIndexMap.put(Constants.COLUMN_F, 5);
        userColumnToIndexMap.put(Constants.COLUMN_G, 6);
        userColumnToIndexMap.put(Constants.COLUMN_H, 7);

        indexToUserColumnMap.put(0, Constants.COLUMN_A);
        indexToUserColumnMap.put(1, Constants.COLUMN_B);
        indexToUserColumnMap.put(2, Constants.COLUMN_C);
        indexToUserColumnMap.put(3, Constants.COLUMN_D);
        indexToUserColumnMap.put(4, Constants.COLUMN_E);
        indexToUserColumnMap.put(5, Constants.COLUMN_F);
        indexToUserColumnMap.put(6, Constants.COLUMN_G);
        indexToUserColumnMap.put(7, Constants.COLUMN_H);
    }

    private void initializeTheBoard() {
        chessBoardMatrix = new Square[8][8];

        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                Square square = new Square();
                square.rowIndex = rowIndex;
                square.columnIndex = columnIndex;

                chessBoardMatrix[rowIndex][columnIndex] = square;
            }
        }
    }

    public Square addPiece(String position, Piece piece) {
        char[] charsInPosition = position.toCharArray();

        String userColumn = String.valueOf(charsInPosition[0]);
        int userRow = Integer.valueOf(String.valueOf(charsInPosition[1]));

        int rowIndex = userRowToIndexMap.get(userRow);
        int columnIndex = userColumnToIndexMap.get(userColumn);

        Square square = chessBoardMatrix[rowIndex][columnIndex];
        square.userPosition = position;
        square.piece = piece;

        return square;
    }

    public void printSquare(String position) {
        char[] charsInPosition = position.toCharArray();

        String userColumn = String.valueOf(charsInPosition[0]);
        int userRow = Integer.valueOf(String.valueOf(charsInPosition[1]));

        int rowIndex = userRowToIndexMap.get(userRow);
        int columnIndex = userColumnToIndexMap.get(userColumn);

        Square square = chessBoardMatrix[rowIndex][columnIndex];
    }

    public void findValidMoves(Square square) {
        ArrayList<String> movesList;

        if (square.piece.getType().equalsIgnoreCase("B")) {
            movesList = findBishopMoves(square);
        } else if (square.piece.getType().equalsIgnoreCase("N")) {
            movesList = findKnightMoves(square);
        }
    }

    private ArrayList<String> findBishopMoves(Square square) {
        ArrayList<String> movesList = new ArrayList<>();

        Piece piece = square.piece;

        int startRowIndex = square.rowIndex - 1;
        int startColumnIndex = square.columnIndex - 1;

        int minRowLimit = 0;
        int maxRowLimit = 7;
        int minColumnLimit = 0;
        int maxColumnLimit = 7;

        // Check direction up-left
        while (startRowIndex >= minRowLimit && startColumnIndex >= minColumnLimit) {
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

            String nextMove = getNextBishipMove(traversedSquare, square, startRowIndex, startColumnIndex);

            if (nextMove != null) {
                movesList.add(nextMove);
            } else {
                // Definitely a piece of different color at square - Stop moving and exit
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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

            String nextMove = getNextBishipMove(traversedSquare, square, startRowIndex, startColumnIndex);

            if (nextMove != null) {
                movesList.add(nextMove);
            } else {
                // Definitely a piece of different color at square - Stop moving and exit
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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

            String nextMove = getNextBishipMove(traversedSquare, square, startRowIndex, startColumnIndex);

            if (nextMove != null) {
                movesList.add(nextMove);
            } else {
                // Definitely a piece of different color at square - Stop moving and exit
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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

            String nextMove = getNextBishipMove(traversedSquare, square, startRowIndex, startColumnIndex);

            if (nextMove != null) {
                movesList.add(nextMove);
            } else {
                // Definitely a piece of different color at square - Stop moving and exit
                break;
            }

            startRowIndex++;
            startColumnIndex++;
        }

        Collections.sort(movesList);

        System.out.println("B on " + square.userPosition + ": " + String.join(" ", movesList));

        return movesList;
    }

    private String getNextBishipMove(Square traversedSquare, Square square, int startRowIndex, int startColumnIndex) {
        // Nothing at square - Keep on moving
        // OR Piece of different color at square - Keep on moving
        if (traversedSquare.piece == null
                || (traversedSquare.piece.getColor().equalsIgnoreCase(square.piece.getColor()) == false)) {
            int userRow = indexToUserRowMap.get(startRowIndex);
            String userColumn = indexToUserColumnMap.get(startColumnIndex);

            return userColumn + userRow;
        }

        return null;
    }

    private ArrayList<String> findKnightMoves(Square square) {
        ArrayList<String> movesList = new ArrayList<>();

        Piece piece = square.piece;

        int minRowLimit = (square.rowIndex - 2 >= 0) ? square.rowIndex - 2 : 0;
        int maxRowLimit = (square.rowIndex + 2 <= 7) ? square.rowIndex + 2 : 7;
        int minColumnLimit = (square.columnIndex - 2 >= 0) ? square.columnIndex - 2 : 0;
        int maxColumnLimit = (square.columnIndex + 2 <= 7) ? square.columnIndex + 2 : 7;

        // Base on Knight's movement, Knight can not move row-wise, column-wise and diagonally.
        // So if currently being iterated Square does not exist in the same row or column or diagonal
        // then it means it's a valid move if different color piece or no piece is present.
        for (int rowIndex = minRowLimit; rowIndex <= maxRowLimit; rowIndex++) {
            for (int columnIndex = minColumnLimit; columnIndex <= maxColumnLimit; columnIndex++) {
                Square traversedSquare = chessBoardMatrix[rowIndex][columnIndex];

                if ((traversedSquare.columnIndex == square.columnIndex ||
                        (traversedSquare.rowIndex == square.rowIndex))) {
                    continue;
                } else if ((traversedSquare.rowIndex + traversedSquare.columnIndex ==
                        square.rowIndex + square.columnIndex) ||
                        (Math.abs(traversedSquare.rowIndex - traversedSquare.columnIndex) ==
                                Math.abs(square.rowIndex - square.columnIndex))) {
                    continue;
                } else if (traversedSquare.piece == null ||
                        (traversedSquare.piece.getColor().equalsIgnoreCase(square.piece.getColor()) == false)) {
                    int userRow = indexToUserRowMap.get(rowIndex);
                    String userColumn = indexToUserColumnMap.get(columnIndex);

                    movesList.add(userColumn + userRow);
                }
            }
        }

        Collections.sort(movesList);

        System.out.println("N on " + square.userPosition + ": " + String.join(" ", movesList));

        return movesList;
    }

    private void checkKnightMoves() {

    }
}
