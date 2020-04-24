package Model.Serialized;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import static App.Controller.Shared.Logger.log;

/**
 *
 * @author Frederick A. Aaron
 */
public class UserList implements Serializable
{
    private ArrayList<User> users = new ArrayList();

    public UserList()
    {

//        String name = "admin";
//        String password = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
//        User user = new User(name, password);
//        users.add(user);
    }

    public boolean newUser(String name, String password)
    {
        boolean upper = false;
        boolean lower = false;
        boolean number = false;
        boolean special = false;
        boolean space = false;
        boolean valid = false;
        char index = ' ';
        for (int i = 0; i < password.length(); i++)
        {
            index = password.charAt(i);
            if ((index == Character.toUpperCase(index))
                    && (!Character.isWhitespace(index)))
            {
                upper = true;
            }
            if ((index == Character.toLowerCase(index))
                    && (!Character.isWhitespace(index)))
            {
                lower = true;
            }
            if ((Character.isDigit(index))
                    && (!Character.isWhitespace(index)))
            {
                number = true;
            }
            if ((!Character.isLetterOrDigit(index))
                   && (!Character.isWhitespace(index)))
            {
                special = true;
            }
            if (upper && lower && number && special && !space)
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
        }
//        System.out.println("Upper is " + upper);
//        System.out.println("Lower is " + lower);
//        System.out.println("Digit is " + number);
//        System.out.println("Special is " + special);
//        System.out.println("Space is " + space);
//        System.out.println("valid is " + valid);
        if (valid)
        {
            User user = new User(name, sha256(password));
            users.add(user);
            log ("User " + name + " created.");
        }
        return (valid);
    }
    
    public boolean foundUser(String name, String password)
    {
        boolean found = false;
        for (int i = 0; i < users.size(); i++)
        {
            if ((users.get(i).getName().equals(name))
                    && users.get(i).getPassword().equals(sha256(password)))
            {
                found = true;
            }
        }
        return (found);
    }

    public byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public String sha256(String password)
    {
        String sha = "";
        try
        {
            sha = (toHexString(getSHA(password)));
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return (sha);
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }
}
