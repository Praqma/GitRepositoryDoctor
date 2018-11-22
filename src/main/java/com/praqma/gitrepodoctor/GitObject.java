/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Objects;

/**
 *
 * @author florenthaxha
 */
public class GitObject implements Comparable<GitObject>{

    private String sha;
    private String type;
    private Long sizeByte;
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public GitObject(String sha, String type, Long sizeByte) {
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
        final GitObject other = (GitObject) obj;
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

    @Override
    public int compareTo(GitObject o) {
        if(sizeByte == o.sizeByte) return 0;
        else if(sizeByte < o.sizeByte) return 1;
        else return -1;
    }
    
    
}
