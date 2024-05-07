public class TableNode implements Comparable<TableNode> {
    private char character;
    private String code;

    public TableNode(char character, String code) {
        this.character = character;
        this.code = code;
    }

    public char getCharacter() {
        return character;
    }

    public String getCode() {
        return code;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(TableNode o) {
        return 0;
    }

    public String toString(){

        return character+"="+code;
    }
}
