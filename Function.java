package softwareProjectLab;
 
import java.util.ArrayList;
 
public class Function
{
     
    String returnType;
    String name;
    ArrayList<Function>calledFunctions;
     
    public Function(String type, String functionName)
    {
        // TODO Auto-generated constructor stub
        returnType = type;
        name = functionName;
        calledFunctions = new ArrayList<>();
    }
     
    void addCalledFunction(Function function)
    {
        calledFunctions.add(function);
    }
     
    @Override
    public String toString()
    {
        String result = "[" + name + "(),";
        for(Function f: calledFunctions)
        {
            result += f.toString();
        }
        return result+"]";
    }
     
    void functionGraph(int spaces)
    {
        for(int i=0 ; i < spaces; i++) 
        	System.out.print(" ");
        
        System.out.println(name+"()");
        
        for(Function f: calledFunctions)
        	f.functionGraph(spaces + 20);
    }
}