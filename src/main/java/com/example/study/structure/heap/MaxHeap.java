package com.example.study.structure.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    //부모 노드의 인덱스 반환
    private int parent(int i) {
        return (i-1)/2;
    }

    //왼쪽 자식 노드의 인덱스 반환
    private int leftChild(int i) {
        return 2*i+1;
    }

    //오른쪽 자식 노드의 인덱스 반환
    private int rightChild(int i) {
        return 2*i+2;
    }

    //두 요소의 위치를 교환
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 힙이 가득 찼는지 확인
    public boolean isFull() {
        return size == capacity;
    }

    // 힙이 비어있는지 확인
    public boolean isEmpty() {
        return size == 0;
    }

    // 힙에 요소 삽입
    public void insert(int key) {
        if(isFull()) {
            System.out.println("힙이 가득 찼습니다.");
            return;
        }

        // 새 요소 추가
        int i = size;
        heap[i] = key;
        size++;

        // 힙 속성 유지(상향식 조정)
        while(i != 0 && heap[parent(i)] < heap[i]) {
            swap(i , parent(i));
            i = parent(i);
        }
    }

    public int extractMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int root = heap[0];
        heap[0] = heap[size-1];
        size--;

        maxHeapify(0);

        return root;
    }

    private void maxHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if(largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    // 힙의 최대값(루트) 반환 (제거하지 않음)
    public int getMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return heap[0];
    }

    public void buildMaxHeap(int[] arr) {
        if (arr.length > capacity) {
            System.out.println("배열이 너무 큽니다.");
            return;
        }

        // 배열 복사
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;

        // 마지막 비-리프 노드부터 시작해 힙 속성 복원
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }
}
