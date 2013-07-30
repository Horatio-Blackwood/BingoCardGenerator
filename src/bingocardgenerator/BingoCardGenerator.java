package bingocardgenerator;

import bingocardgenerator.bingo.Card;
import bingocardgenerator.bingo.CardGenerator;
import bingocardgenerator.file.FileTools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adam
 */
public class BingoCardGenerator {

    /**
     * Executes the application.
     * 
     * @param blargs the command line arguments for this app.  They are as follows:
     *                  0 - The name of the file containing the bingo options.
     *                  1 - Title of the Bingo Card to put on the Cards.
     *                  2 - The number of cards to generate as an integer.
     * 
     * @throws IOException if errors occur reading from or writing to files.
     */
    public static void main(String[] blargs) throws IOException {
        
        if (blargs.length < 3){
            printUsage();
            System.exit(0);
        }

        // CONFIGURABLE VALUES
        String optionsFile = blargs[0];
        String cardTitle = blargs[1];
        int cardsToGenerate = -1;

        // Get and Verify card count is valid.
        try {
            cardsToGenerate = Integer.valueOf(blargs[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number of cards requested - must be an integer.  Supplied # was:  " + blargs[2]);
            printUsage();
            System.exit(0);
        }

        // EXECUTION
        // --- Initialize Objects
        CardGenerator cg = new CardGenerator(optionsFile);
        List<Card> cards = cg.generate(cardsToGenerate);


        // --- Generate Cards & Index
        System.out.println("Preparing Bingo Cards");
        long now = System.currentTimeMillis();
        List<String> files = new ArrayList<>();

        for (Card crd : cards){
            String fname = "Card_" + cards.indexOf(crd) + ".html";
            crd.saveToHtml(fname, cardTitle);
            files.add(fname);
        }
        generateIndexFile(files, cardTitle);


        // --- Final output
        System.out.println("Finished!");
        System.out.println("Generation took:  " + ((System.currentTimeMillis() - now) / 1000) + " seconds.");
    }


    /**
     * Generates an index.html file that links you to all of the newly generated files.
     * 
     * @param files a list of file names to link to.
     * @param title the title of the bingo card / game
     *
     * @throws IOException if an error occurs during file writing.
     */
    private static void generateIndexFile(List<String> files, String title) throws IOException{
        System.out.println("Generating Bingo Card Index:");
        StringBuilder html = new StringBuilder("<html>");

        html.append("<body>");
        html.append("<font face=\"Arial\">");

        // Title
        html.append("<p>");
        html.append("<b>");
        html.append(title);
        html.append("</b>");
        html.append("</p>");
        html.append("<ul>");

        for (String file : files){
            html.append("<li>");
            html.append("<a href=\"");
            html.append(file);
            html.append("\">");
            html.append(file);
            html.append("</a>");
            html.append("</li>");
        }

        html.append("<ul>");
        html.append("</font>");
        html.append("</body>");
        html.append("</html>");

        FileTools.writeFile("index.html", html.toString());
    }

    
    /** Prints instructions on how to run this application. */
    public static void printUsage(){
        String tab = "     ";
        StringBuilder usage = new StringBuilder("Usage:\n");
        usage.append(tab);
        usage.append("java -jar BingoCardGenerator <option file> <card title> <# of cards to generate>\n\n");
        usage.append(tab);
        usage.append("Note:  You must have at least 24 entries in your options file in order to fill\n");
        usage.append(tab);
        usage.append("       a card or a complete bingo card without duplicates cannot be created.\n\n");
        
        System.out.println(usage.toString());
    }
}
