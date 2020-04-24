package Model.Serialized;

import java.io.Serializable;

/**
 *
 * @author Frederick A. Aaron
 */
public class User implements Serializable
{
    private String name;
    private String password;

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
