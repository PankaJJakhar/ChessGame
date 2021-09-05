package com.jakhar.chessgame.core;

import java.util.*;

public class ChessBoard {
    // These 4 Maps are used for mapping user entered location
    // with the game and vice a versa.
    public HashMap<Integer, Integer> mUserRowToIndexMap;
    public HashMap<String, Integer> mUserColumnToIndexMap;
    public HashMap<Integer, Integer> mIndexToUserRowMap;
    public HashMap<Integer, String> mIndexToUserColumnMap;

    public Square[][] mChessBoardMatrix;

    private Bishop mBishop;
    private Knight mKnight;
    private Rook mRook;

    /**
     * Each square on the board will be represented by this
     * Chess has 8x8 squares on the board.
     * Each square has its indices so that we know where it is located.
     * Square also has the information on the chess Piece sitting on it.
     * Square also has information of user entered position.
     */
    public class Square {
        public ChessPiece piece;

        public int rowIndex;
        public int columnIndex;

        public String userPosition;
    }

    public ChessBoard() {
        initialize();
    }

    private void initialize() {
        initializeTheBoard();
        initializeChessPieces();
        createUserInputToBoardMapping();
    }

    /**
     * Empty board with no chess piece on it.
     */
    private void initializeTheBoard() {
        mChessBoardMatrix = new Square[8][8];

        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                Square square = new Square();
                square.rowIndex = rowIndex;
                square.columnIndex = columnIndex;

                mChessBoardMatrix[rowIndex][columnIndex] = square;
            }
        }
    }

    /**
     * Initialize all chess pieces for using their functionalities on the board.
     */
    private void initializeChessPieces() {
        mBishop = new Bishop();
        mKnight = new Knight();
        mRook = new Rook();
    }

    /**
     * This mapping will help in mapping user entered input to game's internal logic.
     */
    private void createUserInputToBoardMapping() {
        mUserRowToIndexMap = new HashMap<>();
        mUserColumnToIndexMap = new HashMap<>();
        mIndexToUserRowMap = new HashMap<>();
        mIndexToUserColumnMap = new HashMap<>();

        mUserRowToIndexMap.put(8, 0);
        mUserRowToIndexMap.put(7, 1);
        mUserRowToIndexMap.put(6, 2);
        mUserRowToIndexMap.put(5, 3);
        mUserRowToIndexMap.put(4, 4);
        mUserRowToIndexMap.put(3, 5);
        mUserRowToIndexMap.put(2, 6);
        mUserRowToIndexMap.put(1, 7);

        mIndexToUserRowMap.put(0, 8);
        mIndexToUserRowMap.put(1, 7);
        mIndexToUserRowMap.put(2, 6);
        mIndexToUserRowMap.put(3, 5);
        mIndexToUserRowMap.put(4, 4);
        mIndexToUserRowMap.put(5, 3);
        mIndexToUserRowMap.put(6, 2);
        mIndexToUserRowMap.put(7, 1);

        mUserColumnToIndexMap.put(Constants.COLUMN_A, 0);
        mUserColumnToIndexMap.put(Constants.COLUMN_B, 1);
        mUserColumnToIndexMap.put(Constants.COLUMN_C, 2);
        mUserColumnToIndexMap.put(Constants.COLUMN_D, 3);
        mUserColumnToIndexMap.put(Constants.COLUMN_E, 4);
        mUserColumnToIndexMap.put(Constants.COLUMN_F, 5);
        mUserColumnToIndexMap.put(Constants.COLUMN_G, 6);
        mUserColumnToIndexMap.put(Constants.COLUMN_H, 7);

        mIndexToUserColumnMap.put(0, Constants.COLUMN_A);
        mIndexToUserColumnMap.put(1, Constants.COLUMN_B);
        mIndexToUserColumnMap.put(2, Constants.COLUMN_C);
        mIndexToUserColumnMap.put(3, Constants.COLUMN_D);
        mIndexToUserColumnMap.put(4, Constants.COLUMN_E);
        mIndexToUserColumnMap.put(5, Constants.COLUMN_F);
        mIndexToUserColumnMap.put(6, Constants.COLUMN_G);
        mIndexToUserColumnMap.put(7, Constants.COLUMN_H);
    }

    /**
     * Add pieces to the board by specifying the position
     *
     * @param position
     * @param piece
     * @return
     */
    public Square addPiece(String position, ChessPiece piece) {
        char[] charsInPosition = position.toCharArray();

        String userColumn = String.valueOf(charsInPosition[0]);
        int userRow = Integer.valueOf(String.valueOf(charsInPosition[1]));

        int rowIndex = mUserRowToIndexMap.get(userRow);
        int columnIndex = mUserColumnToIndexMap.get(userColumn);

        Square square = mChessBoardMatrix[rowIndex][columnIndex];
        square.userPosition = position;
        square.piece = piece;

        return square;
    }

    /**
     * Print the information at some position on the board.
     *
     * @param position
     */
    public void printSquare(String position) {
        char[] charsInPosition = position.toCharArray();

        String userColumn = String.valueOf(charsInPosition[0]);
        int userRow = Integer.valueOf(String.valueOf(charsInPosition[1]));

        int rowIndex = mUserRowToIndexMap.get(userRow);
        int columnIndex = mUserColumnToIndexMap.get(userColumn);

        Square square = mChessBoardMatrix[rowIndex][columnIndex];
    }

    /**
     * Find the valid moves for a piece on specific square on the board.
     *
     * Currently supported piece types:
     * - Bishop
     * - Knight
     * - Rook
     *
     * @param square
     */
    public void findValidMoves(Square square) {
        if (square.piece.type.equalsIgnoreCase(Constants.PIECE_TYPE_BISHOP)) {
            mBishop.findValidMoves(square);
        } else if (square.piece.type.equalsIgnoreCase(Constants.PIECE_TYPE_KNIGHT)) {
            mKnight.findValidMoves(square);
        } else if (square.piece.type.equalsIgnoreCase(Constants.PIECE_TYPE_ROOK)) {
            mRook.findValidMoves(square);
        }
    }
}
