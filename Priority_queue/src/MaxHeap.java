public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    public MaxHeap(){
        data = new Array<E>();
    }

    public int size(){
        return data.getSize();
    }


    public boolean isEmpty(){
        return data.isEmpty();
    }


    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }


    private int rightChild(int index){
        return index * 2 + 2;
    }

    private void siftup(int index)
    {
        while(index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0)
        {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public void add(E e)
    {
        data.addLast(e);
        siftup(data.getSize() - 1);
    }


    private void siftdown(int index)
    {
        while(leftChild(index) < size())
        {
            int j = leftChild(index);
            if (j + 1 < size())
            {
                j = data.get(j).compareTo(data.get(j+1)) >=0  ? j: j+ 1;
            }
            if(data.get(j).compareTo(data.get(index)) < 0)
                break;
            data.swap(j,index);
            index = j;
        }
    }

    public void remove(int index)
    {
        if(index < 0 || index >= data.getSize())
            throw new IllegalArgumentException("the index is invalid");
        if(index == data.getSize() - 1)
        {
            data.removeLast();
        }
        else {
            data.swap(index, data.getSize() - 1);
            data.removeLast();
            siftdown(index);
        }

    }

    public E findMax()
    {
        if(data.getSize() == 0)
            throw new IllegalArgumentException("The Heap is empty");
        return data.get(0);
    }

    public E Extractmax()
    {
        E res = findMax();

        data.swap(0,data.getSize() - 1);
        data.removeLast();
        siftdown(0);
        return res;
    }

}
