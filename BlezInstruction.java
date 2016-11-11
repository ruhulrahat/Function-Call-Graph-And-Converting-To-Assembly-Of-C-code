package softwareProjectLab;

public class BlezInstruction <Rs> implements Instruction 
{
     
    Register<Rs> rs;
     
    public BlezInstruction(Register<Rs>rsReg) {
        // TODO Auto-generated constructor stub
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.blez;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "blez" + "\t" +rs.registerName.toString();
    }

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}