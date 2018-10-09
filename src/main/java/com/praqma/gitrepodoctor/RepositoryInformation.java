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
public class RepositoryInformation {
    private String repositoryname;
    private String repositorypath;
    private Long repositorySize;
    private List<FileInformation> repositoryfiles;

    public RepositoryInformation(String repositoryname, String repositorypath, Long repositorySize, List<FileInformation> files) {
        this.repositoryname = repositoryname;
        this.repositorypath = repositorypath;
        this.repositorySize = repositorySize;
        this.repositoryfiles = files;
    }

    public List<FileInformation> getFiles() {
        return repositoryfiles;
    }

    public Long getRepositorySize() {
        return repositorySize;
    }

    public String getRepositoryname() {
        return repositoryname;
    }

    public String getRepositorypath() {
        return repositorypath;
    }

    public void setFiles(List<FileInformation> files) {
        this.repositoryfiles = files;
    }

    public void setRepositorySize(Long repositorySize) {
        this.repositorySize = repositorySize;
    }

    public void setRepositoryname(String repositoryname) {
        this.repositoryname = repositoryname;
    }

    public void setRepositorypath(String repositorypath) {
        this.repositorypath = repositorypath;
    }
    
}
