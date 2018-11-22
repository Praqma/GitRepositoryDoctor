/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author florenthaxha
 */
public class RepositoryInformation {

    private String repositoryname;
    private String repositorypath;
    private Long repositorySize;
    private List<FileInformation> repositoryfiles;

    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final static FileUtils FUTILS = new FileUtils();

    public RepositoryInformation(String repositoryname, String repositorypath, Long repositorySize, List<FileInformation> files) {
        this.repositoryname = repositoryname;
        this.repositorypath = repositorypath;
        this.repositorySize = repositorySize;
        this.repositoryfiles = files;
    }

    public RepositoryInformation() {
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

    public static RepositoryInformation build(String pathtoRepository) {
        Path path = Paths.get(pathtoRepository);
        Long repoSize = 0L;
        List<FileInformation> fileInfos = new ArrayList<>();
        List<File> files;

        files = getRepoFiles(path);
        for (File file : files) {
            Long fsize = file.length();
            FileInformation fi = new FileInformation(
                    file.getName(),
                    file.getAbsolutePath(),
                    FUTILS.isFileBinary(file),
                    FUTILS.isGitBinary(file),
                    fsize);
            fileInfos.add(fi);
            repoSize += fsize;
        }
        Collections.sort(fileInfos);
        return new RepositoryInformation(path.getFileName().toString(), pathtoRepository, repoSize, fileInfos);
    }

    public String toJson() {
        return GSON.toJson(this);
    }

    public RepositoryInformation fromJson(String json) {
        return GSON.fromJson(json, this.getClass());
    }

    private static List<File> getRepoFiles(Path pathtoRepository) {
        List<File> files = new ArrayList<>();

        try (Stream<Path> fileStream = Files.find(pathtoRepository, 999, (p, bfa) -> bfa.isRegularFile())) {
            fileStream
                    .filter(file -> file.toString().matches("^(?!.*(/.git/|/.DS_Store/)).*$"))
                    .forEach(file -> files.add(file.toFile()));
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return files;
    }

    @Override
    public String toString() {
        return "RepositoryInformation{" + "repositoryname: " + repositoryname +
                ", repositorypath: " + repositorypath +
                ", repositorySize: " + repositorySize + " Byte(s)" +
                ", repositoryfilesSize: " + repositoryfiles.size() + '}';
    }

    
    
    
    
}
