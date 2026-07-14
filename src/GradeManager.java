import java.util.ArrayList;

public class GradeManager {

    private ArrayList<Student> roster;
    private static final double[] cutoffs = {90.0, 80.0, 70.0, 60.0};
    private static final char[] letters = {'A', 'B', 'C', 'D'};

    public GradeManager() {
        this.roster = new ArrayList<>();
    }

    public boolean doesStudentExist(String name) {
        for (Student s : roster) {
            if (s.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student) {
        roster.add(student);
    }

    public ArrayList<Student> getRoster() {
        return roster;
    }

    public boolean isRosterEmpty() {
        return roster.isEmpty();
    }

    public double calculateAverage() {
        if (roster.isEmpty()) {
            return 0.0;
        }

        double total = 0;
        for (Student s : roster) {
            total += s.getGrade();
        }
        return total / roster.size();
    }

    public char getLetterGrade(double grade) {
        for (int i = 0; i < cutoffs.length; i++) {
            if (grade >= cutoffs[i]) {
                return letters[i];
            }
        }
        return 'F';
    }
}