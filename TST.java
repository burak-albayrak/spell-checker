import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

//------------------------------------------
// Summary: Ternary Search Tree (TST) class for storing and retrieving values associated with strings
//------------------------------------------
public class TST<Value> {
    private Node root;

    //------------------------------------------
    // Summary: Node class representing a character in the TST
    //------------------------------------------
    private class Node {
        private char c;
        private Node left, mid, right;
        private Value val;
    }

    //------------------------------------------
    // Summary: Inserts a key-value pair into the TST
    //------------------------------------------
    public void put(String key, Value val) {
        root = put(root, key.toLowerCase(), val, 0);
    }

    //------------------------------------------
    // Summary: Helper method for inserting a key-value pair into the TST
    //------------------------------------------
    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }
        return x;
    }

    //------------------------------------------
    // Summary: Retrieves all keys with the given prefix
    // Args: prefix - The prefix to search for
    // Returns: Iterable<String> - A list of keys with the given prefix
    //------------------------------------------
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<>();
        Node x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.add(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    //------------------------------------------
    // Summary: Helper method to collect all keys with the given prefix
    // Args: x - The current node
    //       prefix - The current prefix
    //       queue - The queue to store the keys
    //------------------------------------------
    private void collect(Node x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left, prefix, queue);
        prefix.append(x.c);
        if (x.val != null) queue.add(prefix.toString());
        collect(x.mid, prefix, queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    //------------------------------------------
    // Summary: Retrieves the node associated with the given key
    // Args: x - The current node
    //       key - The key to search for
    //       d - The current character index
    // Returns: Node - The node associated with the key
    //------------------------------------------
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    //------------------------------------------
    // Summary: Checks if the TST contains the given key
    // Args: key - The key to search for
    // Returns: boolean - True if the key is found, false otherwise
    //------------------------------------------
    public boolean contains(String key) {
        Node x = get(root, key.toLowerCase(), 0);
        return x != null && x.val != null;
    }
}
