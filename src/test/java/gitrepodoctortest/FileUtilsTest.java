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
import java.io.PrintWriter;
import java.util.List;
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
        bw.append(" ");
        bw.append(" ehwiehwaojr wjoeqkowek yeeet");
        b = folder.newFile("fileb.tar");
        c = folder.newFile("filec.png");
        d = folder.newFile("filee.java");
        // creates a 1mb binary file used for testing purpose. 
        Runtime.getRuntime().exec("dd if=/dev/zero of=test.img bs=1024 count=0 seek=1024");
    }
    
    @After
    public void tearDown() {
        folder.delete();
    }

    @Test
    @DisplayName("Test that Git Files are catagolized correctly")
    public void testForGitBinary() throws IOException {
        
        tf = new File(folder.getRoot().getCanonicalPath()+ "/test.img");
        Runtime.getRuntime().exec("git init "+ folder.getRoot().getCanonicalPath());
        
        assertTrue(fu.isGitBinary(tf).equals(Filetypes.GIT_BINARY.toString()));
        assertTrue(fu.isGitBinary(a).equals(Filetypes.GIT_ASCII.toString()));
    }
    
    @Test
    @DisplayName("Test that Files are catagolized correctly")
    public void testForFileBinary() throws IOException{
        
        tf = new File(folder.getRoot().getCanonicalPath()+ "/test.img");
        Runtime.getRuntime().exec("git init "+ folder.getRoot().getCanonicalPath());
        System.out.println(fu.isFileBinary(new File("/Users/florenthaxha/School/deletemee/meascii.txt")));
        //assertTrue(fu.isFileBinary(a).equals(Filetypes.FILE_ASCII.toString()));
        
    }
}
