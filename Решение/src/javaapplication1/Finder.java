package javaapplication1;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Finder {
    private final String[]      userFolders  = {Output.CURRENT_PATH + "\\Каталог1",
                                                "foo1",
                                                "foo2",
                                                "foo3",
                                                "C:/Windows/Fonts"};
    private static List<String> folders         = new ArrayList<String>();
    private int                 foldersCnt      = 0;    

    public List<String> GetFolders() {
        return folders;
    }
    
    public void populateCorrectFolders() {
        ConsoleUI consoleUI = new ConsoleUI();
        for (String folder : userFolders) {
            File filesAndOrSubfolders = new File(folder);
            if (filesAndOrSubfolders.listFiles() != null){
                folders.add(folder);
            } else {
                consoleUI.type("Folder " + folder + " doesn't exist. Please correct it" + ConsoleUI.EOF);
            }
        }
        consoleUI.type(ConsoleUI.EOF);
    }
   
    private List<FileInfo> fileInfo = new ArrayList<FileInfo>();

    public List<FileInfo> FindAllFiles(List<String> folders){
        Thread[] threads = new Thread[folders.size()];
        for (foldersCnt = 0; foldersCnt < folders.size(); foldersCnt++) {
            threads[foldersCnt] = new Thread(new Runnable()
            {
                public void run()
                {                            
                    FindFilesAndSubfoldersInFolder(folders.get(foldersCnt));
                }
            });
            threads[foldersCnt].start();
            try {
                threads[foldersCnt].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Finder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileInfo;
    }

    private void FindFilesAndSubfoldersInFolder(String folder) {
        File filesAndOrSubfolders = new File(folder);
        if (filesAndOrSubfolders.listFiles() != null) {
            for (File fileOrSubfolder : filesAndOrSubfolders.listFiles()) {   
                if (fileOrSubfolder.isFile()) {
                    File file = new File(fileOrSubfolder.getPath());
                    String filePath = fileOrSubfolder.getPath();
                    String fileDate = String.valueOf(Output.DATE_FORMAT.format(file.lastModified()));
                    int fileSize = (int)file.length();
                    fileInfo.add(new FileInfo(filePath, fileDate, fileSize));
                } else if (fileOrSubfolder.isDirectory()) {
                    FindFilesAndSubfoldersInFolder(fileOrSubfolder.getPath());
                }
            }
        } 
    }

    public List<String> removeFolder(String folder, List<String> folders){
        ConsoleUI consoleUI = new ConsoleUI();
        if (folders.remove(folder)){
            consoleUI.type("Folder has been removed successfully!" + ConsoleUI.EOF);
        } else {
            consoleUI.type("Folder "+ folder +" has been already removed or there is no such folder" + ConsoleUI.EOF);
        }
        return folders;
    }
    
    public String getFolderWithoutQuotes(String folder){
        String folderWithoutFirstQuote = folder.substring(1);
        String folderWithoutQuotes = folderWithoutFirstQuote.substring(0, folderWithoutFirstQuote.length() - 1);
        return folderWithoutQuotes;
    }
}