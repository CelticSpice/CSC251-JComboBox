/**
    This is the controller for the JComboBox
    11/2/2016
    CSC 251 0001 - JComboBox Demonstration
    @author James Alves, Shane McCann, Timothy Burns
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxController implements ActionListener
{
    // Fields
    private GUI view;
    private Model model;
    
    /**
        Constructor
        Accepts the view to act as controller for and the model
        
        @param v The GUI to act as controller for
        @param m The model
    */
    
    public ComboBoxController(GUI v, Model m)
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
        // Get user's selection
        String selection = view.getSelection();
        
        // Get index of selection in model
        int index = model.getCountryNameIndex(selection);
        
        // If model contains selection
        if (index != -1)
        {
            // Set translation
            view.setTranslation(model.getTranslation(index));
            
            // Set image
            view.setImage(model.getImage(index));
        }
        else
        {
            // Set message
            view.setTranslation(model.getCountryNotFoundMessage());
            
            // Set image
            view.setImage(model.getCountryNotFoundImage());
        }
    }
}