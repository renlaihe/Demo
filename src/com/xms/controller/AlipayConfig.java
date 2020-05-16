package com.xms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xms.entity.Item;
import com.xms.service.MainService;

@Controller
@RequestMapping("/appli")
public class AlipayConfig {
	
	
	@Autowired
	private MainService mainService;

	private  static String APP_ID = "2016102200735379";
	private  static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkVqfacmESNGZqnxUV9AZuXywpTbuCM320k0GoAacMolWYkFNi4wo7WKHIQURnaeB4v/fPq3ivyVuidO7qxzEG+emjYM4ASDgJ+WUB2hoMAVMxJ7SFNDSxrYmO7B4dCPuUg8Z/Lo8SIzmFQhZVwDYkGLZhu6dI/4cXzUjD1cA0KvEpx8y5Unf6nnX78uZLCI632DPYO4fy1iBVG1kbJMlUtW6YOEnRWmuXIux2jex9SzedhdulJAt+MGlFRWdo7xuG8cFVZnJiYm+QWg6H0xnMz5Fkb3GradlF7ZZ611TxAqypuEWC+/QnE/xk+bsI2c84gPPslXYibNvVbVTUZC/TAgMBAAECggEAfjQSGX8HLuzQzga3MbHCEpbCGkIYwqd6XOdNX5FNUTWFWHFVqDZFUEgDjf8ZYfV8Lh72VyQOvah716nHJMOt0fawRwh7itnrNzWwLRjqMdf+pFwilLz/zoc/gWOwyIlycp40HM2Lj9k/Pif/cQD4UwYrYaizkiqx+0RqIgKP5T7oqG7MjK9H+qlnWWJb3EiR/RcQuY2T9iymboqCwHEJjCV/TR2EoAuOTPKMyoowYlDxgMGGlArHsQFN/wCf/+vVTJB978r1n4JCF5x1vxbDzGFQiYeJkI+yJ2bhb/ZnIjHzowVTPhO3PXj8jcAHhC/yKEpmOfx33YJQ8VBX5eJT8QKBgQD2oLT9M6jueNpSf9xmgFkcZ2D8QqCMERHbFHd1syp15JnEbnFyVsRjF1C3dX/UFnJ+pVjN7ppsnLO+fcZ8FbFU+skIWIiPi+YJkKN4eDTH62WmvdltavdhDMUGiL2kIL+Ym8E54AqrZr0s+jriP2KBo+kcZSgqYeJiakF0cCurWQKBgQCqlWfvDl+xTeBjnzJU2RVHCjM4qNs7/mkxbzUpyJzhnutPUybHs+E8XTVCJbLSsDKgvR27MkqCILPQtT39WqiH0s6U5wfiKf361iX8BdbrEiHHpBP1aKO770bDB9DI8jGfSc2b9vowZ/697Pz81DtbRa2IiIG/5efYbowiCX8LCwKBgFoVRUafEftZBVXRAi4mf2YSlKhPpdjDe2QsS54gPUgmocvQE3wSGVWgdIkuT+yl7lg4CQHfP2SgsD7KLi+wtL83gyqbO/ZCwVmJDUy5i33ii5SmD78ZrRYz1duXIr3KgHE7ow6CuSL2Oe8tz0hIorB7sHAAuvUQH+KWsSXYuvqxAoGBAIMB05yJEXz+LkLUVLdNMUYp00TEifr9XJSWr+GzLKkqf9WgKrxcrbLKcsimYzvkDj3cgCTwbsCZlP/I8h7NrXkCsZ0yAZ/0WBZ4uRtg8LoAZukIXjuvbXA8R8ApFHP/bZTD/aC2gVcDx2TdyT3zUNK8JJYjIGLckr5pyD2rxWIZAoGAPU4F73VoTvGHt9Y1l8egs1KZAVFiICNlwbw2xXjiJUYBGB2qMTGxhijLVI0p5udq4Xfr3NCm+bOHYjW6KK+h9N/97hMKS6dX24APAtXNWA4jjs3qtviXPLoMROogoTOV+3wydPZ3BuHEbTLav1BJXyA4eq2B3Zqsa99TnArwuY4=";
	private  static String CHARSET = "UTF-8";
	public  static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsF/3w0R9ksLH/N+IB+nrtNhmzralXjdx5k2OhIzNTiRsMxLfkO6bu2K6T/NUi5nwiC7h+zu0E0vqhvdJShdOpIwWmvdOWcXwiQBY1Kq98T9sC1jgIwQs/6gXnTRWlFAfcXYsscyyUg6lJuxiRDuA6ofJHFuD3NXoUM3NYBI7Q57orYnaUCvsaPmsbFuhzkX9rYsSp27bKCZY8dv7PnaKuvjJJMD5pbchdmGXlvPBBllVkE9zr5GF6uINdVrURNGEMS44M/00hlaFRX3nrdqzjfO2lrE7IhiWcK3urx6ofb8Jg/tGu6A+Y1KWCC/jeOoTXbbpbKEc3CBjVhM+G1t85wIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	
	// 字符编码格式
		public static String charset = "utf-8";
		
	//签名方式
	public static String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://公网地址/notifyUrl";
	//支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://renlaihe.free.idcfengye.com/XMSCode_SSM_V1/return_url.jsp";
	//private final String RETURN_URL = "http://renlaihe.free.idcfengye.com/XMSCode_SSM_V1/main/toIndex";

	@RequestMapping("/alipay")
	public void alipay(Integer itemIds[] ,HttpServletResponse httpResponse) throws IOException {
	    //实例化客户端,填入所需参数
	    AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
	    AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
	    //在公共参数中设置回跳和通知地址
	    request.setReturnUrl(RETURN_URL);
	    request.setNotifyUrl(NOTIFY_URL);
	    
	    double totalPrice = 0.0;
		
//		统计所有的价格
		List<Item> items = new ArrayList<>();
		for(Integer itemId : itemIds) {
			Item item = mainService.findItemByItemId(itemId);
			mainService.deleteCarItem(itemId);
			totalPrice += item.getC_price();
			items.add(item);
		}
	    
	    
	    
	    
	    //根据订单编号,查询订单相关信息
	    //Order order = payService.selectById(orderId);
	    //商户订单号，商户网站订单系统中唯一订单号，必填
	   // String out_trade_no = order.getId().toString();
	    String out_trade_no = "1001"+System.currentTimeMillis();
	    //付款金额，必填
	    
	    String total_amount =  ""+totalPrice;
	    //订单名称，必填
	    String subject = "测试商品";
	    //商品描述，可空
	    String body = "";
	    request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
	            + "\"total_amount\":\""+ total_amount +"\","
	            + "\"subject\":\""+ subject +"\","
	            + "\"body\":\""+ body +"\","
	            + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	    String form = "";
	    try {
	        form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	    httpResponse.setContentType("text/html;charset=" + CHARSET);
	    httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
	    httpResponse.getWriter().flush();
	    httpResponse.getWriter().close();
	}

	

}
