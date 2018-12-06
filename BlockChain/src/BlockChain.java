import java.util.Vector;

public class BlockChain{
  private Vector<Block> chains;
  private static final String StrFomratHeader1 = " index | nonce |             data               |                     previous hash                                |                            hash                              \n";
  private static final String StrFomratHeader2 = " -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
  private static final String StrFomratHeader = StrFomratHeader1 + StrFomratHeader2;
  private static final String IV = "HelloWorld";
  private static final String StrFomratHashCheck = "Error: Block: %d |hashcheck: %-64s | hashRecord: %-64s\n";
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

  public void rehashing(){
    chains.get(0).reHash();
    for (int i = 1; i < size(); i++){
      Block x = chains.get(i);
      x.setPreviousHash(chains.get(i-1).getBlockHash());
      x.reHash();
    }
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
        hashCheck = SHA.getSHA(0 + this.chains.get(i).getNonce() + IV + this.chains.get(i).getData());
      else
        hashCheck = SHA.getSHA(i + this.chains.get(i).getNonce() + this.chains.get(i).getPreviousHas() + this.chains.get(i).getData());
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
    System.out.printf(StrFomratHeader);
    for (int i = 0; i < size(); i++){
      Block x = this.chains.get(i);
      System.out.print(x.toString());
    }
  }

  public void alterOneBlock(int i, String newData){
    if (i < 0 || i>= size())
      System.out.println("Error index i;\n");
    else {
      Block alterBlock = chains.get(i);
      alterBlock.hackBlock(newData);
    }
    this.rehashing();
  }

  public void printOneBlock(int i){
    if (i < 0 || i>= size())
      System.out.println("Error index i;\n");
    else {
      Block x = this.chains.get(i);
      System.out.print(x.toString());
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

}