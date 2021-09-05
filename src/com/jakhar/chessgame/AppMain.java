package com.jakhar.chessgame;

import com.jakhar.chessgame.core.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AppMain {
    private Scanner inputScanner;
    private ChessBoard gameBoard;

    private ArrayList<ChessPiece> piecesList;
    private ArrayList<ChessBoard.Square> squaresList;

    public static void main(String[] args) {
        AppMain game = new AppMain();

        try {
            game.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void start() {
        System.out.println("> FindValidMoves");

        inputScanner = new Scanner(System.in);

        boolean wantToContinuePlaying = true;

        while (wantToContinuePlaying) {
            squaresList = new ArrayList<>();
            gameBoard = new ChessBoard();

            GameContext.getInstance().setBoard(gameBoard);

            handleGameInputs();
            findValidMoves();

            System.out.print("\nContinue (Y/N)?: ");
            String nextGamePlayOption = inputScanner.next();

            if (nextGamePlayOption.equalsIgnoreCase(Constants.CONTINUE_GAME_OPTION_YES) == false) {
                // If input is not Y, stop the game anyway, don't go in loop.
                wantToContinuePlaying = false;
            }
        }
    }

    private void handleGameInputs() {
        System.out.print("\nEnter number of pieces: ");

        int numberOfPieces = inputScanner.nextInt();

        inputScanner.nextLine();

        for (int inputNumber = 1; inputNumber <= numberOfPieces; inputNumber++) {
            System.out.println("\nPiece " + inputNumber);

            // Color
            System.out.print("Enter colour (W/B): ");
            String color = inputScanner.nextLine();

            // Type
            System.out.print("Enter type (B/N/R): ");
            String type = inputScanner.nextLine();

            // Position
            System.out.print("Enter position: ");
            String position = inputScanner.nextLine();

            ChessPiece piece = new ChessPiece();
            piece.color = color;
            piece.type = type;

            squaresList.add(gameBoard.addPiece(position, piece));
            gameBoard.printSquare(position);
        }
    }

    private void findValidMoves() {
        System.out.println("\nValid moves");
        for (ChessBoard.Square square : squaresList) {
            gameBoard.findValidMoves(square);
        }
    }
}
