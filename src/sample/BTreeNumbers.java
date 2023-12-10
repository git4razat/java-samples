package sample;

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.Queue;

class BTree<Key extends Comparable<Key>, Value> {
	
	

  public static int M = 4;

  private BTreeNode root;
  // height of BTree
  private int HT;
  // number of key-value pairs in BTree
  private int N;

  // linked list//w ww .  ja  v a2 s. co  m
  

  public BTree() {
    root = new BTreeNode(0);
  }

  public int size() {
    return N;
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int height() {
    return HT;
  }

  public Value get(Key k) {
    return search(root, k, HT);
  }

  private Value search(BTreeNode x, Key k, int ht) {
	  BTreeEntry[] children = x.children;

    if (ht == 0) {
      for (int j = 0; j < x.m; j++) {
        if (eq(k, children[j].key))
          return (Value) children[j].value;
      }
    } else {
      for (int j = 0; j < x.m; j++) {
        if (j + 1 == x.m || less(k, children[j + 1].key))
          return search(children[j].next, k, ht - 1);
      }
    }
    return null;
  }

  public void put(Key k, Value v) {
    BTreeNode u = insert(root, k, v, HT);
    N++;

    if (u == null)
      return;

    BTreeNode t = new BTreeNode(2);
    t.children[0] = new BTreeEntry(root.children[0].key, null, root);
    t.children[1] = new BTreeEntry(u.children[0].key, null, u);

    root = t;
    HT++;
  }

  private BTreeNode insert(BTreeNode h, Key k, Value v, int ht) {
    int j;
    BTreeEntry t = new BTreeEntry(k, v, null);

    if (ht == 0) {
      for (j = 0; j < h.m; j++) {
        if (less(k, h.children[j].key))
          break;
      }
    } else {
      for (j = 0; j < h.m; j++) {
        if ((j + 1 == h.m) || less(k, h.children[j + 1].key)) {
          BTreeNode u = insert(h.children[j++].next, k, v, ht - 1);

          if (u == null)
            return null;

          t.key = u.children[0].key;
          t.next = u;
          break;
        }
      }
    }

    for (int i = h.m; i > j; i--)
      h.children[i] = h.children[i - 1];

    h.children[j] = t;
    h.m++;
    if (h.m < M)
      return null;
    else
      return split(h);
  }

  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedList<Key>();
    return keys(root, HT, queue);
  }

  private Iterable<Key> keys(BTreeNode h, int ht, Queue<Key> queue) {
    BTreeEntry[] children = h.children;

    if (ht == 0) {
      for (int j = 0; j < h.m; j++)
        queue.add((Key) children[j].key);
    } else {
      for (int j = 0; j < h.m; j++)
        queue = (Queue<Key>) keys(children[j].next, ht - 1, queue);
    }

    return queue;
  }

  private BTreeNode split(BTreeNode h) {
	  BTreeNode t = new BTreeNode(M / 2);
    h.m = M / 2;
    for (int j = 0; j < M / 2; j++)
      t.children[j] = h.children[M / 2 + j];
    return t;
  }

  private boolean less(Comparable k1, Comparable k2) {
    return k1.compareTo(k2) < 0;
  }

  private boolean eq(Comparable k1, Comparable k2) {
    return k1.compareTo(k2) == 0;
  }

  public static void main(String[] args) {
    BTree<String, String> btree = new BTree<String, String>();
    btree.put("q", "a");
    btree.put("w", "b");
    btree.put("e", "c");
    btree.put("r", "d");
    btree.put("t", "e");
    /*btree.put("y", 1);
    btree.put("u", 1);
    btree.put("i", 1);
    btree.put("o", 1);
    btree.put("p", 1);
    btree.put("[", 1);
    btree.put("a", 1);
    btree.put("s", 1);*/

    for (String k : btree.keys())
      System.out.printf("%s (key) %s (Value) %n", k, btree.get(k));
  }
}

class BTreeNode {
	  int m; // number of children
	  BTreeEntry[] children = new BTreeEntry[BTree.M];

	  BTreeNode(int k) {
	    m = k;
	  }
	}
	class BTreeEntry {
	  Comparable key;
	  Object value;

	  BTreeNode next;

	  BTreeEntry(Comparable key, Object value, BTreeNode next) {
	    this.key = key;
	    this.value = value;
	    this.next = next;
	  }
	}
