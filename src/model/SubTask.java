    package model;

    public class SubTask extends Task {

        private Epic epic;

        Integer epicId;
        private final TaskType type = TaskType.SUBTASK;

        public SubTask(String name, String description) {
            super(name, description);
        }

        public Epic getEpic() {
            return epic;
        }

        public void setEpic(Epic epic) {
            this.epic = epic;
            this.epicId = epic.getId();
        }

        @Override
        public TaskType getType() {
            return type;
        }


        @Override
        public Integer getEpicId() {
            return epicId;
        }

        @Override
        public String toString() {
            return "SubTask{" +
                    "Epic=" + epic +
                    ", id=" + super.getId() +
                    ", name='" + super.getName() + '\'' +
                    ", decription='" + super.getDescription() + '\'' +
                    ", status='" + super.getStatus() + '\'' +
                    '}';
        }
    }
