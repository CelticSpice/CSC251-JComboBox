/**
    This is the GUI of the JComboBoxDemonstration program
    11/7/2016
    CSC 251 0001 - JComboBox Demonstration
    @author James Alves, Shane McCann, Timothy Burns
*/

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame
{
    // Fields
    private JButton addElementButton, deleteElementButton, exitButton;
    private JComboBox box;
    private JLabel translationLabel, imageLabel;
    
    /**
        Constructor
    */
    
    public GUI()
    {
        setTitle("JComboBox Demonstration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createPanel());
        pack();
        setVisible(true);
    }
    
    /**
        The addElement method adds an element to the JComboBox
        
        @param element The element to add
    */
    
    public void addElement(String element)
    {
        box.addItem(element);
    }
    
    /**
        The createPanel method creates the main panel
    
        @return The newly created panel
    */
    
    private JPanel createPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Padding
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        JPanel boxPanel = createBoxPanel();
        panel.add(boxPanel, constraints);
        
        // Padding
        constraints.insets = new Insets(30, 0, 0, 0);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        JPanel translationPanel = createTranslationPanel();
        panel.add(translationPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        JPanel buttonPanel = createButtonPanel();
        panel.add(buttonPanel, constraints);
        
        return panel;
    }
    
    /**
        The createBoxPanel method creates the panel containing the JComboBox
    
        @return The newly created panel
    */
    
    private JPanel createBoxPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        panel.add(new JLabel("Select or enter a country name below:"));
        
        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        box = new JComboBox();
        box.setEditable(true);
        panel.add(box);
        
        return panel;
    }
    
    /**
        The createButtonPanel method creates the panel containing the buttons
       
        @return The newly created panel
    */
    
    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel();
        
        addElementButton = new JButton("Create Element");
        deleteElementButton = new JButton("Delete Element");
        exitButton = new JButton("Exit");
        
        panel.add(addElementButton);
        panel.add(deleteElementButton);
        panel.add(exitButton);
        
        return panel;
    }
    
    /**
        The createTranslationPanel method creates the panel for outputting the
        translation and image
    
        @return The newly created panel
    */
    
    private JPanel createTranslationPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        translationLabel = new JLabel();
        panel.add(translationLabel);
        
        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        imageLabel = new JLabel();
        panel.add(imageLabel);
        
        return panel;
    }
    
    /**
        The getSelection method returns the user's selection
    
        @return The user's selection
    */
    
    public String getSelection()
    {
        return (String) box.getSelectedItem();
    }
    
    /**
        The promptFailure method prompts the user of a failed operation
    */
    
    public void promptFailure()
    {
        JOptionPane.showMessageDialog(this, "Operation Failed");
    }
    
    /**
        The promptSuccess method prompts the user of a successful operation
    */
    
    public void promptSuccess()
    {
        JOptionPane.showMessageDialog(this, "Operation Successful");
    }
    
    /**
        The promptForCountryName method prompts the user to enter a country
        name
    
        @return The user's input
    */
    
    public String promptForCountryName()
    {
        return JOptionPane.showInputDialog(this, "Enter a country name:");
    }
    
    /**
        The promptForLink method prompts the user to enter a URL of an
        image online
        
        @return The user's input
    */
    
    public String promptForLink()
    {
        return JOptionPane.showInputDialog(this, "Enter a URL for an image " +
                                           "of the country's flag:");
    }
    
    /**
        The promptForTranslation method prompts the user to enter a translation
        of the phrase "Hello, World!"
    
        @return The user's input
    */
    
    public String promptForTranslation()
    {
        return JOptionPane.showInputDialog(this, "Enter a translation of " +
                                           "the phrase \"Hello, World!\":");
    }
    
    /**
        The promptGetImageFailure method prompts the user that the program
        failed to retrieve the image at a specified URL and asks if he
        would like to try again
    
        @return Whether the user would like to try again
    */
    
    public boolean promptGetImageFailure()
    {
        boolean tryAgain = false;
        
        int result = JOptionPane.showConfirmDialog(this, "Failed to get " +
                                 "image at the specified URL.\n" +
                                 "Would you like to try again?",
                                 "Failed to Get Image",
                                 JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION)
        {
            tryAgain = true;
        }
        
        return tryAgain;
    }
    
    /**
        The registerControllers method registers controllers with the
        JComboBox and the Buttons
    
        @param comboController The ComboBoxController to register
        @param buttonController The ButtonController to register
    */
    
    public void registerController(ComboBoxController comboController,
                                   ButtonController buttonController)
    {
        box.addActionListener(comboController);
        
        addElementButton.addActionListener(buttonController);
        deleteElementButton.addActionListener(buttonController);
        exitButton.addActionListener(buttonController);
    }
    
    /**
        The removeElement method removes the element at the specified index
        of the JComboBox
    
        @param index The index of the element to remove
    */
    
    public void removeElement(int index)
    {
        box.removeItemAt(index);
    }
    
    /**
        The removeImage method removes the image from the view
    */
    
    public void removeImage()
    {
        imageLabel.setIcon(null);
    }
    
    /**
        The setImage method accepts an image to display
    
        @param image The image to display
    */
    
    public void setImage(BufferedImage image)
    {
        imageLabel.setIcon(new ImageIcon(image));
        pack();
    }
    
    /**
        The setModel method sets the model of this view.
    
        @param m The model of the view
    */
    
    public void setModel(Model m)
    {
        box.setModel(new DefaultComboBoxModel<>(m.getCountryNames()));
    }
    
    /**
        The setTranslation method sets the text of the translation
    
        @param translation The translation
    */
    
    public void setTranslation(String translation)
    {
        translationLabel.setText(translation);
    }
}