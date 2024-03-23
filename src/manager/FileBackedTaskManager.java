package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;
    public static final String TASK_CSV = "resources\\task.csv";

    public FileBackedTaskManager() {
        this(Managers.getDefaultHistory());
    }

    public FileBackedTaskManager(HistoryManager historyManager) {
        this(historyManager, new File(TASK_CSV));
    }

    public FileBackedTaskManager(File file) {
        this(Managers.getDefaultHistory(), file);
    }

    public FileBackedTaskManager(HistoryManager historyManager, File file) {
        super(historyManager);
        this.file = file;
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        manager.loadFromFile();
        return manager;
    }

    @Override
    public Task getTask(int id) {
        Task nextTask = super.getTask(id);
        save();
        return nextTask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic nextEpic = super.getEpic(id);
        save();
        return nextEpic;
    }

    @Override
    public SubTask getSubTask(int id) {
        SubTask nextSubTask = super.getSubTask(id);
        save();
        return nextSubTask;
    }

    @Override
    public Task createTask(Task task) {
        Task newTask = super.createTask(task);
        save();
        return newTask;
    }

    @Override
    public SubTask createSubTask(SubTask subTask, Epic epic) {
        SubTask newSubTask = super.createSubTask(subTask, epic);
        save();
        return newSubTask;
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic newEpic = super.createEpic(epic);
        save();
        return newEpic;
    }

    @Override
    public void removeTask(int id) {
        super.removeTask(id);
        save();
    }

    @Override
    public void removeEpic(int id) {
        super.removeEpic(id);
        save();
    }

    @Override
    public void removeSubTasks(int id) {
        super.removeSubTasks(id);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        save();
    }

    private static String toString(Task task) {
        return task.getId() + "," + task.getType() + "," + task.getName() + "," + task.getStatus() + ","
                + task.getDescription() + "," + task.getEpicId();
    }

    private Task fromString(String value) {
        final String[] columns = value.split(",");
        String name = String.valueOf(columns[2]);
        String description = String.valueOf(columns[4]);
        Status status = Status.valueOf(columns[3]);
        int id = Integer.parseInt(columns[0]);
        TaskType type = TaskType.valueOf(columns[1]);
        Task task = null;
        switch (type) {
            case TASK:
                task = new Task(name, description);
                task.setStatus(status);
                task.setId(id);
                if (seq <= id) {
                    seq = id + 1;
                }
                break;
            case SUBTASK:
                task = new SubTask(name, description);
                task.setStatus(status);
                task.setId(id);
                if (seq <= id) {
                    seq = id + 1;
                }
                break;
            case EPIC:
                task = new Epic(name, description, null);
                task.setStatus(status);
                task.setId(id);
                if (seq <= id) {
                    seq = id + 1;
                }
                break;
        }
        return task;
    }

    static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        List<Task> historyTasks = manager.getAllTasks();
        for (Task task : historyTasks) {
            sb.append(task.getId()).append(",");
        }
        return sb.toString();
    }

    static List<Integer> historyFromString(String value) {
        final String[] ids = value.split(",");
        List<Integer> history = new ArrayList<>();
        for (String id : ids) {
            history.add(Integer.valueOf(id));
        }
        return history;
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

        writer.write("id,type,name,status,description,epic\n");

        for (int i = 0; i < getAllTasks().size(); i++) {
            writer.write(toString(getAllTasks().get(i)));
            writer.newLine();
        }

        for (int i = 0; i < getAllEpics().size(); i++) {
            writer.write(toString(getAllEpics().get(i)));
            writer.newLine();
        }

        for (int i = 0; i < getAllSubTasks().size(); i++) {
            writer.write(toString(getAllSubTasks().get(i)));
            writer.newLine();
        }

        writer.newLine();
        writer.write(historyToString(historyManager));

        } catch (IOException e) {
            throw new RuntimeException("Ошибка в файле: " + file.getAbsolutePath(), e);
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            reader.readLine();
            while (true) {
                String line = reader.readLine();
                if (line.isEmpty()) {
                    break;
                }

                final Task task = fromString(line);

                if (task.getType() == TaskType.TASK) {
                    tasks.put(task.getId(), task);
                } else if (task.getType() == TaskType.EPIC) {
                    epics.put(task.getId(), (Epic) task);
                } else if (task.getType() == TaskType.SUBTASK) {
                    subTasks.put(task.getId(), (SubTask) task);
                }
            }

            String historyData = reader.readLine();
            List<Integer> historyFromFile = historyFromString(historyData);
            for (int id : historyFromFile) {
                if (tasks.containsKey(id)) {
                    historyManager.add(tasks.get(id));
                } else if (epics.containsKey(id)) {
                    historyManager.add(epics.get(id));
                } else if (subTasks.containsKey(id)) {
                    historyManager.add(subTasks.get(id));
                }
            }
        } catch (NullPointerException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в файле: " + file.getAbsolutePath(), e);
        }
    }
}
