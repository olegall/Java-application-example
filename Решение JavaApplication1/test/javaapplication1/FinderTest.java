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

public class FinderTest {
    
    public FinderTest() {
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

    Finder finder = new Finder();    

    @Test       
    public void testFindAllFiles() {
        List<String> folders = new ArrayList<String>();
        folders.add(Output.CURRENT_PATH + "\\Каталог1");
        folders.add(Output.CURRENT_PATH + "\\foo");
        folders.add(Output.CURRENT_PATH + "\\!@#$%^&*~`+=|,./_-");
        
        List<FileInfo> filesInfo = finder.FindAllFiles(folders);
        assertTrue(filesInfo.size() > 0);
        assertNotNull(filesInfo);
    }

    @Test
    public void testRemoveFolder() {
        String folder = "C:/Windows/Fonts";
        List<String> folders = new ArrayList<String>();
        folders.add(folder);
        assertNotNull(folder);
        int foldersSizeAfterRemoval = finder.removeFolder(folder, folders).size();
        assertEquals(0, foldersSizeAfterRemoval);
    }
 
    @Test
    public void testGetFolderWithoutQuotes(){
        String folder = "\"C:/Windows/Fonts\"";
        String expectedFolder = "C:/Windows/Fonts";
        assertEquals(expectedFolder, finder.getFolderWithoutQuotes(folder));
        assertNotNull(finder.getFolderWithoutQuotes(folder));
    }
}