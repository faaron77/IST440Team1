package App.Controller;

import App.View.OCRGUI;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
//import static App.Controller.shared.Logger.log;
import static java.rmi.server.LogStream.log;



/**
 *
 * @author Frederick A. Aaron
 */
public class OCRController
{
    OCRGUI ocrGUI;
    File selectedFile;

    public OCRController(Controller parentController)
    {
        ocrGUI = new OCRGUI(this);
        ocrGUI.setVisible(true);
    }

    public String scan(File file)

    {
        String result = "";
        Tesseract tesseract = new Tesseract();
        try
        {
            
            tesseract.setDatapath("./tessdata");
            log("Scanning " + file.getAbsolutePath() + "\n");
            result = tesseract.doOCR(new File(file.toString()));
        }
        catch (TesseractException e)
        {
            e.printStackTrace();
        }
        return (result);
    }

    public File selectFile()
    {
        try
        {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = jfc.getSelectedFile();
        }
        }
        catch (Exception e)
        {
            System.out.println("JFileChooser Exception");
            e.printStackTrace();
        }
        return (selectedFile);
    }
}
