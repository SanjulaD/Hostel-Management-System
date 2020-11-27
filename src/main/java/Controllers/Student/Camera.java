package Controllers.Student;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;


public class Camera {

public String text ;
public String replaced;
public String name ;

    public  String camera_Open() throws IOException {






      Webcam webcam = Webcam.getDefault();
        webcam.getWebcams().get(1);
        System.out.println("Webcam: " + webcam.getName());
 //System.out.println(  Webcam.getWebcams());

            webcam.open();


        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
            // get image
            BufferedImage image = webcam.getImage();

            // save image to PNG file
            ImageIO.write(image, "PNG", new File("test.png"));


        webcam.close();
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("C:/tessdata");
            String text = tesseract.doOCR(new File("a.jpeg"));

            replaced = text.replace("\u00ff", "");


            String x =   String.format("%040x", new BigInteger(1, text.getBytes(/*YOUR_CHARSET?*/)));
            System.out.println(x);
            String str = text;
            String[] part = str.split("(?<=\\D)(?=\\d)");

            System.out.println(".................");
            System.out.println(part[0]);
            System.out.println(".................");
            System.out.println(part[1]);
            System.out.println(".................");
            System.out.println(part[2]);
            System.out.println(".................");


            part[2] = part[2].replaceAll("\\d","");

            System.out.println( part[2]);
            part[1] = part[1].replaceAll("Valid till", "");

            replaced = part[1];
            
            part[0] = part[0].replaceAll("STUDENT", "");
            
            name = part[0];
        } catch (TesseractException e) {
            e.printStackTrace();

        
            
        }
        
        
        
        
        
        

return replaced;
    }
  public  String scan() throws IOException {
        
           return name;  
             
         }
}