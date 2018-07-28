package leetcode;

import java.util.*;


public class LevelTraversal {

    private class SolutioninBFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
            List<List<Integer>> res = new LinkedList<List<Integer>>();
            if (root != null)
            {
                nodeQueue.add(root);
                while (!nodeQueue.isEmpty())
                {
                    int thislevelnumber = nodeQueue.size();
                    List<Integer> templist = new LinkedList<Integer>();
                    for (int i = 0; i < thislevelnumber; i++)
                    {
                        TreeNode cur = nodeQueue.poll();
                        if(cur.left != null)
                        {
                            nodeQueue.add(cur.left);
                        }
                        if(cur.right != null)
                        {
                            nodeQueue.add(cur.right);
                        }
                        templist.add(cur.val);
                    }
                    res.add(templist);
                }
            }
            return res;
        }
    }

    private class SolutionwithDFS
    {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<List<Integer>>();
            return dfshelper(res, root, 0);

        }
        public List<List<Integer>> dfshelper(List<List<Integer>> res, TreeNode node, int level)
        {
            if(node != null)
            {
                if(res.size() == level)
                    res.add(new LinkedList<Integer>());
                res.get(level).add(node.val);
                if(node.left != null)
                    dfshelper(res, node.left,level + 1);
                if(node.right != null)
                    dfshelper(res, node.right, level+1);
            }
            return res;
        }

    }

}
