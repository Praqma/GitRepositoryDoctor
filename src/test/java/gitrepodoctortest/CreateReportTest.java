package gitrepodoctortest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.praqma.gitrepodoctor.CreateReport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author florenthaxha
 */
public class CreateReportTest {
    public ArrayList<File> files = new ArrayList<>();
    public CreateReport cs = new CreateReport();
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    public CreateReportTest() {
        
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
    
}
