import java.util.Scanner;

public class Assistant {

    public static void checkUserProfile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------\n");
        System.out.println("1. Teacher Profiles");
        System.out.println("2. Student Profiles");
        System.out.println("3. back");
        System.out.print(">> Enter your choice: ");
        int choice = sc.nextInt();
        System.out.println();

        if (choice == 3)
            return;

        switch (choice){
            case 1:
                Hogwarts.viewAllTeachers();
                System.out.print(" >> Enter your choice: ");
                int teacherNumber = sc.nextInt();
                Hogwarts.showProfile(choice, teacherNumber);
                break;
            case 2:
                Hogwarts.viewAllStudents();
                System.out.print(" >> Enter your choice: ");
                int studentNumber = sc.nextInt();
                Hogwarts.showProfile(choice, studentNumber);
                break;
        }
    }

    public static void removeUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------\n");
        System.out.println("1. Remove a Teacher");
        System.out.println("2. Remove a Student");
        System.out.println("3. back");
        System.out.print(">> Enter your choice: ");
        int choice = sc.nextInt();
        System.out.println();

        if (choice == 3)
            return;

        switch (choice){
            case 1:
                Hogwarts.viewAllTeachers();

                System.out.print(" >> Enter your choice: ");
                int teacherNumber = sc.nextInt();

                System.out.println("Are you sure you want to delete this account? (y/n)");
                if (sc.next().equals("n"))
                    return;

                Hogwarts.removeAccount(1, teacherNumber);
                break;
            case 2:
                Hogwarts.viewAllStudents();

                System.out.print(" >> Enter your choice: ");
                int studentNumber = sc.nextInt();

                System.out.println("Are you sure you want to delete this account? (y/n)");
                if (sc.next().equals("n"))
                    return;

                Hogwarts.removeAccount(2, studentNumber);
                break;
        }
    }

    public static void newAssistant(){
        Scanner sc = new Scanner(System.in);
        Administrator newAdmin;
        String username, password, fName;
        System.out.println("\n---------------------------------------\n");

        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = sc.next();
        System.out.print("FullName: ");
        fName = sc.nextLine();

        newAdmin = new Administrator(username, password);
        newAdmin.setFullName(fName);

        Hogwarts.addAccount(newAdmin);
    }

    public static void viewCourses(){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("\n---------------------------------------\n");
        System.out.println("Courses:");
        Hogwarts.viewAllCourses();
        System.out.print(">> Enter your choice: ");
        choice = sc.nextInt();
        Hogwarts.viewCourse(choice);
        System.out.print("\n( enter anything to continue )\n>> ");
        sc.next();
    }

    public static void createCourse(){
        Scanner sc = new Scanner(System.in);
        int choice;
        String title;
        System.out.println("\n---------------------------------------\n");
        System.out.print("Title: ");
        title = sc.nextLine();
        Hogwarts.addCourse(title);
    }

    public static void acceptCourse(){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("\n---------------------------------------\n");

        for (int i = 0; i < Hogwarts.courseRequests.size(); i++){
            System.out.println(i + ". " + Hogwarts.courseRequests.get(i).getCourseTitle());
        }
        System.out.println(Hogwarts.courseRequests.size() + ". back");
        System.out.print("\n>> Enter your choice: ");
        choice = sc.nextInt();

        if (choice == Hogwarts.courseRequests.size())
            return;

        Hogwarts.addCourse(Hogwarts.courseRequests.get(choice).getCourseTitle());
        Hogwarts.courseRequests.remove(choice);
    }

    public static void acceptUser(){
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("\n---------------------------------------\n");

        System.out.println("1. Teacher SignUp Requests");
        System.out.println("2. Student SignUp Requests");
        System.out.println("3. back");
        System.out.print(">> Enter your choice: ");
        choice = sc.nextInt();
        System.out.println();
        if (choice == 3)
            return;

        switch (choice){
            case 1:
                Hogwarts.viewAllTeacherSignupRequests();
                System.out.println("(-1 to back)");
                System.out.print(">> Enter your choice: ");
                choice = sc.nextInt();
                if (choice == -1)
                    return;
                Hogwarts.addAccount(Hogwarts.teacherSignupRequests.get(choice));
                Hogwarts.teacherSignupRequests.remove(choice);
                break;
            case 2:
                Hogwarts.viewAllStudentSignupRequests();
                System.out.println("(-1 to back)");
                System.out.print(">> Enter your choice: ");
                choice = sc.nextInt();
                if (choice == -1)
                    return;
                Hogwarts.addAccount(Hogwarts.studentSignupRequests.get(choice));
                Hogwarts.studentSignupRequests.remove(choice);
                break;
        }
    }
}
