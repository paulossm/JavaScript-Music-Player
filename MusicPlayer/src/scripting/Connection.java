/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 */
package scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import users.User;
import users.VipUser;

/**
 * Class Connection
 * 
 * This class implements all comunnication of the Application
 * with the files in database directory.
 * 
 * All task that involves external files
 * are implemented here.
 * 
 * @author Paulo
 */
public class Connection {
    private static final Connection INSTANCE = new Connection();
    
    private Connection() {
        
    }
    
    public static Connection getInstance() {
         return INSTANCE;
    }
    
    /**
     * Lists All the files
     * in a given directory
     * 
     * 
     * @param pathToFolder
     *  Path to directory to list files
     * 
     * @return
     *  An Array of files
     * 
     * @throws IOException 
     *  may occur I/O errors.
     */
    public File[] listFiles(String pathToFolder) throws IOException {
        File folder = new File(pathToFolder);
        
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                String lowercaseName = name.toLowerCase();
                if(lowercaseName.endsWith(".txt"))
                    return true;
                else
                    return false;
            }
        };
        
        // All .txt files inside the user/playlist directory
        return folder.listFiles(filter);
    }
    
    /**
     * Opens a file by the given Path
     * 
     * @param pathToFile
     *      Path to file to open
     * 
     * @return
     *      The body of the opened file.
     *      which is an array of Strings.
     * 
     * @throws IOException 
     */
    public String[] openFile(String pathToFile) throws IOException {
        
        int lineCounter = countFileLines(pathToFile);
        String[] data = new String[lineCounter];
        
        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader textReader = new BufferedReader(fileReader);
        
        int iterator;
        for(iterator = 0; iterator < lineCounter; iterator++) {
            data[iterator] = textReader.readLine();
        }
        textReader.close();
        return data;
    }
    
    /**
     * Lists all .mp3 files
     * in a given directory
     * 
     * @param pathToFolder
     *      Path to directory to find .mp3 files
     * 
     * @return 
     *  An array of found files
     */
    public File[] listMusicFiles(String pathToFolder) {
        File folder = new File(pathToFolder);
        
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                String lowercaseName = name.toLowerCase();
                if(lowercaseName.endsWith(".mp3"))
                    return true;
                else
                    return false;
            }
        };
        return folder.listFiles(filter);
    }
    
    /**
     * Counts the length of lines
     * of a given file
     * 
     * @param pathToFile
     *      Path to file to count lines
     * 
     * @return
     *      Integer: number of lines in the file
     * 
     * @throws IOException
     */
    public int countFileLines(String pathToFile) throws IOException {
        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader textReader = new BufferedReader(fileReader);
        int lineCounter = 0;
        String aux = "";
        while((aux = textReader.readLine()) != null)
            lineCounter++;
        
        textReader.close();
        return lineCounter;
    }
    
    /**
     * Writes information in a .txt file
     * 
     * @param pathToFile
     *      Path to file where to write
     * 
     * @param textLine
     *      String with information to be written
     * 
     * @param appendToFile
     *      true: appends the textLine to the last line of file
     *      false: deletes all file data and write only the textLine information.
     * 
     * @throws IOException 
     */
    public void writeToFile(String pathToFile, String textLine, boolean appendToFile) throws IOException {
        FileWriter write = new FileWriter(pathToFile, appendToFile);
        PrintWriter printLine = new PrintWriter(write);
        if(!(textLine.equals(""))) {
            printLine.printf("%s" + "%n", textLine);
        }
        printLine.close();
        
    }
    
    /**
     * Creates a new folder
     * in the OS by the given path
     * 
     * @param path
     *      Path to place where
     *      folder will be created
     * @throws IOException 
     */
    public void createFolder(String path) throws IOException {
        File newFolder = new File(path);
        if(!(newFolder.mkdir()))
            throw new IOException();
    }
    
    /**
     * Creates a new file
     * in the diven directory
     * 
     * @param name
     *      File's name
     * 
     * @param directory
     *      where to create the file
     * @param extension
     *      any file's extension (txt, mp3, ...)
     * @throws IOException 
     */
    public void createFile(String name, String directory, String extension) throws IOException {
        File newfile = new File(directory + name + "." + extension);
        newfile.getParentFile().mkdirs();
        if(!(newfile.createNewFile())){
            throw new IOException();
        }
    }

    /**
     * Register a new user
     * to the application
     * by writing to the
     * users.txt file
     * 
     * @param newuser
     *      User created.
     * @param filePath
     *      Path to users.txt file
     * @param isVip
     *      true: if user is Vip User
     *      false: if user is a Default User
     * 
     * @throws IOException 
     */
    public void registerUser(User newuser, String filePath, boolean isVip) throws IOException {
        int newid = countFileLines(filePath) + 1;
        String data = newid + "," + newuser.getUsername() + "," + newuser.getPassword() + "," + (newuser instanceof VipUser);
        writeToFile(filePath, data, true);
        createFolder("src/database/" + newuser.getUsername());
        createFile("songs", ("src/database/" + newuser.getUsername() + "/songs/"), "txt");
        if(isVip) {
            createFolder("src/database/" + newuser.getUsername() + "/playlists");
        }
    }
    
}
