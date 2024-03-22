public interface AccountManagement {
    public boolean validatePassword(String enteredPassword);
    public void changeUsername(String newUsername, int role);
    public void changePassword(String newPassword);
}
