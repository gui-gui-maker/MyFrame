package com.khnt.signseal.util;

@SuppressWarnings("serial")
public class RemoteCallException extends RuntimeException
{
	private int nCode;
	public static final int RCE_LOGOUT=-1;//no login or session time out 
	public static final int RCE_UNKNOWN=-2;//unknown error
	public static final int RCE_NOKEY=-3;//no secure key to develop request
	public static final int RCE_INVALID=-4;//invalid request xml
	public static final int RCE_DATABASE=-5;//database exception
	public static final int RCE_PARAMETER=-6;//parameter exception
	public static final int RCE_ENCRYPT=-7;//EncryptData Exception
	public static final int RCE_DECRYPT=-8;//EncryptData Exception
	public static final int RCE_NOTIFY=-9;//email Exception
	public static final int RCE_DEPRECATED=-10;//ever used but not support now
	public static final int RCE_NOSESSION=-11;//no session for this call
	public static final int RCE_FAILED=-12;//failed
	public static final int RCE_OUTOFMEMORY=-13;//out of memory
	public static final int RCE_IO=-14;//IO error occur
	public static final int RCE_UNAUTHORIZED=-15;//the requested operation is out of the authority
	public static final int RCE_NOMETHOD=-16;//the requested method is not found
	public static final int RCE_ACCOUNT=-17;//the user account exception
	public static final int RCE_REVOKE=-18;//the sign already revoke  exception
	public static final int RCE_EXIST=-19;//the data already exist ecxeption
	public static final int RCE_UPLOAD_FAIL=-20;//上传文件失败
	public static final int RCE_DOWNLOAD_FAIL=-21;//下载文件失败
	public static final int RCE_DELETE_FAIL=-22;//删除文件失败
	public static final int RCE_UPDATE_FAIL=-23;//更新文件失败
	public static final int RCE_NETWORK_FAIL=-24;//网络链接超时
	public static final int RCE_DECIPHERING=-25;//文件内容解密失败
	public static final int RCE_BBF=-26;//表单数据处理异常
	public static final int FAIL_UNKNOWN=-27;//未知错误
	public static final int RCE_PAY_ERROR=-28;//会签计费异常
	public static final int RCE_NO_TEMPLATE=-29;//数据库没有该模版

	public int getNCode()
	{
		return nCode;
	}

	public void setNCode(int code)
	{
		nCode = code;
	}
	public RemoteCallException(int code)
	{
		super("");
		this.nCode = code;
	}
	public RemoteCallException(int code, String message)
	{
		super(message);
		this.nCode = code;
	}
	public RemoteCallException(int code, Exception cause)
	{
		super(cause);
		this.nCode = code;
	}
	public String getMessage()
	{
		String str=super.getMessage();
		if (!"".equals(str))
			return str;
		switch(this.nCode)
		{
			//-1
		case RCE_LOGOUT:
			return "尚未登录或者会话超时。";
			//-3
		case RCE_NOKEY:
			return "没有找到可以解密的密钥。";
			//-4
		case RCE_INVALID:
			return "不合理的请求。";
			//-5
		case RCE_DATABASE:
			return "数据库操作出现问题。";
		case RCE_PARAMETER:
			return "不合理的调用参数。";
		case RCE_ENCRYPT:
			return "加密应答出现问题。";
		case RCE_DECRYPT:
			return "解密请求出现问题。";
		case RCE_NOTIFY:
			return "通知出现问题。";
		case RCE_DEPRECATED:
			return "不再支持这个调用。";
		case RCE_NOSESSION:
			return "没有会话信息。";
		case RCE_FAILED:
			return "调用结果为失败。";
		case RemoteCallException.RCE_OUTOFMEMORY:
			return "内存不足，无法完成相应的操作。";
		case RemoteCallException.RCE_IO:
			return "IO错误发生。";
		case RemoteCallException.RCE_UNAUTHORIZED:
			return "操作超出权限，无法完成。";
		case RemoteCallException.RCE_NOMETHOD:
			return "没有找到这个函数供调用。";
		case RemoteCallException.RCE_ACCOUNT:
			return "用户无账户或账户出现问题。";
		case RemoteCallException.RCE_REVOKE:
			return "会签已被撤销，无法提交，请关闭文档。";
		case RemoteCallException.RCE_EXIST:
			return "数据已存在！";
		case RemoteCallException.RCE_UPLOAD_FAIL:
			return "上传文件服务器失败，请重新上传。";
		case RemoteCallException.RCE_DOWNLOAD_FAIL:
			return "下载文件失败。";
		case RemoteCallException.RCE_DELETE_FAIL:
			return "删除文件失败。";
		case RemoteCallException.RCE_UPDATE_FAIL:
			return "更新文件失败。";
		case RemoteCallException.RCE_NETWORK_FAIL:
			return "网络连接超时。";
		case RemoteCallException.RCE_DECIPHERING:
		    return "文件内容解密失败";
		case RemoteCallException.RCE_BBF:
		    return "表单数据处理异常。";
		case RemoteCallException.RCE_PAY_ERROR:
			return "会签计费异常。";
		case RemoteCallException.RCE_NO_TEMPLATE:
			return "数据库没有该模版。";
			
		case RemoteCallException.FAIL_UNKNOWN:
			return "未知异常。";
		}
		return "未知错误。";
	}
	public String toString()
	{
		return super.toString()+" with error code: "+this.nCode;
	}
}
