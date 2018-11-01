package com.situ.crm.util;

public class ImageServerUtil {
	public static final EnumImageServer IMG_SERVER = EnumImageServer.QINIU;

	private static final String IMG_SERVER_LOCAL = "/pic/";
	private static final String IMG_SERVER_QINIU = "http://pgo71myt9.bkt.clouddn.com/";

	public enum EnumImageServer {
		LOCAL, QINIU
	}

	/**
	 * 服务器+文件名 拼接图片完整的访问路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getImageFullUrl(String fileName) {
		// 为了防止将fullUrl加入到Cookie中，"fullUrl\":\"http://pgo71myt9.bkt.clouddn.com/null\"
		if (null == fileName) {
			return null;
		}
		if (IMG_SERVER == EnumImageServer.LOCAL) {
			return IMG_SERVER_LOCAL + fileName;
		} else {
			return IMG_SERVER_QINIU + fileName;
		}
	}
}
