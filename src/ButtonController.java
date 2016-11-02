/**
    This is the controller for the GUI's buttons
    11/2/2016
    CSC 251 0001 - JComboBox Demonstration
    @author James Alves, Shane McCann, Timothy Burns
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ButtonController implements ActionListener
{
    // Fields
    private GUI view;
    private Model model;
    
    /**
        Constructor
        Accepts the view to control buttons for and the model
    
        @param v The view
        @param m The model
    */
    
    public ButtonController(GUI v, Model m)
    {
        view = v;
        model = m;
    }
    
    /**
        ActionPerformed method to handle events
    
        @param e The ActionEvent
    */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Determine button clicked
        switch (e.getActionCommand())
        {
            case "Create Element":
                /*  
                    The user will be prompted to provide, preferably,
                    a country name and appropriate translation of the phrase
                    "Hello, World!" The user will finally have to provide a
                    good link to an image of the country's flag (or any image,
                    really) found on the Internet to display
                */
                
                // Get country name
                String countryName;
                do
                {
                    countryName = view.promptForCountryName();
                } while (countryName.isEmpty());
                
                // Get translation
                String translation;
                do
                {
                    translation = view.promptForTranslation();
                } while (translation.isEmpty());
                
                // Get image
                boolean success = false;
                boolean keepGoing = false;
                BufferedImage img = null;
                do
                {
                    try
                    {
                        img = getImage();
                        if (img != null)
                        {
                            success = true;
                        }
                        else
                        {
                            throw new IOException();
                        }
                    }
                    catch (IOException ex)
                    {
                        // Prompt of failure and see if user wants to try again
                        keepGoing = view.promptGetImageFailure();
                    }
                } while (keepGoing);
                
                if (success)
                {
                    // Add data to model
                    model.addData(countryName, translation, img);
                    
                    // Update view
                    view.addElement(countryName);
                    
                    // Prompt of success
                    view.promptSuccess();
                }
                else
                {
                    // Prompt of failure
                    view.promptFailure();
                }
                break;
            case "Delete Element":
                // Delete currently selected element from model and view
                String selection = view.getSelection();
                int index = model.getCountryNameIndex(selection);
                if (index != -1)
                {
                    // Remove data from model
                    model.removeData(index);
                    
                    // Update view
                    view.removeElement(index);
                    view.setTranslation("");
                    view.removeImage();
                    
                    // Prompt of success
                    view.promptSuccess();
                }
                else
                {
                    // Prompt failure
                    view.promptFailure();
                }
                break;
            case "Exit":
                // Quit
                System.exit(0);
                break;
        }
    }
    
    /**
        The getImage method gets user input for a link to an image and returns
        the image
    
        @return The image
        @exception IOException If fail to retrieve image at link
    */
    
    private BufferedImage getImage() throws IOException
    {        
        // Get image link
        String link;
        do
        {
            link = view.promptForLink();
        } while (link.isEmpty());
        
        // Attempt to retrieve and return image at specified link
        BufferedImage img = ImageIO.read(new URL(link));
        return img;
    }
}