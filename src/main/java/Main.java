import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Adding 1 admin, 3 teachers and 5 students to construct school
        Hogwarts.init();

        while (true) {
            switch (runMenu()){
                case 3:
                    return;
                case 1:
                    loginMenu();
                    break;
                case 2:
                    signUpMenu();
                    break;
            }
        }
    }

    private static int runMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println(" .*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.");
        System.out.println("----------     Hogwarts     -----------");
        System.out.println(" 1. Login\n 2. Sign Up\n 3. Exit\n");

        System.out.print(" >> Enter your choice: ");
        return sc.nextInt();
    }

    private static void loginMenu() {
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        String username, password;
        int role;
        System.out.println("\n---------------------------------------\n");

        System.out.println("1. Administrator\n2. Teacher\n3. Student\n4. back\n");
        System.out.print(">> Enter your choice: ");
        role = sc.nextInt();
        if (role == 4)
            return;

        System.out.println("\nPlease enter your username and password ( or enter 'b' to return the main menu )\n");
        System.out.print("Username: ");
        username = sc.next();
        if (username.equals("b"))
            return;
        System.out.print("Password: ");
        password = scanner.nextLine();
        if (password.equals("b"))
            return;

        switch (role){
            case 1:
                Hogwarts.adminAccount = Hogwarts.login0(username, password);
                if (Hogwarts.adminAccount == null){
                    System.out.println("user not found!");
                    return;
                }
                System.out.println("Login successful!");
                Hogwarts.adminAccount.dashboard();
                Hogwarts.adminAccount = null;
                break;
            case 2:
                Hogwarts.teacherAccount = Hogwarts.login1(username, password);
                if (Hogwarts.teacherAccount == null){
                    System.out.println("user not found!");
                    return;
                }
                System.out.println("Login successful!");
                Hogwarts.teacherAccount.dashboard();
                Hogwarts.teacherAccount = null;
                break;
            case 3:
                Hogwarts.studentAccount = Hogwarts.login2(username,password);
                if (Hogwarts.studentAccount == null){
                    System.out.println("user not found!");
                    return;
                }
                System.out.println("Login successful!");
                Hogwarts.studentAccount.dashboard();
                Hogwarts.studentAccount = null;
                break;
        }
    }

    private static void signUpMenu() {
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        String username, password, fName;
        int role;
        System.out.println("\n---------------------------------------\n");

        System.out.println("1. Teacher\n2. Student\n3. back\n");
        System.out.print(">> Enter your choice: ");
        role = sc.nextInt();
        if (role == 3)
            return;

        while (true) {
            System.out.println("\nPlease enter your username and password ( or enter 'b' to return the main menu )\n");

            System.out.print("Username: ");
            username = sc.next();
            if (username.equals("b"))
                return;

            System.out.print("Password: ");
            password = sc.next();
            if (password.equals("b"))
                return;

            System.out.print("Full Name: ");
            fName = scanner.nextLine();
            if (fName.equals("b"))
                return;

            if (Hogwarts.isUnique(username, role))
                break;

            System.out.println("try again!");
        }

        switch (role){
            case 1:
                Teacher newTeacher = new Teacher(username, password);
                newTeacher.setFullName(fName);
                Hogwarts.requestAccount(newTeacher);
                break;
            case 2:
                Student newStudent = new Student(username, password);
                newStudent.setFullName(fName);
                Hogwarts.requestAccount(newStudent);
                break;
        }
    }

}
