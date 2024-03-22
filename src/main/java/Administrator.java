import java.util.Scanner;

public class Administrator extends Account{
    public Administrator(String username, String password){
        super(0, username, password);
    }

    @Override
    public void dashboard(){
        while (true){
            Scanner sc = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n-----   Hogwarts Assistant Panel   -----\n");
            System.out.println("Account Management");
            System.out.println(" 1. Check Student/Teacher Profile");
            System.out.println(" 2. Remove a Teacher/Student");
            System.out.println(" 3. Add a new Assistant");
            System.out.println(" 4. Accept a Teacher/Student");
            System.out.println("Course Management");
            System.out.println(" 5. View Courses");
            System.out.println(" 6. Create a Course");
            System.out.println(" 7. Accept a Course Request");
            System.out.println("My Account");
            System.out.println(" 8. Change Username");
            System.out.println(" 9. Change Password");
            System.out.println("\n 0. Logout");

            System.out.print(">> Enter your choice: ");
            int choice = sc.nextInt();
            if (choice == 0) {
                Hogwarts.logout0();
                return;
            }

            switch (choice){
                case 1:
                    Assistant.checkUserProfile();
                    break;
                case 2:
                    Assistant.removeUser();
                    break;
                case 3:
                    Assistant.newAssistant();
                    break;
                case 4:
                    Assistant.acceptUser();
                    break;
                case 5:
                    Assistant.viewCourses();
                    break;
                case 6:
                    Assistant.createCourse();
                    break;
                case 7:
                    Assistant.acceptCourse();
                    break;
                case 8:
                    System.out.print("New Username: ");
                    Hogwarts.adminAccount.changeUsername(scanner.nextLine(), 0);
                    break;
                case 9:
                    System.out.print("New Password: ");
                    Hogwarts.adminAccount.changePassword(scanner.nextLine());
                    break;
            }
        }
    }
}
