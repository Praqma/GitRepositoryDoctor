/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor.Entity;

import com.praqma.gitrepodoctor.Entity.GitObject;
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
import java.util.List;
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

    public static GitObjectInformation build(String repoPath) {
        String idxPath = getIDXPath(repoPath);
        BufferedReader reader = null;
        List<GitObject> gitObjInfos = new ArrayList<>();
        try {
            Process exec = Runtime.getRuntime().exec("git verify-pack -v " + idxPath);
            exec.waitFor();
            reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strs = line.split(" ");
                if (strs[1].equals("blob")) {
                    GitObject goi = new GitObject(strs[0], Long.parseLong(strs[4]), Long.parseLong(strs[5]));
                    gitObjInfos.add(goi);
                }

            }
            Collections.sort(gitObjInfos);
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

    private static String getIDXPath(String repoPath) {

        Optional<Path> idxPath = null;
        Path path = Paths.get(repoPath);
        try (Stream<Path> fileStream = Files.find(path, 999, (p, bfa) -> bfa.isRegularFile())) {
            idxPath = fileStream
                    .filter(file -> file.toString().matches("^.*\\.idx$")).findFirst();
        } catch (IOException e) {
            Logger.getLogger(GitObject.class.getName()).log(Level.SEVERE, null, e);
        }

        return idxPath.get().toString();
    }

    public String toJson() {
        return GSON.toJson(this);
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
