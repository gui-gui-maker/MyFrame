package com.edu.util.dbf;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {
	
	private ByteBuffer byteBuffer = null;
	private int bufSize = 0;
	
	private List<byte[]> byteArrayList = null;
	private int bytesSize = 0;
	
	public CacheManager()
	{
	}
	
	public ByteBuffer getByteBuffer(int size)
	{
		if(this.bufSize < size)
		{
			byteBuffer = ByteBuffer.allocateDirect(size + 1024*8); //多分配一些，避免下次重新分配
			this.bufSize = size + 1024*8;
		}
		byteBuffer.clear();
		return byteBuffer;
	}
	
	public List<byte[]> getByteArrayList(int rowNum, int byteLength)
	{
		if(this.bytesSize!=byteLength)
		{
			byteArrayList = new ArrayList<byte[]>();
			this.bytesSize = byteLength;
		}
		
		if(byteArrayList.size() < rowNum)
		{
			int shouldAddRowCount = rowNum - byteArrayList.size()+100; //多分配100行
			for(int i=0; i<shouldAddRowCount; i++) 
			{
				byteArrayList.add(new byte[bytesSize]);
			}
		}
		
		return byteArrayList;
	}
	
}
