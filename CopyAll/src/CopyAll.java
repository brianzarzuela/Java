import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Multithreaded program to copy a directory to a new location.
 *
 * @author Brian Zarzuela
 * @version 0.5 2019-03-05
 * @since 2019-03-04
 */
class CopyAll extends Thread{
    //private String existingPath;
    private String copyPath;
    private ArrayList<File> files = new ArrayList<>();
    private static File existingDirectory, copyDirectory /*, threadedDirectory/**/;
    private long sequentialDuration/*, threadingDuration/**/;

    CopyAll(String existingPath, String copyPath){
        //this.existingPath = existingPath;
        this.copyPath = copyPath;
        if (Files.exists(Paths.get(existingPath))){
            existingDirectory = new File(existingPath);
            copyDirectory = new File(copyPath);
            long startTime = System.nanoTime();
            sequentialCopy();
            long endTime = System.nanoTime();
            System.out.println("\nSequential Copy Directory : " + copyDirectory.getPath());
            sequentialDuration = (endTime - startTime) / 1000000; // in milliseconds
            displayTimingInformation();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void sequentialCopy(){
        if(copyDirectory.mkdir())
            System.out.printf("\nDirectory %s was created successfully\n", copyPath);
        else {
            copyPath = copyPath + "_copy";
            copyDirectory = new File(copyPath);
            int increment = 1;
            String prefix = copyPath;
            while(!copyDirectory.mkdir()){
                copyPath =  prefix + "(" + increment++ + ")";
                copyDirectory = new File(copyPath);
            }
            System.out.printf("directory %s was created successfully\n", copyPath);
        }

        for (File file : existingDirectory.listFiles()){
            String path = file.getPath();
            files.add(new File(path));
        }

        for (File file : files){
            try {
                String path = copyPath + "\\" + file.getName();
                Files.copy(file.toPath(), Paths.get(path));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void displayTimingInformation(){
        System.out.printf("\nTime taken to make all copies sequentially....%d milliseconds\n", sequentialDuration);
        System.out.printf("\nTime taken to make all copies multithreaded...%d milliseconds\n", 0);
    }
}
