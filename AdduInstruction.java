package softwareProjectLab;

 
public class AdduInstruction <Rd, Rs, Rt> implements Instruction <Rd>
{
         
    Register<Rd> rd;
    Register<Rt> rt;
    Register<Rs> rs;
     
    public AdduInstruction(Register<Rd>rdReg, Register<Rs>rsReg, Register<Rt> rtReg)
    {
        // TODO Auto-generated constructor stub
        rd = rdReg;
        rt = rtReg;
        rs = rsReg;
        if(rd.codeVariable.className.equals("Integer"))
        {
            int temp = (Integer) rs.codeVariable.value + (Integer) rt.codeVariable.value;
            rd.codeVariable.value = (Rd) new Integer(temp);
        }
        else if(rd.codeVariable.className.equals("Character"))
        {
            char temp = (char) ((Character) rs.codeVariable.value + (Character) rt.codeVariable.value);
            rd.codeVariable.value = (Rd) new Character(temp);
        }
        else if(rd.codeVariable.className.equals("Float"))
        {
            float temp = (Float) rs.codeVariable.value + (Float) rt.codeVariable.value;
            rd.codeVariable.value = (Rd) new Float(temp);
        }
        else if(rd.codeVariable.className.equals("Double"))
        {
            double temp = (Double) rs.codeVariable.value + (Double) rt.codeVariable.value;
            rd.codeVariable.value = (Rd) new Double(temp);
        }
    }
 
    @Override
    public MainApp.InstructionType getInstructionType() 
    {
        return MainApp.InstructionType.addu;
    }
    @Override
    public String toString()
    {
        return "addu" + "\t" +rd + "\t" + rs+ "\t" + rt;
    }
 
    @Override
    public Rd getValue() 
    {
        return rd.codeVariable.value;
    }
}