import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Account{
    private String house = "";
    private ArrayList<Double> scores = new ArrayList<>();


    public Student (String username, String password){
        super(2, username, password);
    }

    @Override
    public void dashboard(){
        while (true){
            Scanner sc = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);
            int choice;

            if (Hogwarts.studentAccount.getAge() == 0)
                Hogwarts.studentAccount.completeProfile();

            if (house.isEmpty())
                sortingQuiz();

            System.out.println("\n-----   Hogwarts Student Panel   -----\n");
            System.out.println(Hogwarts.studentAccount.getFullName() + " - " + house);
            System.out.println("1. My Profile");
            System.out.println("2. My Courses");
            System.out.println("3. My Teachers");
            System.out.println("4. Take a Course");
            System.out.println("5. Comment Teachers");
            System.out.println("\n6. Change Username");
            System.out.println("7. Change Password");
            System.out.println("\n0. Logout");
            System.out.print("\n>>Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 0) {
                Hogwarts.logout2();
                return;
            }

            switch (choice){
                case 1:
                    Hogwarts.studentAccount.showProfile();
                    break;
                case 2:
                    showMyCourses();
                    break;
                case 3:
                    showMyTeachers();
                    break;
                case 4:
                    takeCourse();
                    break;
                case 5:
                    comment();
                    break;
                case 6:
                    System.out.print("New Username: ");
                    Hogwarts.studentAccount.changeUsername(scanner.nextLine(), 2);
                    break;
                case 7:
                    System.out.print("New Password: ");
                    Hogwarts.studentAccount.changePassword(scanner.nextLine());
                    break;
            }
        }
    }


    private void comment(){
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int choice;
        double score;
        String comment;
        System.out.println("\n---------------------------------------\n");
        System.out.println("Choose a Teacher: ");

        ArrayList<Course> myCourses = Hogwarts.getStudentCourses(Hogwarts.studentAccount.getUsername());
        ArrayList<Teacher> myTeachers = new ArrayList<>();
        for (Course course : myCourses){
            if(isUniqueTeacher2(course.getTeacher().getFullName(), myTeachers))
                myTeachers.add(course.getTeacher());
        }

        for (int i = 0; i < myTeachers.size(); i++) {
            System.out.println(i + ". " + myTeachers.get(i).getFullName());
        }
        System.out.print("\n>>Enter your choice: ");
        choice = sc.nextInt();

        System.out.print("Score: ");
        score = sc.nextDouble();

        System.out.print("Comment: ");
        comment = scanner.nextLine();

        Comment newComment = new Comment(score, comment);
        Hogwarts.addComment(newComment, myTeachers.get(choice).getUsername());
    }
    private void takeCourse(){
        ArrayList<Course> courses = Hogwarts.getOtherStudentCourses();
        Scanner sc = new Scanner(System.in);
        int choice;
        String yesNo;
        System.out.println("\n---------------------------------------\n");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i).getCourseTitle());
        }
        System.out.println(courses.size() + ". back");
        System.out.print("\n>>Enter your choice: ");
        choice = sc.nextInt();

        if (choice == courses.size())
            return;

        System.out.println("Are you sure? (y/n)");
        yesNo = sc.next();

        if (yesNo.equals("n"))
            return;

        courses.get(choice).addStudent(Hogwarts.studentAccount);
        Hogwarts.modifyCourses(courses);
    }
    private boolean isUniqueTeacher(String name,  ArrayList<String> teachers){
        for (String teacher : teachers){
            if (teacher.equals(name))
                return false;
        }
        return true;
    }
    private boolean isUniqueTeacher2(String name,  ArrayList<Teacher> teachers){
        for (Teacher teacher : teachers){
            if (teacher.getFullName().equals(name))
                return false;
        }
        return true;
    }
    private void showMyTeachers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------\n");
        System.out.println("My Teachers: ");
        ArrayList<Course> myCourses = Hogwarts.getStudentCourses(Hogwarts.studentAccount.getUsername());
        ArrayList<String> myTeachers = new ArrayList<>();
        for (Course course : myCourses){
            if(isUniqueTeacher(course.getTeacher().getFullName(), myTeachers))
                myTeachers.add(course.getTeacher().getFullName());
        }
        for (int i = 0; i < myTeachers.size(); i++) {
            System.out.println(i + ". " + myTeachers.get(i));
        }
        System.out.print("\n>>Enter something: ");
        sc.next();
    }
    private void showMyCourses(){
        Scanner sc = new Scanner(System.in);
        int choice;
        ArrayList<Course> myCourses = Hogwarts.getStudentCourses(Hogwarts.studentAccount.getUsername());
        for (int i = 0; i < myCourses.size(); i++) {
            System.out.println(i + ". " + myCourses.get(i).getCourseTitle());
        }
        System.out.println(myCourses.size() + ". back");
        System.out.print("\n>>Enter your choice: ");
        choice = sc.nextInt();

        if (choice == myCourses.size())
            return;

        myCourses.get(choice).view();
        System.out.print("\n>>Enter something: ");
        sc.next();
    }
    private void sortingQuiz(){
        Scanner sc = new Scanner(System.in);
        String yesNo;
        System.out.println("\n---------------------------------------\n");
        System.out.println("Sorting Quiz");
        System.out.println("You have no house, Do you want to take a sorting quiz? (y/n)");
        yesNo = sc.next();
        if (yesNo.equals("n"))
            return;

        switch ((int)((Math.floor(Math.random()*10))%4)) {
            case 0:
                house = "Gryffindor";
                break;
            case 1:
                house = "Hufflepuff";
                break;
            case 2:
                house = "Ravenclaw";
                break;
            case 3:
                house = "Slytherin";
                break;
            default:
                house = "Slytherin";
                break;
        }
        System.out.println("Your house: " + house);
    }
    public void addScore(double score) { scores.add(score); }
    public ArrayList<Double> getScores() { return scores; }
}
