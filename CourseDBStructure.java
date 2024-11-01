import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class CourseDBStructure implements CourseDBStructureInterface {

    protected LinkedList<CourseDBElement>[] hashTable;

    /**
     * Constructor for initializing the hash table with a given size.
     * 
     * @param size - the number of buckets in the hash table
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(int size) {
        hashTable = new LinkedList[size];
    }

    /**
     * Constructor that allows an additional parameter but functions similarly to the other constructor.
     * 
     * @param name - a string (unused in this code)
     * @param size - the number of buckets in the hash table
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(String name, int size) {
        hashTable = new LinkedList[size];
    }

    /**
     * Adds a CourseDBElement to the data structure. If a LinkedList does not exist at the calculated
     * hash index, a new LinkedList is created. If the element already exists in the list, it will not be added again.
     * 
     * @param element - the CourseDBElement to add
     */
    @Override
    public void add(CourseDBElement element) {
        int index = getHashIndex(element);
        
        // Initialize LinkedList if it's null at the calculated index
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        // Add element if it doesn't already exist in the LinkedList
        if (!hashTable[index].contains(element)) {
            hashTable[index].add(element);
        }
    }

    /**
     * Retrieves a CourseDBElement with a given CRN from the data structure.
     * If the element is not found, an IOException is thrown.
     * 
     * @param crn - the CRN number of the element to retrieve
     * @return the CourseDBElement with the specified CRN
     * @throws IOException if the element is not found
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int index = getHashIndex(new CourseDBElement(crn, "", "", "", 0));
        LinkedList<CourseDBElement> list = hashTable[index];

        // Search for the element with the specified CRN in the LinkedList
        if (list != null) {
            for (CourseDBElement element : list) {
                if (element.getCRN() == crn) {
                    return element;
                }
            }
        }

        throw new IOException("CourseDBElement with CRN " + crn + " not found.");
    }

    /**
     * @return the size of the hash table (number of buckets)
     */
    @Override
    public int getTableSize() {
        return hashTable.length;
    }

    /**
     * Computes the hash index for a CourseDBElement based on its hashCode.
     * 
     * @param element - the CourseDBElement to compute the index for
     * @return the computed hash index
     */
    private int getHashIndex(CourseDBElement element) {
        int hashIndex = Math.abs(element.hashCode() % hashTable.length);
        return hashIndex;
    }

    @Override
    public ArrayList<String> showAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showAll'");
    }
}
