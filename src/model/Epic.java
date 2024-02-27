    package model;

    import java.util.ArrayList;
    import java.util.List;

    public class Epic extends Task {

        private List<SubTask> subTasks = new ArrayList<>();

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
        public String toString() {
            return "Epic{" +
                    "id=" + super.getId() +
                    ", name='" + super.getName() + '\'' +
                    ", decription='" + super.getDescription() + '\'' +
                    ", status='" + super.getStatus() + '\'' +
                    '}';
        }
    }
