public class CourseDBElement implements Comparable<CourseDBElement> {

    private String courseId;
    private int crn;
    private int credits;
    private String room;
    private String instructor;

    // Constructor with parameters
    public CourseDBElement(String data, int i, int j, String room, String instructor2) {
        this.courseId = data;
        this.crn = i;
        this.credits = j;
        this.room = room;
        this.instructor = instructor2;
    }

    // Default constructor
    public CourseDBElement(int crn1, String string, String string1, String string2, int par) {
        this("", 0, 0, "", "");
    }

    public CourseDBElement(String data, int int1, int int2, String room2, String instructor2) {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getCRN() {
        return crn;
    }

    public void setCRN(int crn) {
        this.crn = crn;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Computes the hash code for the CourseDBElement based on its CRN.
     * 
     * @return hash code as an integer
     */
    @Override
    public int hashCode() {
        String crnString = String.valueOf(crn);
        int hash = 0;
        int prime = 31;
        for (int i = 0; i < crnString.length(); i++) {
            hash = prime * hash + crnString.charAt(i);
        }
        return hash;
    }

    /**
     * Returns a string representation of the CourseDBElement in a specified format.
     * 
     * @return formatted string representation
     */
    @Override
    public String toString() {
        return String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s",
                courseId, crn, credits, instructor, room);
    }

    /**
     * Compares this CourseDBElement with another based on CRN.
     * 
     * @param element - the CourseDBElement to compare with
     * @return comparison result based on CRN
     */
    @Override
    public int compareTo(CourseDBElement element) {
        return Integer.compare(this.crn, element.crn);
    }

    /**
     * Checks equality between this CourseDBElement and another object, based on CRN.
     * 
     * @param obj - the object to compare with
     * @return true if obj is a CourseDBElement with the same CRN, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseDBElement that = (CourseDBElement) obj;
        return crn == that.crn;
    }
}
