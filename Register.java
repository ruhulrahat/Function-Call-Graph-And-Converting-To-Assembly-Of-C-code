package softwareProjectLab;
 
public class Register <Generic> 
{
     
    MainApp.Register registerName;
    Variable<Generic> codeVariable;
     
    public Register(MainApp.Register name, Variable<Generic> variable)
    {
        registerName = name;
        codeVariable = variable;
    }
     
    @Override
    public String toString()
    {
        return registerName.toString() + "(" + codeVariable.value + ")";
    }
 
}