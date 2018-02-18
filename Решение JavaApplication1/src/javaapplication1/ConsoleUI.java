/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;

public class ConsoleUI {
    public final static String  EOF                     = System.getProperty("line.separator");
    private int                 animationCounter        = 1; 
    private Timer               timer                   = new Timer();
    private final int           DOT_TYPE_INTERVAL_MS    = 1000;
    private final int           STICK_TYPE_INTERVAL_MS  = 6000;
    private final String        DELIMITER               = " ";
    
    public void GetCommand() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        type("Enter command: ");
        String command = null;
        try {
            command = reader.readLine();
            parseCommand(command);
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
        GetCommand();
    }
    
    private void parseCommand(String command) throws IOException{
        Finder finder = new Finder();
        char key = command.charAt(0);
        switch (key) {
            case '-':   String folder = command.split(DELIMITER)[1];
                        String folderWithoutExtraQuotes = finder.getFolderWithoutQuotes(folder);
                        List<String> folders = finder.GetFolders();
                        List<String> foldersAfterRemoval = finder.removeFolder(folderWithoutExtraQuotes, finder.GetFolders());
                        Collections.copy(folders, foldersAfterRemoval);
                        break;
            // расширение набора ключей
            default:    type("You forgot type a key or it is incorrect. Please try again" + EOF);
                        break;
        }
    }

    public void animateFoldersScan() {
        type("Folders scan has started" + EOF);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if ( (animationCounter >= STICK_TYPE_INTERVAL_MS/DOT_TYPE_INTERVAL_MS) && 
                     (DOT_TYPE_INTERVAL_MS * animationCounter % STICK_TYPE_INTERVAL_MS == 0) ) {
                    type("|");
                } else {
                    type(".");
                }
                animationCounter++;                 
            }
        }, 0, DOT_TYPE_INTERVAL_MS);
    };
    
    public void stopAnimateFoldersScan() {    
        timer.cancel();
        type(EOF + "Folders scan has finished" + EOF);
    }
    
    public void typeHelp(){
        type("Available commands: " + EOF +
            "- \"folder\" - remove folder" + EOF +
            "If use parameter delimit one and command by blank character:"+ EOF + EOF);
    }
    
    public void type(String message){
        System.out.print(message);
    }
}