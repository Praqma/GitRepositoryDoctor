/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florenthaxha
 */
public class CreateStatistics implements CreateStatisticsIF {

    private final FileUtils fu = new FileUtils();
    private final FindGitRepo fgr = new FindGitRepo();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     *
     * @param pathtoRepository this is the Repository you wish to create a report over
     * @return JSON String of FileInformation Objects
     */
    @Override
    public String createReportJSON(String pathtoRepository) {
        Path path = Paths.get(pathtoRepository);
        Long repoSize = 0L;
        List<FileInformation> fileInfos = new ArrayList<>();
        List<File> files;
        try {
            files = fgr.getRepoFiles(path);
            for (File file : files) {
                Long fsize = file.length();
                FileInformation fi = new FileInformation(
                        file.getName(),
                        file.getAbsolutePath(),
                        fu.isFileBinary(file),
                        fu.isGitBinary(file),
                        fsize);
                fileInfos.add(fi);
                repoSize += fsize;
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(new RepositoryInformation(
                            path.getFileName().toString(), 
                            pathtoRepository, 
                            repoSize, 
                            fileInfos));
    }

    /**
     *
     * @param Json JSON String made using createReportJson()
     * @param targetPWD File named repository_verdict_{UnixTimeStamp}.txt will be created under the targetPWD
     * 
     */
    @Override
    public void saveJSONReport(String Json, String targetPWD) {
        // we add unix timestamp to make sure that we don't overwrite older reports
        Path fileToCreate = Paths.get(targetPWD).resolve("repository_verdict_" + Instant.now().getEpochSecond() + ".txt");
        try {
            Files.createFile(fileToCreate);
            BufferedWriter writer = Files.newBufferedWriter(fileToCreate, Charset.defaultCharset());
            writer.append(" ");
            writer.append(Json);
            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param pathtoRepository this is the Repository you wish to create a report over
     * @param targetPWD this is the place you want to save your report
     */
    @Override
    public void createAndSaveReport(String pathtoRepository, String targetPWD) {
        saveJSONReport(createReportJSON(pathtoRepository), targetPWD);
    }
    
}
