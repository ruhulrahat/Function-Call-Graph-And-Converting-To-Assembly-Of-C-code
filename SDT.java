package softwareProjectLab;
 
import java.util.ArrayList;
import java.util.Stack;
 
public class SDT 
{
    ArrayList <Node> TreeRoots = new ArrayList<>();
    ArrayList <String> tokens;
    ArrayList <String> DataTypes= new ArrayList<>();
    ArrayList <String> Comparators = new ArrayList<>();
    Node Root = null;
     
    public SDT(ArrayList<String> tokens)
    {
        this.tokens = tokens;
        String dataTypes[] = {"int","float","double","void","long"};
        for(int i=0; i<dataTypes.length; i++)
            DataTypes.add(dataTypes[i]);
        String comparators[] = {"=",">","<"};
        for(int i=0; i<comparators.length; i++)
            Comparators.add(comparators[i]);
    }
     
    int blockNumber = 0;
     
    public void manageTree(ArrayList <String> Tokens)
    {
         
        //System.out.println(Tokens.toString() + TreeRoots.size());
         
        Stack<Block>Blocks = new Stack<>();
        Node Previous=null,Current=null;
        String previousToken = new String();
        ExpressionTree e1 = new ExpressionTree();
         
        for(int i=0; i<Tokens.size(); i++)
        {
             
            String currentToken = Tokens.get(i);
             
            if(DataTypes.contains(currentToken))
            {
                i++;
                String nextToken = Tokens.get(i);
                Previous = new Node("declare", currentToken);
                Current = new Node("",nextToken);
                Previous.RightChild = Current;
                Root = Previous;
                 
                 
                while(true)
                {
                    i++;
                    currentToken = Tokens.get(i);
                    if(currentToken.equals(";")){
                        Current.Name = ";";
                        TreeRoots.add(Root);
                        break;
                    }
                    else if(currentToken.equals("="))
                    {
                        ExpressionTree e = new ExpressionTree();
                        while(true)
                        {
                            i++;
                            nextToken = Tokens.get(i);
                            if(nextToken.equals(",") || nextToken.equals(";"))
                            {
                                e.buildTree();
                                Node left = new Node("=", Current.LeftChild.Name);
                                left.RightChild = e.Root;
                                Current.LeftChild = left;
                                i--;
                                break;
                            }
                            else
                            {
                                e.add(nextToken);
                            }
                        }
                    }
                    else if(currentToken.equals(","))
                    {
                        Current.Name = ",";
                        i++;
                        nextToken = Tokens.get(i);
                        Current.RightChild = new Node("", nextToken);
                        Current = Current.RightChild;
                    }
                    else
                    {
                        System.out.println(currentToken);
                    }
                }
            }
            else if(currentToken.equals("while"))
            {
                blockNumber++;
                TreeRoots.add(new Node("StartOfBlock"+Integer.toString(blockNumber)));
                i++;
                int leftBracket = 1;
                ExpressionTree tree = new ExpressionTree();
                while(true)
                {
                    i++;
                    String temp = Tokens.get(i);
                    if(temp.equals("("))
                    {
                        leftBracket++;
                        tree.add("(");
                        continue;
                    }
                    if(temp.equals(")"))
                    {
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    tree.add(temp);
                }
                tree.buildTree();
                Root = new Node("if");
                Root.RightChild = new Node(" EndOfBlock"+Integer.toString(blockNumber));//go to
                Root.LeftChild = new Node("==", tree.Root, new Node("false"));
                TreeRoots.add(Root);
                i++;
                leftBracket = 1;
                ArrayList<String>interior = new ArrayList<>();
                while(true){
                    i++;
                    String temp = Tokens.get(i);
                    if(temp.equals("{")){
                        leftBracket++;
                        interior.add(temp);
                        continue;
                    }
                    if(temp.equals("}")){
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    interior.add(temp);
                }
                //System.out.println(interior);
                manageTree(interior);
                TreeRoots.add(new Node(" StartOfBlock"+Integer.toString(blockNumber)));//go to
                TreeRoots.add(new Node("EndOfBlock"+Integer.toString(blockNumber)));
                blockNumber--;
            }
            else if(currentToken.equals("do")){
                blockNumber++;
                TreeRoots.add(new Node("StartOfBlock"+Integer.toString(blockNumber)));
                i++;
                int leftBracket = 1;
                ArrayList<String>interior = new ArrayList<>();
                while(true){
                    i++;
                    String temp = Tokens.get(i);
                    if(temp.equals("{")){
                        leftBracket++;
                        interior.add(temp);
                        continue;
                    }
                    if(temp.equals("}")){
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    interior.add(temp);
                }
                manageTree(interior);
                i++;
                i++;
                leftBracket = 1;
                ExpressionTree tree = new ExpressionTree();
                while(true)
                {
                    i++;
                    String temp = Tokens.get(i);
                    //System.out.println(temp);
                    if(temp.equals("(")){
                        leftBracket++;
                        tree.add("(");
                        continue;
                    }
                    if(temp.equals(")")){
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    tree.add(temp);
                    //System.out.println(temp);
                }
                tree.buildTree();
                Root = new Node("if");
                Root.RightChild = new Node(" StartOfBlock"+Integer.toString(blockNumber));//go to
                Root.LeftChild = tree.Root;
                TreeRoots.add(Root);
                TreeRoots.add(new Node("EndOfBlock"+Integer.toString(blockNumber)));
                blockNumber--;
                i++;
                 
            }
            else if(currentToken.equals("for"))
            {
                blockNumber++;
                TreeRoots.add(new Node("StartOfBlock"+Integer.toString(blockNumber)));
                i++;
                ArrayList<String>interior = new ArrayList<>();
                while(true)
                {
                    i++;
                    currentToken = Tokens.get(i);
                    if(currentToken.equals(",")){
                        interior.add(";");
                        manageTree(interior);
                        interior.clear();
                    }
                    else if(currentToken.equals(";")){
                        interior.add(";");
                        manageTree(interior);
                        interior.clear();
                        break;
                    }
                    else{
                        interior.add(currentToken);
                    }
                }
                TreeRoots.add(new Node("StartOfWorkingBlock"+Integer.toString(blockNumber)));
                e1 = new ExpressionTree();
                while(true){
                    i++;
                    currentToken = Tokens.get(i);
                    if(currentToken.equals(";")) break;
                    else e1.add(currentToken);
                }
                e1.buildTree();
                Node left = new Node("==");
                left.LeftChild = e1.Root;
                left.RightChild = new Node("false");
                TreeRoots.add(new Node("if", left, new Node(" EndOfBlock"+Integer.toString(blockNumber))));//go to
                int leftBracket = 1;
                while(true){
                    i++;
                    String temp = Tokens.get(i);
                    //System.out.println(temp);
                    if(temp.equals("(")){
                        leftBracket++;
                        interior.add("(");
                        continue;
                    }
                    if(temp.equals(",")) temp = ";";
                    else if(temp.equals(")")){
                        if(leftBracket>1)leftBracket--;
                        else{
                            interior.add(";");
                            break;
                        }
                    }
                    interior.add(temp);
                    //System.out.println(temp);
                }
                i++;
                leftBracket = 1;
                ArrayList<String>interior1 = new ArrayList<>();
                while(true){
                    i++;
                    String temp = Tokens.get(i);
                    if(temp.equals("{")){
                        leftBracket++;
                        interior1.add(temp);
                        continue;
                    }
                    if(temp.equals("}")){
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    interior1.add(temp);
                }
                manageTree(interior1);
                manageTree(interior);
                //System.out.println(interior1);
                //System.out.println(interior);
                TreeRoots.add(new Node(" StartOfWorkingBlock"+Integer.toString(blockNumber)));//go to
                TreeRoots.add(new Node("EndOfBlock"+Integer.toString(blockNumber)));
                blockNumber--;
            }
            else if(currentToken.equals("if")){
                blockNumber++;
                i++;
                int leftBracket = 1;
                ExpressionTree tree = new ExpressionTree();
                while(true){
                    i++;
                    String temp = Tokens.get(i);
                    if(temp.equals("(")){
                        leftBracket++;
                        tree.add("(");
                        continue;
                    }
                    if(temp.equals(")")){
                        if(leftBracket>1)leftBracket--;
                        else break;
                    }
                    tree.add(temp);
                }
                tree.buildTree();
                Root = new Node("if");
                Root.RightChild = new Node("EndOfBlock"+Integer.toString(blockNumber));//go to
                Root.LeftChild = new Node("==", tree.Root, new Node("false"));
                TreeRoots.add(Root);
                i++;
                
                if(Tokens.get(i).equals("{"))
                {
                    ArrayList<String>interior=new ArrayList<>();
                    leftBracket = 1;
                    while(true){
                        i++;
                        String temp = Tokens.get(i);
                        if(temp.equals("{")){
                            leftBracket++;
                            interior.add(temp);
                            continue;
                        }
                        if(temp.equals("}")){
                            if(leftBracket>1)leftBracket--;
                            else break;
                        }
                        interior.add(temp);
                    }
                    manageTree(interior);
                }
                else
                {
                    ArrayList<String>interior = new ArrayList<>();
                    while(true)
                    {
                        String temp = Tokens.get(i);
                        //System.out.println(temp);
                        interior.add(temp);
                        if(temp.equals(";"))break;
                        i++;
                    }
                    manageTree(interior);
                }
                TreeRoots.add(new Node("EndOfBlock"+blockNumber));
                blockNumber--;
            }
            else{
                 
                if(currentToken.equals(";")){
                    e1.buildTree();
                    TreeRoots.add(e1.Root);
                    e1 = new ExpressionTree();
                }
                else{
                    e1.add(currentToken);
                }
            }
        }
         
        //System.out.println(Tokens.toString()+TreeRoots.size());
    }
}