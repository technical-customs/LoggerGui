package display;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public final class LoggerDisplay{
    //COMMAND LINE VARIABLES
    private JFrame textMainFrame;
    private JPanel textMainPane;
    private JTextArea textArea;
    
    //GUI VARIABLES
    private String title;
    private JFrame mainFrame;
    private JPanel mainPane;
    
    private boolean textGui = true;
    public LoggerDisplay(){
        if(textGui){
            title = "Text Display";
            setupTextGUI();
        }else{
            title = "GUI Display";
            setupGUI();
        }
    }
    public LoggerDisplay(boolean useGUI){
        if(!useGUI){
            title = "Text Display";
            setupTextGUI();
        }else{
            title = "GUI Display";
            setupGUI();
        }
    }
    
    
    //COMMAND LINE TEXT AREA DISPLAY
    public void setupTextGUI(){
        textMainFrame = new JFrame();
        textMainFrame.setTitle(getTitle());
        textMainFrame.setSize(500,500);
        textMainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        textMainPane = new JPanel(new BorderLayout());
        textArea = new JTextArea(300,300);
        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setEditable(false);
        
        textMainPane.add(scroll);
        textMainFrame.add(textMainPane);
        
        textMainFrame.setVisible(true);
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String name){
        textMainFrame.setTitle(name);
    }
    public void clearDisplay(){
        textArea.setText(null);
    }
    public void writeToDisplay(String string){
        try{
            DefaultCaret caret = (DefaultCaret) textArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            textArea.append(string + "\n");
        }catch(NullPointerException ex){
            System.exit(0);
        }
    }
    public void addToDisplay(String string){
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.append(string);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
    }
    public int screenWidth(){
        return textArea.getWidth();
    }
    
    //GUI DISPLAY
    private void setupGUI(){
        mainFrame = new JFrame();
        mainFrame.setTitle(getTitle());
        mainFrame.setSize(500,500);
        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        
        
        
        
        mainPane = new JPanel(new BorderLayout());
        mainFrame.getContentPane().add(mainPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    public static void main(String[] args){
        LoggerDisplay ld = new LoggerDisplay();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoggerDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ld.writeToDisplay("Test");
    }
}