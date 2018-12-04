import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static ArrayList<Block> PeerA = new ArrayList<>();
	public static ArrayList<String> Master = new ArrayList<>();
 	//public static ArrayList<Block> PeerB = new ArrayList<>();
	//public static ArrayList<Block> PeerC = new ArrayList<>();
	public static ArrayList<Block> Target;
	public static int size = 1;
	public static void main(String[] args)
	{
		String genData = ("Starting data");
		Block genesisBlock = new Block(size, "IV" ,genData);
		PeerA.add(genesisBlock);
		Master.add(genesisBlock.getBlockHash());
		//PeerB.add(genesisBlock);
		//PeerC.add(genesisBlock);
		size++;
		
		String data2 = ("secondary data");
		Block block2 = new Block(size,genesisBlock.getBlockHash(),data2);
		PeerA.add(block2);
		Master.add(block2.getBlockHash());
		//PeerB.add(block2);
		//PeerC.add(block2);
		size++;
		
		
		boolean loop = true; 
		Scanner kb = new Scanner(System.in);
		while(loop)
		{
			System.out.println("1 to add block");
			System.out.println("2 to hack a block");
			System.out.println("3 to display current block chain");
			System.out.println("0 to Exit");
			
			System.out.println("PeerA  Hash:"+(PeerA.get(PeerA.size()-1)).getBlockHash());
			System.out.println("Master Record:"+Master.get(Master.size()-1));
			
			
			//System.out.println("PeerB  Hash:"+(PeerB.get(PeerB.size()-1)).getBlockHash());
			//System.out.println("PeerC  Hash:"+(PeerC.get(PeerC.size()-1)).getBlockHash());
			
			switch(kb.nextInt())
			{
				case 0: loop = false;
					break;
				case 1: //add a new block to the chain
					System.out.println("Enter data for new block");
					String data = kb.next();
					Block newBlock = new Block(size, (PeerA.get(PeerA.size()-1)).getBlockHash() , data);
					PeerA.add(newBlock);
					Master.add(newBlock.getBlockHash());
					//PeerB.add(newBlock);
					//PeerC.add(newBlock);
					size++;
					break;
				case 2://hack a block to alter data
					//System.out.println("Select who's block chain who attack: A  B  C");
					//String peer = kb.next();
					System.out.println("Select which block to alter");
					for(int i = 1; i < size; i++)
					{
						System.out.print(""+(i)+",");
					}
					int blk = kb.nextInt()-1;
					
					
					Target = PeerA;
					/*switch(peer) //copy the selected peer blockchain
					{
					case "A":Target = PeerA;
						break;
					case "B": Target = PeerB;
						break;
					case "C":Target = PeerC;
						break;
					default:
						break;
					}*/
					
					
					if(Target != null)
					{
						System.out.println("Block data:"+Target.get(blk).getData());
						System.out.println("Enter replacement data:");
						String fakeData = kb.next();
						Target.get(blk).hackBlock(fakeData); //alter selected block
						
						/*switch(peer)//alter the selected peers blockchain
						{
						case "A": PeerA = Target;
							break;
						case "B":  PeerB = Target ;
							break;
						case "C": PeerC = Target;
							break;
						default:
							break;
						}
						Target = null;*/
					}
					break;
					
				case 3://display current block chain					
					//.get(size-1).getBlockHash() will grab the last block in the chain and return its hash
					boolean change = false;
					for(int j = 0; j < Master.size(); j++)
					{
						if(Master.get(j) != (PeerA.get(j).getBlockHash()))
						{
							change = true;
						}
					}
					if(change == true)
					{
						System.out.println("Change Detected");
					}
					else { System.out.println("No Change detected");}
					
					/*if(PeerA.get(size-2).getBlockHash() == PeerB.get(size-2).getBlockHash() && PeerA.get(size-2).getBlockHash() == PeerC.get(size-2).getBlockHash())
					{
						System.out.println("No Change Detected ");
					}
					else if(PeerA.get(size-2).getBlockHash() != PeerB.get(size-2).getBlockHash() && PeerA.get(size-2).getBlockHash() != PeerC.get(size-2).getBlockHash())
					{
						//PeerA altered
						System.out.println("WARNING: PeerA has a corrupted Block Chain");
					}
					else if(PeerB.get(size-2).getBlockHash() != PeerA.get(size-2).getBlockHash() && PeerB.get(size-2).getBlockHash() != PeerC.get(size-2).getBlockHash())
					{
						//PeerB altered
						System.out.println("WARNING: PeerB has a corrupted Block Chain");
					}
					else
					{
						//PeerC must have been altered
						System.out.println("WARNING: PeerC has a corrupted Block Chain");
					}*/
					break;
			}//Main Menu Selection Switch
			
		}//while loop
		
	}
	
}
