/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florenthaxha
 */
public class CreateReport implements CreateReportIF {
    /**
     *
     * @param json JSON String made using createReportJson()
     * @param targetPath File named repository_verdict_{UnixTimeStamp}.txt will be created under the targetPWD
     * 
     */
    @Override
    public void saveJSONReport(String json, String targetPath) {
        // we add unix timestamp to make sure that we don't overwrite older reports
        Path fileToCreate = Paths.get(targetPath).resolve("repository_verdict_" + Instant.now().getEpochSecond() + ".txt");
        try {
            Files.createFile(fileToCreate);
            try (BufferedWriter writer = Files.newBufferedWriter(fileToCreate, Charset.defaultCharset())) {
                writer.append(" ");
                writer.append(json);
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createAndSaveReport(String pathtoRepository, String targetPath){
        saveJSONReport(RepositoryInformation.build(pathtoRepository).toJson(), targetPath);
    }
    
}
