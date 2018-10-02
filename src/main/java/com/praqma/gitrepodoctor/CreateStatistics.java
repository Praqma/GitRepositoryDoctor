/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.File;
import java.util.List;

/**
 *
 * @author florenthaxha
 */
public class CreateStatistics implements CreateStatisticsIF{

    /**
     *
     * @param files
     * @return Long representing Actual repository size in Kilobyte
     */
    @Override
    public long getRepoSize(List<File> files) {
        long RepoSize = 0;
        for (int i = 0; i < files.size(); i++) {
            RepoSize += files.get(i).getTotalSpace();
        }
        return RepoSize;
    }

    /**
     *
     * @param files
     * @return List of FileInformation Objects, See FileInformation doc for more info on values
     */
    @Override
    public List<FileInformation> createReportFiles(List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param files
     * @return JSON String of FileInformation Objects
     */
    @Override
    public String createReportJSON(List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param files
     * @param targetPWD
     * Saves all files at the location given as targetPWD
     */
    @Override
    public void saveReportFiles(List<File> files, String targetPWD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
