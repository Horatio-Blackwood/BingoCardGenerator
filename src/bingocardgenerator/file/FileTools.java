package bingocardgenerator.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adam
 */
public class FileTools {

    /**
     * Writes the provided text out to a file with the supplied name.
     *
     * @param filename the name of the file to write.
     * @param text the text to write to the file.
     */
    public static void writeFile(String filename, String text) throws IOException{
        File outputFile = new File(filename);
        FileWriter writer = new FileWriter(outputFile);

        writer.write(text);
        writer.flush();
        writer.close();
    }

    /**
     * Reads in an entire file and returns the String of text read.
     *
     * @param filename the name of the file.
     * @return the text read in from the file.
     */
    public static String readFile(String filename) throws IOException{
        File inFile = new File(filename);

        BufferedReader reader = new BufferedReader(new FileReader(inFile));

        StringBuilder text = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            text.append(line);
        }

        return text.toString();
    }

    /**
     * Reads in a file line by line and returns those lines in an ordered list.  Ignores lines that start with the
     * specified comment prefix.
     *
     * @param filename the file you want to read in.
     * @param commentPrefix the prefix prepended to lines that should be ignored in the file to be read.
     *
     * @return the lines of the file in a list of lines in the order they were read in.
     * @throws IOException if an error is encountered when reading in the file.
     */
    public static List<String> readLinesFromFile(String filename, String commentPrefix) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = inFile.readLine()) != null){

            if (!line.isEmpty()){
                if (commentPrefix != null){
                    if (!line.startsWith(commentPrefix)){
                        lines.add(line);
                    }
                } else {
                    lines.add(line);
                }
            }
        }
        inFile.close();
        return lines;
    }

    /**
     * Reads in a file line by line and returns those lines in an ordered list.
     *
     * @param filename the file you want to read in.
     * @return the lines of the file in a list of lines in the order they were read in.
     *
     * @throws IOException if an error is encountered when reading in the file.
     */
    public static List<String> readAllLinesFromFile(String filename) throws IOException{
        return readLinesFromFile(filename, null);
    }
}
