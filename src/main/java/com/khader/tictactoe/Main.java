package com.khader.tictactoe;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        char[][] gameState = new char[3][3];
        String cells = "         ";
        gameState = initStateBuilder(cells);
        initPrintState(cells);
        boolean isX = true;
        boolean flag = true;
        while (flag) {
            readCoordinates(gameState, isX);
            isX = !isX;
            if(checkState(gameState) != '#')
                break;
        }

    }

    public static void readCoordinates(char[][] gameState, boolean isX) {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            System.out.print("Enter the Coordinates: ");
            String moveX = scanner.next();
            String moveY = scanner.next();
            check = checkInput(moveX, moveY, gameState);
            if (check) {
                stateUpdate(gameState, Integer.parseInt(moveX), Integer.parseInt(moveY), isX);
                printState(gameState);
            }
        }
    }

    public static char winCheck(char[][] cells) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == 'O' && cells[i][1] == 'O' && cells[i][2] == 'O') {
                return 'O';
            }
            if (cells[0][i] == 'O' && cells[1][i] == 'O' && cells[2][i] == 'O') {
                return 'O';
            }
            if (cells[i][0] == 'X' && cells[i][1] == 'X' && cells[i][2] == 'X') {
                return 'X';
            }
            if (cells[0][i] == 'X' && cells[1][i] == 'X' && cells[2][i] == 'X') {
                return 'X';
            }
        }
        if (cells[0][0] == 'X' && cells[1][1] == 'X' && cells[2][2] == 'X') {
            return 'X';
        }
        if (cells[2][0] == 'X' && cells[1][1] == 'X' && cells[0][2] == 'X') {
            return 'X';
        }
        if (cells[0][0] == 'O' && cells[1][1] == 'O' && cells[2][2] == 'O') {
            return 'O';
        }
        if (cells[2][0] == 'O' && cells[1][1] == 'O' && cells[0][2] == 'O') {
            return 'O';
        }
        return '#';

    }

    public static boolean drawCheck(char[][] cells) {
        for (char[] row : cells) {
            for (char aChar : row) {
                if (aChar == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printState(char[][] state) {
        System.out.println("---------");
        for (char[] chars : state) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void initPrintState(String state) {
        System.out.println("---------");
        for (int x = 0; x < 3; x++) {
            System.out.print("| ");
            for (int i = 0; i < 3; i++) {
                System.out.print(state.charAt(0) + " ");
                state = state.substring(1);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static char[][] initStateBuilder(String state) {
        char[][] tempState = new char[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tempState[x][y] = state.charAt(0);
                state = state.substring(1);
            }
        }
        return tempState;
    }

    public static void stateUpdate(char[][] state, int moveX, int moveY, boolean isX) {
        state[3 - moveY][moveX - 1] = isX ? 'X' : 'O';
    }

    public static char checkState(char[][] gameState) {
        char winner = winCheck(gameState);
        if (winner != '#') {
            System.out.print(winner + " wins");
            return winner;
        } else if (drawCheck(gameState)) {
            System.out.print("Draw");
            return 'D';
        } else {
            return '#';
        }
    }

    public static boolean checkInput(String moveX, String moveY, char[][] state) {
        if (moveX.chars().anyMatch(x -> x > '9' || x < '0') || moveY.chars().anyMatch(x -> x > '9' || x < '0')) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (Integer.parseInt(moveX) > 3 || Integer.parseInt(moveY) > 3 || Integer.parseInt(moveX) < 1 || Integer.parseInt(moveY) < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (state[3 - Integer.parseInt(moveY)][Integer.parseInt(moveX) - 1] != ' ') {
            System.out.println("Already occupied, please enter a new location!");
            return false;
        }
        return true;
    }
}
