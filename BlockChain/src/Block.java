import java.util.Arrays;

public class Block {
	private String previousHash;
	private String data;
	private int blockNum;
	private String blockHash;
	
	public Block(int blockNum, String previousHash, String data)
	{
		this.blockNum = blockNum;
		this.previousHash = previousHash;
		this.data = data;
		String contents = blockNum + previousHash + data;
    this.blockHash = SHA.getSHA(contents);
  }
	public String getPreviousHas()
	{
		return previousHash;
	}
	public void hackBlock(String d)
	{		
		data = d;
		String contents = blockNum + d + previousHash;
		
		blockHash = SHA.getSHA(contents);
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
}
