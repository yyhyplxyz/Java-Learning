import java.lang.reflect.Array;
import java.util.*;

public class Segment_Tree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public Segment_Tree(E[]arr, Merger<E> merger)
    {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        data = Arrays.copyOf(arr, arr.length);
        tree = (E[]) new Object[arr.length * 4];
        buildsegmenttree(0,0,arr.length-1);
    }
    private void buildsegmenttree(int index, int left, int right)
    {
        if(left == right)
        {
            tree[index] = data[left];
            return;
        }
        int leftindex = leftChild(index);
        int rightindex = rightChild(index);
        int mid = left + (right - left) / 2;
        buildsegmenttree(leftindex, left, mid);
        buildsegmenttree(rightindex, mid + 1, right);
        tree[index] = merger.merge(tree[leftindex], tree[rightindex]);
    }

    private int leftChild(int index)
    {
        return 2 * index + 1;
    }

    private int rightChild(int index)
    {
        return 2 * index + 2;
    }


    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if(l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if(queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public E query(int queryL, int queryR){

        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

}
