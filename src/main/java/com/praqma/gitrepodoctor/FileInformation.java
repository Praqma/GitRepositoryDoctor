/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;


/**
 *
 * @author florenthaxha
 */
public class FileInformation {
    private String filename;
    private String filepath;
    private String filetype;
    private Long fileSize;
    
    
    public FileInformation(String fname, String fpath, String ftype, Long fsize){
        this.filename = fname;
        this.filepath = fpath;
        this.filetype = ftype;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    
}