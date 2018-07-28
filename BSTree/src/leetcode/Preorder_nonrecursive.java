package leetcode;

import java.util.*;
public class Preorder_nonrecursive {

    ArrayList<Integer> alist = new ArrayList<Integer>();
    public List<Integer> preorderTraversal(TreeNode root) {

        if(root == null){
            return alist;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode current = stack.pop();
            if(current !=null){
                alist.add(current.val);
                stack.push(current.right);
                stack.push(current.left);
            }
        }
        return alist;
    }
}
