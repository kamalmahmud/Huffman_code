import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// HuffmanCode class for Huffman coding implementation
public class HuffmanCode {
    private HuffmanNode root; // Root of the Huffman tree
    private LinkedList<HuffmanNode> freqLinked; // Linked list for storing frequency nodes
    private LinkedList<TableNode> HuffmanCodes = new LinkedList<>(); // Linked list for Huffman codes

    // Constructor that initializes the HuffmanCode object with the given text
    public HuffmanCode(String letterFrqFile) throws IOException {

        String text = new String(Files.readAllBytes(Paths.get(letterFrqFile)));

        fillFrqLinkedList(text);
        creatRoot(freqLinked);
        generateHuffmanTable(root,"");

    }

    // Fills the frequency linked list based on the characters in the letter.txt
    private void fillFrqLinkedList(String text){
        freqLinked = new LinkedList<>();
        ArrayList<HuffmanNode> arr = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (arr.isEmpty()){
                arr.add(new HuffmanNode(text.charAt(i),1));
            }else {
                boolean check = false;
                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(j).getCharacter()== text.charAt(i) ){
                        arr.get(j).setFrequency(arr.get(j).getFrequency()+1);;
                        check = true;
                        break;
                    }
                }
                if (check == false){
                    arr.add(new HuffmanNode(text.charAt(i),1));
                }

            }
        }
        for (int i = 0; i < arr.size(); i++) {
            freqLinked.sortedInsert(arr.get(i));
        }
    }

    // Creates the Huffman tree root from the frequency linked list
    private void creatRoot(LinkedList<HuffmanNode> leafLinkedList){
        while (leafLinkedList.getSize() > 1){
            HuffmanNode left = leafLinkedList.getHead().value;
            leafLinkedList.deleteTheFirst();
            HuffmanNode right = leafLinkedList.getHead().value;
            leafLinkedList.deleteTheFirst();
            leafLinkedList.sortedInsert(new HuffmanNode(left,right));
        }

        root = leafLinkedList.getHead().value;
    }

    // Generates the Huffman table with codes for each character
    private void generateHuffmanTable(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            HuffmanCodes.insertToFront(new TableNode(node.getCharacter(),code));
            return;
        }
        generateHuffmanTable(node.getLeftNode(), code.concat("0"));
        generateHuffmanTable(node.getRightNode(), code.concat("1"));
    }


    // Encodes the input file and writes the encoded data to the specified output file
    public void enCoded(String fileToRead, String fileToWrite) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(fileToRead)));
        StringBuilder s = new StringBuilder();
        for (char c: text.toCharArray()){
            Node<TableNode> it = HuffmanCodes.getHead();
            while (it != null){
                if (it.value.getCharacter() == c){
                    s.append(it.value.getCode());
                    break;
                }
                else {
                    it = it.next;
                }
            }

        }
        FileWriter fw=new FileWriter(fileToWrite);
        fw.write(s.toString());
        fw.close();

    }


    // Decodes the input file and writes the decoded data to the specified output file
    public void deCoded(String fileToRead,String fileToWrite) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(fileToRead)));
        StringBuilder result = new StringBuilder();
        HuffmanNode it = root;
        for (char c: text.toCharArray()){
            if (c == '0'){
                it = it.getLeftNode();
            }else{
                it = it.getRightNode();
            }
            if (it.isLeaf()){
                result.append(it.getCharacter());
                it = root;

            }

        }
        FileWriter fw=new FileWriter(fileToWrite);
        fw.write(result.toString());
        fw.close();
    }

    // Displays the Huffman codes
    public void display(){

        Node<TableNode> iterator=HuffmanCodes.getHead();
        StringBuilder result = new StringBuilder();
        while(iterator!=null){
            if (iterator.value.getCharacter() == '\n'){
            result.append("\\n").append("=").append(iterator.value.getCode()).append(" ");
            }else {
            result.append(iterator).append(" ");}
            iterator=iterator.next;
        }
        System.out.println(result.toString());
    }

}


//    if (c == '0'){
//        it = it.getLeftNode();
//        if (it.isLeaf()){
//            result+=it.getCharacter();
//            it = root;
//
//        }
//    }else{
//        it = it.getRightNode();
//        if (it.isLeaf()){
//            result+=it.getCharacter();
//            it = root;
//        }
//    }
