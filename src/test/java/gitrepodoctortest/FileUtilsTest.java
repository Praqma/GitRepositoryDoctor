package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.FileUtils;
import com.praqma.gitrepodoctor.Filetypes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author florenthaxha
 */
public class FileUtilsTest {
    
    FileUtils fu = new FileUtils();
    
    File a, b, c, d, tf;
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
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
        
        a = folder.newFile("filea.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(a, true));
        bw.append("This is a text file with text and i should be caught as a ascii file");
        b = folder.newFile("fileb.img");
        
        Files.copy(Paths.get("src/test/resources/test.img"), Paths.get(folder.getRoot().getAbsolutePath()+ "/test.img"), StandardCopyOption.REPLACE_EXISTING);
        c = new File(folder.getRoot().getAbsolutePath()+ "/test.img");
        d = folder.newFile("filee.java");
    }
    
    @After
    public void tearDown() throws IOException {
        folder.delete();
        
    }

    @Test
    @DisplayName("Test that Files are catagolized correctly by Git")
    public void testForGitBinary() throws IOException {
        
        Runtime.getRuntime().exec("git init "+ folder.getRoot().getCanonicalPath());

        assertTrue(fu.isGitBinary(a).equals(Filetypes.GIT_ASCII.toString()));
        assertTrue(fu.isGitBinary(c).equals(Filetypes.GIT_BINARY.toString()));
        assertTrue(fu.isGitBinary(b).equals(Filetypes.GIT_ASCII.toString()));
    }
    
    @Test
    @DisplayName("Test that Files are catagolized correctly")
    public void testForFileBinary() throws IOException{
        
        Runtime.getRuntime().exec("git init "+ folder.getRoot().getCanonicalPath());
        
        assertTrue(fu.isFileBinary(a).equals(Filetypes.FILE_ASCII.toString()));
        assertTrue(fu.isFileBinary(c).equals(Filetypes.FILE_BINARY.toString()));
    }
}
