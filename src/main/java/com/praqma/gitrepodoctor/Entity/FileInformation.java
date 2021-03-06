/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor.Entity;


/**
 *
 * @author florenthaxha
 */
public class FileInformation implements Comparable<FileInformation>{
    private String filename;
    private String filepath;
    private String filetype;
    private String gitfiletype;
    private Long fileSize;

    public FileInformation(String fname, String fpath, String ftype, String gftype, Long fsize) {
        this.filename = fname;
        this.filepath = fpath;
        this.filetype = ftype;
        this.gitfiletype = gftype;
        this.fileSize = fsize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getGitfiletype() {
        return gitfiletype;
    }

    public void setGitfiletype(String gitfiletype) {
        this.gitfiletype = gitfiletype;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileInformation{" + "filename=" + filename + ", filepath=" + filepath + ", filetype=" + filetype + ", gitfiletype=" + gitfiletype + ", fileSize=" + fileSize + '}';
    }
    
    @Override
    public int compareTo(FileInformation o) {
        if(fileSize == o.fileSize) return 0;
        else if(fileSize < o.fileSize) return 1;
        else return -1;
    }


}
