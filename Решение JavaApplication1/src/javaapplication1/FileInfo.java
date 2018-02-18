package javaapplication1;

public class FileInfo implements Comparable<FileInfo>{
    public String   path; 
    public String   date; 
    public int      size;
    
    public FileInfo(String path, String date, int size){
        if (path == null || date == null) {
            throw new NullPointerException();
        }
        this.path = path;
        this.date = date;
        this.size = size;
    }
    
    public FileInfo(){
    }
    
    public int compareTo(FileInfo fileInfo) {
        int lastCmp = path.compareTo(fileInfo.path);
        return (lastCmp != 0 ? lastCmp : path.compareTo(fileInfo.path));
    }
}