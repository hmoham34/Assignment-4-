import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
    private CourseDBManagerInterface manager;

    @Before
    public void setUp() {
        manager = new CourseDBManager();
    }

    @After
    public void tearDown() {
        manager = null;
    }

    /**
     * Tests the add method to ensure no exception is thrown and no duplicate elements are added.
     */
    @Test
    public void testAddToDB() {
        try {
            manager.add("CMSC207", 30509, 4, "SC453", "Edward E. Nigma");
            assertEquals("There should be 1 element after adding a unique course", 1, manager.showAll().size());

            // Attempt to add a duplicate course
            manager.add("CMSC207", 30509, 4, "SC453", "Edward E. Nigma");
            assertEquals("Duplicate course should not increase the number of elements", 1, manager.showAll().size());
        } catch (Exception e) {
            fail("Adding a course should not cause an exception");
        }
    }

    /**
     * Tests the readFile method by reading from a test file and ensuring the courses are added correctly.
     */
    @Test
    public void testReadFile() {
        try {
            File inputFile = new File("TestStudent.txt");
            PrintWriter writer = new PrintWriter(inputFile);
            writer.println("CMSC204 31504 4 SC445 Joey Balloney");
            writer.println("CMSC204 30503 4 SC450 Matt Damon");
            writer.close();

            // Verify empty state before reading file
            assertEquals("Initially, there should be no courses", 0, manager.showAll().size());

            // Read file and check added courses
            manager.readFile(inputFile);
            assertEquals("File should add 2 courses", 2, manager.showAll().size());
            
            // Clean up test file
            inputFile.delete();
        } catch (Exception e) {
            fail("Reading file should not cause an exception");
        }
    }

    /**
     * Tests the showAll method by adding multiple courses and verifying the order and content.
     */
    @Test
    public void testShowAll() {
        manager.add("CMSC204", 31504, 4, "SC445", "Joey Balloney");
        manager.add("CMSC204", 30503, 4, "SC450", "Matt Damon");
        manager.add("CMSC207", 30559, 4, "SC453", "Edward E. Nigma");

        ArrayList<String> list = manager.showAll();

        // Verify the correct elements are returned in the expected order
        assertEquals("\nCourse:CMSC204 CRN:30503 Credits:4 Instructor:Matt Damon Room:SC450", list.get(0));
        assertEquals("\nCourse:CMSC207 CRN:30559 Credits:4 Instructor:Edward E. Nigma Room:SC453", list.get(1));
        assertEquals("\nCourse:CMSC204 CRN:31504 Credits:4 Instructor:Joey Balloney Room:SC445", list.get(2));
    }

    /**
     * Tests the get method to retrieve CourseDBElements by CRN and verifies their correctness.
     */
    @Test
    public void testGet() {
        manager.add("CMSC204", 31504, 4, "SC445", "Joey Balloney");
        manager.add("CMSC207", 30559, 4, "SC453", "Edward E. Nigma");

        // Retrieve elements by CRN and check their string representations
        assertEquals("\nCourse:CMSC207 CRN:30559 Credits:4 Instructor:Edward E. Nigma Room:SC453", manager.get(30559).toString());
        assertEquals("\nCourse:CMSC204 CRN:31504 Credits:4 Instructor:Joey Balloney Room:SC445", manager.get(31504).toString());
    }
}
