/* Mark Bussard
 * Comp Sci 282 Project 2
 * Description: This is the node class, handles setting the new order for the tree,
 * inserting data items, removing items, connecting children and disconnecting children.
 * 
 */
package project2;

public class Node {

    private static int ORDER = 4;
    private int numItems;
    private Node parent;
    private final Node childArray[] = new Node[ORDER];
    private final DataItem itemArray[] = new DataItem[ORDER - 1];

    public static void setOrder(int num) {
        Node.ORDER = num;
    }

    public static int getOrder() {
        return ORDER;
    }

    // connect child to this node
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
    }

    // disconnect child from this node, return it
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public Node getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return (childArray[0] == null);
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) // get DataItem at index
    {
        return itemArray[index];
    }

    public boolean isFull() {
        return (numItems == ORDER - 1);
    }

    public int findItem(long key) // return index of
    { // item (within node)
        for (int j = 0; j < ORDER - 1; j++) // if found,
        { // otherwise,
            if (itemArray[j] == null) // return -1
            {
                break;
            } else if (itemArray[j].dData == key) {
                return j;
            }
        }
        return -1;
    } // end findItem

    public int insertItem(DataItem newItem) {
        // assumes node is not full
        numItems++; // will add new item
        long newKey = newItem.dData; // key of new item
        for (int j = ORDER - 2; j >= 0; j--) // start on right,
        { // examine items
            if (itemArray[j] == null) // if item null,
            {
                // go left one cell
                
            } else // not null,
            { // get its key
                long itsKey = itemArray[j].dData;
                if (newKey < itsKey) // if it’s bigger
                {
                    itemArray[j + 1] = itemArray[j]; // shift it right
                } else {
                    itemArray[j + 1] = newItem; // insert new item
                    return j + 1; // return index to
                } // new item
            } // end else (not null)
        } // end for // shifted all items,
        itemArray[0] = newItem; // insert new item
        return 0;
    } // end insertItem()

    public DataItem removeItem() // remove largest item
    {
        // assumes node not empty
        DataItem temp = itemArray[numItems - 1]; // save item
        itemArray[numItems - 1] = null; // disconnect it
        numItems--; // one less item
        return temp; // return item
    }

    public void displayNode() // format “/24/56/74/”
    {
        for (int j = 0; j < numItems; j++) {
            itemArray[j].displayItem(); // “/56”
        }
        System.out.println("/"); // final “/”
    }
}
