package App.Controller;

import static App.Controller.Shared.Logger.log;
import App.View.LoginGUI;
import Model.Serialized.UserList;



/**
 *
 * @author Frederick A. Aaron
 */
public class LoginController
{
    private LoginGUI gui;
    private UserList userList;
    private Controller parentController;

    public LoginController(Controller cntrl)
    {
        parentController = cntrl;
        userList = new UserList();
        gui = new LoginGUI(this);
        gui.setVisible(true);
    }

        public boolean createUser(String name, String password)
    {
        boolean valid;
        valid = userList.newUser(name, password);
        if (valid)
        {
            parentController.updateCollection();
        }
        return (valid);
    }
        
    public void loginRequest(String name, String password)
    {
        try
        {
            if (userList.foundUser(name, password))
            {
                log("User " + name + " Authenticated");
                parentController.updateCollection();

                gui.validLogin();
                parentController.ocr();
            }
            else
            {
                log("Authentication Failed. User " + name + " Not Found");
                gui.invalidLogin();
            }
        }
        catch (NullPointerException ex)
        {
            gui.invalidLogin();
        }
    }

    public void cancelRequest()
    {
        parentController.updateCollection();
    }

    public LoginGUI getGui()
    {
        return gui;
    }

    public void setGui(LoginGUI gui)
    {
        this.gui = gui;
    }

    public UserList getUserList()
    {
        return userList;
    }

    public void setUserList(UserList userList)
    {
        this.userList = userList;
    }

    public Controller getParentController()
    {
        return parentController;
    }

    public void setParentController(Controller parentController)
    {
        this.parentController = parentController;
    }
}
