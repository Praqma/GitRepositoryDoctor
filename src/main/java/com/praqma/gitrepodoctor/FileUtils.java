/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.Tika;

/**
 *
 * @author florenthaxha
 */
public class FileUtils implements FileUtilsIF {

    @Override
    public String isGitBinary(File file) {
        StringBuilder output = new StringBuilder();
        String filename = file.getName();
        
        try {
            Process exec = Runtime.getRuntime().exec("git diff --no-index --numstat /dev/null " + filename);
            
            exec.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {                
                output.append(line);
            }
            if(output.toString().contains("-	-	/dev/null => " + filename)){ return Filetypes.GIT_BINARY.toString(); }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Filetypes.GIT_ASCII.toString();
    }

    @Override
    public String isFileBinary(File file) {
        Tika mimeDetecter = new Tika();
        try {
            String mimeType = mimeDetecter.detect(file);
            if (mimeType == null) {
                return Filetypes.FILE_EMPTY.toString();
            } else if (mimeType.contains("text")) {
                return Filetypes.FILE_ASCII.toString();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Filetypes.FILE_BINARY.toString();
    }

}
