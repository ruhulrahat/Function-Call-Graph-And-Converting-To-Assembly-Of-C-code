package softwareProjectLab;
 
public class BneInstruction <Rs, Rt> implements Instruction 
{
     
    Register<Rs> rs;
    Register<Rt> rt;
     
    public BneInstruction(Register<Rs>rsReg, Register<Rt> rdReg) 
    {
        // TODO Auto-generated constructor stub
        rs = rsReg;
        rt = rdReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType()
    {
        return MainApp.InstructionType.bne;
    }
    @Override
    public String toString()
    {
        return "bne" + "\t" + rs.registerName.toString()+ "\t" + rt.registerName.toString();
    }
}