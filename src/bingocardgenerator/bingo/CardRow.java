package bingocardgenerator.bingo;

/**
 *
 * @author adam
 */
public class CardRow {

    private int m_length;

    private BingoItem[] m_row;

    /**
     * Creates a new card row of the specified length.
     * @param length the length of the card row.
     */
    public CardRow(int length){
        m_row = new BingoItem[length];
        m_length = length;
        for (int i = 0; i < length; i++){
            m_row[i] = new BingoItem("Uninitialized");
        }
    }

    /**
     * Inserts an item at the specified index.
     * @param item the item to insert.
     * @param index the index to insert it at.
     */
    public void insert(BingoItem item, int index){
        m_row[index] = item;
    }

    /**
     * Returns the item at the specified row index.
     * @param index the index of the item to get.
     * @return the item at the specified row index.
     */
    public BingoItem get(int index){
        return m_row[index];
    }

    public int getLength(){
        return m_length;
    }
}
