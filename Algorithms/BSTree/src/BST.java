public class BST <E extends Comparable<E>> {
    public class Node
    {
        public E e;
        public Node left, right;

        public Node(E e)
        {
            left= right = null;
            this.e = e;
        }
    }
    public Node root;
    public int size;
    public BST()
    {
        root = null;
        size = 0;

    }

    public int getsize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void add(E e)
    {
       root = add(root, e);
    }

    public Node add(Node node, E e)
    {
        if(node == null)
        {
            size++;
            return new Node(e);
        }
        else if(e.compareTo(node.e) < 0)
        {
            node.left =  add(node.left, e);
        }
        else if(e.compareTo(node.e) > 0)
        {
            node.right = add(node.right, e);
        }
        return node;
    }

    public void preorder()
    {
        preorder(root);
    }


    private void preorder(Node node)
    {
        if(node == null)
            return;
        System.out.println(node.e);
        preorder(node.left);
        preorder(node.right);
    }


    public void inorder()
    {
        inorder(root);
    }

    private void inorder(Node node)
    {
        if(node == null)
            return;
        inorder(node.left);
        System.out.println(node.e);
        inorder(node.right);
    }

    public void postorder()
    {
        postorder(root);
    }

    private void postorder(Node node)
    {
        if(node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.e);
    }

    public boolean iscontain(E e)
    {
        return iscontain(root, e);
    }

    private boolean iscontain(Node node, E e)
    {
        if(e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return iscontain(node.left,e);
        else
            return iscontain(node.right, e);
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }
}
