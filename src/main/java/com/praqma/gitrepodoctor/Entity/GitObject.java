/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor.Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author florenthaxha
 */
public class GitObject implements Comparable<GitObject>{

    private final String sha;
    private final Long fileSize;
    private final Long sizeInPackfile;
    private List<String> paths = new ArrayList();
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public GitObject(String sha, Long fileSize, Long sizeInPack) {
        this.sha = sha;
        this.fileSize = fileSize;
        this.sizeInPackfile = sizeInPack;
    }
    
    public String getSha() {
        return sha;
    }

    public Long getSizeByte() {
        return fileSize;
    }

    public List<String> getPaths() {
        return paths;
    }
    
    public void addPath(String path){
        paths.add(path);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.sha);
        hash = 37 * hash + Objects.hashCode(this.fileSize);
        hash = 37 * hash + Objects.hashCode(this.sizeInPackfile);
        hash = 37 * hash + Objects.hashCode(this.paths);
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
        final GitObject other = (GitObject) obj;
        if (!Objects.equals(this.sha, other.sha)) {
            return false;
        }
        if (!Objects.equals(this.fileSize, other.fileSize)) {
            return false;
        }
        if (!Objects.equals(this.sizeInPackfile, other.sizeInPackfile)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public int compareTo(GitObject o) {
        if(fileSize == o.fileSize) return 0;
        else if(fileSize < o.fileSize) return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return "GitObject{" + "sha=" + sha + ", fileSize=" + fileSize + ", sizeInPack=" + sizeInPackfile + ", paths=" + paths + '}';
    }
}
