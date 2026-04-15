# 🌳 Binary Tree Cheat Sheet

## 🔹 Definition
A Binary Tree is a tree where each node has **at most 2 children**:
- Left child
- Right child

---

## 🔹 Key Properties

| Property | Description |
|----------|------------|
| Root     | Top node of the tree |
| Leaf     | Node with no children |
| Height   | Max depth of tree |
| Depth    | Distance from root |
| Subtree  | Tree formed by any node |

---

## 🔹 Types of Binary Trees

| Type                | Description |
|---------------------|------------|
| Full                | 0 or 2 children |
| Perfect             | All levels filled |
| Complete            | Last level left-aligned |
| Skewed              | Like a linked list |
| Balanced            | Height difference ≤ 1 |

---

## 🔹 Traversals (DFS)

| Type      | Order               |
|----------|---------------------|
| Preorder  | Root → Left → Right |
| Inorder   | Left → Root → Right |
| Postorder | Left → Right → Root |

---

## 🔹 BFS (Level Order)

| Type        | Order |
|-------------|------|
| Level Order | Level by level |

---

## 🔹 Code Patterns

### ✅ Preorder
```java
void preorder(Node root) {
    if (root == null) return;
    System.out.print(root.val + " ");
    preorder(root.left);
    preorder(root.right);
}
```

### ✅ Inorder
```java
void inorder(Node root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.val + " ");
    inorder(root.right);
}
```

### ✅ Postorder
```java
void postorder(Node root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.val + " ");
}
```

### ✅ BFS
```java
void bfs(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        Node curr = queue.poll();
        System.out.print(curr.val + " ");

        if (curr.left != null) queue.offer(curr.left);
        if (curr.right != null) queue.offer(curr.right);
    }
}
```

---

## 🔹 Complexity

| Operation | Time | Space |
|----------|------|-------|
| DFS      | O(n) | O(h)  |
| BFS      | O(n) | O(n)  |

---

## 🔹 Height

```java
int height(Node root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

---

## 🔹 Common Problems
- Maximum depth of binary tree
- Diameter of binary tree
- Balanced binary tree
- Lowest Common Ancestor (LCA)
- Path Sum
- Level Order Traversal

---

## 🔥 Quick Summary

- DFS → Stack / Recursion  
- BFS → Queue  
- Height → Recursion  
- Traversal → DFS/BFS  
