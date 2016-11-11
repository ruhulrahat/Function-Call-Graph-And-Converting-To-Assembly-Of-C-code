package softwareProjectLab;

 
public class BeqInstruction <Rs, Rt> implements Instruction 
{
     
    Register<Rs> rs;
    Register<Rt> rt;
     
    public BeqInstruction(Register<Rs>rsReg, Register<Rt> rdReg) 
    {
        // TODO Auto-generated constructor stub
        rs = rsReg;
        rt = rdReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() 
    {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.beq;
    }
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "srl" + "\t" + rs.registerName.toString()+ "\t" + rt.registerName.toString();
    }

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}