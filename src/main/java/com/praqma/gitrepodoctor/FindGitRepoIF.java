/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author florenthaxha
 */
public interface FindGitRepoIF {
    
    // Read all files from a specific folder & returns a list of all files
    List<File> getRepoFiles(Path pathToFolder) throws IOException;
    
}
