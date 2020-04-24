package Model.Serialized;

import java.io.Serializable;

/**
 *
 * @author Frederick A. Aaron
 */
public class DataCollection implements Serializable
{
    private UserList userList;

    public UserList getUserList()
    {
        return userList;
    }

    public void setUserList(UserList userList)
    {
        this.userList = userList;
    }
}
