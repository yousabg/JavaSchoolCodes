/* Name: Yousab Grees
# Date: 4/10/22
# Class: CSC 1120
# Pledge: I have neither given nor received unauthorized aid on this program.
# Description: Bullseye is a program that will calculate the scores of two players playing a game of darts.
               Simply input the x coordinate and y coordinates for the dart position and the game will calculate
               the points for you.
# Input: The user will only input their x and y coordinates. 3 inputs for player 1, and 3 inputs for player 2. (explain commands)."
# Output: The program will give a winner per round and an ultimate winner of all the rounds combined.
*/
import java.util.*;
import java.io.*;
import java.lang.Math;
public class Bullseye {
    public static void main(String[]args) throws IOException {
        Scanner fileScan;
        int throwCount = 0; //A variable that will ensure each player gets 3 rounds.
        int round = 0;
        int p1score = 0; //Player 1's temporary score per round.
        int p2score = 0; //Player 2's temporary score per round.
        int p1totalScore = 0;
        int p2totalScore = 0;

        fileScan = new Scanner(new File("coordinates.txt"));

        while (fileScan.hasNext()) {
            double x1 = fileScan.nextDouble();
            double y1 = fileScan.nextDouble();

            int score = score(x1, y1);
            if (throwCount < 3) { //When the first player reaches 3, calculate his/her score for round and add it to his/her total points.
                p1score += score;
                p1totalScore += score;
            } else {
                p2score += score;
                p2totalScore += score;
            }
            throwCount++;
            if (throwCount == 6) { //When 6 rounds have been played, both players have had 3 rounds, and now calculate round winner.
                round++;
                printWinner(round, p1score, p2score);
                p1score = 0;
                p2score = 0;
                throwCount = 0;
            }
        } //After the while loop is over, all values of coordinates.txt has been used and the final winner will now be calculated.
        printWinner(0, p1totalScore, p2totalScore);

    }

    /*
    # This function gives points based on the radius of x and y coordinates provided.
    # Parameters: x, the x coordinate. y, the y coordinate.
    # Returns: The score given after the calculations and conditions to show what score should be given.
     */
    public static int score(double x, double y)
    {
        double radius = Math.sqrt((x*x) + (y*y));
        int score;

        //5 circles, total range of 40, so increments of 4 starting from 20 (40 is diameter, 20 is radius)
        if (radius <= 4) {
            score = 100;
        } else if (radius <= 8) {
            score = 80;
        } else if (radius <= 12) {
            score = 60;
        } else if (radius <= 16) {
            score = 40;
        } else if (radius <= 20) {
            score = 20;
        } else score = 0;

        return score;
    }

    /*
    # This function will print out the scores of player 1 and player 2, and the winner between them.
    # Parameters: round: the round number (if 0, it is final round), p1score: score for player 1, p2score: score for player 2
     */
    public static void printWinner(int round, int p1score, int p2score)
    {
        if (round!= 0) { //If final round, method call will be 0 instead of round variable.
            System.out.print("ROUND " + round + " ");
            if (p1score > p2score) {
                System.out.println("SCORE: " + p1score + " to " + p2score + ", PLAYER 1 WINS.");
            } else if (p2score > p1score) {
                System.out.println("SCORE: " + p1score + " to " + p2score + ", PLAYER 2 WINS.");
            } else {
                System.out.println("SCORE: " + p1score + " to " + p2score + ", TIE.");
            }
        }
        else
        {
            if (p1score > p2score) {
                System.out.println("FINAL SCORE: " + p1score + " to " + p2score + ", PLAYER 1 WINS.");
            } else if (p2score > p1score) {
                System.out.println("FINAL SCORE: " + p1score + " to " + p2score + ", PLAYER 2 WINS.");
            } else {
                System.out.println("FINAL SCORE: " + p1score + " to " + p2score + ", TIE.");
            }
        }
    }
}
