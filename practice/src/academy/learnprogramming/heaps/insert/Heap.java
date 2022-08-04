package academy.learnprogramming.heaps.insert;

public class Heap {

    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }

    //Always put the new element at the end of the index
    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }

        heap[size] = value;

        fixHeapAbove(size);
        size++;
    }

    //Look above the heap, to compare with his parent to check if value is greater or not
    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParent(index)]) { //Compare with his parent
            //Swap the value
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        //Set the new value
        heap[index] = newValue;
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

}
