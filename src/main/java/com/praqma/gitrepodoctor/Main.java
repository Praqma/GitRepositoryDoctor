/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.util.List;

/**
 *
 * @author florenthaxha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // this only works on my machine
        String pathtoRepository = "/Users/florenthaxha/School/Databases/gutenberg-backend";
        
        // CreateStatistics cs = new CreateStatistics();
        
        // String json = RepositoryInformation.build("/Users/florenthaxha/Work/GitRepoDoctor").toJson();
        // System.out.println(json);

        //RepositoryInformation ri = RepositoryInformation.build(pathtoRepository);
        //ri.getFiles().forEach(FileInformation -> System.out.println(FileInformation.getFileSize()));
        
        // cs.createAndSaveReport("/Users/florenthaxha/Work/GitRepoDoctor", "/Users/florenthaxha/School");
        // RepositoryInformation rii = new RepositoryInformation().fromJson(json);
        // System.out.println(rii);
        
        //GitObjectInformation goi = GitObjectInformation.build("/Users/florenthaxha/School/Databases/gutenberg-backend");
        //goi.getGitObjects().forEach(GitObject -> System.out.println(GitObject.toString()));
        
        //assert args.length == 1;
        //assert Files.exists(Paths.get(args[0]));
        //System.out.println(RepositoryInformation.build(args[0]).toJson());
    }

}
