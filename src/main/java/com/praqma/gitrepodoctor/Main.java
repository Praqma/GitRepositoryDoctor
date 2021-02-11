/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import com.praqma.gitrepodoctor.Entity.GitObjectInformation;
import com.praqma.gitrepodoctor.Entity.RepositoryInformation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author florenthaxha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // this only works on my machine
        String pathToGitRepository = "/Users/florenthaxha/Work/code-utils/.git";
        String pathToRepository = "/Users/florenthaxha/Work/code-utils";
        //CreateStatistics cs = new CreateStatistics();
        
        //String json = RepositoryInformation.build("/Users/florenthaxha/Work/newTestRepo").toJson();
        // System.out.println(json);

        RepositoryInformation ri = RepositoryInformation.build(pathToRepository);
        //ri.getFiles().forEach(FileInformation -> System.out.println(FileInformation.getFileSize()));
        System.out.println(ri.toJson());
        // cs.createAndSaveReport("/Users/florenthaxha/Work/GitRepoDoctor", "/Users/florenthaxha/School");
        //RepositoryInformation rii = new RepositoryInformation().fromJson(json);
        //System.out.println(rii);
        FileUtils fu = new FileUtils();
        System.out.println(fu.gitRepacker(pathToGitRepository));
        GitObjectInformation goi = GitObjectInformation.build(pathToGitRepository);
        //goi.getGitObjects().forEach(GitObject -> System.out.println(GitObject.toString()));
        System.out.println(goi.toJson());
        System.out.println("length = " + goi.getGitObjects().size());
        Path directory = Paths.get(pathToRepository);
        System.out.println("-----------------------------------------");
        Files.walk(directory).filter(Files::isDirectory).forEach(System.out::println);
        //assert args.length == 1;
        //assert Files.exists(Paths.get(args[0]));
        //System.out.println(RepositoryInformation.build(args[0]).toJson());
    }
    
}
