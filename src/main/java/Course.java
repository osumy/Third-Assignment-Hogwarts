import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String courseTitle;
    private UUID courseID;
    private Teacher teacher;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> assignments = new ArrayList<>();


    public Course(String courseTitle, Teacher teacher) {
        this.courseTitle = courseTitle;
        courseID = UUID.randomUUID();
        this.teacher = teacher;
    }
    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
        courseID = UUID.randomUUID();
    }

    public void view(){
        System.out.println("\n---------------------------------------\n");
        System.out.println(courseTitle);
        System.out.println("Teacher: " + teacher.getFullName());
        System.out.println("Students:");
        for (int i = 1; i <= students.size(); i++) {
            System.out.println(" " + i + ". " + students.get(i-1).getFullName());
        }
        System.out.println("Assignments: ");
        if (assignments.size() == 0)
            System.out.println("(empty)");
        else {
            for (String assignment : assignments)
                System.out.println(assignment);
        }
    }
    public String getCourseTitle() { return courseTitle; }
    public UUID getCourseID() { return courseID; }
    public Teacher getTeacher() { return teacher; }
    public ArrayList<Student> getStudents() { return students; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public void addStudent(Student student) { students.add(student); }
    public void addAssignment(String assignment) {assignments.add(assignment); }

}
