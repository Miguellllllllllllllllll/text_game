package org.example;

import java.util.Optional;

public record Action(Optional<String> room, Optional<String> message, Optional<String> addState, Optional<String> ifState) {}
