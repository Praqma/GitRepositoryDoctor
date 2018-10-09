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
public interface CreateStatisticsIF {
    
    // Takes a List of Files and uses it's data to create report files
    List<FileInformation> createReportFiles(String pathToFolder);
    
    // Takes a List of Files and uses it's data to create a report as JSON
    String createReportJSON(String pathToFolder);
    
    // Takes a List of report Files and a Path String and saves them in that location, If no target path is given the currentDIR is used.
    void saveReportFiles(List<File> files, String targetPWD);
    
}
