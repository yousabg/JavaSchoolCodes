/*
Name: Yousab Grees
Date: 3/20/2022
Class: CSC 1120
Pledge: I have neither given nor received unauthorized aid on this program.
Description: Four In A row is a game where in a 4x4 board,
             two players complete and must try to fill up
             four of the spaces beside each other.
Input: The user(s) will type their names before the game, and what spaces
       they want to fill during the rounds.
Output: The program will mark the board on the spaces inputted and when a
        winner is found, it will state so. If there is no winner, the
        program will declare it a draw.
 */

import java.util.Scanner;
public class FourInARowAdvanced {
    public static void main(String[] args) {
        String playerOneName;
        String playerTwoName;
        boolean winnerA = false;
        boolean winnerB = false;
        int count = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Four in a Row!");

        System.out.println("Please enter the name of player 1!");
        playerOneName = input.nextLine();
        System.out.println("Player One is: " + playerOneName);
        System.out.println("Please enter the name of player 2!");
        playerTwoName = input.nextLine();
        System.out.println("Player Two is: " + playerTwoName);

        String[][] board = new String[4][4];
        resetBoard(board);

        //This while loop runs the game. round and isWin are called until isWin returns true or count = 16 (meaning there is a draw.)
        while (!winnerA && !winnerB) {
            count++;
            if (count%2 == 1) round(board, count, playerOneName);
            else round(board, count, playerTwoName);
            winnerA = isWin(board, "A");
            winnerB = isWin(board, "B");
            if (count == 16)
            {
                winnerA = true;
                winnerB = true;
            }
        }
        if (winnerA && winnerB){
            printBoard(board);
            System.out.println("There is a draw.");
        }
        else if (winnerA) {
            printBoard(board);
            System.out.println(playerOneName + " has won!");
        }
        else {
            printBoard(board);
            System.out.println(playerTwoName + " has won!");}

        System.out.println("Would you like to play again?");
        String result = input.nextLine();
        while (result.equals("yes") || result.equals("Yes"))
        {
            resetBoard(board);
            winnerA = false;
            winnerB = false;
            count = 0;

            while (!winnerA && !winnerB) {
                count++;
                if (count%2 == 1) round(board, count, playerOneName);
                else round(board, count, playerTwoName);
                winnerA = isWin(board, "A");
                winnerB = isWin(board, "B");
                if (count == 16)
                {
                    winnerA = true;
                    winnerB = true;
                }
            }
            if (winnerA && winnerB){
            printBoard(board);
            System.out.println("There is a draw.");
        }
            else if (winnerA) {
                printBoard(board);
                System.out.println(playerOneName + " has won!");
            }
            else {
                printBoard(board);
                System.out.println(playerTwoName + " has won!");
            }
                System.out.println("Would you like to play again?");
                result = input.nextLine();

        }
    System.out.println("Ok! Thanks for playing!");


    }
    /*
    This method will reset the board to the default value (_).
    The parameter is the board to be reset.
     */
    public static void resetBoard(String[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int k = 0; k < board.length; k++)
            {
                board[i][k] = "_";
            }
        }
    }
    /*
    This method will print out the board.
    The parameter is the board to be print.
     */
    public static void printBoard(String[][] board)
    {

        for (int i = 0; i< 4; i++)
        {
            for (int k = 0; k < 4; k++)
            {
                System.out.print(board[i][k] + "  ");
            }
            System.out.println();

        }
    }
    /*
    This method will process one round.
    The parameters are the board, the current count of the game (round number), and the name of the player.
     */
    public static void round(String[][] board, int count, String name)
    {
        boolean accurate = false;
        int column = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("This is the current state of the board:");
        printBoard(board);
        System.out.println("It is currently " + name + "'s turn.");
        System.out.println("Please state the column you would like to play.");
        System.out.println("You may have to type it twice.");
        column = scan.nextInt();
        while (column < 1 || column > 4)
        {
            System.out.println("You need to pick a number between 1 and 4.");
            column = scan.nextInt();
        }
        while (!accurate)
        {
            //Checks to see what is the lowest row in that column that the letter could be placed.
            if (board[3][column-1].equals("_"))
            {
                if (count%2 == 1)
                {
                    board[3][column-1] = "A";
                }
                else board[3][column-1] = "B";
                accurate = true;
            }
            else if (board[2][column-1].equals("_"))
            {
                if (count%2 == 1)
                {
                    board[2][column-1] = "A";
                }
                else board[2][column-1] = "B";
                accurate = true;
            }
            else if (board[1][column-1].equals("_"))
            {
                if (count%2 == 1)
                {
                    board[1][column-1] = "A";
                }
                else board[1][column-1] = "B";
                accurate = true;
            }
            else if (board[0][column-1].equals("_"))
            {
                if (count%2 == 1)
                {
                    board[0][column-1] = "A";
                }
                else board[0][column-1] = "B";
                accurate = true;
            }
            else System.out.println("That column is full! Pick again.");
            column = scan.nextInt();
        }

    }

    /*
    This method checks to see if there is a winner.
    The parameters are the board and a letter (A or B) to see if that specific letter has won or not.
    The return is true if that letter has won, or false if that letter has not won yet.
     */
    public static boolean isWin(String[][] board, String letter)
    {
        int countA = 0;
        int countB = 0;
        boolean winA = false;
        boolean winB = false;

        //Checks vertically
        for (int i = 0; i<4; i++)
        {
            for (int k = 0; k<4; k++) {
                if (board[i][k].equals("A")) {
                    countA++;
                }
                if (countA == 4) {
                    winA = true;
                }

                if (board[i][k].equals("B")) {
                    countB++;
                }
                if (countB == 4) {
                    winB = true;
                }
            }
            countA = 0;
            countB = 0;
        }

        //Checks horizontally
        for (int o = 0; o< 4; o++)
        {
            for (int p = 0; p<4; p++)
            {
                if (board[p][o].equals("A")){
                    countA++;
                }
                if (countA == 4)
                {
                    winA = true;
                }
                if (board[p][o].equals("B")){
                    countB++;
                }
                if (countB == 4)
                {
                    winB = true;
                }
            }
            countA = 0;
            countB = 0;
        }
        //Checks negative diagonal
        if (!winA && !winB) {
            for (int a = 0; a < 4; a++)
            {
                if (board[a][a].equals("A"))
                {
                    countA++;
                }
                if (board[a][a].equals("B"))
                {
                    countB++;
                }
                if (countA == 4)
                {
                    winA = true;
                }
                if (countB == 4)
                {
                    winB = true;
                }
            }
            //Checks positive diagonal
            if (board[3][0].equals("A") && board[2][1].equals("A") && board[1][2].equals("A") && board[0][3].equals("A"))
            {
                winA = true;
            }
            if (board[3][0].equals("B") && board[2][1].equals("B") && board[1][2].equals("B") && board[0][3].equals("B"))
            {
                winA = true;
            }
        }
        if (letter.equals("A") && winA)
            return true;

        if (letter.equals("B") && winB)
            return true;

        return false;
    }
}

