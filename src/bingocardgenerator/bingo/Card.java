package bingocardgenerator.bingo;

import bingocardgenerator.file.FileTools;
import java.io.IOException;

/**
 *
 * @author adam
 */
public class Card {

    private int m_rowCount;
    private CardRow[] m_rows;

    public Card(int dimension){
        m_rowCount = dimension;
        m_rows = new CardRow[dimension];

        //Init CardRows
        for (int i = 0; i < m_rowCount; i++){
            m_rows[i] = new CardRow(dimension);
        }
    }

    /**
     * Inserts an item at the specified index.
     * @param item the item to insert.
     * @param index the index to insert it at.
     */
    public void insert(CardRow item, int index){
        m_rows[index] = item;
    }

    /**
     * Returns the item at the specified row index.
     * @param index the index of the item to get.
     * @return the item at the specified row index.
     */
    public CardRow getRow(int index){
        return m_rows[index];
    }

    /** {@inheritDoc} */
    @Override
    public String toString(){

        StringBuilder bldr = new StringBuilder("Galley Movie Bingo Card:\n");
        for (int row = 0; row < m_rowCount; row++){
            bldr.append("[");
            for (int col = 0; col < m_rowCount; col++){
                bldr.append(getRow(row).get(col).getText());
                bldr.append(", ");
            }
            bldr.append("]\n");
        }
        return bldr.toString();
    }


    public void saveToHtml(String fileName, String title) throws IOException{
        StringBuilder html = new StringBuilder("<html>");
        html.append("<body>");
        html.append("<font face=\"Arial\">");

        // Title
        html.append("<p>");
        html.append("<b>");
        html.append(title);
        html.append("</b>");
        html.append("</p>");

        // Set up Table
        html.append("<table cellpadding=\"4\" style=\"border:3px solid; border-color:#8F8F8F; border-collapse:collapse;\">");
        html.append("<col width=\"200px\">");
        html.append("<col width=\"200px\">");
        html.append("<col width=\"200px\">");
        html.append("<col width=\"200px\">");
        html.append("<col width=\"200px\">");

        // Add Row Data
        for (CardRow row : m_rows){
            html.append("<tr>");
            for (int i = 0; i < row.getLength(); i++){
                html.append("<td valign=\"top\" style=\"border:3px solid; border-color:#8F8F8F;\">");
                String option = row.get(i).getText();
                html.append(option);
                html.append("<br>");
                html.append("<br>");
                if (!option.contains("<br>")){
                    html.append("<br>");
                }
                html.append("Note:                 ");
                html.append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("</font>");
        html.append("</body>");
        html.append("</html>");

        FileTools.writeFile(fileName, html.toString());
    }

}
