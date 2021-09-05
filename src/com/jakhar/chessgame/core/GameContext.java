package com.jakhar.chessgame.core;

public class GameContext {
    private static GameContext mGameContext;

    private ChessBoard mBoard;

    public static GameContext getInstance() {
        if (mGameContext == null) {
            mGameContext = new GameContext();
        }

        return mGameContext;
    }

    // DON'T allow direct instantiation
    private GameContext() {
    }

    public ChessBoard getBoard() {
        return mBoard;
    }

    public void setBoard(ChessBoard board) {
        mBoard = board;
    }
}
