package App.Controller;

import Model.Serialized.DataCollection;
import Model.Serialized.UserList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static App.Controller.Shared.Logger.log;

/**
 *
 * @author Frederick A. Aaron
 */
public class Controller
{
    DataCollection data;
    LoginController login;
    UserList userList;

    public Controller()
    {
        log("Started");
        data = new DataCollection();
        login = new LoginController(this);
        initializeData();
    }

    public void initializeData()
    {
        userList = login.getUserList();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream("Collection.ser");
            in = new ObjectInputStream(fis);
            data = (DataCollection) in.readObject();

            // Load User List
            login.getUserList().setUsers(data.getUserList().getUsers());
            System.out.println(data.getUserList().getUsers().get(0).getPassword());
            in.close();
        }
        catch (FileNotFoundException ex)
        {
            log("Data Collection not found");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateCollection()
    {
        log("Updating DataCollection (Collection.ser)");
        try
        {
            data.setUserList(login.getUserList());
            //data.getUserList().setUserList(userList.getUserList());
            FileOutputStream fos = null;
            ObjectOutputStream out = null;

            log(data.getUserList().getUsers().toString());

            fos = new FileOutputStream("Collection.ser");
            out = new ObjectOutputStream(fos);
            out.writeObject(data);
            out.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void ocr()
    {
        OCRController ocr = new OCRController(this);
    }
}
