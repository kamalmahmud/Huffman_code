public class HuffmanNode implements Comparable<HuffmanNode> {
    private char character;
    private int frequency;
    private HuffmanNode leftNode = null;
    private HuffmanNode rightNode = null;


    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(HuffmanNode leftNode, HuffmanNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.frequency = leftNode.getFrequency() + rightNode.getFrequency();
    }

    public HuffmanNode(char character, int frequency, HuffmanNode leftNode, HuffmanNode rightNode) {
        this.character = character;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int compareTo(HuffmanNode node) {
        return Integer.compare(frequency, node.getFrequency());
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    protected boolean isLeaf(){
        return this.getLeftNode() == null && this.getRightNode() == null;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        this.rightNode = rightNode;
    }

    public String toString(){

        return character+"="+frequency;
    }
}
