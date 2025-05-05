package net.jasonly027.todolist.lib;

import net.jasonly027.todolist.models.Priority;
import net.jasonly027.todolist.models.Task;
import net.jasonly027.todolist.models.TaskBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Database {
    private final Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:tasks.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setup();
    }

    private void setup() {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS tasks (
                        id TEXT NOT NULL PRIMARY KEY,
                        name TEXT NOT NULL,
                        description NOT NULL,
                        date TEXT,
                        priority TEXT NOT NULL,
                        tags TEXT NOT NULL,
                        is_done INTEGER NOT NULL
                    )
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTasks() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks");

            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(createTaskFromResultSet(rs));
            }

            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Task task) {
        try {
            PreparedStatement st = conn.prepareStatement("""
                    INSERT INTO tasks(
                        id,
                        name,
                        description,
                        date,
                        priority,
                        tags,
                        is_done
                    ) VALUES (?, ?, ?, ?, ?, ?, ?)
                    """);
            st.setString(1, task.getId().toString());
            st.setString(2, task.nameProperty().get());
            st.setString(3, task.descriptionProperty().get());
            LocalDate date = task.dateProperty().get();
            st.setString(4, date != null ? date.toString() : null);
            st.setString(5, task.priorityProperty().get().toString());
            st.setString(6, String.join(",", task.tagsProperty().get()));
            st.setInt(7, task.isDoneProperty().get() ? 1 : 0);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task createTaskFromResultSet(ResultSet rs) throws SQLException {
        String date = rs.getString("date");

        return new TaskBuilder()
                .setId(UUID.fromString(rs.getString("id")))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setDate(date != null ? LocalDate.parse(date) : null)
                .setPriority(Priority.valueOf(rs.getString("priority")))
                .setTags(rs.getString("tags").split(","))
                .setIsDone(rs.getBoolean("is_done"))
                .build();
    }

    public void update(Task task) {
        try {
            PreparedStatement st = conn.prepareStatement("""
                    UPDATE tasks SET
                    name = ?,
                    description = ?,
                    date = ?,
                    priority = ?,
                    tags = ?,
                    is_done = ?
                    WHERE id = ?
                    """);
            st.setString(1, task.nameProperty().get());
            st.setString(2, task.descriptionProperty().get());
            LocalDate date = task.dateProperty().get();
            st.setString(3, date != null ? date.toString() : null);
            st.setString(4, task.priorityProperty().get().toString());
            st.setString(5, String.join(",", task.tagsProperty().get()));
            st.setInt(6, task.isDoneProperty().get() ? 1 : 0);
            st.setString(7, task.getId().toString());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(UUID id) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM tasks WHERE id = " + id.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(UUID... ids) {
        try {
            String placeholders = String.join(",", Collections.nCopies(ids.length, "?"));
            PreparedStatement st =
                    conn.prepareStatement("DELETE FROM tasks WHERE id IN (" + placeholders + ")");
            for (int i = 0; i < ids.length; ++i) {
                st.setString(i + 1, ids[i].toString());
            }
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
