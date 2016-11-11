package softwareProjectLab;

 
public interface Instruction <Rd> 
{
     
    public MainApp.InstructionType getInstructionType();
    @Override
    public String toString();
    public Rd getValue();
}