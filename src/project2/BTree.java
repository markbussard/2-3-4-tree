/* Mark Bussard
 * Comp Sci 282 Project 2
 * Description: B-Tree class, inheriting from the Tree234 class. Contains
 * different split method to deal with order of tree > 4. Same insert, find
 * and display method from Tree234 class, but placed in here and overriden to deal
 * with the proper root.
 * 
 */
package project2;

public class BTree extends Tree234 {

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    private Node root = new Node();

    @Override
    public void split(Node thisNode) // split the node
    {                                // assumes node is full
        int itemIndex;
        int arraySize;
        Node parent;
        Node newRight = new Node(); // make new node
        if ((Node.getOrder() - 1) % 2 == 0) { // if order - 1 is even
            arraySize = Node.getOrder() / 2; // number of nodes to be moved
        } else { // else if order - 1 is odd, add one to account for size of array
            arraySize = ((Node.getOrder() - 1) / 2) + 1;
        }
        // array to hold nodes to be moved
        DataItem[] items = new DataItem[arraySize];
        // nodes to be moved, added to array
        for (int i = arraySize - 1; i >= 0; i--) {
            items[i] = thisNode.removeItem();
        } // end for

        if (thisNode == root) // if this is the root,
        {
            root = new Node(); // make new root
            parent = root; // root is our parent
            root.connectChild(0, thisNode); // connect to parent
        } else // this node not the root
        {
            parent = thisNode.getParent(); // get parent
        }
        // deal with parent      
        itemIndex = parent.insertItem(items[0]); // item B to parent, first value in items array

        int n = parent.getNumItems(); // total items?
        for (int j = n - 1; j > itemIndex; j--) // move parent’s connections
        {
            Node temp = parent.disconnectChild(j); // one child
            parent.connectChild(j + 1, temp); // to the right
        } // end for
        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);

        for (int i = 1; i < arraySize; i++) { // deal with adding items to new right
            newRight.insertItem(items[i]);
        }
        int childCount = 0; // counter for number of children to move
        int childIndex = 0; // index for child to connect
        int childToMove = Node.getOrder() - arraySize; // index to start at for children

        while (childCount++ < arraySize) { // deal with adding children
            // disconnect far right children and connect to new right
            newRight.connectChild(childIndex++, thisNode.disconnectChild(childToMove++));
        } // end while
    }

    @Override
    public void insert(long dValue) { // insert a DataItem
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);
        while (true) {
            if (curNode.isFull()) // if node full,
            {
                split(curNode); // split it
                curNode = curNode.getParent(); // back up
                // search once
                curNode = getNextChild(curNode, dValue);
            } // end if(node is full)
            else if (curNode.isLeaf()) // if node is leaf,
            {
                break; // go insert
            }// node is not full, not a leaf; so go to lower level
            else {
                curNode = getNextChild(curNode, dValue);
            }
        } // end while
        curNode.insertItem(tempItem); // insert new DataItem
    } // end insert()

    @Override
    // Method for inserting a data item with long value and string record
    public void insert(String record) {
        Node curNode = root;
        DataItem tempItem = new DataItem(record);
        long dValue = tempItem.getdData();
        while (true) {
            if (curNode.isFull()) // if node full,
            {
                split(curNode); // split it
                curNode = curNode.getParent(); // back up
                // search once
                curNode = getNextChild(curNode, dValue);
            } // end if(node is full)
            else if (curNode.isLeaf()) // if node is leaf,
            {
                break; // go insert
            }// node is not full, not a leaf; so go to lower level
            else {
                curNode = getNextChild(curNode, dValue);
            }
        } // end while
        curNode.insertItem(tempItem); // insert new DataItem
    } // end insert()

    @Override
    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.print("level = " + level + "child = " + childNumber + " ");
        thisNode.displayNode(); // display this node
        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }
        } // end for
    }

    @Override
    public int find(long key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber; // found it
            } else if (curNode.isLeaf()) {
                return -1; // can’t find it
            } else // search deeper
            {
                curNode = getNextChild(curNode, key);
            }
        } // end while
    }
}
