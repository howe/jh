package cn.jh6.weixin.module;

import java.util.Arrays;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.jh6.weixin.tool.EncoderHelper;

/**
 * wx.jh6.cn 微信自动服务模块
 * 
 * @author yangq(qi.yang.cn@gmail.com) 2013-2-17
 */
@At("/handle")
@IocBean
public class HandleModule {

	private static final Log log = Logs.get();
	private static String TOKEN = "a1af0b28c7185818400c836b2e5e891f";

	// 校验请求是否来自微信服务器
	@At
	@Ok("raw")
	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {

		String[] array = {TOKEN, timestamp, nonce};
		Arrays.sort(array);
		String tmp = Arrays.toString(array);

		tmp = tmp.substring(1, tmp.length() - 1);
		tmp = tmp.replaceAll(",", "");
		tmp = tmp.replaceAll("\\s*", "");

		tmp = EncoderHelper.encode("SHA1", tmp);

		// System.out.println("signature1:" + signature);
		// System.out.println("signature2:" + tmp);

		if (tmp.equals(signature)) {
			// System.out.println("ok~~~" + echostr);
			return echostr;
		}

		return "";
	}

	@At
	public String responseMsg() {

		System.out.println("this is a test");

		return "";
	}

}
