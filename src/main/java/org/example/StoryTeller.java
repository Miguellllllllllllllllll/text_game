package org.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StoryTeller {
    public StoryReader storyReader;
    public GameConsole console;  // Verwende jetzt GameConsole
    public Story story;
    public Room currentRoom;
    public Set<String> states; // Set to keep track of the game states

    public StoryTeller(String filePath) {
        this.storyReader = new StoryReader();
        this.console = new GameConsole();  // Hier ebenfalls GameConsole
        this.states = new HashSet<>();
        try {
            this.story = storyReader.readStory(filePath);
            this.currentRoom = story.rooms().get(story.startRoom());
        } catch (IOException e) {
            console.writeOutput("Fehler beim Lesen der Story: " + e.getMessage());
        }
    }

    public void startGame() {
        console.writeOutput(story.description());
        while (true) {
            console.writeOutput(currentRoom.description());
            String input = console.readInput().toLowerCase();
            String[] parts = input.split(" ", 2);
            String verb = parts[0];
            String object = parts.length > 1 ? parts[1] : "";

            processCommand(verb, object);
        }
    }

    private void processCommand(String verb, String object) {
        if (currentRoom.verbs().containsKey(verb)) {
            Map<String, List<Action>> objectActions = currentRoom.verbs().get(verb);
            if (objectActions.containsKey(object)) {
                List<Action> actions = objectActions.get(object);
                for (Action action : actions) {
                    executeAction(action);
                }
            } else {
                console.writeOutput("Ungültiges Objekt für dieses Verb.");
            }
        } else {
            console.writeOutput("Ungültiges Verb.");
        }
    }

    private void executeAction(Action action) {
        if (action.ifState().isPresent() && !states.contains(action.ifState().get())) {
            console.writeOutput("Du kannst das nicht tun.");
            return;
        }

        action.message().ifPresent(console::writeOutput);
        action.addState().ifPresent(states::add);
        action.room().ifPresent(roomKey -> {
            currentRoom = story.rooms().get(roomKey);
            console.writeOutput("Du betrittst: " + currentRoom.name());
        });
    }
}
