/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor.Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author florenthaxha
 */
public class GitObjectInformation {

    private String repositoryPath;
    private List<GitObject> gitObjects;

    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public GitObjectInformation(String repositoryPath, List<GitObject> gitObjects) {
        this.repositoryPath = repositoryPath;
        this.gitObjects = gitObjects;
    }

    public static GitObjectInformation build(String repoPath) throws IOException {
        String idxPath = getIDXPath(repoPath);
        BufferedReader reader = null;
        List<GitObject> gitObjInfos = new ArrayList<>();
        HashMap<String, String> hmap = getAllFileShas(repoPath);
        Long dirSize = getDirectorySize(repoPath);
        System.out.println(dirSize+"bytes");
        if (idxPath != null) {
            try {
                Process exec = Runtime.getRuntime().exec("git verify-pack -v " + idxPath);
                reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] strs = line.split(" ");
                    if (strs[1].equals("blob")) {
                        Long fileSize = Long.parseLong(strs[4]);    
                        GitObject goi = new GitObject(strs[0], fileSize, calcPercentage(fileSize, dirSize) , Long.parseLong(strs[5]), hmap.get(strs[0]));
                        gitObjInfos.add(goi);
                    }

                }
                Collections.sort(gitObjInfos);
                exec.waitFor();
            } catch (IOException | InterruptedException e) {

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Logger.getLogger(GitObject.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
            return new GitObjectInformation(repoPath, gitObjInfos);
        }
        return null;
    }
    
    private static HashMap<String, String> getAllFileShas(String repoPath){
        HashMap<String, String> hmap = new HashMap<>();
        BufferedReader reader = null;
        try {
            Process exec = Runtime.getRuntime().exec("git --git-dir="+ repoPath +" rev-list --objects --all");
            reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                String[] strs = line.split(" ");
                if(strs.length == 2){
                    hmap.put(strs[0], strs[1]);
                }
            }
            exec.waitFor();
        } catch (IOException | InterruptedException e) {
            Logger.getLogger(GitObject.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(GitObjectInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(hmap.toString());
        return hmap;
    }

    private static String getIDXPath(String repoPath) throws NoSuchElementException {

        Optional<Path> idxPath = null;
        Path path = Paths.get(repoPath);
        try (Stream<Path> fileStream = Files.find(path, 999, (p, bfa) -> bfa.isRegularFile())) {
            idxPath = fileStream
                    .filter(file -> file.toString().matches("^.*\\.idx$")).findFirst();
        } catch (IOException | NoSuchElementException e) {
            Logger.getLogger(GitObject.class.getName()).log(Level.SEVERE, null, e);
        }

        return idxPath.get().toString();
    }
    
    private static Double calcPercentage(long fileSize, long dirSize){
        return (double)fileSize / dirSize * 100;
    }
    
    private static Long getDirectorySize(String dirpath) throws IOException{
        Path directory = Paths.get(dirpath);
        long size = Files.walk(directory)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        return size;
    }

    public String toJson() {
        return GSON.toJson(this.gitObjects);
    }
    
    public String toHtmlTable(){
        String htmlTable = "";
        htmlTable +="<table class='pure-table'>";
        htmlTable +="<thead><tr>"
                + "<th>Sha</th>"
                + "<th>File Size</th>"
                + "<th>Size in Packfile</th>"
                + "<th>File Name</th>"
                + "<th>Percentage Size</th>"
                + "</tr></thead>";
        htmlTable += this.gitObjects.stream().map((go) -> "<tr>"
                + "<td>" + go.getSha() + "</td>"
                        + "<td>" + go.getSizeByte() + "</td>"
                                + "<td>" + go.getSizeInPackfile() + "</td>"
                                        + "<td>" + go.getFileName() + "</td>"
                                                + "<td>" + go.getPercentageSize() + "</td>"
                                                        + "</tr>").reduce(htmlTable, String::concat);
        return htmlTable;
    }

    public GitObjectInformation fromJson(String json) {
        return GSON.fromJson(json, this.getClass());
    }

    public List<GitObject> getGitObjects() {
        return gitObjects;
    }

    public void setGitObjects(List<GitObject> gitObjects) {
        this.gitObjects = gitObjects;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }
    
}
