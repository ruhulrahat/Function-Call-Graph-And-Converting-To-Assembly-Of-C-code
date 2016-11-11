package softwareProjectLab;
 
public class SrlInstruction <Rd, Rs, Rt> implements Instruction {
     
    Register<Rd> rd;
    Register<Rt> rt;
    Register<Rs> rs;
     
    public SrlInstruction(Register<Rd>rdReg, Register<Rs>rsReg, Register<Rt> rtReg) {
        // TODO Auto-generated constructor stub
        rd = rdReg;
        rt = rtReg;
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.srl;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "srl" + "\t" +rd.registerName.toString() + "\t" + rs.registerName.toString()+ "\t" + rt.registerName.toString();
    }

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}