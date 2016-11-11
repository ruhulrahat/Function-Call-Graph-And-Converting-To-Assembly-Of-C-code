package softwareProjectLab;
 
public class Jump implements Instruction
{
     
    String jumpLevel;
     
    public Jump(String level)
    {
        jumpLevel = level;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() 
    {
    	return MainApp.InstructionType.jump;
    }
     
    @Override
    public String toString() 
    {
    	return "j" + "\t" + jumpLevel;
    }

	@Override
	public Object getValue() 
	{
		return null;
	}
 
}