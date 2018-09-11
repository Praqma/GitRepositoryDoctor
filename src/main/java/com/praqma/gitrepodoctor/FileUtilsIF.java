/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.File;

/**
 *
 * @author florenthaxha
 */
public interface FileUtilsIF {
      
    int isGitBinary(File file);
    
    int isFileBinary(File file);
    
    double getFileSize(File file);
    
}
