package manager;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static class Node {

        Node prev;
        Task item;
        Node next;

        public Node(Node prev, Task item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    HashMap<Integer, Node> history = new HashMap<>();

    Node first;
    Node last;

    @Override
    public void add(Task task) {
        Node node = history.get(task.getId());
        if (history.containsKey(task.getId())) {
            removeNode(node);
        }
        linkLast(task);
    }

    @Override
    public List<Task> getAllTasks() {
        ArrayList<Task> list = new ArrayList<>();
        Node current = first;
        while (current != null) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    @Override
    public void remove(int id) {
        Node node = history.get(id);
        if (node != null) {
            removeNode(node);
            history.remove(id);
        }
    }

    public void linkLast(Task task) {
        Node l = last;
        Node node = new Node(l, task, null);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        history.put(task.getId(), node);
    }

    public void removeNode(Node node) {
        if (node.prev == null) {
            first = node.next;
        } else if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }
}
