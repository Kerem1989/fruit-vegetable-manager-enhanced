import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */

public class Account {
    private String username;
    private String password;
    private File usernameTextFile = new File("usernames.txt");
    private File passwordTextFile = new File("passwords.txt");

    public Account (String username, String password){
        this.username = username;
        this.password = password;
        addAccountInformation();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void addAccountInformation() {
        try {
            FileWriter writeToUserNameTxt = new FileWriter(usernameTextFile, true);
            writeToUserNameTxt.write(this.username + System.lineSeparator());
            writeToUserNameTxt.close();
            FileWriter writeToPasswordTxt = new FileWriter(passwordTextFile, true);
            writeToPasswordTxt.write(this.password + System.lineSeparator());
            writeToPasswordTxt.close();

        } catch (IOException e) {
            System.out.println("Error: Could not create an account.");
        }

    }



}
