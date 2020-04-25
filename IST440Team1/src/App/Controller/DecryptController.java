package App.Controller;

import App.View.DecryptGUI;

/**
 *
 * @author Frederick A. Aaron
 */
public class DecryptController
{
    String ocrString;
    String language; // English = eng, Polish = pol, Spanish = spa_old
    String message;  // The decoded message to be passed to the translator.
    DecryptGUI gui;
    
    public DecryptController(String ocrString, String language)
    {
        this.language = language;
        this.ocrString = ocrString;
        init();
    }
    
    public void init()
    {
        gui = new DecryptGUI(this);
        gui.setVisible(true);
    }
    
    public void translate() throws Exception
    {
        TranslateController translator = new TranslateController(message, language);
        gui.dispose();
    }
}
