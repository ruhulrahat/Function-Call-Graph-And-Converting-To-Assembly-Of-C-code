package softwareProjectLab;
 
public class LaInstruction <Rd, Rs> implements Instruction <Rd> 
{
     
    Register<Rd> rd;
    Register<Rs> rs;
     
    public LaInstruction(Register<Rd> rdReg, Register<Rs> rsReg)
    {
        rd = rdReg;
        rs  = rsReg;
        rd.codeVariable.value = (Rd) rs.codeVariable.value;
    }
    
    @Override
    public MainApp.InstructionType getInstructionType()
    {
        return MainApp.InstructionType.la;
    }
    
    @Override
    public String toString()
    {
        return "la" + "\t" + rd + "\t" + rs;
    }
    @Override
    public Rd getValue()
    {
        return rd.codeVariable.value;
    }
}