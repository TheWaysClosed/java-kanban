    package model;

    import java.util.List;

    public class Epic extends Task {

        private List<SubTask> subTasks;
        private final TaskType type = TaskType.EPIC;

        public Epic(String name, String description,  List<SubTask> subTasks) {
            super(name, description);
            this.subTasks = subTasks;
        }



        public List<SubTask> getSubTasks() {
            return subTasks;
        }

        public void setSubTasks(List<SubTask> subTasks) {
            this.subTasks = subTasks;
        }

        @Override
        public TaskType getType() {
            return type;
        }



        @Override
        public String toString() {
            return "Epic{" +
                    "id=" + super.getId() +
                    ", name='" + super.getName() + '\'' +
                    ", decription='" + super.getDescription() + '\'' +
                    ", status='" + super.getStatus() + '\'' +
                    '}';
        }
    }
