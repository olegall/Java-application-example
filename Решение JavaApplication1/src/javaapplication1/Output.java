package javaapplication1;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.file.Paths;

public class Output {
    public  static final SimpleDateFormat   DATE_FORMAT     = new SimpleDateFormat("yyyy.MM.dd");
    public  static final String             CURRENT_PATH    = Paths.get(".").toAbsolutePath().normalize().toString();
    private final String                    SIZE_MODIFIER   =  "][";
    private final String                    START_SYMBOL    =  "[";
    private final String                    END_SYMBOL      =  "]";
    private final String                    FILE_NAME       = "result.txt";
    private final String                    ENCODING        = "UTF-8";
    private final String                    PATH_TO_FILE    = CURRENT_PATH + "\\" + FILE_NAME;

    public List<FileInfo> GetSortedByPathFilesInfo(List<FileInfo> filesInfo){ 
        java.util.Collections.sort(filesInfo);        
        return filesInfo;
    }
    
    public boolean writeFilesInfoToFile(List<FileInfo> filesInfo) {
        OutputStreamWriter outputStreamWriter;
        File file = new File(PATH_TO_FILE);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_FILE);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, ENCODING);   
            List<FileInfo> sortedFilesInfo = GetSortedByPathFilesInfo(filesInfo);
            outputStreamWriter.write(START_SYMBOL + ConsoleUI.EOF);
            String size = "";
            for (int i = 0; i < sortedFilesInfo.size(); i++) {
                size = "size = " + sortedFilesInfo.get(i).size + SIZE_MODIFIER + ConsoleUI.EOF;
                if (i == sortedFilesInfo.size()-1){
                    size = "size = " + sortedFilesInfo.get(i).size + END_SYMBOL;
                }
                outputStreamWriter.write("file = " + sortedFilesInfo.get(i).path + ConsoleUI.EOF +
                                         "date = " + sortedFilesInfo.get(i).date + ConsoleUI.EOF +
                                         size);
            }
            outputStreamWriter.close();
            if (file.length() > 0){
                return true;
            }
        } catch(Exception ex) {
            Logger.getLogger(Finder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}