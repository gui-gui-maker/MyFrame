package com.guido.util.dbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

//import org.apache.commons.lang3.StringUtils;

/**
 * DBF 文件读取器
 * 
 * @author ld
 */
public class DBFReader {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/DD");
    private static final byte HEAD_LOC = 0;
    private static final byte VERSION_LOC = HEAD_LOC, VERSION_LEN = 1;
    private static final byte UPDATE_LOC = VERSION_LOC + VERSION_LEN, UPDATE_LEN = 3;
    private static final byte COUNT_LOC = UPDATE_LOC + UPDATE_LEN, COUNT_LEN = 4;
    private static final byte DATAIDX_LOC = COUNT_LOC + COUNT_LEN, DATAIDX_LEN = 2;
    private static final byte LENGTH_LOC = DATAIDX_LOC + DATAIDX_LEN, LENGTH_LEN = 2;
    private static final byte RESERVED1_LOC = LENGTH_LOC + LENGTH_LEN, RESERVED1_LEN = 16;
    private static final byte TABLETAG_LOC = RESERVED1_LOC + RESERVED1_LEN, TABLETAG_LEN = 1;
    private static final byte PAGETAG_LOC = TABLETAG_LOC + TABLETAG_LEN, PAGETAG_LEN = 1;
    private static final byte RESERVED2_LOC = PAGETAG_LOC + PAGETAG_LEN, RESERVED2_LEN = 2;
    private static final byte HEAD_LEN = RESERVED2_LOC + RESERVED2_LEN;

    private static final byte FIELD_LOC = HEAD_LEN;

    private final File file;
    private final RandomAccessFile dbf;
    private final FileChannel fc;
    private final Charset charset;

    private int version;
    private byte[] update;
    private int count;
    private int dataidx;
    private int length;
    private int tabletag;
    private int pagetag;
    private Field[] fields;

    public static void main(String[] args) {
        try {
            try {

                long tsBegin = System.nanoTime();
                DBFReader dbfreader_SH = new DBFReader(new File("C://Users//Guido//Desktop//workspace//check-sys//5112.dbf"));
                Field[] fields = dbfreader_SH.getFields();
                for (int i = 0; i < fields.length; i++) {
                	
                	System.out.println(fields[i].getName());
				}
                List<DBFReader.Record> list_sh = dbfreader_SH.loadRecords();
			
                long tsEnd = System.nanoTime();
                System.out.println("count=" + list_sh.size() + "time=" + (tsEnd - tsBegin));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
    }

    public DBFReader(final File file) throws FileNotFoundException, IOException {
        this(file, Charset.forName("gbk"));
    }

    public DBFReader(final File file, final Charset charset) throws FileNotFoundException, IOException {

        this.dbf = new RandomAccessFile(file, "r");
        this.fc = dbf.getChannel();
        this.charset = charset;
        this.file = file;

        load(true);
    }

    /**
     * 获取此读取的所指向的文件对象
     * 
     * @return 文件对象
     */
    public File getFile() {
        return this.file;
    }

    /**
     * 重新读取文件，使此对象与文件内容保持一致
     * 
     * @param all
     *            指示是否将所有字段信息一同载入内存
     * @throws IOException
     *             读取文件失败
     */
    public void load(boolean all) throws IOException {
        loadHead();
        if (all)
            loadField();
    }

    /**
     * 装载文件头信息
     * 
     * @throws IOException
     *             读取文件失败
     */
    private void loadHead() throws IOException {

        ByteBuffer bb = loadData(HEAD_LOC, HEAD_LEN);
        orderBytes(bb);

        version = bb.get();
        update = new byte[UPDATE_LEN];
        bb.get(update);
        count = bb.getInt();
        dataidx = bb.getShort();
        length = bb.getShort();
        bb.position(bb.position() + RESERVED1_LEN);
        tabletag = bb.get();
        pagetag = bb.get();

        bb.clear();

        // if (count == 0) {
        long fileSize = this.file.length();
        long countSize = fileSize - dataidx;
        if (length != 0) {
            count = (int) countSize / length;
        }
        // }
    }

    /**
     * 装载字段信息
     * 
     * @throws IOException
     *             读取文件失败
     */
    private void loadField() throws IOException {

        ByteBuffer bb = loadData(FIELD_LOC, getFieldCount() * Field.LENGTH);
        orderBytes(bb);

        Field[] fds = new Field[getFieldCount()];
        for (int i = 0; i < fds.length; i++) {
        	System.out.println(i+"----"+bb);
            fds[i] = new Field(i, bb);
        }
        fields = fds;

        bb.clear();
    }

    /**
     * 将所有记录一次性载入内存
     * 
     * @throws IOException
     *             读取文件失败
     */
    private List<Record> loadRecordsJustDel() throws IOException {

        ByteBuffer bb = loadData(getDataIndex(), getCount() * getRecordLength());

        List<Record> rds = new ArrayList<Record>(getCount());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);

            if ((char) b[0] == '*') {
                Record r = new Record(b);
                rds.add(r);
            }
        }

        bb.clear();

        return rds;
    }

    private List<Record> loadRecordsWithOutDel() throws IOException {

        ByteBuffer bb = loadData(getDataIndex(), getCount() * getRecordLength());

        List<Record> rds = new ArrayList<Record>(getCount());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);

            if ((char) b[0] != '*') {
                Record r = new Record(b);
                rds.add(r);
            }
        }

        bb.clear();

        return rds;
    }

    public List<Record> loadRecords() throws IOException {

        ByteBuffer bb = loadData(getDataIndex(), getCount() * getRecordLength());

        List<Record> rds = new ArrayList<Record>(getCount());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            Record r = new Record(b);
            rds.add(r);
        }

        bb.clear();

        return rds;
    }

    /**
     * 深圳确认库装载指定记录迭代器
     * 
     * @param offset
     *            起始位置
     * @param count
     *            最大读取数量
     * @return
     * @throws IOException
     */
    public List<Record> loadRecordsForSZWTSure(int offset, int count) throws IOException {
        if (getCount() < offset) {
            return new ArrayList<Record>();
        }
        int relCount = count;
        if ((getCount() - offset) <= count) {
            relCount = getCount() - offset;
        }
        // System.out.println("=============" + offset + "||" + count);
        ByteBuffer bb = loadData(getDataIndex() + offset * getRecordLength(), relCount * getRecordLength());

        List<Record> rds = new ArrayList<Record>(relCount);
        for (int i = 0; i < relCount; i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            // 将确认的数据读入内存，不管是否返回正确
            if ((char) b[65] != 'z') {
                // 确认标志加入库中
                Record r = new Record(b);
                rds.add(r);
            } else {
                break;
            }
        }

        bb.clear();

        return rds;
    }

    /**
     * 深圳非交易保送库装载指定记录迭代器
     * 
     * @param offset
     *            起始位置
     * @param count
     *            最大读取数量
     * @return
     * @throws IOException
     */
    public List<Record> loadRecordsForSZFJYQRSure(int offset, int count) throws IOException {
        if (getCount() < offset) {
            return new ArrayList<Record>();
        }
        int relCount = count;
        if ((getCount() - offset) <= count) {
            relCount = getCount() - offset;
        }
        // System.out.println("=============" + offset + "||" + count);
        ByteBuffer bb = loadData(getDataIndex() + offset * getRecordLength(), relCount * getRecordLength());

        List<Record> rds = new ArrayList<Record>(relCount);
        for (int i = 0; i < relCount; i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            // 将确认的数据读入内存，不管是否返回正确
            // TODO
            if ((char) b[341] != 'z') {
                // 确认标志加入库中
                Record r = new Record(b);
                rds.add(r);
            } else {
                break;
            }
        }

        bb.clear();

        return rds;
    }

    /**
     * 一次性将深圳申报库正常的数据装载到内存中
     * 
     * @throws IOException
     *             读取文件失败
     */
    public List<Record> loadRecordsForSZWTForUse() throws IOException {

        ByteBuffer bb = loadData(getDataIndex(), getCount() * getRecordLength());

        List<Record> rds = new ArrayList<Record>(getCount());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);

            if ((char) b[0] != '*' && ((char) b[65] == '1' || (char) b[65] == 'z' || (char) b[65] == 'Z')) {
                Record r = new Record(b);
                rds.add(r);
            }
        }

        bb.clear();

        return rds;
    }

    /**
     * 一次性将深圳非交易报送的正常数据装载到内存中
     * 
     * @throws IOException
     *             读取文件失败
     */
    public List<Record> loadRecordsForSZFJYQRForUse() throws IOException {

        ByteBuffer bb = loadData(getDataIndex(), getCount() * getRecordLength());

        List<Record> rds = new ArrayList<Record>(getCount());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            if ((char) b[0] != '*' && ((char) b[341] == '1' || (char) b[341] == 'z' || (char) b[341] == 'Z')) {
                Record r = new Record(b);
                rds.add(r);
            }
        }

        bb.clear();

        return rds;
    }

    /**
     * 深圳回报库装载指定记录迭代器
     * 
     * @param offset
     *            起始位置
     * @param count
     *            最大读取数量
     * @return
     * @throws IOException
     */
    public List<Record> loadRecordsForSZWTReport(int offset, int count) throws IOException {
        if (getCount() < offset) {
            return new ArrayList<Record>();
        }
        int relCount = count;
        if ((getCount() - offset) <= count) {
            relCount = getCount() - offset;
        }
        ByteBuffer bb = loadData(getDataIndex() + offset * getRecordLength(), relCount * getRecordLength());

        List<Record> rds = new ArrayList<Record>(relCount);
        for (int i = 0; i < relCount; i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            Record r = new Record(b);
            rds.add(r);
        }

        bb.clear();

        return rds;
    }

    /**
     * 读取用户最大合同序号工具
     * 
     * @param userFlag
     *            用户号
     * @param channelFlag
     *            通道号
     * @return
     * @throws IOException
     */
    public int loadMaxRecordSequenceForSure(char userFlag, char channelFlag, int seqLen) throws IOException {
        int maxSequence = 0;
        int relCount = count;
        ByteBuffer bb = loadData(getDataIndex(), relCount * getRecordLength());
        for (int i = 0; i < relCount; i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            // 如果是此用户的合同号数据
            if ((char) b[17] == userFlag && (char) b[18] == channelFlag) {//第17，18位
                // 获取流水号
                String seqStr = "";
                for (int j = 0; j < seqLen; j++) {
                    seqStr = seqStr + (char) b[19 + j];
                }
                int seq = Integer.parseInt(seqStr);
                if (seq > maxSequence) {
                    maxSequence = seq;
                }
            }
        }

        return maxSequence;
    }

    /**
     * 读取用户最大合同序号工具
     * 
     * @param userFlag
     *            用户号
     * @param channelFlag
     *            通道号
     * @return
     * @throws IOException
     */
    public int loadMaxRecordSequenceForSjsFjyBsSure(char userFlag, char channelFlag, int seqLen) throws IOException {
        int maxSequence = 0;
        int relCount = count;
        ByteBuffer bb = loadData(getDataIndex(), relCount * getRecordLength());
        for (int i = 0; i < relCount; i++) {
            byte[] b = new byte[getRecordLength()];
            bb.get(b);
            // 如果是此用户的合同号数据
            if ((char) b[12] == userFlag && (char) b[13] == channelFlag) {
                // 获取流水号
                String seqStr = "";
                for (int j = 0; j < seqLen; j++) {
                    seqStr = seqStr + (char) b[14 + j];
                }
                int seq = Integer.parseInt(seqStr);
                if (seq > maxSequence) {
                    maxSequence = seq;
                }
            }
        }

        return maxSequence;
    }

    private ByteBuffer loadData(int offset, int length) throws IOException {
        // return fc.map(MapMode.READ_ONLY, offset, length).load();
        ByteBuffer b = ByteBuffer.allocateDirect(length);
        fc.position(offset);
        fc.read(b);
        b.rewind();
        return b;

    }

    /**
     * 关闭文件
     * 
     * @throws IOException
     *             操作文件失败
     */
    public void close() throws IOException {
        fc.close();
        dbf.close();
    }

    /**
     * 获取处理字符型数据的字符集
     * 
     * @return 字符集
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 获取版本号，分别有： 0x02 FoxBASE 0x03 FoxBASE+/dBASE III PLUS 无备注 0x30 Visual FoxPro 0x43 dBASE IV SQL 表文件 无备注 0x63 dBASE
     * IV SQL 系统文件 无备注 0x83 FoxBASE+/dBASE III PLUS 有备注 0x8B dBASE IV 有备注 0xCB dBASE IV SQL 表文件 有备注 0xF5 FoxPro
     * 2.x（或更早版本）有备注 0xFB FoxBASE
     * 
     * @return DBF 文件版本
     */
    public int getVersion() {
        return version;
    }

    /**
     * 获取最后更新日期
     * 
     * @return 更新日期，总是返回 3 个元素的数组，分别表示：0 年；1 月；2 日
     */
    public byte[] getUpdate() {
        return update;
    }

    /**
     * 获取记录总数
     * 
     * @return 记录总数
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取记录数据的开始位置
     * 
     * @return 数据的开始位置的索引值
     */
    public int getDataIndex() {
        return dataidx;
    }

    /**
     * 获取每条记录的长度
     * 
     * @return 记录长度
     */
    public int getRecordLength() {
        return length;
    }

    /**
     * 获取表标记，分别有： 0x01 具有 .cdx 结构的文件 0x02 文件包含备注 0x04 文件是数据库 .dbc
     * 
     * @return 表标记
     */
    public int getTableTag() {
        return tabletag;
    }

    /**
     * 获取代码页标记
     * 
     * @return 代码页标记
     */
    public int getPageTag() {
        return pagetag;
    }

    /**
     * 获取字段的数量
     * 
     * @return 字段数量
     */
    public int getFieldCount() {
        if (fields == null)
            return (getDataIndex() - HEAD_LEN - 1) / Field.LENGTH;
        else
            return fields.length;
    }

    /**
     * 获取所有字段信息
     * 
     * @return 所有字段的信息
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * 获取指定索引处的数据记录，与 records 方法不同的是，如果该记录尚未载入内存则此方法将只读取文件中该记录的数据块
     * 
     * @param index
     *            记录索引
     * @return 索引处的数据记录
     * @throws IOException
     *             读取文件失败
     */
    public Record getRecord(int index) throws IOException {
        if (index < 0 || index >= getCount())
            throw new IndexOutOfBoundsException("index " + index + " out of bound " + getCount());
        int i = getDataIndex() + getRecordLength() * index;
        ByteBuffer b = loadData(i, getRecordLength());
        Record r = new Record(b);
        return r;
    }

    /**
     * 获取所有记录的迭代器(不包括标志位为删除的)，此方法将把所有记录一次性载入内存
     * 
     * @return 迭代器
     * @throws IOException
     *             读取文件失败
     */
    public List<Record> recordsWithOutDel() throws IOException {
        return loadRecordsWithOutDel();
    }
    
    class ReadRecordWorker implements Runnable
    {
    	private ByteBuffer bb;
    	private int startIndexOfRow;
    	private int endIndexOfRow;
    	private List<byte[]> byteArrayList;
    	private CountDownLatch countDownLatch;
    	public ReadRecordWorker(ByteBuffer bb, int startIndexOfRow, int endIndexOfRow, 
    			List<byte[]> byteArrayList, CountDownLatch countDownLatch)
    	{
    		this.bb = bb;
    		this.startIndexOfRow =startIndexOfRow;
    		this.endIndexOfRow = endIndexOfRow;
    		this.byteArrayList = byteArrayList;
    		this.countDownLatch = countDownLatch;
    	}
    	
		@Override
		public void run() {
			try
			{
	    		for (int i = startIndexOfRow; i <= endIndexOfRow; i++) {
	                byte[] b = byteArrayList.get(i);
	                bb.get(b);
	            }
	    		
	            bb.clear();
			}
			finally
			{
				if(countDownLatch!=null)
					countDownLatch.countDown();
			}
		}
    }

    public List<byte[]> recordsWithOutDel_efficiently(CacheManager cacheManager) throws IOException {

    	ByteBuffer bb = cacheManager.getByteBuffer(getCount() * getRecordLength());
    	//Long t1 = System.nanoTime();
        fc.position(getDataIndex());
        fc.read(bb);
        //Long t2 = System.nanoTime();
        //System.out.println("read file speed time:" + (t2-t1));
        bb.rewind();
    	//ByteBuffer bb = fc.map(MapMode.READ_ONLY, getDataIndex(), getCount() * getRecordLength()).load();
        List<byte[]> rds = new ArrayList<byte[]>(getCount());
        List<byte[]> byteArrayList = cacheManager.getByteArrayList(getCount(), getRecordLength());
        for (int i = 0; i < getCount(); i++) {
            byte[] b = byteArrayList.get(i);
            bb.get(b);

            if ((char) b[0] != '*') {
                rds.add(b);
            }
        }

        bb.clear();

        return rds;
    }
    
    public String get_string_efficiently(byte[] src,final int index) {
        return get_string_efficiently(src, getFields()[index]).trim();
    }

    public String get_string_efficiently(byte[] src,final Field field) {
        return new String(src, field.getOffset(), field.getLength(), getCharset()).trim();
    }
    
    public long get_long_efficiently(byte[] src, final int index)
    {
    	long result =0;
    	Field field = getFields()[index];
    	for(int i =field.getOffset(); i< field.getOffset()+ field.getLength(); i++)
    	{
    		if(src[i]==32) continue;
    		if(src[i]<48 || src[i]>57) throw new NumberFormatException();
    		result = result*10 + (src[i]-48);
    	}
    	return result;
    }
    
    public long get_long_efficiently_and_multiply_1000(byte[] src, final int index)
    {
    	long multiplicand = 3;
    	long result =0;
    	Field field = getFields()[index];
    	boolean in_decimal_part = false;
    	int offset = field.getOffset();
    	int length = field.getLength();
    	int end = offset+length;
    	for(int i =field.getOffset(); i< end; i++)
    	{
    		byte ch = src[i];
    		
    		if(ch>=48 && ch<=57)
    		{
    			result *= 10;
        		result += ch-48;
        		if(in_decimal_part)
        			multiplicand--;
        		if(multiplicand==0) break;
        		continue;
    		}
    		
    		if(ch==32) 
    			continue;
    		
    		if(ch == 46) 
    		{
    			in_decimal_part = true;
    			continue;
    		}
    		
    		throw new NumberFormatException();
    		
    	}
    	
    	if(multiplicand == 3)
    		result *= 1000;
    	else if (multiplicand == 2)
    		result *=100;
    	else if (multiplicand == 1)
    		result *=10;
    	
    	return result;
    }
    

    /**
     * 获取所有记录的迭代器(不包括标志位为删除的)，此方法将把所有记录一次性载入内存
     * 
     * @return 迭代器
     * @throws IOException
     *             读取文件失败
     */
    public List<Record> recordsJustDel() throws IOException {
        return loadRecordsJustDel();
    }

    /**
     * 获取所有记录的迭代器，此方法将把所有记录一次性载入内存
     * 
     * @return 迭代器
     * @throws IOException
     *             读取文件失败
     */
    public List<Record> records() throws IOException {
        return loadRecords();
    }

    private ByteBuffer orderBytes(ByteBuffer bytes) {
        return bytes.order(ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * 字段属性
     */
    public class Field {

        public static final byte START_LOC = 0; // START_LEN = 1;
        public static final byte NAME_LOC = START_LOC, NAME_LEN = 11;
        public static final byte TYPE_LOC = NAME_LOC + NAME_LEN, TYPE_LEN = 1;
        public static final byte OFFSET_LOC = TYPE_LOC + TYPE_LEN, OFFSET_LEN = 4;
        public static final byte LENGTH_LOC = OFFSET_LOC + OFFSET_LEN, LENGTH_LEN = 1;
        public static final byte DECIMAL_LOC = LENGTH_LOC + LENGTH_LEN, DECIMAL_LEN = 1;
        public static final byte TAG_LOC = DECIMAL_LOC + DECIMAL_LEN, TAG_LEN = 1;
        public static final byte RESERVED_LOC = TAG_LOC + TAG_LEN, RESERVED_LEN = 13;
        public static final byte LENGTH = RESERVED_LOC + RESERVED_LEN;

        private final int index;
        private final String name;
        private final char type;
        private final int offset;
        private final int length;
        private final int decimal;
        private final int tag;

        public Field(final int index, final ByteBuffer src) {
            src.position(index * LENGTH);
            this.index = index;

            byte[] tb = new byte[NAME_LEN];
            src.get(tb);
            this.name = loadName(tb);

            this.type = (char) src.get();
            this.offset = src.getInt();
            this.length = src.get();
            this.decimal = src.get();
            this.tag = src.get();

        }

        private String loadName(byte[] src) {
            int i = NAME_LOC;
            for (; i < NAME_LOC + NAME_LEN; i++)
                if (src[i] == 0)
                    break;
            return new String(src, NAME_LOC, i, getCharset());
        }

        /**
         * 获取字段位置索引
         * 
         * @return 字段索引
         */
        public int getIndex() {
            return index;
        }

        /**
         * 获取字段的名称
         * 
         * @return 字段名称
         */
        public String getName() {
            return name;
        }

        /**
         * 获取字段类型，分别有： C-字符型 Y-货币型 N-数值型 F-浮点型 D-日期型 T-日期时间型 B-双精度型 I-整型 L-逻辑型 M-备注型 G-通用型 C-字符型（二进制） M-备注型（二进制） P-图片型
         * 
         * @return 字段类型
         */
        public char getType() {
            return type;
        }

        /**
         * 获取记录中该字段的偏移量
         * 
         * @return 偏移量
         */
        public int getOffset() {
            return offset;
        }

        /**
         * 获取该字段的长度（以字节为单位）
         * 
         * @return 字段的长度
         */
        public int getLength() {
            return length;
        }

        /**
         * 获取该字段的小数点位数
         * 
         * @return 小数点位数
         */
        public int getDecimal() {
            return decimal;
        }

        /**
         * 获取该字段的标记，分别是： 0x01 系统列（用户不可见） 0x02 可存储 null 值的列 0x04 二进制列（只适于字符型和备注型）
         * 
         * @return 字段的标记
         */
        public int getTag() {
            return tag;
        }
    }

    public class Record {

        private final byte[] src;

        public Record(final ByteBuffer src) {
            byte[] bs = new byte[src.capacity()];
            src.get(bs);
            this.src = bs;
        }

        public Record(final byte[] src) {
            this.src = src;
        }

        public String getString(final int index) {
            return getString(getFields()[index]).trim();
        }

        public String getString(final Field field) {
        	try {
        		//System.out.println("offset:"+field.getOffset()+"...length:"+field.getLength()+"...charset:"+getCharset());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "UTF-8").trim());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "UTF-8").trim());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "UTF-8").trim());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "ISO8859_1").trim());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "GB2312").trim());
        		//System.out.println(new String(src, field.getOffset(), field.getLength(), "GBK").trim());
        		
        		
        		
        		return new String(src, field.getOffset(), field.getLength(), getCharset()).trim();
				
			} catch (Exception e) {
				//e.printStackTrace();
				return "";
			}
        }

        /**
         * 获取字段的值，不格式化空格
         * 
         * @param index
         * @return
         */
        public String getStringNotTrim(final int index) {
            return getStringNotTrim(getFields()[index]);
        }

        /**
         * 获取字段的值，不格式化空格
         * 
         * @param field
         * @return
         */
        public String getStringNotTrim(final Field field) {
            return new String(src, field.getOffset(), field.getLength(), getCharset());
        }

        public int getInt(final int index) {
            return getInt(getFields()[index]);
        }

        public int getInt(final Field field) {
            String s = getString(field);
            System.out.println("number:" + s);
            if (!StringUtils.isEmpty(s)) {
                return Integer.valueOf(s.trim());
            } else {
                return -1;
            }
        }

        public long getLong(final int index) {
            return getLong(getFields()[index]);
        }
        
        public long get_long_efficiently(final int index)
        {
        	long result =0;
        	Field field = getFields()[index];
        	for(int i =field.getOffset(); i< field.getOffset()+ field.getLength(); i++)
        	{
        		if(src[i]==32) continue;
        		if(src[i]<48 || src[i]>57) throw new NumberFormatException();
        		result = result*10 + (src[i]-48);
        	}
        	return result;
        }
        
        public long get_long_efficiently_and_multiply_1000(final int index)
        {
        	long multiplicand = 1000;
        	long result =0;
        	Field field = getFields()[index];
        	boolean in_decimal_part = false;
        	int offset = field.getOffset();
        	int length = field.getLength();
        	int end = offset+length;
        	for(int i =field.getOffset(); i< end; i++)
        	{
        		byte ch = src[i];
        		if(ch==32) 
        			continue;
        		if(ch == 46) 
        		{
        			in_decimal_part = true;
        			continue;
        		}
        		
        		if(ch<48 || ch>57) 
        			throw new NumberFormatException();
        		
        		result *= 10;
        		result += ch-48;
        		if(in_decimal_part)
        			multiplicand /= 10;
        		if(multiplicand==1) break;
        	}
        	result = result*multiplicand;
        	return result;
        }

        public long getLong(final Field field) {
            String s = getString(field);
            if (!StringUtils.isEmpty(s)) {
                return Long.valueOf(s.trim());
            } else {
                return -1;
            }
        }

        public float getFloat(final int index) {
            return getFloat(getFields()[index]);
        }

        public float getFloat(final Field field) {
            String s = getString(field);
            if (!StringUtils.isEmpty(s)) {
                return Float.valueOf(s);
            } else {
                return -1;
            }
        }

        public BigDecimal getBigDecimal(final int index) {
            return getBigDecimal(getFields()[index]);
        }

        public BigDecimal getBigDecimal(final Field field) {
            String s = getString(field);
            if (!isNumeric(s)) {
                // 如果不是数字，返回0,注意效率
                return BigDecimal.ZERO;
            }
            if (!StringUtils.isEmpty(s)) {
                return BigDecimal.valueOf(Double.valueOf(s));
            } else {
                return new BigDecimal(-1);
            }
        }

        public String getDate(final Field field) {
            String s = getString(field);
            if (!StringUtils.isEmpty(s)) {
                return sdf.format(s);
            } else {
                return "";
            }
        }

        public String getDate(final int index) {
            return getDate(getFields()[index]);
        }

        public Object getObject(final int index) {
            return getObject(getFields()[index]);
        }

        public char getChar(final int index) {
            return getChar(getFields()[index]);
        }

        public char getChar(final Field field) {
            String s = getString(field);
            if (!StringUtils.isEmpty(s)) {
                return s.charAt(0);
            } else {
                return '\u0000';
            }
        }

        public Object getObject(final Field field) {
            switch (field.getType()) {
            case 'C':
                return getString(field);
            case 'N':
                if (field.getDecimal() > 0)
                    return getFloat(field);
                else
                    return getLong(field);
            case 'I':
                return getInt(field);
            default:
                throw new UnsupportedOperationException("Unsupport type: " + field.getType());
            }
        }

        /**
         * 判断是否为数字
         * 
         * @param str
         * @return
         */
        public boolean isNumeric(String str) {
            Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
            Matcher isNum = pattern.matcher(str);
            if (!isNum.matches()) {
                return false;
            }
            return true;
        }

    }

}
