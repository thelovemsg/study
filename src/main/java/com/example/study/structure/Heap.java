package com.example.study.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Heap {
    List<Integer> heap;

    int[] test = {7,4,5,8,4};
    List<Integer> result;

    public Heap() {
        heap = new ArrayList<>();
        heap.add(0);
    }

    public void push(int val) {
        heap.add(val);
        int i = heap.size() - 1;

        //Percolate up
        while (i > 1 && heap.get(i) < heap.get(i/2)){
            int tmp = heap.get(i);
            heap.set(i, heap.get(i/2));
            heap.set(i/2, tmp);
            i = i/2;
        }
    }

    public int pop() {
        if(heap.size() == 1) {
            return -1;
        }

        if(heap.size() == 2) {
            return heap.remove(heap.size()-1);
        }

        int res = heap.get(1);

        heap.set(1, heap.remove(heap.size() -1));
        int i = 1;

        while(2*i < heap.size()) {
            if(i*2 + 1<heap.size() && heap.get(2*i+1) < heap.get(i*2) && heap.get(i) > heap.get(2*i+1)) {
                int tmp = heap.get(i);
                heap.set(i, heap.get(2*i));
                heap.set(2*i+1, tmp);
                i = 2*i+1;
            } else if(heap.get(i) > heap.get(2*i)) {
                int tmp = heap.get(i);
                heap.set(i, heap.get(i*i));
                heap.set(2*i, tmp);
                i = 2*i;
            } else {
                break;
            }
        }

        return res;
    }
}
