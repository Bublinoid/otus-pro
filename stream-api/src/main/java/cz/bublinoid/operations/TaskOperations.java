package cz.bublinoid.operations;

import cz.bublinoid.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskOperations {

    public List<Task> getTaskByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public boolean doesTaskExist(List<Task> tasks, int id) {
        return tasks.stream()
                .anyMatch(task -> task.getId() == id);
    }

    public List<Task> getSortedTaskByStatus(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

    public long countTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .count();
    }
}
