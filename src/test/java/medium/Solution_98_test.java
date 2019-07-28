package medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_98_test {

    @Test
    public void testTrees() {
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(stringToTreeNode("[2,1,3]"));
        treeNodes.add(stringToTreeNode("[5,1,4,null,null,3,6]"));
        treeNodes.add(stringToTreeNode("[1, 1]"));
        treeNodes.add(stringToTreeNode("[10,5,15,null,null,6,20]"));
        Solution_98 solution = new Solution_98();
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.val == 2) {
                assert solution.isValidBST(treeNode);
            }
            if (treeNode.val == 5) {
                assert !solution.isValidBST(treeNode);
            }
            if (treeNode.val == 1) {
                assert !solution.isValidBST(treeNode);
            }
            if (treeNode.val == 10) {
                assert !solution.isValidBST(treeNode);
            }
        }
    }

    private TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }



}
