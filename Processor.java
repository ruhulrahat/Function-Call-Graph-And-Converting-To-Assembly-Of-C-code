
package softwareProjectLab;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 
import javax.swing.JFrame;
 
public class Processor
{
     
    Scanner Input;
 
    public Processor(File file) 
    {
        try 
        {
            Input = new Scanner(file);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
     
    ArrayList<String> tokens = new ArrayList<>();
    ArrayList<String> customCharacters = new ArrayList<>();
    String tokenName = "";
    String previous = "";
     
    public void process()
    {
        String customs[] = {"&&","||","==","<=",">="};
        for(int i=0; i<customs.length; i++){
            customCharacters.add(customs[i]);
        }
        while(Input.hasNext())
        {
            String line = Input.nextLine();
            if(line.length()==0 ||  line.charAt(0)=='/' || line.charAt(0)=='#') 
            	continue;
            
            for(int i=0; i<line.length(); i++)
            {
                processLine(line.charAt(i));
            }
        }
    }
    public void processLine(char ch)
    {
        if(customCharacters.contains(new String(previous+ch)))
        {
            tokens.remove(tokens.size()-1);
            tokens.add(previous+ch);
        }
        else if(Character.isLetter(ch) || Character.isDigit(ch) || ch=='.')
        	tokenName += ch;
        else
        {
            if(!tokenName.equals(""))
            {
                tokens.add(tokenName);
                previous =  tokenName;
                tokenName = "";
            }
            if(ch==' ' || ch=='\t') return;
            if(ch==';')
            {
                tokens.add(Character.toString(';'));    
            }
            else
            {
                tokens.add(new String(Character.toString(ch)));
                previous = Character.toString(ch);
            }
        }
    }
    public void buildSDT()
    {
        //System.out.println(tokens);
        CallGraphBuilder call = new CallGraphBuilder(tokens);
        call.functions.get("main").functionGraph(0);
        /*for(String token: tokens){
            System.out.print(token+" ");
        }*/
        /*SDT sdt = new SDT(tokens);
        sdt.manageTree(sdt.tokens);
        MipsCodeGenerator m = new MipsCodeGenerator();
        for(Node Root: sdt.TreeRoots){
            m.add(Root);
        }*/
        /*m.print();
        IntermediateCode i = new IntermediateCode();
        for(Node Root: sdt.TreeRoots){
            i.add(Root);
        }
        /*int n=1;
        for(Node Root:sdt.TreeRoots){
            TreePrinter t = new TreePrinter(Root,n);
            t.setSize(1300, 900);
            t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            t.setVisible(true);
            n++;
        }*/
    }
}