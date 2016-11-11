package softwareProjectLab;
 
public class DivuInstruction <Rd, Rs, Rt> implements Instruction {
     
    Register<Rd> rd;
    Register<Rt> rt;
    Register<Rs> rs;
     
    public DivuInstruction(Register<Rd>rdReg, Register<Rs>rsReg, Register<Rt> rtReg) {
        // TODO Auto-generated constructor stub
        rd = rdReg;
        rt = rtReg;
        rs = rsReg;
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() {
        // TODO Auto-generated method stub
        return MainApp.InstructionType.divu;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "divu" + "\t" +rd.registerName.toString() + "\t" + rs.registerName.toString()+ "\t" + rt.registerName.toString();
    }
}