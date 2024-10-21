package org.example;

import java.util.Scanner;

public class GameConsole {
    private Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }

    public void writeOutput(String output) {
        System.out.println(output);
    }
}
