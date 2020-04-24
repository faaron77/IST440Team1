package App.Controller;

import App.View.OCRGUI;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import static App.Controller.Shared.Logger.log;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Frederick A. Aaron
 */
public class OCRController
{
    OCRGUI ocrGUI;
    File selectedFile;
    private String language = "eng";

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
            tesseract.setLanguage(language);
            log("Scanning " + file.getAbsolutePath() + ". Language = " + language + ".\n");
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
            jfc.addChoosableFileFilter(new ImageFilter());
            jfc.setAcceptAllFileFilterUsed(false);
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

    public void Decrypt()
    {
        DecryptController decryptor = new DecryptController(ocrGUI.getTxtOutput().getText(), language);
        ocrGUI.dispose();
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    class ImageFilter extends FileFilter
    {
        public final static String JPEG = "jpeg";
        public final static String JPG = "jpg";
        public final static String GIF = "gif";
        public final static String TIFF = "tiff";
        public final static String TIF = "tif";
        public final static String PNG = "png";

        @Override
        public boolean accept(File f)
        {
            if (f.isDirectory())
            {
                return true;
            }

            String extension = getExtension(f);
            if (extension != null)
            {
                if (extension.equals(TIFF)
                        || extension.equals(TIF)
                        || extension.equals(GIF)
                        || extension.equals(JPEG)
                        || extension.equals(JPG)
                        || extension.equals(PNG))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }

        @Override
        public String getDescription()
        {
            return "Image Only";
        }

        String getExtension(File f)
        {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1)
            {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }
}
