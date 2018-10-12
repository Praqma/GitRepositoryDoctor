/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;


/**
 *
 * @author florenthaxha
 */
public interface CreateStatisticsIF {
    
    // Takes a List of Files and uses it's data to create a report as JSON
    String createReportJSON(String pathtoRepository);
    
    // Takes a JSON String and a Path String and saves them in that location, If no target path is given the currentDIR is used.
    void saveJSONReport(String Json, String targetPWD);
    
    void createAndSaveReport(String pathtoRepository, String targetPWD);
}
