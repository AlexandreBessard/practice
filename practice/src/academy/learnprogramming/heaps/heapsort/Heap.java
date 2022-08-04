package academy.learnprogramming.heaps.heapsort;

public class Heap {

    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }

    //O(log N) due to fix the heap
    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }

        heap[size] = value; //constant time

        fixHeapAbove(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        return heap[0];
    }

    //O(log N) log N is to fix the heap
    public int delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent = getParent(index);
        int deletedValue = heap[index];

        heap[index] = heap[size - 1];

        if (index == 0 || heap[index] < heap[parent]) {
            fixHeapBelow(index, size - 1);
        }
        else {
            fixHeapAbove(index);
        }

        size--;

        return deletedValue;

    }

    //Sort in increasing order, the root will become the smaller element of the heap
    public void sort() {
        int lastHeapIndex = size - 1; //Get last index
        for (int i = 0; i < lastHeapIndex; i++) { //heap reduce by one in each iteration
            int tmp = heap[0]; //swap root (largest value) with the last item
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = tmp;
            //Swap the root -> index 0
            // after Swapped, we exclude the old root placed at last index : (lastHeapIndex - i - 1)
            fixHeapBelow(0, lastHeapIndex - i - 1);
        }
    }

    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }

        heap[index] = newValue;
    }

    private void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwap;

        while (index <= lastHeapIndex) {
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);
            if (leftChild <= lastHeapIndex) {
                if (rightChild > lastHeapIndex) {
                    childToSwap = leftChild;
                }
                else {
                    childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }

                if (heap[index] < heap[childToSwap]) {
                    int tmp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = tmp;
                }
                else {
                    break;
                }

                index = childToSwap;
            }
            else {
                break;
            }
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            System.out.print(", ");
        }
        System.out.println();
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getChild(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }

}
