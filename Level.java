package softwareProjectLab;

import softwareProjectLab.MainApp.InstructionType;
 
public class Level implements Instruction
{
     String levelName;
     
    public Level(String name)
    {
        levelName = name;
    }
 
    @Override
    public InstructionType getInstructionType() 
    {
        return MainApp.InstructionType.level;
    }
     
    @Override
    public String toString() 
    {
        return levelName + ":";
    }
 
    @Override
    public Object getValue()
    {
        return null;
    }
 
}