package com.listo.pms.tree;

import com.listo.pms.model.Product;

public class ProductBinaryTree {
    private BinaryTreeNode root;

    public ProductBinaryTree() {
        this.root = null;
    }

    public void addProduct(Product product) {
        root = addRecursive(root, product);
    }

    private BinaryTreeNode addRecursive(BinaryTreeNode node, Product product) {
        if (node == null) {
            return new BinaryTreeNode(product);
        }

        if (product.compareTo(node.getProduct()) < 0) {
            node.setLeft(addRecursive(node.getLeft(), product));
        } else if (product.compareTo(node.getProduct()) > 0) {
            node.setRight(addRecursive(node.getRight(), product));
        }

        return node;
    }

    public Product searchProduct(String name) {
        return searchRecursive(root, name);
    }

    private Product searchRecursive(BinaryTreeNode node, String name) {
        if (node == null) {
            return null;
        }

        if (name.equals(node.getProduct().getName())) {
            return node.getProduct();
        }

        if (name.compareTo(node.getProduct().getName()) < 0) {
            return searchRecursive(node.getLeft(), name);
        } else {
            return searchRecursive(node.getRight(), name);
        }
    }

    public void deleteProduct(String name) {
        root = deleteRecursive(root, name);
    }

    private BinaryTreeNode deleteRecursive(BinaryTreeNode node, String name) {
        if (node == null) {
            return null;
        }

        if (name.equals(node.getProduct().getName())) {

            // Case 1: Node with only one child or no child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // Case 2: Node with two children
            Product smallestProduct = findSmallestProduct(node.getRight());
            node.setProduct(smallestProduct);
            node.setRight(deleteRecursive(node.getRight(), smallestProduct.getName()));
        } else if (name.compareTo(node.getProduct().getName()) < 0) {
            node.setLeft(deleteRecursive(node.getLeft(), name));
        } else {
            node.setRight(deleteRecursive(node.getRight(), name));
        }

        return node;
    }

    private Product findSmallestProduct(BinaryTreeNode root) {
        return root.getLeft() == null ? root.getProduct() : findSmallestProduct(root.getLeft());
    }

    // In-order traversal (for testing purposes)
    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(BinaryTreeNode node) {
        if (node != null) {
            inOrderRecursive(node.getLeft());
            System.out.println(node.getProduct().getName());
            inOrderRecursive(node.getRight());
        }
    }
}
