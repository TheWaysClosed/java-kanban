    package TaskManager;

    import model.Epic;
    import model.Status;
    import model.SubTask;
    import model.Task;
    import java.util.HashMap;

    public class TaskManager {

        private HashMap<Integer, Task> tasks;
        private HashMap<Integer, Epic> epics;
        private HashMap<Integer, SubTask> subTasks;

        int seq = 1;

        private int generateId() {
            return seq++;
        }

        public TaskManager() {
            this.tasks = new HashMap<>();
            this.epics = new HashMap<>();
            this.subTasks = new HashMap<>();
        }

        public Task create(Task task) {
            task.setId(generateId());
            tasks.put(task.getId(), task);
            return task;
        }

        public Epic create(Epic epic) {
            epic.setId(generateId());
            epics.put(epic.getId(), epic);
            return epic;
        }

        public SubTask create(SubTask subTask, Epic epic) {
            subTask.setId(generateId());
            subTasks.put(subTask.getId(), subTask);
            epic.getSubTasks().add(subTask);
            subTask.setEpic(epic);
            updateEpicStatus(epic);
            return subTask;
        }

        public void printAllTasks() {
            for (Integer task : tasks.keySet()) {
                System.out.println(tasks.get(task));
            }
        }

        public void printAllEpics() {
            for (Integer epic : epics.keySet()) {
                System.out.println(epics.get(epic));
            }
        }

        public void printAllSubTasks() {
            for (Integer subTask : subTasks.keySet()) {
                System.out.println(subTasks.get(subTask));
            }
        }

        public void printAllSubTasksInEpic(Epic epic) {
            if (epic.getSubTasks().isEmpty()) {
                System.out.println("Список подзадач пуст");
            } else {
                for (SubTask sub : epic.getSubTasks()) {
                    System.out.println(sub);
                }
            }
        }

        public void printTask(int id) {
            if (tasks.containsKey(id)) {
                System.out.println(tasks.get(id));
            } else {
                return;
            }
        }

        public void printEpic(int id) {
            if (epics.containsKey(id)) {
                System.out.println(epics.get(id));
            } else {
                return;
            }
        }

        public void printSubTasks(int id) {
            if (subTasks.containsKey(id)) {
                System.out.println(subTasks.get(id));
            } else {
                return;
            }
        }

        public void removeTask(int id) {
            if (tasks.containsKey(id)) {
                tasks.remove(id);
            } else {
                return;
            }
        }

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

        public void removeAllTasks() {
            tasks.clear();
        }

        public void removeAllEpic() {
            epics.clear();
            subTasks.clear();
        }

        public void removeAllSubTasks() {
            subTasks.clear();
            for (int epicID : epics.keySet()) {
                epics.get(epicID).setStatus(Status.NEW);
            }
        }

        public void updateTask(Task task) {
            if (tasks.containsKey(task.getId())) {
                Task saved = tasks.get(task.getId());
                saved.setName(task.getName());
                saved.setStatus(task.getStatus());
                saved.setDescription(task.getDescription());
            } else {
                return;
            }
        }

        public void updateEpic(Epic epic) {
            if (epics.containsKey(epic.getId())) {
                Task saved = epics.get(epic.getId());
                saved.setName(epic.getName());
                saved.setDescription(epic.getDescription());
            } else {
                return;
            }
        }

        public void updateSubTask(SubTask subTask) {
            if (subTasks.containsKey(subTask.getId())) {
                SubTask saved = subTasks.get(subTask.getId());
                saved.setName(subTask.getName());
                saved.setDescription(subTask.getDescription());
                saved.setStatus(subTask.getStatus());
                updateEpicStatus(saved.getEpic());
            } else {
                return;
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
    }
