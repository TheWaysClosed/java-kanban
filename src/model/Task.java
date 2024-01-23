    package model;

    public class Task {

        private int id;
        private String name;

        private Status status;
        private String description;

        public Task(String name, String description, Status status) {
            this.name = name;
            this.description = description;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", decription='" + description + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
