package com.jakhar.chessgame.core;

import java.util.*;

public class Board {
    private HashMap<Integer, Integer> userRowToIndexMap;
    private HashMap<String, Integer> userColumnToIndexMap;
    private HashMap<Integer, Integer> indexToUserRowMap;
    private HashMap<Integer, String> indexToUserColumnMap;

    public Square[][] chessBoardMatrix;

    public class Square {
        private int rowIndex;
        private int columnIndex;

        private Piece piece;

        public int getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        public Piece getPiece() {
            return piece;
        }

        public void setPiece(Piece piece) {
            this.piece = piece;
        }
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
                square.setRowIndex(rowIndex);
                square.setColumnIndex(columnIndex);

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

        System.out.println("Square: " + square.piece);
    }

    public void findValidMoves(Square square) {
        ArrayList<String> movesList;

        if (square.piece.getType().equalsIgnoreCase("B")) {
            movesList = findBishopMoves(square);
        } else if (square.piece.getType().equalsIgnoreCase("N")) {
            movesList = findBishopMoves(square);
        }
    }

    private ArrayList<String> findBishopMoves(Square square) {
        ArrayList<String> movesList = new ArrayList<>();

        Piece piece = square.getPiece();

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

        System.out.println("Bishop Valid Moves: " + String.join(" ", movesList));

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

        Piece piece = square.getPiece();

        int startRowIndex = square.rowIndex - 1;
        int startColumnIndex = square.columnIndex - 1;

        int minRowLimit = (startRowIndex - 2 >= 0) ? startRowIndex - 2 : 0;
        int maxRowLimit = (startRowIndex + 2 <= 7) ? startRowIndex + 2 : 7;
        int minColumnLimit = (startColumnIndex - 2 >= 0) ? startColumnIndex - 2 : 0;
        int maxColumnLimit = (startColumnIndex + 2 <= 7) ? startColumnIndex + 2 : 7;

        return null;
    }

    private void checkKnightMoves() {

    }
}
