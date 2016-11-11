package softwareProjectLab;
 
public class SraInstruction <Rd, Rs, Rt> implements Instruction
{
    Register<Rd> rd;
    Register<Rt> rt;
    Register<Rs> rs;
     
    public SraInstruction(Register<Rd>rdReg, Register<Rs>rsReg, Register<Rt> rtReg) 
    {
        rd = rdReg;
        rt = rtReg;
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.sra;
    }
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "sra" + "\t" +rd.registerName.toString() + "\t" + rs.registerName.toString()+ "\t" + rt.registerName.toString();
    }
}