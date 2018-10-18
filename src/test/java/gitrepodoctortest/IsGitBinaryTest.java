/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gitrepodoctortest;

import com.praqma.gitrepodoctor.FileUtils;
import com.praqma.gitrepodoctor.Filetypes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author florenthaxha
 */
@RunWith(value = Parameterized.class)
public class IsGitBinaryTest {
    private final FileUtils fu = new FileUtils();
    private File actualFile;
    private final String testFile;
    private final String filename;
    private final String expected;
    
    private final String userdir = System.getProperty("user.dir");
    
    public IsGitBinaryTest(String testFile, String filename , String expected) {
        this.testFile = testFile;
        this.filename = filename;
        this.expected = expected;
    }
    
    @Parameterized.Parameters(name = "{index}: File type recognized by git({1} == {2})")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {"src/test/resources/test.img" , "/test.img" ,Filetypes.GIT_BINARY.toString()},
            {"src/test/resources/textfile.txt", "/textfile.txt" , Filetypes.GIT_ASCII.toString()},
            {"src/test/resources/noMimetype", "/noMimeType", Filetypes.GIT_ASCII.toString()},
            {"src/test/resources/noMimeTypeBinaryContent", "/noMimeTypeBinaryContent", Filetypes.GIT_BINARY.toString()},
            {"src/test/resources/textfile.pdf", "/textfile.pdf", Filetypes.GIT_ASCII.toString()}
        });
    }
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        Files.copy(Paths.get(testFile), Paths.get(userdir + filename), StandardCopyOption.REPLACE_EXISTING);
    }
    
    @After
    public void tearDown() throws IOException {
        Files.delete(Paths.get(actualFile.getAbsolutePath()));
    }

    @Test
    public void isFileGitBinary(){
        actualFile = new File(userdir + filename);
        assertEquals(expected, fu.isGitBinary(actualFile));
    }
}
