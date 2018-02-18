package javaapplication1;
import java.io.IOException;
import java.util.List;
 
public class JavaApplication1 {
    static Finder       finder      = new Finder();
    static ConsoleUI    consoleUI   = new ConsoleUI();
    static Output       output      = new Output();
                                           
    public static void main(String[] args) {
        consoleUI.typeHelp();
        finder.populateCorrectFolders();
        consoleUI.animateFoldersScan();
        List<FileInfo> fileInfo = finder.FindAllFiles(finder.GetFolders());
        consoleUI.stopAnimateFoldersScan();
        output.writeFilesInfoToFile(fileInfo);
        consoleUI.GetCommand();
    }
}