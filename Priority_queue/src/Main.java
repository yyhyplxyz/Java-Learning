//动态选择优先级高的事务进行
//一般可以使用普通线性结构 入队O(1), 出队O(n)
// 也可以使用顺序线性结构  入队 O(n)  出队O(1)
//还可以使用堆，入队出队都是logn
// 二插堆是一个完全二叉树
//用我们实现的堆来解决 https://leetcode.com/problems/top-k-frequent-elements/description/该问题

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
class Solution
{
    private class Freq implements Comparable<Freq>{

        public int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            if(this.freq < another.freq)
                return 1;
            else if(this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        MaxHeap<Freq> maxHeap = new MaxHeap<>();
        for(int key: map.keySet()){
            if(maxHeap.size() < k)
                maxHeap.add(new Freq(key, map.get(key)));
            else if(map.get(key) > maxHeap.findMax().freq){
                maxHeap.Extractmax();
                maxHeap.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!maxHeap.isEmpty())
            res.add(maxHeap.Extractmax().e);
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
