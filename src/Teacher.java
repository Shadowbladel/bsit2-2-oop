
// TODO 8 done
public class Teacher extends User {

    private String department;

    public Teacher(int id, String name, String email, String department) {
        super(id, name, email); // must be the first line
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String role() {
        return "TEACHER";
    }

    @Override
    public String permissions() {
        return "read, update grades";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + department;
    }
}