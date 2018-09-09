package TaskManager;


public class TaskManager {

    Task[] heap;
    int[] availableData; // the reference array
    int size;			 // the current number of tasks awaiting completion

    /**
     * Creates an empty data structure with the given capacity.
     * The capacity dictates how many different tasks may be put into the data structure.
     * Moreover, the capacity gives an upper bound to the serial number of tasks to be put in the data structure.
//     *
     */
    public TaskManager(int capacity){
        this.heap = new Task[capacity + 1];     //in order to start the index count from 1. 0 cell is empty.
        this.availableData = new int[capacity + 1];
        for (int i = 0; i < this.availableData.length; i++) {   //initialize all the values to -1.
            this.availableData[i] = -1;
        }
        this.size = 0;
    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
		return this.size;
    }

    /**
     * Checks if the current node is larger than his parent.
     * @param index
     * @return true if there's a need to percolate up, false otherwise.
     */
    public boolean shouldPercUp(int index) {
        if (index == 1) return false;                   //if we percolated all the way up to the root
        if (heap[index].compareTo(heap[index / 2]) > 0) {   //integers division, so both children 2i and 2i+1
                                                            // will compare with their parents (i)
            return true;
        } else return false;
    }

    /**
     * Checks to see if the current node is smaller than one of his children.
     * @param index
     * @return true if there's a need to percolate down, false otherwise.
     */
    public boolean shouldPercDown(int index) {
        if (index > (this.size / 2)) return false;                  //if we percolated all the way down to the leaves
        if ((heap[index].compareTo(heap[index * 2]) < 0) ||         //if parent is smaller than one of his children
            (heap[index].compareTo(heap[(index * 2) + 1]) < 0)) {
            return true;
        } else return false;
    }

    /**
     * Percolate up. Replaces the cell with it's parent cell.
     * @param index
     * @return The new index (the index which belonged to the parent).
     */
    public int percUp(int index) {
        Task temp = heap[index];
        int tempIndex = availableData[heap[index / 2].serial];

        availableData[heap[index / 2].serial] = availableData[heap[index].serial];  //updating the reference array
        availableData[heap[index].serial] = tempIndex;
        heap[index] = heap[index / 2];  //updating the heap
        heap[index / 2] = temp;
        return index / 2;
    }

    /**
     * Percolate down. Replaces the parent with his maximal child.
     * @param index
     * @return The new index (the index which belonged to the maximal child).
     */
    public int percDown(int index) {
        //check to see if there are two children to avoid pointing to null
        if (heap[(index * 2) + 1] != null) {
            if (heap[index * 2].compareTo(heap[(index * 2) + 1]) > 0) {
                Task temp = heap[index * 2];
                int tempIndex = availableData[heap[index * 2].serial];
                //updating the reference array
                availableData[heap[index * 2].serial] = availableData[heap[index].serial];
                availableData[heap[index].serial] = tempIndex;
                //updating the heap
                heap[index * 2] = heap[index];
                heap[index] = temp;
                return (index * 2);
            }
            else {
                Task temp = heap[(index * 2) + 1];
                int tempIndex = availableData[heap[(index * 2) + 1].serial];
                //updating the reference array
                availableData[heap[(index * 2) + 1].serial] = availableData[heap[index].serial];
                availableData[heap[index].serial] = tempIndex;
                //updating the heap
                heap[(index * 2) + 1] = heap[index];
//                heap[index] = temp;
                return ((index * 2) + 1);
            }
        }
        //if there are only left child, since we know we should percolate down - swap the nodes
        else {
            Task temp = heap[index * 2];
            int tempIndex = availableData[heap[index * 2].serial];
            //updating the reference array
            availableData[heap[index * 2].serial] = availableData[heap[index].serial];
            availableData[heap[index].serial] = tempIndex;
            //updating the heap
            heap[index * 2] = heap[index];
            heap[index] = temp;
            return (index * 2);
        }
    }

    /**
     * Fixing the order of the nodes in the heap, if necessary.
     * @param index
     */
    public void reorderHeap(int index) {
        if (shouldPercUp(index)) {
            while (shouldPercUp(index)) {
                index = percUp(index);
            }
        }
        else if (shouldPercDown(index)) {
            while (shouldPercDown(index)) {
                index = percDown(index);
            }
        }
        return;
    }

    /**
     * Inserts a given Task into the heap.
     *
     * @param t - the Task to be inserted.
     */
    public void insert(Task t){

        if (this.size == heap.length - 1) {
            System.out.println("Overflow!");
            return;
        }

        this.size++;
        heap[size] = t;

        int index = size;
        this.availableData[t.serial] = index;

        reorderHeap(index);

		return;
    }

    /**
     * Returns the Task with the highest priority number in the heap.
     * Recall that you are not allowed to traverse all elements of the heap array.
     *
     * @return the Task with the highest serial number in the heap.
     */
    public Task findMax(){
		return heap[1];
    }

    /**
     * Removes and returns the Task with the highest priority from the heap.
	 * You may not use any loops (or recursion) in this function.
     *
     * @return the Task with the highest priority in the heap.
     */

    public Task extractMax(){
		Task max = findMax();

        availableData[findMax().serial] = -1;
        availableData[heap[size].serial] = 1;
        heap[1] = heap[size];
        this.size--;

        //fix the order in the heap, if necessary
 		int index = 1;
        reorderHeap(index);

		return max;
    }

    /**
     * Updates the priority of a given task.
	 * Does nothing if the task is not already in the heap.
     * Recall that you are not allowed to traverse all elements of the heap array.
     * Think about what can go wrong in the heap as you change the priority of a given task. How will you fix it?
	 *
     * @param t - the given task
     * @param newPriority - the new priority of the given task.
     */
    public void updatePriority(Task t, int newPriority){
		if (availableData[t.serial] == -1) {
            System.out.println("here");
		    return;
        }
        t.priority = newPriority;
		//fix the order in the heap, if necessary
        int index = availableData[t.serial];
        reorderHeap(index);
        return;
    }

    /*
     * Test code; output should be:
     * task: abbreviate notes, priority: 10
	 * task: download new version, priority: 20
	 * task: bring food, priority: 11
	 * task: abbreviate notes, priority: 10
     * task: clear histories, priority: 3
     * task: download new version, priority: 0
     */
    public static void main (String[] args){
    	TaskManager demo = new TaskManager(10);
    	Task a = new Task(1, 10, "abbreviate notes");
    	Task b = new Task(2, 2, "bring food");
    	Task c = new Task(3, 3, "clear histories");
    	Task d = new Task(4, 20, "download new version");

        demo.insert(a);
        System.out.println(demo.findMax());

        demo.insert(b);
        demo.insert(c);
        demo.insert(d);

        System.out.println(demo.findMax());
        demo.updatePriority(b, 11);
        demo.updatePriority(d, 0);
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
        System.out.println(demo.extractMax());
    }
}
