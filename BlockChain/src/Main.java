import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Main {

  private static final String StrFormat_DataAndHash = "%d | %50s | %64s\n";

  public static BlockChain[] Master;
  
  private static final String[] originalData = {"Alice", "Bob", "Sally"};

  public static void demoInitiliaze(){
    Master = new BlockChain[3];
    for (int i = 0 ; i < Master.length; i++){
      Master[i] = new BlockChain();
      Master[i].initialize();
      for (String x : originalData)
        Master[i].addBlock(x);
    }
  }
  public static void crossChecking(){
    Vector<Integer> errorIndex = new Vector<Integer>();
    for (int i = 0; i < Master[0].size(); i++){
      String hash = Master[0].getBlockHash(i);
      for (BlockChain x : Master){
        if (!x.getBlockHash(i).equals(hash)){
          errorIndex.add(i);
          break;
        }
      }
    }
    if (errorIndex.size() == 0)
      System.out.println("ALL OK");
    else{
      for (Integer i : errorIndex)
        for (BlockChain b : Master){
          b.printOneBlock(i);
        }
    }

  }

	public static void main(String[] args)
	{
    
    demoInitiliaze();
    
    for (BlockChain x : Master){
      x.CheckInvalidBlock();
      x.printChain();
    }
    Scanner kb = new Scanner(System.in);
    boolean loop = true;
		while(loop){
			System.out.println("\n1 to add block");
			System.out.println("2 to hack a block");
      System.out.println("3 to print blockChain");
      System.out.println("4 to check blockChain Validity");
      System.out.println("5 Initilalize demo");
      System.out.println("6 check blockChain with backup master");
			System.out.println("0 to Exit");
      
      String data;
			switch(kb.nextInt())
			{
				case 0: loop = false;
					break;
				case 1: //add a new block to the chain
					System.out.println("Enter data for new block");
					data = kb.next();
          for (BlockChain x: Master)
            x.addBlock(data);
					break;
				case 2:
					System.out.println("Select which block to alter");
          int blockNum = kb.nextInt();
          if (blockNum < 0 || blockNum >= Master[0].size() ){
            System.out.println("invalid Block\n");
          }
          else{
            data = kb.next();
            System.out.println("Old Data:");
            Master[0].printOneBlock(blockNum);
            Master[0].alterOneBlock(blockNum, data);
            System.out.println("New Data:");
            Master[0].printOneBlock(blockNum);
          }
          break;
        case 3:
          Master[0].printChain();
          break;
        case 4:
          Master[0].CheckInvalidBlock();
          break;
        case 5:
          demoInitiliaze();
          break;
        case 6:
          crossChecking();
          break;
        default:
          System.out.println("invalid Input\n");
          break;
          
        }
      }
    }
  }

