package net.jasonly.todolist.lib;

public class ReinitializationException extends IllegalStateException {
    public ReinitializationException() {
        super("Object was already initialized");
    }
}
