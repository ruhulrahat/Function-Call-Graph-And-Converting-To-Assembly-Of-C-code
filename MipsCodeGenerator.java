package softwareProjectLab;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
public class MipsCodeGenerator
{
     
	ArrayList<Instruction> instructions;
    Map<String, MainApp.Register> savedRegisterMap = new HashMap<>();
    Map<String, MainApp.Register> temporaryRegisterMap = new HashMap<>();
    Map<String, Register> usedVariables = new HashMap<>();
    Map<String, Integer> priority = new HashMap<>();
     
    public MipsCodeGenerator() 
    {
        instructions = new ArrayList<>();
        setMap();
        setPriority();
    }
     
    ArrayList<String> commands;
    int temporary = 0;
    int registerNumber = 0;
 
     
    public void add(Node Root)
    {
        if(Root.LeftChild==null && Root.RightChild==null)
        {
            //commands.add(Root.Name);
            if(Root.Name.charAt(0) == ' ') 
            	instructions.add(new Jump(Root.Name.substring(1)));
            else 
            	instructions.add(new Level(Root.Name));
            //System.out.println(Root.Name+":");
        }
        else if(Root.Name.equals("declare"))
        {
            solveForDeclaration(Root);
        }
        else solve(Root);
        temporary=0;
    }
     
    public <Generic> Register<Generic> solve(Node Root)
    {
         
        if(Root.Name.equals("if"))
        {	
        	return null;
        }
        if(Root.LeftChild==null && Root.RightChild==null)
        {
            //commands.add(Root.Name);
            //System.out.println(Root.Name);
        	
            String name = Root.Name;
            
            if(Character.isDigit(name.charAt(0)) || name.charAt(0) == '.')
            {
                if(name.contains("."))
                {
                    Register<Double> toReturn = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary, Double.parseDouble(name)));
                    temporary++;
                    return (Register<Generic>) toReturn;
                }
                else
                {
                    Register<Integer>toReturn = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary, new Integer(name)));
                    temporary++;
                    return (Register<Generic>) toReturn;
                }
            }
            else if(name.charAt(0) == '\'')
            {
                Register<Character> toReturn = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary, new Character(name.charAt(1))));
                temporary++;
                return (Register<Generic>) toReturn;
            }
            else
            {
                if(usedVariables.containsKey(name))
                {
                    return usedVariables.get(name);
                }
                else
                {
                    Register<Integer> toReturn = new Register<Integer>(savedRegisterMap.get("$s"+registerNumber), new Variable<Integer>(name, -1));
                    registerNumber++;
                    return (Register<Generic>) toReturn;
                }
            }
        }
        /*if(Root.LeftChild==null){
            String result = "("+Root.Name + " "+solve(Root.RightChild)+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            //System.out.println("\t"+result);
            return "t"+temporary;
        }
        if(Root.RightChild==null){
            String result = "("+solve(Root.LeftChild) + " instr"+Root.Name+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            //System.out.println("\t"+result);
            return "t"+temporary;
        }*/
        else
        {
            /*String result = "("+solve(Root.LeftChild) + " "+Root.Name + " "+solve(Root.RightChild)+")";
            temporary++;
            result = "t"+temporary + "=" + result;
            commands.add(result);
            //System.out.println("\t"+result);
            return "t"+temporary;*/
        	
            String name = Root.Name;
            
            if(name.equals("="))
            {
                Register<Generic>rd = solve(Root.LeftChild);
                Register<Generic>rs = solve(Root.RightChild);
                String rdClass = rd.codeVariable.className;
                String rsClass = rs.codeVariable.className;
                 
                if(rdClass.equals("Integer") && rsClass.equals("Integer"))
                {
                    LaInstruction<Integer, Integer> ins = new LaInstruction(rd, rs);
                    Register<Integer> toReturn = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Integer") && rsClass.equals("Character"))
                {
                    LaInstruction<Integer, Character> ins = new LaInstruction(rd, rs);
                    Register<Integer> toReturn = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Integer") && rsClass.equals("Float"))
                {
                    LaInstruction<Integer, Float> ins = new LaInstruction(rd, rs);
                    Register<Integer> toReturn = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Integer") && rsClass.equals("Double"))
                {
                    LaInstruction<Integer, Double> ins = new LaInstruction(rd, rs);
                    Register<Integer> toReturn = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                 
                else if(rdClass.equals("Character") && rsClass.equals("Integer"))
                {
                    LaInstruction<Character, Integer> ins = new LaInstruction(rd, rs);
                    Register<Character> toReturn = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Character") && rsClass.equals("Character"))
                {
                    LaInstruction<Character, Character> ins = new LaInstruction(rd, rs);
                    Register<Character> toReturn = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Character") && rsClass.equals("Float"))
                {
                    LaInstruction<Character, Float> ins = new LaInstruction(rd, rs);
                    Register<Character> toReturn = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Character") && rsClass.equals("Double"))
                {
                    LaInstruction<Character, Double> ins = new LaInstruction(rd, rs);
                    Register<Character> toReturn = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                 
                else if(rdClass.equals("Float") && rsClass.equals("Integer"))
                {
                    LaInstruction<Float, Integer> ins = new LaInstruction(rd, rs);
                    Register<Float> toReturn = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Float") && rsClass.equals("Character"))
                {
                    LaInstruction<Float, Character> ins = new LaInstruction(rd, rs);
                    Register<Float> toReturn = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Float") && rsClass.equals("Float"))
                {
                    LaInstruction<Float,Float> ins = new LaInstruction(rd, rs);
                    Register<Float> toReturn = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Float") && rsClass.equals("Double"))
                {
                    LaInstruction<Float, Double> ins = new LaInstruction(rd, rs);
                    Register<Float> toReturn = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                 
                else if(rdClass.equals("Double") && rsClass.equals("Integer"))
                {
                    LaInstruction<Double, Integer> ins = new LaInstruction(rd, rs);
                    Register<Double> toReturn = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Double") && rsClass.equals("Character"))
                {
                    LaInstruction<Double,Character> ins = new LaInstruction(rd, rs);
                    Register<Double> toReturn = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Double") && rsClass.equals("Float"))
                {
                    LaInstruction<Double,Float> ins = new LaInstruction(rd, rs);
                    Register<Double> toReturn = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                else if(rdClass.equals("Double") && rsClass.equals("Double"))
                {
                    LaInstruction<Double, Double> ins = new LaInstruction(rd, rs);
                    Register<Double> toReturn = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary,ins.getValue() ));
                    temporary++;
                    instructions.add(ins);
                    System.out.println(ins);
                    return (Register<Generic>) toReturn;
                }
                 
            }
            else if(name.equals("+"))
            {
                Register<Generic> rs = solve(Root.LeftChild);
                Register<Generic> rt = solve(Root.RightChild);
                
                String largerBucket = getLargerBucket(rs.codeVariable.className, rt.codeVariable.className);
                if(largerBucket.equals("Integer"))
                {
                    Register<Integer> rd = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary, -1));
                    AdduInstruction<Integer, Integer, Integer> ins = new AdduInstruction<Integer,Integer,Integer>(rd,(Register<Integer>)rs,(Register<Integer>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Character"))
                {
                    Register<Character> rd = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary, '/'));
                    AdduInstruction<Character, Character, Character> ins = new AdduInstruction<Character,Character,Character>(rd,(Register<Character>)rs,(Register<Character>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Float"))
                {
                    Register<Float> rd = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary, (float)-1.0));
                    AdduInstruction<Float, Float, Float> ins = new AdduInstruction<Float,Float,Float>(rd,(Register<Float>)rs,(Register<Float>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Double"))
                {
                    Register<Double>rd = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary, -1.0));
                    AdduInstruction<Double, Double, Double> ins = new AdduInstruction<Double,Double,Double>(rd,(Register<Double>)rs,(Register<Double>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
            }
            else if(name.equals("-"))
            {
                Register<Generic>rs = solve(Root.LeftChild);
                Register<Generic>rt = solve(Root.RightChild);
                String largerBucket = getLargerBucket(rs.codeVariable.className, rt.codeVariable.className);
                if(largerBucket.equals("Integer"))
                {
                    Register<Integer>rd = new Register<Integer>(temporaryRegisterMap.get("$t"+temporary), new Variable<Integer>("temporary"+temporary, -1));
                    SubuInstruction<Integer, Integer, Integer> ins = new SubuInstruction<Integer,Integer,Integer>(rd,(Register<Integer>)rs,(Register<Integer>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Character"))
                {
                    Register<Character>rd = new Register<Character>(temporaryRegisterMap.get("$t"+temporary), new Variable<Character>("temporary"+temporary, '/'));
                    SubuInstruction<Character, Character, Character> ins = new SubuInstruction<Character,Character,Character>(rd,(Register<Character>)rs,(Register<Character>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Float"))
                {
                    Register<Float>rd = new Register<Float>(temporaryRegisterMap.get("$t"+temporary), new Variable<Float>("temporary"+temporary, (float)-1.0));
                    SubuInstruction<Float, Float, Float> ins = new SubuInstruction<Float,Float,Float>(rd,(Register<Float>)rs,(Register<Float>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
                else if(largerBucket.equals("Double"))
                {
                    Register<Double>rd = new Register<Double>(temporaryRegisterMap.get("$t"+temporary), new Variable<Double>("temporary"+temporary, -1.0));
                    SubuInstruction<Double, Double, Double> ins = new SubuInstruction<Double,Double,Double>(rd,(Register<Double>)rs,(Register<Double>)rt);
                    instructions.add(ins);
                    temporary++;
                    System.out.println(ins);
                    return (Register<Generic>) rd;
                }
            }
            return null;
        }
    }
     
    public String solveForDeclaration(Node Root)
    {
        String declare = "";
        Node current = Root.RightChild;
        while(true)
        {
            solve(current.LeftChild);
            if(current.Name.equals(";")) break;
            current = current.RightChild;
        }
        return declare;
    }
     
    private String getLargerBucket(String a, String b)
    {
        if(priority.get(a) < priority.get(b)) return a;
        return b;
    }
     
    private void setPriority()
    {
        priority.put("Integer", 1);
        priority.put("Character", 2);
        priority.put("Float", 3);
        priority.put("Double", 4);
    }
     
    private void setMap()
    {
        savedRegisterMap.put("$s0", MainApp.Register.$s0);
        savedRegisterMap.put("$s1", MainApp.Register.$s1);
        savedRegisterMap.put("$s2", MainApp.Register.$s2);
        savedRegisterMap.put("$s3", MainApp.Register.$s3);
        savedRegisterMap.put("$s4", MainApp.Register.$s4);
        savedRegisterMap.put("$s5", MainApp.Register.$s5);
        savedRegisterMap.put("$s6", MainApp.Register.$s6);
        savedRegisterMap.put("$s7", MainApp.Register.$s7);
         
        temporaryRegisterMap.put("$t0", MainApp.Register.$t0);
        temporaryRegisterMap.put("$t1", MainApp.Register.$t1);
        temporaryRegisterMap.put("$t2", MainApp.Register.$t2);
        temporaryRegisterMap.put("$t3", MainApp.Register.$t3);
        temporaryRegisterMap.put("$t4", MainApp.Register.$t4);
        temporaryRegisterMap.put("$t5", MainApp.Register.$t5);
        temporaryRegisterMap.put("$t6", MainApp.Register.$t6);
        temporaryRegisterMap.put("$t7", MainApp.Register.$t7);
        temporaryRegisterMap.put("$t8", MainApp.Register.$t8);
        temporaryRegisterMap.put("$t9", MainApp.Register.$t9);
    }
     
    void print(){
        //System.out.println(instructions);
    }
 
}