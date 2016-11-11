package softwareProjectLab;
 
public class Block
{
     
    int BlockNumber;
    String BlockName;
    MainApp.BlockTypes BlockType;
     
    public Block(int blockNumber, String blockName, MainApp.BlockTypes blockType)
    {
        BlockNumber = blockNumber;
        BlockName = blockName;
        BlockType = blockType;
    }
 
}