import java.util.Vector;

public class BlockChain{
  private Vector<Block> chains;

  private static final String IV = "HelloWorld";
  private static final String StrFomratHashCheck = "Error: Block: %d |hashcheck: %-64s | hashRecord: %-64s\n";
  private static final String StrFomratChainDetials = "index: %d |data: %-30s |previous hash: %-64s | hash: %-64s\n";
  public BlockChain(){
    chains = new Vector<Block>();
  }
  public int size(){
    return chains.size();
  }
  public void initialize(){
    this.chains = new Vector<Block>();
    this.chains.add(new Block(0, IV, IV));
  }
  public void addBlock(String data){
      this.chains.add(new Block(this.size(), chains.get(this.size()-1).getBlockHash(), data));
  }
  public void CheckInvalidBlock(){
    System.out.print("\nChecking Validilty of Blocks....");
    boolean isValid = true;
    for (int i = 0; i < size(); i++){
      String hashCheck;
      if (i == 0)
        hashCheck = SHA.getSHA(0 + IV + this.chains.get(i).getData());
      else
        hashCheck = SHA.getSHA(i + this.chains.get(i-1).getBlockHash() + this.chains.get(i).getData());
      if (!hashCheck.equals( chains.get(i).getBlockHash() )) {
        System.out.printf(StrFomratHashCheck, i, hashCheck, this.chains.get(i).getBlockHash());
        isValid = false;
      }
    }
    if (isValid)
      System.out.println("OK\n");
    else
    System.out.println("Not Consistent\n");
  }

  public void printChain(){
    for (int i = 0; i < size(); i++){
      Block x = this.chains.get(i);
      System.out.printf(StrFomratChainDetials, x.getBlockNum(), x.getData(), x.getPreviousHas(), x.getBlockHash());
    }
  }

  public void alterOneBlock(int i, String newData){
    if (i < 0 || i>= size())
      System.out.println("Error index i;\n");
    else {
      Block alterBlock = chains.get(i);
      alterBlock.hackBlock(newData);
    }
  }

  public void printOneBlock(int i){
    if (i < 0 || i>= size())
      System.out.println("Error index i;\n");
    else {
      Block x = this.chains.get(i);
      System.out.printf(StrFomratChainDetials, x.getBlockNum(), x.getData(), x.getPreviousHas(), x.getBlockHash());
    }
  }

  public String getBlockHash(int i){
    if (i < 0 || i>= size()){
      System.out.println("Error index i;\n");
      return "";
    }
    else
      return chains.get(i).getBlockHash();
  }
  /*
  public void crossChecking(BlockChain[] ref){
    System.out.println("Check master BlockChain:");
    this.CheckInvalidBlock();
    for(int i = 0; i < ref.length; i++){
      System.out.printf("Check backup %d BlockChain:", i);
      ref[i].CheckInvalidBlock();
    }
    boolean isValid = true;
    for (int i = 0; i < size(); i++)
      if (!this.chains.get(i).getBlockHash().equals(ref.chains.get(i).getBlockHash())){
        System.out.printf(StrFomratChainDetialsWithName, "Current", i, this.chains.get(i).getBlockHash());
        for (BlockChain x : ref)
          System.out.printf(StrFomratChainDetialsWithName, "BackUp", i, this.chains.get(i).getBlockHash());
        isValid = false;
      }
    if (isValid)
      System.out.println("OK\n");
    else
      System.out.println("Not Consistent\n");

  }
  */
}