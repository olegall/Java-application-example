/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutputTest {
    
    public OutputTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }    
    
    Output output = new Output();    
    
    @Test
    public void testGetSortedByPathFilesInfo() {

        List<FileInfo> filesInfo = new ArrayList<FileInfo>();
        filesInfo.add        (new FileInfo("Каталог2/info.txt", "", 0));
        filesInfo.add        (new FileInfo("Каталог1/picture.jpg", "", 0));
        filesInfo.add        (new FileInfo("Каталог3/music.mp3", "", 0));
        filesInfo.add        (new FileInfo("Каталог4/Подкаталог3/info.txt", "", 0));
        filesInfo.add        (new FileInfo("Каталог4/Подкаталог2/picture.jpg", "", 0));
        filesInfo.add        (new FileInfo("Каталог4/Подкаталог1/music.mp3", "", 0));
        filesInfo.add        (new FileInfo("Каталог5", "", 0));
        filesInfo.add        (new FileInfo("Folder5", "", 0));        
                
        List<FileInfo> expectedFilesInfo = new ArrayList<FileInfo>();
        expectedFilesInfo.add(new FileInfo("Folder5", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог1/picture.jpg", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог2/info.txt", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог3/music.mp3", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог4/Подкаталог1/music.mp3", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог4/Подкаталог2/picture.jpg", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог4/Подкаталог3/info.txt", "", 0));
        expectedFilesInfo.add(new FileInfo("Каталог5", "", 0));
        
        List<FileInfo> sortedByPathFilesInfo = output.GetSortedByPathFilesInfo(filesInfo);
        
        for (int i = 0; i < filesInfo.size(); i++){
            assertEquals(expectedFilesInfo.get(i).path, sortedByPathFilesInfo.get(i).path);
        }
        assertNotNull(filesInfo);
    }
    
    @Test
    public void testWriteFilesInfoToFile(){
        
        List<FileInfo> filesInfo = new ArrayList<FileInfo>();
        filesInfo.add(new FileInfo("Каталог2/info.txt", "", 0));
        filesInfo.add(new FileInfo("Каталог1/picture.jpg", "", 0));
        filesInfo.add(new FileInfo("Каталог3/music.mp3", "", 0));
        filesInfo.add(new FileInfo("Каталог4/Подкаталог3/info.txt", "", 0));
        filesInfo.add(new FileInfo("Каталог4/Подкаталог2/picture.jpg", "", 0));
        filesInfo.add(new FileInfo("Каталог4/Подкаталог1/music.mp3", "", 0));
        
        assertTrue(output.writeFilesInfoToFile(filesInfo));
        assertNotNull(filesInfo);
    }
}