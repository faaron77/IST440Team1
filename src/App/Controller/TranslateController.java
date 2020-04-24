package App.Controller;

import App.View.TranslateGUI;

/**
 *
 * @author Frederick A. Aaron
 */
public class TranslateController
{
    String message;
    String language;
    TranslateGUI gui;
    
    public TranslateController(String message, String language)
    {
        this.language = language;
        this.message = message;
        init();
    }
    
    public void init()
    {
        gui = new TranslateGUI(this);
        gui.setVisible(true);
    }
}
