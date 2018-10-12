package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.FileInformation;
import com.praqma.gitrepodoctor.RepositoryInformation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author florenthaxha
 */
public class FindGitRepoTest {
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    public FindGitRepoTest() {
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

    @Test
    public void FilterGit() throws IOException{
        FileInformation fi = new FileInformation(".git", "/users/something/project/.git", "", "", 200L);
        List<FileInformation> testList;
        
        testList = RepositoryInformation.build(System.getProperty("user.dir")).getFiles();
        assertFalse(testList.contains(fi));
    }
}
