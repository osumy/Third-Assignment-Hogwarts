import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Account{
    private ArrayList<Comment> comments = new ArrayList<>();

    public Teacher (String username, String password){
        super(1, username, password);
    }

    @Override
    public void dashboard(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);
            int choice;

            if (Hogwarts.teacherAccount.getAge() == 0)
                Hogwarts.teacherAccount.completeProfile();

            System.out.println("\n-----   Hogwarts Teacher Panel   -----\n");
            System.out.println(Hogwarts.teacherAccount.getFullName());
            System.out.println("Score: " + Hogwarts.teacherAccount.getScore() + "\n");
            System.out.println("1. My Profile");
            System.out.println("2. My Courses"); // see the list of students and assignments, and also add a new assignment
            System.out.println("3. Take a Course");
            System.out.println("4. Score Students");
            System.out.println("5. View Comments");
            System.out.println("6. Request a Course");
            System.out.println("\n7. Change Username");
            System.out.println("8. Change Password");
            System.out.println("\n0. Logout");
            System.out.print("\n>>Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 0) {
                Hogwarts.logout1();
                return;
            }

            switch (choice) {
                case 1:
                    Hogwarts.teacherAccount.showProfile();
                    break;
                case 2:
                    modifyCourses();
                    break;
                case 3:
                    takeCourse();
                    break;
                case 4:
                    scoreStudents();
                    break;
                case 5:
                    viewComments();
                    break;
                case 6:
                    requestCourse();
                    break;
                case 7:
                    System.out.print("New Username: ");
                    Hogwarts.teacherAccount.changeUsername(scanner.nextLine(), 1);
                    break;
                case 8:
                    System.out.print("New Password: ");
                    Hogwarts.teacherAccount.changePassword(scanner.nextLine());
                    break;
            }
        }
    }

    private void scoreStudents(){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("\n---------------------------------------\n");

        System.out.println("Courses");
        ArrayList<Course> courses = Hogwarts.getTeacherCourses(Hogwarts.teacherAccount.getUsername());
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i).getCourseTitle());
        }
        System.out.println(courses.size() + ". back");

        System.out.print("\n>> Enter your choice: ");
        choice = sc.nextInt();

        for (int i = 0; i < courses.get(choice).getStudents().size(); i++) {
            double score;
            System.out.print(courses.get(choice).getStudents().get(i).getFullName() + ": ");
            score = sc.nextDouble();
            courses.get(choice).getStudents().get(i).addScore(score);
        }

        Hogwarts.modifyCourses(courses);
        System.out.println("Done!");
    }
    private void requestCourse(){
        Scanner sc = new Scanner(System.in);
        String title;
        System.out.print("Title: ");
        title = sc.next();
        Hogwarts.requestCourse(title);
        System.out.println("Successful!");
    }
    private void viewComments(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------\n");
        for (Comment comment : comments){
            System.out.println("(" + comment.score + ")" + " - " + comment.comment);
        }
        System.out.print("\n>>Enter something: ");
        sc.next();
    }
    private void takeCourse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------\n");
        int choice;
        ArrayList<Course> courses = Hogwarts.getOtherCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i).getCourseTitle());
        }
        System.out.println(courses.size() + ". back");
        System.out.print("\n>>Enter your choice: ");
        choice = sc.nextInt();

        if (choice == courses.size())
            return;

        courses.get(choice).setTeacher(Hogwarts.teacherAccount);
    }
    private void modifyCourses(){
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int choice;
        String yesNo, assignment;
        ArrayList<Course> myCourses = Hogwarts.getTeacherCourses(Hogwarts.teacherAccount.getUsername());
        while (true) {
            System.out.println("\n---------------------------------------\n");

            for (int i = 0; i < myCourses.size(); i++) {
                System.out.println(i + ". " + myCourses.get(i).getCourseTitle());
            }
            System.out.println(myCourses.size() + ". back");
            System.out.print("\n>>Enter your choice: ");
            choice = sc.nextInt();

            if (choice == myCourses.size())
                break;

            myCourses.get(choice).view();

            System.out.println("Do you want to add an assignment? (y/n)");
            yesNo = sc.next();
            if (yesNo.equals("n"))
                continue;

            System.out.println("New Assignment: ");
            assignment = scanner.nextLine();

            myCourses.get(choice).addAssignment(assignment);
        }
        Hogwarts.modifyCourses(myCourses);
    }
    public void addComment(Comment comment) { comments.add(comment); }
    public double getScore() {
        double sum = 0;
        for (Comment comment : comments)
            sum += comment.score;
        return sum/comments.size();
    }
}
