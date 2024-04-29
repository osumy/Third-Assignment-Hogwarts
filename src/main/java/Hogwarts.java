import java.util.ArrayList;
import java.util.Scanner;

public class Hogwarts {
    private static ArrayList<Administrator> admins = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    public static ArrayList<Teacher> teacherSignupRequests = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Student> studentSignupRequests = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Course> courseRequests = new ArrayList<>();


    // login
    public static Administrator adminAccount;
    public static Teacher teacherAccount;
    public static Student studentAccount;


    public static void removeAccount(int role, int index){
        if (role == 1)
            teachers.remove(index);
        else
            students.remove(index);
    }
    public static void addAccount(Administrator admin){ admins.add(admin); }
    public static void addAccount(Teacher teacher){ teachers.add(teacher); }
    public static void addAccount(Student student){ students.add(student); }
    public static void requestAccount(Teacher teacher){ teacherSignupRequests.add(teacher); }
    public static void requestAccount(Student student){ studentSignupRequests.add(student); }


    public static Administrator login0(String username, String password){
        for (Administrator admin : admins){
            if (admin.getUsername().equals(username) && admin.validatePassword(password)){
                return admin;
            }
        }

        return null; // not found!
    }

    public static Teacher login1(String username, String password){
        for (Teacher teacher : teachers){
            if (teacher.getUsername().equals(username) && teacher.validatePassword(password)){
                return teacher;
            }
        }

        return null; // not found!
    }

    public static Student login2(String username, String password){
        for (Student student : students){
            if (student.getUsername().equals(username) && student.validatePassword(password)){
                return student;
            }
        }

        return null; // not found!
    }
    public static void logout0(){
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getUsername().equals(adminAccount.getUsername())) {
                admins.set(i, adminAccount);
                return;
            }
        }
    }
    public static void logout1() {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getUsername().equals(teacherAccount.getUsername())) {
                teachers.set(i, teacherAccount);
                return;
            }
        }
    }
    public static void logout2() {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUsername().equals(studentAccount.getUsername())) {
                students.set(i, studentAccount);
                return;
            }
        }
    }


    public static void init (){
        admins.add(new Administrator("dumbledore", "admin"));
        admins.getFirst().setFullName("Albus Dumbledore");

        teachers.add(new Teacher("dumbledore", "admin"));
        teachers.getFirst().setFullName("Albus Dumbledore");
        teachers.getFirst().addComment(new Comment(10.0, "this is a test comment."));
        teachers.add(new Teacher("snape", "strong Passw/?ord"));
        teachers.get(1).setFullName("Severus Snape");
        teachers.add(new Teacher("mcgonagall", "1b3d!"));
        teachers.get(2).setFullName("Minerva McGonagall");

        students.add(new Student("harry", "stuPass1"));
        students.getFirst().setFullName("Harry Potter");
        students.add(new Student("ron", "stuPass2"));
        students.get(1).setFullName("Ron Weasley");
        students.add(new Student("hermione", "stuPass3"));
        students.get(2).setFullName("Hermione Granger");
        students.add(new Student("fred","stuPass4"));
        students.get(3).setFullName("Fred Weasley");
        students.add(new Student("draco", "stuPass5"));
        students.get(4).setFullName("Draco Malfoy");

        courses.add(new Course("Transfiguration", teachers.get(2)));
        courses.getFirst().addStudent(students.get(0));
        courses.getFirst().addStudent(students.get(1));
        courses.getFirst().addStudent(students.get(2));
        courses.getFirst().addStudent(students.get(3));
        courses.getFirst().addStudent(students.get(4));
    }
    public static boolean isUnique(String username, int role){
        switch (role){
            case 0:
                for (Administrator admin : admins)
                    if (admin.getUsername().equals(username))
                        return false;
                return true;
            case 1:
                for (Teacher teacher : teachers)
                    if (teacher.getUsername().equals(username))
                        return false;
                return true;
            case 2:
                for (Student student : students)
                    if (student.getUsername().equals(username))
                        return false;
                return true;
            default:
                return false;
        }
    }

    public static void showProfile(int role, int index){
        switch (role){
            case 1:
                teachers.get(index).showProfile();
                break;
            case 2:
                students.get(index).showProfile();
                break;
        }
    }
    public static void viewAllTeachers(){
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(i + ". " + teachers.get(i).getFullName());
        }
    }
    public static void viewAllTeacherSignupRequests(){
        for (int i = 0; i < teacherSignupRequests.size(); i++) {
            System.out.println(i + ". " + teacherSignupRequests.get(i).getFullName());
        }
    }
    public static void viewAllStudents(){
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + ". " + students.get(i).getFullName());
        }
    }
    public static void viewAllStudentSignupRequests(){
        for (int i = 0; i < studentSignupRequests.size(); i++) {
            System.out.println(i + ". " + studentSignupRequests.get(i).getFullName());
        }
    }
    public static void viewAllCourses(){
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i).getCourseTitle());
        }
    }
    public static void viewCourse(int index){ courses.get(index).view(); }
    public static void addCourse(String title){
        Scanner sc = new Scanner(System.in);
        Course newCourse = new Course(title);
        System.out.println("Are you sure? (y/n)");
        if (sc.next().equals("n"))
            return;
        courses.add(newCourse);
    }
    public static void requestCourse(String title){
        Course newCourse = new Course(title);
        courseRequests.add(newCourse);
    }
    public static ArrayList<Course> getTeacherCourses(String username){
        ArrayList<Course> myCourses = new ArrayList<>();
        for (Course course : courses){
            if (course.getTeacher().getUsername().equals(username)){
                myCourses.add(course);
            }
        }
        return myCourses;
    }
    public static ArrayList<Course> getOtherCourses(){
        ArrayList<Course> courses1 = new ArrayList<>();
        for (Course course : courses){
            if (course.getTeacher() == null)
                courses1.add(course);
        }
        return courses1;
    }
    public static void modifyCourses(ArrayList<Course> modifiedCourses){
        for (Course modifiedCourse : modifiedCourses) {
            for (int i = 0; i < courses.size(); i++){
                if (courses.get(i).getCourseID().equals(modifiedCourse.getCourseID()))
                    courses.set(i, modifiedCourse);
            }
        }
    }
    public static ArrayList<Course> getStudentCourses(String username){
        ArrayList<Course> courses1 = new ArrayList<>();
        for (Course course : courses){
            for (Student student : course.getStudents()){
                if (student.getUsername().equals(username))
                    courses1.add(course);
            }
        }
        return courses1;
    }
    private static boolean isTaken(String username, ArrayList<Student> students){
        for (Student student : students){
            if (student.getUsername().equals(username))
                return true;
        }
        return false;
    }
    public static ArrayList<Course> getOtherStudentCourses(){
        ArrayList<Course> courses1 = new ArrayList<>();
        for (Course course : courses){
            if (!isTaken(studentAccount.getUsername(), course.getStudents()))
                courses1.add(course);
        }
        return courses1;
    }
    public static void addComment(Comment comment, String username){
        for (Teacher teacher : teachers){
            if (teacher.getUsername().equals(username)) {
                teacher.addComment(comment);
                return;
            }
        }
    }
}
