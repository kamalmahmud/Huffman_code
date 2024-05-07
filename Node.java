public class Node<T> {
    protected T value;
    protected Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public String toString(){
        return String.valueOf(value);
    }
}
