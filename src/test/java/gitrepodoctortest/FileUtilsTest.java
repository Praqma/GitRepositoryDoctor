package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.FileUtils;
import com.praqma.gitrepodoctor.Filetypes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author florenthaxha
 */
public class FileUtilsTest {
    
    FileUtils fu = new FileUtils();
    
    File textFile, noMimetype, binaryFile;
    String userdir = System.getProperty("user.dir");
    
    public FileUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        
        Files.copy(Paths.get("src/test/resources/test.img"), Paths.get(userdir + "/test.img"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/resources/textfile.txt"), Paths.get(userdir + "/textfile.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/resources/noMimetype"), Paths.get(userdir + "/noMimetype"), StandardCopyOption.REPLACE_EXISTING);
        textFile = new File(userdir + "/textfile.txt");
        noMimetype = new File(userdir + "/noMimetype");
        binaryFile = new File(userdir + "/test.img");
        
    }
    
    @After
    public void tearDown() throws IOException {
        
        Files.delete(Paths.get(textFile.getAbsolutePath()));
        Files.delete(Paths.get(noMimetype.getAbsolutePath()));
        Files.delete(Paths.get(binaryFile.getAbsolutePath()));
    }

    @Test
    @DisplayName("Test that Files are catagolized correctly by Git")
    public void testForGitBinary() throws IOException {
        
        assertTrue(fu.isGitBinary(textFile).equals(Filetypes.GIT_ASCII.toString()));
        assertTrue(fu.isGitBinary(binaryFile).equals(Filetypes.GIT_BINARY.toString()));
        assertTrue(fu.isGitBinary(noMimetype).equals(Filetypes.GIT_ASCII.toString()));
    }
    
    @Test
    @DisplayName("Test that Files are catagolized correctly")
    public void testForFileBinary() throws IOException{
        
        assertTrue(fu.isFileBinary(textFile).equals(Filetypes.FILE_ASCII.toString()));
        assertTrue(fu.isFileBinary(binaryFile).equals(Filetypes.FILE_BINARY.toString()));
        assertTrue(fu.isFileBinary(noMimetype).equals(Filetypes.FILE_EMPTY.toString()));
    }
}
