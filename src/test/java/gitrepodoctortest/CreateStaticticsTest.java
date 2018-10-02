package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.CreateStatistics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author florenthaxha
 */
public class CreateStaticticsTest {
    public ArrayList<File> files = new ArrayList<>();
    public CreateStatistics cs = new CreateStatistics();
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    public CreateStaticticsTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() { 
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUpData() throws IOException {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    @DisplayName("Collected size of all files in KB")
    public void getRepoSizetest() throws IOException{
        File a = folder.newFile("filea.txt");
        File b = folder.newFile("fileb.txt");
        File c = folder.newFile("filec.png");
        File d = folder.newFile("filed.jpg");
        File e = folder.newFile("filee.java");
        files.add(a);
        files.add(b);
        files.add(c);
        files.add(d);
        files.add(e);
        
        assertTrue(cs.getRepoSize(files) == 1224562680);
        assertTrue(files.size() == 5);
    }
    
}
