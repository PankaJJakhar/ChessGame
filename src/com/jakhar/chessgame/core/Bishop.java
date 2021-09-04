package com.jakhar.chessgame.core;

public class Bishop extends Piece {
    private MoveStrategy moveStrategy;

    public Bishop() {
        moveStrategy = MoveStrategy.BISHOP;
    }
}
