import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CourseDBManager allows users to add courses to a database, retrieve courses,
 * and read courses from a file.
 */
public class CourseDBManager implements CourseDBManagerInterface {

    private CourseDBStructure cds;

    public CourseDBManager() {
        cds = new CourseDBStructure(10);
    }

    /**
     * Adds a course to the database.
     * 
     * @param id         - the ID of the course
     * @param crn        - the CRN number of the course
     * @param credits    - the number of credits of the course
     * @param roomNum    - the room number
     * @param instructor - the name of the instructor
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
        cds.add(element);
    }

    /**
     * Reads courses from a file and adds them to the database.
     * Each line in the file should contain course information in the format:
     * [CourseID CRN Credits RoomNum Instructor]
     * 
     * @param input - the file containing the course data
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            br.lines().forEach(line -> {
                String[] data = line.split(" ");
                if (data.length >= 5) {
                    String instructor = String.join(" ", List.of(data).subList(4, data.length));
                    CourseDBElement element = new CourseDBElement(data[0], Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]), data[3], instructor);
                    cds.add(element);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all courses in the database as a list of formatted strings.
     * 
     * @return an ArrayList of strings representing each course
     */
    @Override
    public ArrayList<String> showAll() {
        return cds.getAllElements()
                  .stream()
                  .map(CourseDBElement::toString)
                  .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retrieves a course by its CRN.
     * 
     * @param crn - the CRN number of the course to retrieve
     * @return the CourseDBElement if found, otherwise null
     */
    @Override
    public CourseDBElement get(int crn) {
        try {
            return cds.get(crn);
        } catch (IOException e) {
            System.err.println("Error retrieving course with CRN: " + crn);
        }
        return null;
    }
}
