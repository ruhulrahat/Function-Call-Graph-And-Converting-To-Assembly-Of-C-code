package softwareProjectLab;
 
public class Node {
     
    String Name;
    Node LeftChild;
    Node RightChild;
     
    public Node(String name) {
        Name = name;
        LeftChild = null;
        RightChild = null;
    }
     
    public Node(String name, String leftName) {
        Name = name;
        LeftChild = new Node(leftName);
    }
     
    public Node(String name, Node leftChild, Node rightChild) {
        Name = name;
        LeftChild = leftChild;
        RightChild = rightChild;
    }
     
    public String toString() {
        return "[" + Name + "," + LeftChild + "," + RightChild + "]" ;
    }
}