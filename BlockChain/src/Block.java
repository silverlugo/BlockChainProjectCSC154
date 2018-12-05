import java.util.Arrays;

public class Block {
	private String previousHash;
	private String data;
  private int blockNum;
  private int nonce;
	private String blockHash;
  
  private static final String StrFomratChainDetials = "index: %2d |nonce: %-5d |data: %-30s |previous hash: %-64s | hash: %-64s\n";

	public Block(int blockNum, String previousHash, String data)
	{
		this.blockNum = blockNum;
		this.previousHash = previousHash;
		this.data = data;
    mine();
  }
  public void mine(){
    int i;
    String contents = "";
    
    for (i = 0; i < 1000000; i++){
      contents = blockNum + i+ previousHash + data;
      //System.out.println(contents + SHA.getSHA(blockNum + i+ previousHash + data).substring(0, 3));
      if (SHA.getSHA(contents).indexOf("000") == 0){
        break;
      }
    }
    this.nonce = i;
    this.blockHash = SHA.getSHA(contents);
  }

	public String getPreviousHas()
	{
		return previousHash;
	}
	public void hackBlock(String d)
	{		
		this.data = d;
		String contents = blockNum + previousHash + + nonce + d;
		
		this.blockHash = SHA.getSHA(contents);
	}
	public String getData()
	{
		return data;
	}
	public String getBlockHash()
	{
		return blockHash;
	}
	public int getBlockNum()
	{
		return blockNum;
  }
  public int getNonce()
	{
		return nonce;
  }
  public void setPreviousHash(String ph){
    this.previousHash = ph;
  }
  public void reHash(){
    String contents = blockNum + nonce + previousHash + data;
		this.blockHash = SHA.getSHA(contents);
  }
  @Override
  public String toString(){
    return String.format(StrFomratChainDetials, blockNum, nonce, data, previousHash, blockHash);
  }
}
