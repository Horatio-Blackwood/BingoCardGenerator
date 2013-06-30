package bingocardgenerator.bingo;

import bingocardgenerator.BingoCardGenerator;
import bingocardgenerator.file.FileTools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class CardGenerator {

    /** The file that contains the possible values for the bingo cards.  */
    private String m_optionFile;

    /** A List of Options read in from the option file. */
    private List<String> m_options;

    /** The number of rows/columns of the bingo cards. */
    private static final int DIMENSIONS = 5;
    
    /** The number of cells to populate with options.  Equal to:  DIMENSION^2 - 1. */
    private static final int NUMBER_OF_CELLS = 24;
    
    /** The index of the center space (the FREE space) of the bingo card. */
    private static final int CENTER_INDEX = 2;

    /** A Random used to select random options from the List. */
    private Random m_rand;

    /**
     * Creates a new CardGenerator.
     * 
     * @param optionFile the name of a file full of options.
     * @param dimensions the number of rows/columns for the Cards (both dimensions are the same i.e. cards are square).
     */
    public CardGenerator(String optionFile){
        // Load Options from File
        try {
            m_options = new ArrayList<>(FileTools.readLinesFromFile(optionFile, "#"));
        } catch (IOException ex) {
            Logger.getLogger(CardGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (m_options.size() < NUMBER_OF_CELLS){
            System.out.println("Not enough options to create cards without repeating cells.");
            BingoCardGenerator.printUsage();
            System.exit(1);
        }

        // Initialize members
        m_optionFile = optionFile;
        m_rand = new Random();
        System.out.println("Read In " + m_options.size() + " options from the data file.");
    }

    /**
     * Generates the supplied number of cards.
     * @param quantity the number of cards to generate.
     * @return The generated cards.
     */
    public List<Card> generate(int quantity){
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < quantity; i++){
            cards.add(makeBingoCard());
        }

        return cards;
    }

    /**
     * Constructs a bingo Card.
     * @return the constructed Card.
     */
    private Card makeBingoCard(){
        Card card = new Card(DIMENSIONS);

        // Create a copy of the options
        List<String> options = new ArrayList<>(m_options);

        // For each row/col pair - get an option taking care not to use any option twice.
        for (int row = 0; row < DIMENSIONS; row++){
            for (int col = 0; col < DIMENSIONS; col++){
                BingoItem item = null;
                if (col == CENTER_INDEX && row == CENTER_INDEX){
                    item = new BingoItem("FREE");
                } else {
                    int selectedOption = m_rand.nextInt(options.size());
                    item = new BingoItem(options.get(selectedOption));
                    options.remove(selectedOption);
                }
                card.getRow(row).insert(item, col);
            }
        }

        return card;
    }
}