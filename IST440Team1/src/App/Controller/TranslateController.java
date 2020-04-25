package App.Controller;

import App.View.TranslateGUI;
import Model.Translator;

/**
 *
 * @author Frederick A. Aaron
 */
public class TranslateController
{
    String message;
    String fromLanguage ;
    String toLanguage = "en";
    TranslateGUI gui;
    Translator translator;

    public TranslateController(String message, String language) throws Exception
    {
//        if (language.equals("spa_old"))
//        {
//            fromLanguage = "es";
//        }
//        else if (language.equals("pol"))
//        {
//            fromLanguage = "pl";
//        }
//        else
        {
            fromLanguage = "es";
        }

        message = "You Quiero Taco Bell";
        this.fromLanguage = fromLanguage;
        //this.toLanguage = toLanguage;
        this.message = message;
        init();

    }

    public void init()
    {
        gui = new TranslateGUI(this);
        gui.setVisible(true);
        gui.getTxtInput().setText(message);
        translator = new Translator();
    }

    public void translate() throws Exception
    {
        gui.getTxtOutput().setText(translator.translate(fromLanguage, toLanguage, message));
    }

//    public void polishTranslator() throws Exception
//    {
//        // TODO: Specify your translation requirements here:
////    this.fromLanguage = "pl";
////    this.toLanguage = "en";
////    this.message = "Zabawmy się!!";//we need to pass the ocr message here
//
//        gui.getTxtOutput().setText(Model.Translator.translate(fromLanguage, toLanguage, message));
//
//    }
//
//    public void spanishTranslator() throws Exception
//    {
//        // TODO: Specify your translation requirements here:
//        this.fromLanguage = "es";
//        this.toLanguage = "en";
//        this.message = "Zabawmy się!!";//we need to pass the OCR message here
//
////    Model.SpanishTranslator.translate(fromLanguage, toLanguage, message);
   // }
}
