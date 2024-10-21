package org.example;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/resources/Tutorial.yaml";
        StoryTeller storyteller = new StoryTeller(filePath);
        storyteller.startGame();
    }
}
