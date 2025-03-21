public class Node<K,V> {
    Node<K,V> next;
    Node<K,V> prev;
    K key;
    V val;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
