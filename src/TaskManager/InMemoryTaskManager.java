    package taskManager;

    import model.*;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.LinkedList;
    import java.util.List;

    public class InMemoryTaskManager implements TaskManager {

        private final HashMap<Integer, Task> tasks;
        private final HashMap<Integer, Epic> epics;
        private final HashMap<Integer, SubTask> subTasks;
        private final HistoryManager historyManager;

        private final LinkedList<Task> oldValuesTask = new LinkedList<>();

        int seq = 1;

        public InMemoryTaskManager(HistoryManager defaultHistory) {
            this.historyManager = defaultHistory;
            tasks = new HashMap<>();
            epics = new HashMap<>();
            subTasks = new HashMap<>();
        }

        private int generateId() {
            return seq++;
        }

        @Override
        public Task create(Task task) {
            task.setId(generateId());
            tasks.put(task.getId(), task);
            return task;
        }

        @Override
        public Epic create(Epic epic) {
            epic.setId(generateId());
            epics.put(epic.getId(), epic);
            return epic;
        }

        @Override
        public SubTask create(SubTask subTask, Epic epic) {
            subTask.setId(generateId());
            subTasks.put(subTask.getId(), subTask);
            epic.getSubTasks().add(subTask);
            subTask.setEpic(epic);
            updateEpicStatus(epic);
            return subTask;
        }

        @Override
        public void printAllTasks() {
            for (Integer task : tasks.keySet()) {
                System.out.println(tasks.get(task));
            }
        }

        @Override
        public void printAllEpics() {
            for (Integer epic : epics.keySet()) {
                System.out.println(epics.get(epic));
            }
        }

        @Override
        public void printAllSubTasks() {
            for (Integer subTask : subTasks.keySet()) {
                System.out.println(subTasks.get(subTask));
            }
        }

        @Override
        public void printAllSubTasksInEpic(Epic epic) {
            if (epic.getSubTasks().isEmpty()) {
                System.out.println("Список подзадач пуст");
            } else {
                for (SubTask sub : epic.getSubTasks()) {
                    System.out.println(sub);
                }
            }
        }

        @Override
        public void printTask(int id) {
            if (tasks.containsKey(id)) {
                System.out.println(tasks.get(id));
            } else {
                return;
            }
        }

        @Override
        public void printEpic(int id) {
            if (epics.containsKey(id)) {
                System.out.println(epics.get(id));
            } else {
                return;
            }
        }

        @Override
        public void printSubTasks(int id) {

            if (subTasks.containsKey(id)) {
                System.out.println(subTasks.get(id));
            } else {
                return;
            }
        }

        @Override
        public void removeTask(int id) {
            if (tasks.containsKey(id)) {
                tasks.remove(id);

            } else {
                return;
            }
        }

        @Override
        public void removeEpic(int id) {
            if (epics.containsKey(id)) {
                Epic epic = epics.get(id);
                for (SubTask subTask : epic.getSubTasks()) {
                    subTasks.remove(subTask.getId());
                }
                epics.remove(id);
            } else {
                return;
            }
        }

        @Override
        public void removeSubTasks(int id) {
            if (subTasks.containsKey(id)) {
                SubTask subTask = subTasks.get(id);
                subTasks.remove(id);
                Epic epic = subTask.getEpic();
                epic.getSubTasks().remove(subTask);
                updateEpicStatus(epic);
            } else {
                return;
            }
        }

        @Override
        public void removeAllTasks() {
            tasks.clear();
        }

        @Override
        public void removeAllEpic() {
            epics.clear();
            subTasks.clear();
        }

        @Override
        public void removeAllSubTasks() {
            subTasks.clear();
            for (int epicID : epics.keySet()) {
                epics.get(epicID).setStatus(Status.NEW);
            }
        }

        @Override
        public void updateTask(Task task) {
            if (tasks.containsKey(task.getId())) {
                historyManager.add(task);
                Task saved = tasks.get(task.getId());
                saved.setName(task.getName());
                saved.setStatus(task.getStatus());
                saved.setDescription(task.getDescription());
                saved.setId(generateId());
            } else {
                return;
            }
        }

        @Override
        public void updateEpic(Epic epic) {
            if (epics.containsKey(epic.getId())) {
                Task saved = epics.get(epic.getId());
                saved.setName(epic.getName());
                saved.setDescription(epic.getDescription());
            } else {
                return;
            }
        }

        @Override
        public void updateSubTask(SubTask subTask) {
            if (subTasks.containsKey(subTask.getId())) {
                SubTask saved = subTasks.get(subTask.getId());
                saved.setName(subTask.getName());
                saved.setDescription(subTask.getDescription());
                saved.setStatus(subTask.getStatus());
                updateEpicStatus(saved.getEpic());
                historyManager.add(saved);
            } else {
                return;
            }
        }

        @Override
        public Task getTask(int id) {
            Task task = tasks.get(id);
            if (task == null) {
                return null;
            } else {
                historyManager.add(task);
                return task;
            }
        }

        @Override
        public Epic getEpic(int id) {
            Epic epic = epics.get(id);
            if (epic == null) {
                return null;
            } else {
                historyManager.add(epic);
                return epic;
            }

        }

        @Override
        public SubTask getSubTask(int id) {
            SubTask subTask = subTasks.get(id);
            if (subTask == null) {
                return null;
            } else {
                historyManager.add(subTask);
                return subTask;
            }
        }


        private void updateEpicStatus(Epic epic) {
            if (epic.getSubTasks().isEmpty()) {
                epic.setStatus(Status.NEW);
                return;
            }
            boolean allSubTasksDone = true;
            for (SubTask subTask : epic.getSubTasks()) {
                if (subTask.getStatus() != Status.DONE) {
                    allSubTasksDone = false;
                    break;
                }
            }
            if (allSubTasksDone) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }

        }

        @Override
        public List<Task> getHistory() {
            return historyManager.getAll();
        }
    }
