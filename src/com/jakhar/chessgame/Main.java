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
        squaresList = new ArrayList<>();
        gameBoard = new Board();

        handleGameInputs();
        findValidMoves();
    }

    private void handleGameInputs() {
        System.out.println("> FindValidMoves");

        inputScanner = new Scanner(System.in);

        System.out.print("Enter number of pieces: ");

        int numberOfPieces = inputScanner.nextInt();

        System.out.println("");
        inputScanner.nextLine();

        for (int inputNumber = 1; inputNumber <= numberOfPieces; inputNumber++) {
            System.out.println("Piece " + inputNumber);

            // Color
            System.out.print("Enter colour (W/B): ");
            String color = inputScanner.nextLine();
            System.out.println("You entered color: " + color);

            // Type
            System.out.print("Enter type (B/N): ");
            String type = inputScanner.nextLine();
            System.out.println("You entered type: " + type);

            // Position
            System.out.print("Enter position: ");
            String position = inputScanner.nextLine();
            System.out.println("You entered type: " + type);

            Piece piece = new Piece();
            piece.setColor(color);
            piece.setType(type);

            squaresList.add(gameBoard.addPiece(position, piece));
            gameBoard.printSquare(position);
        }
    }

    private void findValidMoves() {
        for (Board.Square square : squaresList) {
            gameBoard.findValidMoves(square);
        }
    }

    private void findValidMoves(Board.Square square) {

    }
}
