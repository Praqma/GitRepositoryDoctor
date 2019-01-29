package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.Entity.FileInformation;
import com.praqma.gitrepodoctor.Entity.RepositoryInformation;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author florenthaxha
 */
public class RepositoryInformationTest {
    
    public RepositoryInformationTest(){}
    
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
