package softwareProjectLab;
 
public class Variable <Generic> extends Token
{
     
    Generic value;
    String className;
     
    public Variable(String name, Generic val) 
    {
        tokenType = MainApp.Types.Variable;
        tokenName = name;
        value = val;
        className = value.getClass().getSimpleName();
    }
 
}