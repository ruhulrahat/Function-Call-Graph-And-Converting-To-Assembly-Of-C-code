package softwareProjectLab;
 
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import javax.swing.JFrame;
 
public class ExpressionTree
{
    Node Root;
    ArrayList<Node>Infix;
    ArrayList<Node>Postfix = new ArrayList<>();
    Map<String, Integer>Priority;
     
    public ExpressionTree() 
    {
        Root = null;
        Infix = new ArrayList<>();
        Priority = new TreeMap<>();
        Priority.put("(", 5);
        Priority.put(")", 5);
        Priority.put("*", 4);
        Priority.put("/", 4);
        Priority.put("+", 3);
        Priority.put("-", 3);
        Priority.put(">", 2);
        Priority.put("<", 2);
        Priority.put(">=", 2);
        Priority.put("<=", 2);
        Priority.put("||", 1);
        Priority.put("&&", 1);
        Priority.put("=", 0);
    }
     
    public void add(String token)
    {
        Infix.add(new Node(token));
    }
     
    public boolean lowerPrecedence(Node infixSymbol, Node stackSymbol)
    {
        if(Priority.get(infixSymbol.Name)>Priority.get(stackSymbol.Name)) return true;
        return false;
    }
     
    public void buildTree()
    {
        //System.out.println(Infix);
        Stack<Node>saves = new Stack<>();
        saves.push(new Node("("));
        Infix.add(new Node(")"));
        
        for(int i=0; i<Infix.size(); i++)
        {
            if(Priority.containsKey(Infix.get(i).Name)==false)
            {
                Postfix.add(Infix.get(i));
            }
            else
            {
                if(Infix.get(i).Name.equals("("))
                {
                    saves.add(Infix.get(i));
                }
                else if(Infix.get(i).Name.equals(")"))
                {
                    while(true){
                        Node top = saves.pop();
                        if(top.Name.equals("(")){
                            break;
                        }
                        else{
                            Postfix.add(top);
                        }
                    }
                }
                else{
                    while(true){
                        Node top = saves.peek();
                        if(lowerPrecedence(Infix.get(i), top) || top.Name.equals("(")) break;
                        Postfix.add(top);
                        saves.pop();
                    }
                    saves.push(Infix.get(i));
                }
            }
        }
         
        saves.clear();
        for(Node node: Postfix){
            if(Priority.containsKey(node.Name)){
                node.RightChild = saves.pop();
                node.LeftChild = saves.pop();
                saves.add(node);
            }
            else{
                saves.add(node);
            }
        }
        Root = saves.pop();
        //System.out.println(Infix);
    }
     
    /*public static void main(String[] args) {
        ExpressionTree e = new ExpressionTree();
        String []str = {"(","55","+","13",")","*","(","92","-","(","33","+","2",")",")"};
        //String str[] = {"a",">=","55","||","m","<","54"};
        for(int i=0; i<str.length; i++)
            e.add(str[i]);
        e.buildTree();
        TreePrinter t = new TreePrinter(e.Root,0);
        t.setSize(1300, 900);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }*/
}