package com.listo.pms.tree;

import com.listo.pms.model.Product;

public class BinaryTreeNode {
    private Product product;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Product product) {
        this.product = product;
        this.left = null;
        this.right = null;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
