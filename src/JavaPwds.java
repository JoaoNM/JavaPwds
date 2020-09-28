import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Joaoninamatos
 */

public class JavaPwds {

    // global scanner
    static Scanner userIn = new Scanner(System.in);

    public static void addNewPwd() {
        System.out.println("Enter your login title");
        String loginTitle = userIn.next();

        System.out.println("Enter the username (optional)");
        String username = userIn.next();

        System.out.println("Enter the password");
        String password = userIn.next();


    }

    // note that this method is recusive
    // method to delete all user data
    public static void resetAll() {
        System.out.println("Are you sure want to reset everything? \n all passwords and user information will be destroyed - this cannot be undone \n Enter yes or no");

        // confirm user wants to delete everything
        String res = userIn.nextLine();

        switch (res) {
            case "yes":
                // they have confirmed, go ahead and delete
                File f = new File("user.txt");
                f.delete();
                System.out.println("Your data has been erased");
                break;
            case "no":
                System.out.println("Your data has not been removed \n");
                break;
            default:
                System.out.println("Please enter a valid option\n");
                resetAll();
        }
    }

    // this method is used to setup a new user
    // this includes making a user file, as well as the passwords file
    public static void newUser() {
        System.out.println("Welcome to the java password manager");

        System.out.println("Enter a username");

        String username = userIn.nextLine();

        try {
            // store result in the variable to debug if needed
            FileWriter fw = new FileWriter("./user.txt");
            fw.write("PROFILE INFO \n");
            fw.write("---\n");
            fw.write("Username: " + username + "\n");
            fw.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }


    // this method is essentially the CLI for this app
    // it calls other methods depending on user input
    // it's also a recursive method
    public static void controller() {
        System.out.println("What would you like to do? \n enter 'help' or read the docs to see all available commands");

        // get user input
        String option = userIn.nextLine();

        switch (option) {
            // password management functions


            // user management functions
            case "resetAll":
                resetAll();
                controller();

                // admin functions: help and quit
            case "help":
                System.out.println("COMMAND \t\t\t DESCRIPTION");
                System.out.println("resetAll \t\t\t Delete all data, including user data and passwords");
                System.out.println("quit \t\t\t quit the program");
                controller();
            case "quit":
                System.out.println("Stopping program, thank you for using java passwords :)");
                break;
            default:
                System.out.println("Please enter a valid command\n ");
                controller();
        }
    }

    public static void main(String[] args) {

        File fUser = new File("user.txt");
        File fPwd = new File("passwords.txt");
        boolean bool = false;

        try {
            bool = fUser.createNewFile();
            bool = fPwd.createNewFile();
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (bool) {
            newUser();
        } else {
            System.out.println("Welcome back to the java password manager");
        }

        controller();
    }

}
