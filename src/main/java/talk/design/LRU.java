package src.main.java.design;

/**
 * Created by Liu On 2019/6/24
 * Description: 简单的单链表实现LRU
 * email: mingming.liu@quvideo.com
 */
public class LRU<T> {
    int size;
    int capacity;
    Node<T> mHead = new Node<>("",null, null);

    LRU(int capacity){
        if (capacity <= 0) {
            throw new IllegalArgumentException(" capacity must greater than zero");
        }
        this.capacity = capacity;
    }

    public void add(String key, T t){
        addHead(new Node(key, t, null));
    }

    public T get(String key){
        Node node = findByKey(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        addHead(node);
        return (T) node.data;
    }

    private Node removeNode(Node node){
        Node cusor = mHead;
        Node next = cusor.next;
        while (next != null){
            if (next ==  node) {
                cusor.next = next.next;
                next.next = null;
                return next;
            }
            cusor = next;
            next = next.next;
        }
        return null;
    }

    private void addHead(Node node){
        Node head = mHead.next;
        mHead.next = node;
        node.next = head;
    }

    public Node findByKey(String key){
        Node cusor = mHead;
        while (cusor != null){
            if (cusor.key.equals(key)) {
                return cusor;
            }
            cusor = cusor.next;
        }
        return null;
    }

    private static class Node<T>{
        T data;
        String key;
        Node<T> next;

        Node(String key,T data, Node next){
            this.key = key;
            this.data = data;
            this.next = next;
        }

        public Node next(){
            return this.next;
        }
    }

}
