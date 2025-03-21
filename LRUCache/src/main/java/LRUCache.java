import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final int capacity;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final Map<K,Node<K,V>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head=new Node<K, V>(null,null);
        tail=new Node<K, V>(null,null);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }
    public synchronized V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        Node<K,V> node=map.get(key);
        remove(node);
        add(node);
        return node.val;
    }
    public synchronized void put(K key,V value){
        if(map.get(key)!=null){
            remove(map.get(key));
        }
        if(map.size()==capacity){
            remove(tail.prev);
        }
        Node<K,V> node=new Node<>(key,value);
        add(node);
    }


        private void remove(Node node){
        if (node == null || node == head || node == tail) {
            return;
        }
        map.remove(node.key);
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    private void add(Node<K,V> node){
        map.put(node.key,node);
        Node headNext=head.next;
        head.next=node;
        node.next=headNext;
        node.prev=head;
        headNext.prev=node;

    }
}
