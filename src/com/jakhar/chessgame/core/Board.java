package com.jakhar.chessgame.core;

import java.util.*;

public class Board {
    public HashMap<Integer, Integer> userRowToIndexMap;
    public HashMap<String, Integer> userColumnToIndexMap;
    public HashMap<Integer, Integer> indexToUserRowMap;
    public HashMap<Integer, String> indexToUserColumnMap;

    public Square[][] chessBoardMatrix;

    /**
     * Each square on the board will be represented by this
     * Chess has 8x8 squares on the board.
     * Each square has its indices so that we know where it is located.
     * Square also has the information on the chess Piece sitting on it.
     * Square also has information of user entered position.
     */
    public class Square {
        public int rowIndex;
        public int columnIndex;

        public String userPosition;

        public Piece piece;
    }

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
        if (square.piece.getType().equalsIgnoreCase(Constants.PIECE_TYPE_BISHOP)) {
            findBishopMoves(square);
        } else if (square.piece.getType().equalsIgnoreCase(Constants.PIECE_TYPE_KNIGHT)) {
            findKnightMoves(square);
        } else if (square.piece.getType().equalsIgnoreCase(Constants.PIECE_TYPE_ROOK)) {
            findRookMoves(square);
        }
    }

    private void findBishopMoves(Square square) {
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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

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
            Square traversedSquare = chessBoardMatrix[startRowIndex][startColumnIndex];

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

        Collections.sort(movesList);

        System.out.println("B on " + square.userPosition + ": " + String.join(" ", movesList));
    }

    private void findKnightMoves(Square square) {
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
    }

    private void findRookMoves(Square square) {
        ArrayList<String> movesList = new ArrayList<>();

        Piece piece = square.piece;

        // Check vertically up.
        for (int rowIndex = square.rowIndex - 1; rowIndex >= 0; rowIndex--) {
            Square traversedSquare = chessBoardMatrix[rowIndex][square.columnIndex];

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
            Square traversedSquare = chessBoardMatrix[rowIndex][square.columnIndex];

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
            Square traversedSquare = chessBoardMatrix[square.rowIndex][columnIndex];

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
            Square traversedSquare = chessBoardMatrix[square.rowIndex][columnIndex];

            MoveStats moveStats = getMoveStats(traversedSquare, square, square.rowIndex, columnIndex);

            if (moveStats.move != null) {
                movesList.add(moveStats.move);
            }

            if (moveStats.shouldBreak) {
                break;
            }
        }

        Collections.sort(movesList);

        System.out.println("R on " + square.userPosition + ": " + String.join(" ", movesList));
    }

    public MoveStats getMoveStats(Square traversedSquare, Square square, int rowIndex, int columnIndex) {
        MoveStats moveStats = new MoveStats();

        int userRow = indexToUserRowMap.get(rowIndex);
        String userColumn = indexToUserColumnMap.get(columnIndex);

        if (traversedSquare.piece == null) {
            moveStats.move = userColumn + userRow;
        } else if (traversedSquare.piece != null &&
                (traversedSquare.piece.getColor().equalsIgnoreCase(square.piece.getColor()) == false)) {
            // Piece exist at square but of different color - Legal move.
            moveStats.move = userColumn + userRow;

            moveStats.shouldBreak = true;
        } else if (traversedSquare.piece != null &&
                (traversedSquare.piece.getColor().equalsIgnoreCase(square.piece.getColor()) == true)) {
            // Piece exist at square but of different color - Illegal move.
            moveStats.shouldBreak = true;
        }

        return moveStats;
    }
}
