/**
    This class holds the model data for the program
    11/2/2016
    CSC 251 0001 - JComboBox Demonstration
    @author James Alves, Shane McCann, Timothy Burns
*/

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Model
{
    // Fields
    private ArrayList<BufferedImage> images;
    private ArrayList<String> countryNames, translations;
    
    /**
        Constructor
        Accepts the model data
    
        @param names The country names
        @param trans The translations
        @param imgs The images
    */
    
    public Model(String[] names, String[] trans, BufferedImage[] imgs)
    {
        countryNames = new ArrayList<>(Arrays.asList(names));
        translations = new ArrayList<>(Arrays.asList(trans));
        images = new ArrayList<>(Arrays.asList(imgs));
    }
    
    /**
        The addData method adds a new data entry into the model
    
        @param name Country name to add
        @param translation Translation of hello world
        @param image Country image
    */
    
    public void addData(String name, String translation, BufferedImage image)
    {
        countryNames.add(name);
        translations.add(translation);
        images.add(images.size() - 1, image);
    }
    
    /**
        The getCountryNameIndex method returns the index of the passed country
        name; -1 if it doesn't exist
    
        @param name The name of the country to get the index of
        @return index The index of the country name in the model; else, -1
    */
    
    public int getCountryNameIndex(String name)
    {
        int index = -1;
        for (int i = 0; i < countryNames.size() && index == -1; i++)
        {
            if (countryNames.get(i).equalsIgnoreCase(name))
            {
                index = i;
            }
        }
        return index;
    }
    
    /**
        The getCountryNames method returns the country names as an array
    
        @return The country names
    */
    
    public String[] getCountryNames()
    {
        return countryNames.toArray(new String[countryNames.size()]);
    }
    
    /**
        The getCountryNotFoundImage method returns an image to display
        for when a piece of data is not found in the model
    
        @return Image to display
    */
    
    public BufferedImage getCountryNotFoundImage()
    {
        // This particular image is always last in the list
        return images.get(images.size() - 1);
    }
    
    /**
        The getCountryNotFoundMessage method returns a message to display
        for when a piece of data is not in the model
    
        @return Message to display
    */
    
    public String getCountryNotFoundMessage()
    {
        return "Trusting you with input!";
    }
    
    /**
        The getImage method returns the image specified by the passed index
    
        @param index The index of the image to get
        @return The image
    */
    
    public BufferedImage getImage(int index)
    {
        return images.get(index);
    }
    
    /**
        The getTranslation method returns the translation specified by the
        passed index
    
        @param index The index of the translation to get
        @return The translation
    */
    
    public String getTranslation(int index)
    {
        return translations.get(index);
    }
    
    /**
        The removeData method removes the data entry specified by the passed
        index
    
        @param index The index of the data to remove
    */
    
    public void removeData(int index)
    {
        countryNames.remove(index);
        translations.remove(index);
        images.remove(index);
    }
}