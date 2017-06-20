import java.util.*;
/**
 * @author Julia
 */
public class MyHashMap<K, V> {
    private Node[] table;//main hash table
    private int size;//size of hash map

    MyHashMap() {
        table = new Node[Const.DEFAULT_SIZE];//set default size
    }

    private class Node<K, V> {
        private int hash;
        private Integer key;
        private Long value;
        private List<Node<K, V>> nodes;

        public Node(Integer key, Long value) {
            this.key = key;
            this.value = value;
            this.nodes = new ArrayList<Node<K, V>>();
            this.hash = Const.logicHash(key, table.length);
        }

        public Integer getKey() {
            return key;
        }

        public Long getValue() {
            return value;
        }

        public List<Node<K, V>> getNodes() {
            return nodes;
        }

        public String toString() {
            return key + "=" + value;
        }

        public Long setValue(Long newValue) {
            Long oldValue = value;
            value = newValue;
            return oldValue;//return previous value
        }

        @Override
        public int hashCode() {
            int result = hash;
            result = 31 * result + (key != null ? key.hashCode() : 0);
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + (nodes != null ? nodes.hashCode() : 0);
            return result;
        }
    }

    //return: previous value or null
    public Long put(Integer key, Long value) throws Exception {
        Node<K, V> newNode = new Node<K, V>(key, value);
        int index = newNode.hash;
        if (table[index] == null) {//if new pair(key-value) -> addNode to table
            if (addNode(index, newNode)) {
                return null;
            }
        }

        List<Node<K, V>> nodeList = table[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (isExistKeyAndNewValue(node, newNode)) {
                return node.setValue(value);//set new value
            }

            if (isCollision(node, newNode)) {
                nodeList.add(newNode);
                size++;
                return null;
            }
        }
        throw new Exception("error in put");
    }

    private boolean isExistKeyAndNewValue(final Node<K, V> nodeFromList, final Node<K, V> newNode) {
        return newNode != null && nodeFromList != null &&
                newNode.getKey().equals(nodeFromList.getKey()) &&
                !nodeFromList.getValue().equals(newNode.getValue());
    }

    private boolean isCollision(final Node<K, V> nodeFromList, final Node<K, V> newNode) {
        return newNode != null && nodeFromList != null
                && newNode.hashCode() == nodeFromList.hashCode()
                && !Objects.equals(newNode.getKey(), nodeFromList.getKey())
                && !Objects.equals(newNode.getValue(), nodeFromList.getValue());
    }

    //add new pair to table
    private boolean addNode(int index, Node<K, V> newNode) throws Exception {
        table[index] = new Node<Integer, Long>(null, null);
        table[index].getNodes().add(newNode);
        size++;
        return true;
    }

    public Long get(Integer key) {
        int index = Const.logicHash(key, table.length);
        if (table[index] != null) {
            List<Node<K, V>> nodeList = table[index].getNodes();
            for (Node<K, V> node : nodeList) {
                if ((key == null && node.getKey() == null) || key != null && key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        throw new NullPointerException("key = " + key + " was not found");
    }

    public int size() {
        return size;
    }

}
