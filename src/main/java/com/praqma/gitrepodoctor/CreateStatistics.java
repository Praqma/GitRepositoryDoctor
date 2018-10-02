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
    public Long getRepoSize(List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<File> createReportFiles(List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String createReportJSON(List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveReportFiles(List<File> files, String targetPWD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
