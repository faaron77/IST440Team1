package Model.Serialized;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
