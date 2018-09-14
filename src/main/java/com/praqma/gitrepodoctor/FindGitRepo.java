/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praqma.gitrepodoctor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author florenthaxha
 */
public class FindGitRepo implements FindGitRepoIF{
        
    @Override
    public List<File> getRepoFiles(String pathToFolder) throws IOException{
        List<File> files = new ArrayList<>();
        
        try(Stream<Path> fileStream = Files.find(Paths.get(pathToFolder), 999, (p, bfa) -> bfa.isRegularFile())){
            fileStream
                    .filter(file -> file.getFileName().toString().matches("^((?!.git).)*$"))
                    .forEach(file -> files.add(file.getFileName().toFile()));
            fileStream.close();
        }
        
        return files;
    }

}
