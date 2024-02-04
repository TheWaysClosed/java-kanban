    package model;

    public class SubTask extends Task {

        private Epic epic;

        public SubTask(String name, String description) {
            super(name, description);
        }

        public Epic getEpic() {
            return epic;
        }

        public void setEpic(Epic epic) {
            this.epic = epic;
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
