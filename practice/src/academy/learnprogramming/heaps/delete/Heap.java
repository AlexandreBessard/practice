package academy.learnprogramming.heaps.delete;

public class Heap {

    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }

    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }

        heap[size] = value;

        fixHeapAbove(size);
        size++;
    }


    public int delete(int index) { // Get the index
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent = getParent(index);
        int deletedValue = heap[index];
        //Get the left most element in the heap
        heap[index] = heap[size - 1];
        //We look up or down the heap
        if (index == 0 || heap[index] < heap[parent]) { //Need to check below if root or parent is greater
            //Less than its parent, need to look at the heap below
            //size - 1 -> get the last Heap Index
            fixHeapBelow(index, size - 1);
        }
        else {
            //If it is greater than its parent, need to fix above
            fixHeapAbove(index);
        }

        size--;

        return deletedValue;

    }

    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }

        heap[index] = newValue;
    }

    // lastHeapIndex is useful for heap sort
    private void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwap;

        while (index <= lastHeapIndex) {
            //Fixing below
            int leftChild = getChild(index, true); //Get left child from this index
            int rightChild = getChild(index, false); //Get right child from this index
            //Check if This node has a left child
            if (leftChild <= lastHeapIndex) { //If passes, has a left child, else do nothing, we already know node does not have a right child for sure
                //Check if This node has a right child
                if (rightChild > lastHeapIndex) {
                    //Does not have a right child
                    childToSwap = leftChild;
                }
                else {
                    //Have a right child
                    //Get the bigger one between these two children
                    //to compare with the index
                    childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }

                if (heap[index] < heap[childToSwap]) { //value is index is less than child to swap -> do the swap
                    //Swap
                    int tmp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = tmp;
                }
                else {
                    break;
                }

                index = childToSwap;
            }
            else { //does not have any children
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

    //Get the child from that index for left or right to add 1 or 2
    public int getChild(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }

}
