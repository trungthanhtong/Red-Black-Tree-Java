package com.company;

public class node {
    public int data;
    public node left;
    public node right;
    public node parent;
    public char color;

    public node() {
        left = right = parent = null;
        color = 'r';
    }
}
