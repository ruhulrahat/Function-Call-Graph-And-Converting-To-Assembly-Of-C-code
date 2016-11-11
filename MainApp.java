package softwareProjectLab;
 
import java.io.File;
 
 
public class MainApp
{
     
    public static enum Register {$zero,$at,$v0,$v1,$a0,$a1,$a2,$a3,$t0,$t1,$t2,$t3,$t4,$t5,$t6,$t7,$s0,$s1,$s2,$s3,$s4,$s5,$s6,$s7,$t8,$t9,$k0,$k1,$gp,$sp,$fp,$ra};
    public static enum InstructionType {level,addu,subu,or,xor,nor,sll,srl,sra,multu,divu,jump,beq,bne,blez,bgez,bltz,bgtz,la};
    public static enum Types {Variable,DataType,Comparator,Operator,Function,Parenthesis,Punctuation,Constant,KeyWords,Defaults};
    public static enum BlockTypes {Function,Loop,Condition};
    public static enum DataTypes {Integer,Float,Double,Character,Void};
     
    public static void main(String[] args) 
    {
        File file = new File("C:\\Users\\Rahat-PDM\\workspace\\spl-1\\src\\softwareProjectLab\\rec.txt");
        Processor processor = new Processor(file);
        processor.process();
        processor.buildSDT();
    }
}