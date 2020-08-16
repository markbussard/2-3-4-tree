/* Mark Bussard
 * Comp Sci 282 Project 2
 * Description: DataItem class, contains the actual information being held,
 * dData (the user ID), and the whole record (contains user ID and more information)
 * 
 */
package project2;

class DataItem {

    public long dData; // user id
    public String record; // whole record

    public DataItem(long dd) { // constructor
        dData = dd;
    }

    public DataItem(String record) { // constructor for reading text file
        this.record = record; // setting record
        String[] temp = record.split(","); // splitting record to get user id separate
        this.dData = Long.parseLong(temp[0]); // setting user id to dData              
    }

    public void displayItem() // display item, format “/27”
    {
        System.out.print("/" + dData);
    }
    
    public String getRecord(){
        return this.record;
    }
    
    public long getdData(){
        return this.dData;
    }
}