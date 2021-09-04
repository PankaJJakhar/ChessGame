package com.jakhar.chessgame;

import com.jakhar.chessgame.core.Board;
import com.jakhar.chessgame.core.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Scanner inputScanner;
    private Board gameBoard;

    private ArrayList<Piece> piecesList;
    private ArrayList<Board.Square> squaresList;

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }

    public void start() {
        System.out.println("> FindValidMoves");

        boolean wantToContinuePlaying = true;

        while (wantToContinuePlaying) {
            squaresList = new ArrayList<>();
            gameBoard = new Board();

            handleGameInputs();
            findValidMoves();

            System.out.print("\nContinue (Y/N)?: ");
            String nextGamePlayOption = inputScanner.next();

            if (nextGamePlayOption.equalsIgnoreCase("Y") == false) {
                // If input is not Y, stop the game anyway, don't go in loop.
                wantToContinuePlaying = false;
            }
        }
    }

    private void handleGameInputs() {
        inputScanner = new Scanner(System.in);

        System.out.print("\nEnter number of pieces: ");

        int numberOfPieces = inputScanner.nextInt();

        inputScanner.nextLine();

        for (int inputNumber = 1; inputNumber <= numberOfPieces; inputNumber++) {
            System.out.println("\nPiece " + inputNumber);

            // Color
            System.out.print("Enter colour (W/B): ");
            String color = inputScanner.nextLine();

            // Type
            System.out.print("Enter type (B/N): ");
            String type = inputScanner.nextLine();

            // Position
            System.out.print("Enter position: ");
            String position = inputScanner.nextLine();

            Piece piece = new Piece();
            piece.setColor(color);
            piece.setType(type);

            squaresList.add(gameBoard.addPiece(position, piece));
            gameBoard.printSquare(position);
        }
    }

    private void findValidMoves() {
        System.out.println("\nValid moves");
        for (Board.Square square : squaresList) {
            gameBoard.findValidMoves(square);
        }
    }

    private void findValidMoves(Board.Square square) {

    }
}
