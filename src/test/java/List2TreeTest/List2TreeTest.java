package List2TreeTest;


import List2Tree.List2Tree;
import List2Tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class List2TreeTest {

    @Test
    public void test_List2Tree() {

        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(TreeNode.builder().id(1).build());
        nodeList.add(TreeNode.builder().id(2).parentId(1).build());
        nodeList.add(TreeNode.builder().id(3).parentId(1).build());
        nodeList.add(TreeNode.builder().id(4).parentId(2).build());
        nodeList.add(TreeNode.builder().id(5).parentId(2).build());
        nodeList.add(TreeNode.builder().id(6).parentId(3).build());
        nodeList.add(TreeNode.builder().id(7).parentId(3).build());

        List2Tree list2Tree = new List2Tree();
        TreeNode rootTreeNode = list2Tree.getTreeNode(nodeList, 1);
        Assertions.assertEquals(rootTreeNode, nodeList.get(0));

    }

}
