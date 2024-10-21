package org.example;

import java.util.Map;

public record Story(String description, String startRoom, Map<String, Room> rooms) {}
