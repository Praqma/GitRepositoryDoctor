/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final Gson gson = new Gson();

    /**
     *
     * @param pathToFolder
     * @return List of FileInformation Objects, See FileInformation doc for more
     * info on values
     */
    @Override
    public List<FileInformation> createReportFiles(String pathToFolder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param pathToFolder 
     * @return JSON String of FileInformation Objects
     */
    @Override
    public String createReportJSON(String pathToFolder) {
        Path path = Paths.get(pathToFolder);
        Long repoSize = 0L;
        List<FileInformation> fileInfos = new ArrayList<>();
        List<File> files;
        try {
            files = fgr.getRepoFiles(path);
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                final Long fsize = file.getTotalSpace();
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
        
        return gson.toJson(new RepositoryInformation(path.getFileName().toString(), pathToFolder, repoSize, fileInfos));
    }

    /**
     *
     * @param files
     * @param targetPWD Saves all files at the location given as targetPWD
     */
    @Override
    public void saveReportFiles(List<File> files, String targetPWD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
