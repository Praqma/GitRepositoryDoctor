/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florenthaxha
 */
public class FileUtils implements FileUtilsIF {

    @Override
    public int isGitBinary(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int isFileBinary(File file) {
        try {
            Path path = file.toPath();
            if (Files.probeContentType(path) == null) {
                return 2;
            } else if (Files.probeContentType(path).contains("text")) {
                return 0;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 1;
    }

    @Override
    public double getFileSize(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
