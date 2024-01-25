    import TaskManager.TaskManager;
    import model.*;
    import java.util.ArrayList;

    public class Main {

        public static void main(String[] args) {

            TaskManager taskManager = new TaskManager();
            Task task1 = taskManager.create(new Task("task1", "task1 descriptoin", Status.NEW));
            Task task2 = taskManager.create(new Task("task2", "task2 descriptoin", Status.NEW));
            Epic epic1 = taskManager.create(new Epic("epic1", "epic1 descriptoin", new ArrayList<>()));

            System.out.println("epic1 получает статус NEW");
            taskManager.printEpic(epic1.getId());
            System.out.println();

            SubTask sub1 = taskManager.create(new SubTask("sub1", "sub1 descriptoin", Status.NEW), epic1);
            SubTask sub2 = taskManager.create(new SubTask("sub2", "sub2 descriptoin", Status.NEW), epic1);
            Epic epic2 = taskManager.create(new Epic("epic2", "epic2 descriptoin", new ArrayList<>()));
            SubTask sub3 = taskManager.create(new SubTask("sub3", "sub3 descriptoin", Status.NEW), epic2);

            System.out.println("epic1 и epic2 получают статус IN_PROGRESS");
            taskManager.printAllTasks();
            System.out.println();
            taskManager.printAllEpics();
            System.out.println();
            taskManager.printAllSubTasks();
            System.out.println();

            Task newTask = new Task("new task1", "new task1 description", Status.DONE);
            newTask.setId(task1.getId());
            taskManager.updateTask(newTask);

            SubTask newSub = new SubTask("new sub1", "new sub1 description", Status.DONE);
            newSub.setId(sub3.getId());
            taskManager.updateSubTask(newSub);

            System.out.println("epic2 получает статус DONE");
            taskManager.printAllTasks();
            System.out.println();
            taskManager.printAllEpics();
            System.out.println();
            taskManager.printAllSubTasks();
            System.out.println();

            taskManager.removeTask(task2.getId());
            taskManager.removeSubTasks(sub3.getId());
            taskManager.removeEpic(epic1.getId());

            System.out.println("epic2 получает статус NEW");
            System.out.println("Также удалятся sub1 и sub2, принадлежащие epic1");
            taskManager.printAllTasks();
            System.out.println();
            taskManager.printAllEpics();
            System.out.println();
            taskManager.printAllSubTasks();
            System.out.println();
        }
    }
