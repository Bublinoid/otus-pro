package cz.bublinoid;

import cz.bublinoid.model.Task;
import cz.bublinoid.operations.TaskOperations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task1", "Open"));
        tasks.add(new Task(2, "Task2", "In Process"));
        tasks.add(new Task(3, "Task3", "Closed"));
        tasks.add(new Task(4, "Task4", "Open"));
        tasks.add(new Task(5, "Task5", "Closed"));

        TaskOperations operations = new TaskOperations();

        List<Task> tasksByStatus = operations.getTaskByStatus(tasks, "Open");
        System.out.println("Tasks with status 'Open': ");
        tasksByStatus.forEach(task -> System.out.println(task.getName()));

        int taskIdToCheck = 3;
        boolean taskExists = operations.doesTaskExist(tasks, taskIdToCheck);
        System.out.println("Task with ID " + taskIdToCheck + " exists: " + taskExists);

        List<Task> sortedTasks = operations.getSortedTaskByStatus(tasks);
        System.out.println("Sorted tasks by status: ");
        sortedTasks.forEach(task -> System.out.println(task.getName() + " - " + task.getStatus()));

        String statusToCount = "Closed";
        long countByStatus = operations.countTasksByStatus(tasks, statusToCount);
        System.out.println("Number of tasks with status: " + statusToCount + " : " + countByStatus);



    }
}
