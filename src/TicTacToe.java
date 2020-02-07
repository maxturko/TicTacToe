import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] arrayOfElements = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        int coordinateX;
        int coordinateY;
        char nextElement = 'X';
        boolean isGameStateImpossible;
        boolean didXWin;
        boolean didOWin;
        boolean isItADraw;

        //game loop
        while (true){
            //checking if game has ended - 4 possible endings available
            isGameStateImpossible = isGameStateImpossible(arrayOfElements);
            didXWin = didAPlayerWin('X', arrayOfElements);
            didOWin = didAPlayerWin('O', arrayOfElements);
            isItADraw = isItADraw(arrayOfElements);
            //Printing current board state
            printBoard(arrayOfElements);
            //If game ended - print the result and break the loop
            if (isGameStateImpossible || (didXWin && didOWin)){
                System.out.println("Impossible");
            } else if (didXWin){
                System.out.println("X wins");
                break;
            } else if (didOWin){
                System.out.println("O wins");
                break;
            } else if (isItADraw) {
                System.out.println("Draw");
                break;
            } else {
                //game not ended. entering the coordinate input loop
                while (true) {
                    System.out.print("Enter the coordinates: ");
                    try {
                        coordinateX = scanner.nextInt();
                        coordinateY = scanner.nextInt();
                        if (areCoordinatesValid(coordinateX, coordinateY, arrayOfElements, nextElement)) {
                            if (nextElement == 'X') {
                                nextElement = 'O';
                            } else {
                                nextElement = 'X';
                            }
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("You should enter numbers!");
                    }
                }
            }

        }
    }

    public static boolean areCoordinatesValid (int x, int y, char[] ch, char nextElement){

        int indexOfChar = x - 1 + (3 - y) * 3;

        if (indexOfChar < 0 || indexOfChar > 8 ){
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (ch[indexOfChar] == 'X' || ch[indexOfChar] == 'O'){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        ch[indexOfChar] = nextElement;

        return true;
    }

    public static boolean isItADraw (char[] ch){
        int chCount = 0;

        for (char c : ch) {
            if (c == 'X') {
                chCount++;
            } else if (c == 'O') {
                chCount++;
            }
        }

        if (chCount == ch.length){
            return true;
        } else {
            return false;
        }
    }

    public static void printBoard (char[] arrayOfElements){
        System.out.println("---------");
        System.out.println("| " + arrayOfElements[0] + " " + arrayOfElements[1] + " " + arrayOfElements[2] +" |");
        System.out.println("| " + arrayOfElements[3] + " " + arrayOfElements[4] + " " + arrayOfElements[5] +" |");
        System.out.println("| " + arrayOfElements[6] + " " + arrayOfElements[7] + " " + arrayOfElements[8] +" |");
        System.out.println("---------");
    }

    public static boolean isGameStateImpossible (char[] ch){
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < ch.length; i++){
            if (ch[i] == 'X'){
                xCount++;
            } else if (ch[i] == 'O'){
                oCount++;
            }
        }

        if (xCount == oCount || xCount == oCount + 1 || xCount + 1 == oCount || (xCount == 0 && oCount == 0)){
            return false;
        } else {
            return true;
        }
    }

    public static boolean didAPlayerWin(char xo, char[] ch){
        if (xo == ch[0] && xo == ch[1] && xo == ch[2]){
            return true;
        }

        if (xo == ch[0] && xo == ch[4] && xo == ch[8]){
            return true;
        }
        if (xo == ch[0] && xo == ch[3] && xo == ch[6]){
            return true;
        }
        if (xo == ch[1] && xo == ch[4] && xo == ch[7]){
            return true;
        }
        if (xo == ch[2] && xo == ch[5] && xo == ch[8]){
            return true;
        }
        if (xo == ch[2] && xo == ch[4] && xo == ch[6]){
            return true;
        }
        if (xo == ch[3] && xo == ch[4] && xo == ch[5]){
            return true;
        }
        if (xo == ch[6] && xo == ch[7] && xo == ch[8]){
            return true;
        }
        return false;
    }
}
