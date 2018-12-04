import java.util.Arrays;

public class Block {
	private int previousHash;
	private String data;
	private int blockNum;
	private int blockHash;
	
	public Block(int blockNum,int previousHash, String data)
	{
		this.blockNum = blockNum;
		this.previousHash = previousHash;
		this.data = data;
		Object[] contents = {blockNum, data, previousHash};
		
		this.blockHash = Arrays.hashCode(contents);
	}
	public int getPreviousHas()
	{
		return previousHash;
	}
	public void hackBlock(String d)
	{		
		data = d;
		Object[] contents = {blockNum, d, previousHash};
		
		blockHash = Arrays.hashCode(contents);
	}
	public String getData()
	{
		return data;
	}
	public int getBlockHash()
	{
		return blockHash;
	}
	public int getBlockNum()
	{
		return blockNum;
	}
}
