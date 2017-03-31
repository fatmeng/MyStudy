package com.chris.mystudy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 17/3/21.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class BinaryNode{
    private int value;
    private BinaryNode left;
    private BinaryNode right;
    private BinaryNode root;

    private static int[] src = new int[]{1,2,3,4,5,6,7,8,9};
    private static List<BinaryNode> nodeList = null;
    public BinaryNode(int value) {
        left = null;
        right = null;
        this.value = value;
    }

    public static void createBinaryTree(){
        nodeList = new LinkedList<>();
        for (int i = 0;i<src.length;i++){
            nodeList.add(new BinaryNode(src[i]));
        }
        for (int i =0;i<(src.length-1)/2;i++){
            nodeList.get(i).left = nodeList.get(i*2+1);
            nodeList.get(i).right = nodeList.get(i*2+2);
        }
        int lastParentIndex = (src.length-1)/2-1;
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex*2+1);
        if (src.length%2 == 0){
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex*2+2);
        }
    }
    public static BinaryNode getFisrt(){
        return nodeList.get(0);
    }

    /**
     * @param node
     */
    public static void preOderTraverse(BinaryNode node){
        if (node == null){
            return;
        }
        System.out.println("" + node.value + '\t');
        preOderTraverse(node.left);
        preOderTraverse(node.right);
    }


    public static void midOdderTraverse(BinaryNode node){
        if (node == null){
            return;
        }
        midOdderTraverse(node.left);
        System.out.println("" + node.value + '\t');
        midOdderTraverse(node.right);
    }

    public static void postOderTraverse(BinaryNode node){
        if (node == null){
            return;
        }
        postOderTraverse(node.left);
        postOderTraverse(node.right);
        System.out.println("" + node.value);
    }


}
