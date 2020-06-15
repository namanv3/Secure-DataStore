import datastore.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the platform name: ");
        String platform = s.nextLine();

        IDataStore newDS = new MongoDBDataStore(platform);
        IPublicStore newPS = new MongoDBPublicStore(platform);
        Map<String,User> signedInUsers = new HashMap<>();

        while (true) {
            System.out.println("1 for sign up, 2 for sign in, 3 for continuing.");
            String choice = s.nextLine();
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.println("Please enter a valid input");
                continue;
            }
            if (choice.equals("3")) break;

            while (choice.equals("1")) {
                System.out.println("Enter Username:");
                String username = s.nextLine();
                System.out.println("Enter Password:");
                String password = s.nextLine();
                try {
                    newDS.initUser(username,password,newPS);
                    break;
                } catch (UserAlreadyExists e) {
                    System.out.println("Username already exists. Try another one.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            while (choice.equals("2")) {
                System.out.println("Enter Username:");
                String username = s.nextLine();
                System.out.println("Enter Password:");
                String password = s.nextLine();
                try {
                    User newUser = newDS.getUser(username,password,newPS);
                    System.out.println(newUser.getUsername() + " has been signed in.");
                    signedInUsers.put(username,newUser);
                    break;
                } catch (InvalidUsername e) {
                    System.out.println("Username incorrect. Try again.");
                } catch (InvalidPassword e) {
                    System.out.println("Password incorrect. Try again.");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
