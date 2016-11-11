package softwareProjectLab;
 
public class BltzInstruction <Rs> implements Instruction 
{
     
    Register<Rs> rs;
     
    public BltzInstruction(Register<Rs>rsReg) 
    {
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType()
    {
        return MainApp.InstructionType.bltz;
    }
    @Override
    public String toString()
    {
        return "bltz" + "\t" +rs.registerName.toString();
    }

	@Override
	public Object getValue()
	{
	
		return rs;
	}
}