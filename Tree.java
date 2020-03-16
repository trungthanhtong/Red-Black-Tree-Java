package com.company;

import java.text.ParseException;

public class Tree {
    private node root;
    private node hold;

    public Tree() {
        root = hold = null;
    }

    public int insert(int num) {
        this.root = insert(this.root, null, num);
        fix(this.hold);
        return 0;
    }

    private node insert(node root, node previous, int num)  {
        if (root == null) {
            root = new node();
            root.data = num;
            root.parent = previous;
            this.hold = root; //catch the new node
            return root;
        }
        if (num >= root.data)
            root.right = insert(root.right, root, num);
        if (num < root.data)
            root.left = insert(root.left, root, num);
        return root;
    }

    public void display() {
        display(root);
    }

    private void display(node root) {
        if (root == null) return;

        display(root.left);
        System.out.println(root.data);
        display(root.right);
    }

    private void leftRotate(node root) {
        node child = root.right;
        node hold = child.left;
        if (root.parent != null) {
            if (root.parent.left == root)
                root.parent.left = child;
            else
                root.parent.right = child;
        }

        child.parent = root.parent;
        child.left = root;
        root.parent = child;
        root.right = hold;
        if (hold != null)
            hold.parent = root;
        if (root == this.root)
            this.root = child;
    }

    private void rightRotate(node root) {
        node child = root.left;
        node hold = child.right;
        if (root.parent != null) {
            if (root.parent.left == root)
                root.parent.left = child;
            else
                root.parent.right = child;
        }

        child.parent = root.parent;
        child.right = root;
        root.parent = child;
        root.left = hold;
        if (hold != null)
            hold.parent = root;
        if (root == this.root)
            this.root = child;
    }

    private void fix(node root) {
        if (root == this.root) {
            this.root.color = 'b';
            return;
        }
        if (root.parent.parent == null || root.parent.color == 'b') {
            return;
        }
        else {
            node parent = root.parent;
            node grand = parent.parent;
            node uncle;
            if (parent == grand.left)
                uncle = grand.right;
            else
                uncle = grand.left;
            if (uncle != null && uncle.color == 'r') { //uncle is red -> recolor
                grand.color = 'r';
                parent.color = 'b';
                uncle.color = 'b';
                fix(grand);
            }
            else {  //uncle is black -> recolor and rotate
                if (parent == grand.left) { //parent is left
                    if (root == parent.left) { //root is left => Line => recolor and rotate
                        grand.color = 'r';
                        parent.color = 'b';
                        rightRotate(grand);
                    }
                    else { //root is right => triangle
                        leftRotate(parent);
                        fix(parent);
                    }
                }
                else { //parent is right
                    if (root == parent.right) { //root is right => Line => recolor and rotate
                        grand.color = 'r';
                        parent.color = 'b';
                        leftRotate(grand);
                    }
                    else { //Triangle
                        rightRotate(parent);
                        fix(parent);
                    }
                }
            }
        }
        this.root.color = 'b';
    }


}
