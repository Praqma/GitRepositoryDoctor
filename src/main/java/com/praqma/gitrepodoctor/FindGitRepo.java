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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author florenthaxha
 */
public class FindGitRepo implements FindGitRepoIF{
        
    @Override
    public List<File> getRepoFiles(Path pathToFolder) throws IOException{
        
        List<File> files = new ArrayList<>();
        
        try(Stream<Path> fileStream = Files.find(pathToFolder, 999, (p, bfa) -> bfa.isRegularFile())){
            fileStream
                    .filter(file -> file.toString().matches("^(?!.*(/.git/|/.DS_Store/)).*$"))
                    .forEach(file -> files.add(file.toFile()));
            fileStream.close();
        }
        
        return files;
    }

}
