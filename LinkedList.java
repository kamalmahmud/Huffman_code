import java.io.*;

public class LinkedList<T extends Comparable> {
    private Node<T> head;
    private int size;

    public Node<T> createNode(T val){
        return new Node<T>(val);
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void insertToFront(T val){
        Node<T> newNode=createNode(val);
        newNode.next=head;
        head=newNode;
        size++;
    }
    public void insertToEnd(T val){
        Node<T> newNode=createNode(val);
        if(head==null){
            head=newNode;
            size++;
            return;
        }
        Node<T> iterator=head;
        while(iterator.next!=null)
            iterator=iterator.next;
        iterator.next=newNode;
        size++;

    }

    public void sortedInsert(T val){
        Node<T> newNode = createNode(val);
        if (head == null) {
            head = newNode;
            size++;
        }

        else if (val.compareTo(head.value) <= 0) {
            newNode.next = head;
            head = newNode;
            size++;
        } else {
            Node<T> iterator = head;
            while (iterator.next != null && iterator.next.value.compareTo(val) == -1) {
                iterator = iterator.next;
            }
            newNode.next = iterator.next;
            iterator.next = newNode;
            size++;
        }
    }

    public void swapFirstLast(){
        if (head==null || head.next==null)
            return;
        Node<T> iterator=head, previous=head;
        while(iterator.next!=null){
            previous=iterator;
            iterator=iterator.next;
        }
        if(head.next==iterator){
            iterator.next=head;
        }else{
            iterator.next=head.next;
            previous.next=head;
        }
        head.next=null;
        head=iterator;

    }
    public T findMin(){
        if (head==null)
            return null;
        T min=head.value;
        Node<T> iterator=head.next;
        while (iterator!=null){
            if(min.compareTo(iterator.value)==1)
                min=iterator.value;
            iterator=iterator.next;
        }
        return min;
    }

    public Node<T> findMinNode(Node<T> start){
        if (start==null)
            return null;
        Node<T> min = start;
        Node<T> it = start;
        while (it != null){
            if (min.value.compareTo(it.value)==1){
                min = it;
            }
            it = it.next;
        }
        return min;
    }

    public void selectionSort(){
        if (head == null)return;
        Node<T> iterator = head;

        while (iterator.next != null){
            Node<T>min = findMinNode(iterator);
            T value = iterator.value;
            iterator.value = min.value;
            min.value =value;
            iterator = iterator.next;
        }
    }

    public void deleteTheFirst(){
        if(head!=null){
            head=head.next;
            size--;
        }

    }
    public void delete(T val){
        if(head==null)
            return;
        if(head.value.compareTo(val)==0)
            head=head.next;
        else{
            Node<T> previous=head, iterator=head;
            while(iterator!=null && iterator.value.compareTo(val)!=0)   {
                previous=iterator;
                iterator=iterator.next;
            }
            if(iterator!=null)
                previous.next=iterator.next;

        }
    }



    public void deleteAll(T val){
        if(head==null) return;
        // remove the head until it is null or the head value does not equal val
        while (head != null && head.value.compareTo(val)==0) head=head.next;

        Node<T> iterator = head;
        while (iterator != null  ) {

            if ( iterator.next != null && iterator.next.value.compareTo(val) == 0) {
                iterator.next = iterator.next.next;
            }

            else {
                iterator = iterator.next;
            }
        }
    }

    public void recursiveAddToEnd(T val) {
        head = recursiveAddToEnd(head, val);
    }

    public Node<T> recursiveAddToEnd(Node<T> tempHead, T val) {
        if (tempHead == null)
            return createNode(val);
        else {
            tempHead.next = recursiveAddToEnd(tempHead.next, val);
        }
        return tempHead;
    }

    public void swapFirstAndLastNodes(){
        if (head==null || head.next==null)
            return;
        Node<T> iterator=head, previous=head;
        while(iterator.next!=null){
            previous=iterator;
            iterator=iterator.next;
        }
        if(head.next==iterator){
            iterator.next=head;
        }else{
            iterator.next=head.next;
            previous.next=head;
        }
        head.next=null;
        head=iterator;
    }

    public Node<T> getHead(){
        return head;
    }
    public boolean recursiveSearch(Node<T> curr, T val){
        if (curr == null) return false;
        if (curr.value.compareTo(val) == 0) return true;
        else return recursiveSearch(curr.next,val);
    }


    // create a linked list from file using buffer
    public void createListFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    T parsedValue = (T) Integer.valueOf(value);// assuming T is Integer for simplicity
                    if (!recursiveSearch(getHead(),parsedValue))
                        insertToEnd(parsedValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this methode will count the number of node access and return it for the single search
    public int search(T val){
        int memoryAccess = 0;
        Node<T> it = head;
        memoryAccess++;
        while (it != null ){
            memoryAccess++;
            if (it.value.compareTo(val) == 0 ){
                break;
            }
            it = it.next;
            memoryAccess++;


        }
        return memoryAccess;
    }

    // this methode will use search methode to calculate all search access and return array with two values index 0
    // return the number of access search and the 1 index will return the average access memory

    public int[] searchMemoryAccess(String path) {
        int numAccess = 0;
        int numSearch = 0;
        int[] res = new int[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            if (line != null) {
                String[] strNums;
                strNums = line.split(",");
                numSearch = strNums.length;

                for (String value : strNums) {
                    T searchValue = (T) Integer.valueOf(value);
                    numAccess += search(searchValue);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res[0] = numAccess;
        res[1] = numAccess/numSearch;

        return res;
    }

    public int getSize() {
        return size;
    }
// this methode will swap the given node to the first
//    public void SwapToFirst(Node<T> previous, Node<T> iterator){
//        if (iterator == null || previous == iterator){
//            return;
//        }
//
//        if (previous == head && previous.next == iterator && iterator.next == null){
//            swapFirstLast();
//        }
//        else {
//            previous.next = iterator.next;
//            iterator.next = head;
//            head = iterator;
//        }
//    }

    // this methode will count the number of node access and use SwapToFirst method to swap the given node to the first
    // return it for the single search
    public int searchSwap(T val){
        int memoryAccess = 0;
        Node<T> previous = head;

        Node<T> iterator = head;
        memoryAccess+=2;

        while (iterator != null ) {
            memoryAccess++;
            if (iterator.value.compareTo(val) == 0){
                if (iterator == null || previous == iterator){
                    if (iterator == null)memoryAccess++;
                    else {
                        memoryAccess+=2;
                    }
                    break;
                }

                if (previous == head && previous.next == iterator && iterator.next == null){
                    swapFirstLast();
                    memoryAccess+=5;
                }
                else {
                    previous.next = iterator.next;

                    iterator.next = head;

                    head = iterator;
                    memoryAccess+=5;
                }
                break;
            }
            previous = iterator;

            iterator = iterator.next;
            memoryAccess+=2;
        }

        return memoryAccess;
    }


    // this methode will use searchSwap methode to calculate all search access and return array with two values index 0
    // return the number of access search and the 1 index will return the average
    public int[] searchAndMove(String path){
        int numAccess = 0;
        int numSearch = 0;
        int[] res = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            if (line != null) {
                String[] strNums;
                strNums = line.split(",");
                numSearch = strNums.length;

                for (String value : strNums) {
                    T searchValue = (T) Integer.valueOf(value);
                    numAccess += this.searchSwap(searchValue);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        res[0] = numAccess;
        res[1] = numAccess/numSearch;
        return res;
    }

    public void display(){
        Node<T> iterator=head;
        while(iterator!=null){
            System.out.print(iterator+" ");
            iterator=iterator.next;
        }
        System.out.println();

    }

}

//    public int searchSwap(T val){
//        int n = 0;
//        Node<T> it = head;
//        Node<T> prev = head;
//        if (head == null){
//            return 0;
//        }
//        if (head.next==null)
//            return n;
//        while (it != null && it.value.compareTo(val) != 0 ){
//            prev = it;
//            it = it.next;
//            n++;
//        }
//        SwapToFirst(prev,it);
//
//        return n;
//    }
//
//
