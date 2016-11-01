/**
    This program is a demonstration of JComboBox, and attempts to adhere
    to the Model-View-Controller (MVC) pattern
    Date
    CSC 251 0001 - JComboBox Demonstration
    @author James Alves, Shane McCann, Timothy Burns
*/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class JComboBoxDemonstration
{    
    /**
        Main method
    
        @param args The arguments
    */
    
    public static void main(String[] args)
    {
        // Initialize model
        Model model = loadModel();
        
        // Construct GUI
        GUI gui = new GUI();
        
        // Set the model for the GUI
        gui.setModel(model);
        
        // Construct the controller for the JComboBox
        ComboBoxController comboController = new ComboBoxController(gui, model);
        
        // Construct the controller for the Buttons
        ButtonController buttonController = new ButtonController(gui, model);
        
        // Register controllers
        gui.registerController(comboController, buttonController);
    }
    
    /**
        The loadModel method loads the initial model data
    
        @return The initial model
    */
    
    private static Model loadModel()
    {
        String[] countryNames = loadCountryNames();
        String[] translations = loadTranslations();
        BufferedImage[] images = loadImages();
        
        return new Model(countryNames, translations, images);
    }
    
    /**
        The loadCountryNames method loads the initial country names
    
        @return names An array containing the initial country names
    */
    
    private static String[] loadCountryNames()
    {
        final int NUM_NAMES = 3;
        String[] names = new String[NUM_NAMES];
        names[0] = "Finland";
        names[1] = "France";
        names[2] = "Germany";
        return names;
    }
    
    /**
        The loadTranslations method loads the initial translations
    
        @return translations An array containing the initial translations
    */
    
    private static String[] loadTranslations()
    {
        final int NUM_TRANSLATIONS = 3;
        String[] translations = new String[NUM_TRANSLATIONS];
        translations[0] = "Hei, maailma!";
        translations[1] = "Bonjour le monde!";
        translations[2] = "Hallo Welt!";
        return translations;
    }
    
    /**
        The loadImages method loads the initial images from the images
        directory
    
        @return images An array containing the initial images
    */
    
    private static BufferedImage[] loadImages()
    {
        BufferedImage[] images = null;
        
        try
        {
            // Get image files
            String[] files = new File(JComboBoxDemonstration.class.
                             getResource("images").toURI()).list();
            
            // Sort alphabetically
            Arrays.sort(files);
            
            // Read images
            images = new BufferedImage[files.length];
            for (int i = 0; i < images.length; i++)
            {
                images[i] = ImageIO.read(JComboBoxDemonstration.class.
                            getResourceAsStream("images/" + files[i]));
            }
        }
        catch (IOException | URISyntaxException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return images;
    }
}