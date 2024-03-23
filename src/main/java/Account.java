import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;

public class Account implements AccountManagement {
    private UUID accountID;
    private int role; // 0:Admin  1:Teacher  2:Student
    private String username;
    private String password;
    private String fullName = "";
    private int age = 0;
    private String gender = "";
    private String birthday = "";


    public Account(int role, String username, String password){
        accountID = UUID.randomUUID();
        this.role = role;
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
    }
    public void dashboard(){}
    public void showProfile(){
        System.out.println("\n---------------------------------------\n");
        System.out.print(fullName + " ( ");
        switch (role){
            case 0:
                System.out.print("Assistant )\n");
                break;
            case 1:
                System.out.print("Teacher )\n");
                break;
            case 2:
                System.out.print("Student )\n");
                break;
        }
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Birthday: " + birthday);
        System.out.print("\n( enter something to continue )\n>> ");
        Scanner sc = new Scanner(System.in);
        sc.next();
    }
    public void completeProfile(){
        System.out.println("\n---------------------------------------\n");
        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Complete your Profile (this is your first login, you have to provide some personal information)\n");
        System.out.print("Age: ");
        this.age = sc.nextInt();
        System.out.print("Gender: ");
        this.gender = sc.next();
        System.out.print("Birthday: ");
        this.birthday = scanner.nextLine();
    }

    public String getUsername() { return username; }
    public UUID getAccountID() { return accountID; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setBirthday(String birthday) { this.birthday = birthday; }


    @Override
    public boolean validatePassword(String enteredPassword) { return password.equals(DigestUtils.sha256Hex(enteredPassword)); }
    @Override
    public void changeUsername(String newUsername, int role) {
        if (username.equals(newUsername)) {
            System.out.println("Please enter a different username.");
        }
        else if (!Hogwarts.isUnique(newUsername, role)) {
            System.out.println("This username is already exist, please try again.");
        }
        else
            username = newUsername;
    }
    @Override
    public void changePassword(String newPassword) { password = DigestUtils.sha256Hex(newPassword); }
}
