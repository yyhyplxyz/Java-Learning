package leetcode;

import java.util.*;
public class Construct_String {
    public String tree2str(TreeNode t) {
        StringBuilder res = new StringBuilder();
        preorder(res, t);
        return res.toString();
    }
    public void preorder(StringBuilder r, TreeNode t)
    {
        if(t != null)
        {
            r.append(t.val);
            if(t.left != null)
            {
                r.append('(');
                preorder(r, t.left);
                r.append(')');
            }
            if(t.left == null && t.right != null)
            {
                r.append('(');
                r.append(')');
            }
            if(t.right != null)
            {
                r.append('(');
                preorder(r, t.right);
                r.append(')');
            }
        }
    }
}
