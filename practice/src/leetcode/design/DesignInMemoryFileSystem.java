package leetcode.design;

import java.util.*;

//https://leetcode.com/problems/design-in-memory-file-system/
public class DesignInMemoryFileSystem {
/*
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.
 */
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.ls("/"));                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello"); //Latest element d is a file from c directory
        System.out.println(fileSystem.ls("/"));                         // return ["a"]
        System.out.println("-> " + fileSystem.ls("/a/b/c")); //c is considered a file stored from b directory
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d")); // return "hello"
    }

    //See image: practice/resources/fileSystem.png
    static class FileSystem {
        Dir root;
        public FileSystem() {
            root = new Dir();
        }

        public List<String> ls(String path) {
            Dir currDir = root;
            List<String> files = new ArrayList<>(); //Result
            if(!path.equals("/")) { //true means it is a complete path
                String[] dirs = path.split("/");
                //Start to 1 because first element is empty -> ""
                for(int i = 1; i < dirs.length - 1; i++) {
                    currDir = currDir.dirs.get(dirs[i]);
                }
                String requestedFile = dirs[dirs.length - 1];
                //If path is a file path, returns a list that only contains this file's name.
                if(currDir.files.containsKey(requestedFile)) { //true if this directory contains that requested file
                    files.add(requestedFile);
                    return files;
                } else {
                    //If path is a directory path, returns the list of file and directory names in this directory.
                    currDir = currDir.dirs.get(requestedFile);
                }
            }
            files.addAll(new ArrayList<>(currDir.dirs.keySet()));
            files.addAll(new ArrayList<>(currDir.files.keySet()));
            Collections.sort(files);
            return files;
        }

        public void mkdir(String path) {
            Dir currDir = root;
            String[] dir = path.split("/");
            for(int i = 1; i < dir.length; i++) {
                String d = dir[i];
                if(!currDir.dirs.containsKey(d)) { //true if does NOT exist
                    currDir.dirs.put(d, new Dir());
                }
                currDir = currDir.dirs.get(d);
            }
        }

        public void addContentToFile(String filePath, String content) {
            Dir currDir = root;
            String[] dir = filePath.split("/");
            for(int i = 1; i < dir.length - 1; i++) {
                String d = dir[i];
                currDir = currDir.dirs.get(d);
            }
            String latestDir = dir[dir.length - 1];
            //Create a file from the existing directory
            currDir.files.put(latestDir, currDir.files.getOrDefault(latestDir, "") + content);
        }

        public String readContentFromFile(String filePath) {
            Dir currDir = root;
            String[] dir = filePath.split("/");
            for(int i = 1; i < dir.length - 1; i++) {
                String d = dir[i];
                currDir = currDir.dirs.get(d);
            }
            String latestDir = dir[dir.length - 1];
            return currDir.files.get(latestDir);
        }
    }

    static class Dir {
        //See image for better understanding : practice/resources/fileSystem.png
        //Key: Subdirectory name, Value: subdirectory structure
        Map<String, Dir> dirs = new HashMap<>();
        //Key: file name, Value: Files content
        Map<String, String> files = new HashMap<>();
    }
}
