// Student.java
// A CHILD of User with one extra field of its own.

// TODO 9 (done):
// (a) private field "course"
// (b) constructor(id, name, email, course) calls super(...) first,
//     then sets this.course
// (c) getCourse() getter
// (d) role() -> "STUDENT"
// (e) permissions() -> "read only"
// (f) toCsv() overridden to reuse the parent's 4 columns and append
//     the course as a 5th column.
public class Student extends User {

    private String course;

    public Student(int id, String name, String email, String course) {
        super(id, name, email); // must be the first line
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String role() {
        return "STUDENT";
    }

    @Override
    public String permissions() {
        return "read only";
    }

    @Override
    public String toCsv() {
        // Reuses User's toCsv() (id,name,email,role) and tacks on course.
        return super.toCsv() + "," + course;
    }
}