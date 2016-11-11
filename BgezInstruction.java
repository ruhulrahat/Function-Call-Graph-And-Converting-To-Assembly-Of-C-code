package softwareProjectLab;

public class BgezInstruction <Rs> implements Instruction {
     
    Register<Rs> rs;
     
    public BgezInstruction(Register<Rs>rsReg) 
    {
        // TODO Auto-generated constructor stub
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.bgez;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "bgez" + "\t" +rs.registerName.toString();
    }

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}