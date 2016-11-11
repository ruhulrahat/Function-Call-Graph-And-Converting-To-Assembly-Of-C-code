package softwareProjectLab;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
public class CallGraphBuilder
{
     
    Map<String,Function> functions = new HashMap<>();
    ArrayList<Function> functionList = new ArrayList<>();
     
    public CallGraphBuilder(ArrayList<String> tokens)
    {
        saveAllFunctions(tokens);   
    }
     
    void saveAllFunctions(ArrayList<String> tokens)
    {
         
        Function f = new Function("int", "scanf");
        functionList.add(f);
        functions.put("scanf", f);
        
        f = new Function("int", "printf");
        functionList.add(f);
        functions.put("printf", f);
         
        String type = "";
        String name = "";
         
        for(int i=1; i < tokens.size(); i++)
        {
            if(tokens.get(i).equals("("))
            {
                name = tokens.get(i-1);
                if(i>1) type = tokens.get(i-2);
            }
            else if(tokens.get(i).equals("{") && tokens.get(i-1).equals(")"))
            {
                if(!isDataType(type)) type = "void";
                f = new Function(type, name);
                functions.put(name, f);
                functionList.add(f);
                 
                while(true)
                {
                    i++;
                    if(tokens.get(i).equals("}")) break;
                    
                    else if(functions.containsKey(tokens.get(i)))
                    {
                        functions.get(name).addCalledFunction(functions.get(tokens.get(i)));
                    }
                }
            }
        }
    }
     
    boolean isOperator(String str){
        if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) return true;
        else return false;
    }
     
    boolean isDataType(String str){
        if(str.equals("int") || str.equals("float") || str.equals("double") || str.equals("float")) return true;
        else return false;
    }
}