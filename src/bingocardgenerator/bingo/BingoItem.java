package bingocardgenerator.bingo;

import java.util.Objects;

/**
 *
 * @author adam
 */
public class BingoItem implements Comparable<BingoItem> {

    private String m_text;

    public BingoItem(String text){
        m_text = text;
    }

    public String getText(){
        return m_text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.m_text);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BingoItem other = (BingoItem) obj;
        if (!Objects.equals(this.m_text, other.m_text)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BingoItem o) {
        return this.m_text.compareTo(o.getText());
    }
}
