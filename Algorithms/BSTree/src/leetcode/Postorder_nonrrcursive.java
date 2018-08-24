package leetcode;
import java.util.*;

public class Postorder_nonrrcursive {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = root;
        while (root != null) {// go to left deeply
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            TreeNode goRight = top.right;
            if (goRight != null) {
                if (prev == goRight) {// duplicate visit
                    ans.add(top.val);
                    stack.pop();
                    prev = top;
                } else {
                    do {// go to left deeply
                        stack.push(goRight);
                        goRight = goRight.left;
                    } while (goRight != null);
                }
            } else {
                // do visit
                ans.add(top.val);
                stack.pop();
                prev = top;
            }
        }
        return ans;
    }
}
