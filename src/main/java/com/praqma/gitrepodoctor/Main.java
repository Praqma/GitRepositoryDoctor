/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author florenthaxha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // CreateStatistics cs = new CreateStatistics();
       // String json = RepositoryInformation.build("/Users/florenthaxha/Work/GitRepoDoctor").toJson();
       // System.out.println(json);
       // cs.createAndSaveReport("/Users/florenthaxha/Work/GitRepoDoctor", "/Users/florenthaxha/School");
       // RepositoryInformation rii = new RepositoryInformation().fromJson(json);
       // System.out.println(rii);
        
       assert args.length == 1;
       assert Files.exists(Paths.get(args[0]));
       System.out.println(RepositoryInformation.build(args[0]).toJson());
    }
    
}
