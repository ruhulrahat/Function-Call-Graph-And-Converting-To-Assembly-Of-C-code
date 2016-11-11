package softwareProjectLab;
 
import java.util.ArrayList;
 
public class IntermediateCode {
     
    ArrayList<String>commands;
    int temporary=0;
     
    public IntermediateCode() {
        commands = new ArrayList<>();
    }
     
    public void add(Node Root){
        if(Root.LeftChild==null && Root.RightChild==null){
            commands.add(Root.Name);
            System.out.println(Root.Name+":");
        }
        else if(Root.Name.equals("declare")){
            solveForDeclaration(Root);
        }
        else solve(Root);
        temporary=0;
    }
     
    public String solve(Node Root){
        if(Root.Name.equals("if")){
            String result = "if ("+solve(Root.LeftChild)+") "+solve(Root.RightChild);
            commands.add(result);
            System.out.println("\t"+result);
            return "";
        }
        if(Root.LeftChild==null && Root.RightChild==null){
            commands.add(Root.Name);
            //System.out.println(Root.Name);
            return Root.Name;
        }
        if(Root.LeftChild==null){
            String result = "("+Root.Name + " "+solve(Root.RightChild)+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            System.out.println("\t"+result);
            return "t"+temporary;
        }
        if(Root.RightChild==null){
            String result = "("+solve(Root.LeftChild) + " "+Root.Name+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            System.out.println("\t"+result);
            return "t"+temporary;
        }
        else{
            String result = "("+solve(Root.LeftChild) + " "+Root.Name + " "+solve(Root.RightChild)+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            System.out.println("\t"+result);
            return "t"+temporary;
        }
    }
     
    public String solveForDeclaration(Node Root){
        String declare = "";
        Node current = Root.RightChild;
        while(true){
            solve(current.LeftChild);
            if(current.Name.equals(";")) break;
            current = current.RightChild;
        }
        return declare;
    }
}