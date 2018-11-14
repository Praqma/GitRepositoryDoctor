/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author florenthaxha
 */
public class GitObjectInformation {

    private String sha;
    private String type;
    private Long sizeByte;

    public GitObjectInformation(String sha, String type, Long sizeByte) {
        this.sha = sha;
        this.type = type;
        this.sizeByte = sizeByte;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSizeByte() {
        return sizeByte;
    }

    public void setSizeByte(Long sizeByte) {
        this.sizeByte = sizeByte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.sha);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.sizeByte);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GitObjectInformation other = (GitObjectInformation) obj;
        if (!Objects.equals(this.sha, other.sha)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.sizeByte, other.sizeByte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GitObjectInformation{" + "sha=" + sha + ", type=" + type + ", sizeByte=" + sizeByte + '}';
    }

    public static List<GitObjectInformation> build(String pathToRepository) {
        
        String idxPath = getIDXPath(pathToRepository);
        BufferedReader reader = null;
        List<GitObjectInformation> gitObjInfos = new ArrayList<>();
        try {
            Process exec = Runtime.getRuntime().exec("git verify-pack -v "+ idxPath);
            exec.waitFor();
            reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strs = line.split(" ");
                if(strs[1].equals("blob")){
                    GitObjectInformation goi = new GitObjectInformation(strs[0], strs[1], Long.parseLong(strs[4]));
                    gitObjInfos.add(goi);
                }
                
            }
        } catch (IOException | InterruptedException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Logger.getLogger(GitObjectInformation.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return gitObjInfos;
    }

    private static String getIDXPath(String pathToRepository) {
        
        Optional<Path> idxPath = null;
        Path path = Paths.get(pathToRepository);
        try (Stream<Path> fileStream = Files.find(path, 999, (p, bfa) -> bfa.isRegularFile())) {
            idxPath = fileStream
                    .filter(file -> file.toString().matches("^.*\\.idx$")).findFirst();
        } catch (IOException e) {
            Logger.getLogger(GitObjectInformation.class.getName()).log(Level.SEVERE, null, e);
        }
     
        return idxPath.get().toString();
    }
}
