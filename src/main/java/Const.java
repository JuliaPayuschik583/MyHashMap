/**
 * @author Julia
 */
public class Const {
    public static final int DEFAULT_SIZE = 16;//default size of hash map

    public static int logicHash(final Integer key, final int lengthOfTable) {
        int hash = 31;
        hash = hash * 17;
        if (key != null ) {
            hash += key.hashCode();
        }
        return hash % lengthOfTable;
    }
}
