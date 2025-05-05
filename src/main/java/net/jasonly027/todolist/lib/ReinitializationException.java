package net.jasonly027.todolist.lib;

public class ReinitializationException extends IllegalStateException {
    public ReinitializationException() {
        super("Object was already initialized");
    }
}
