package cn.zsgithub.collections.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		if(args.length>0){
			String host = args[0];
			InetAddress[] addresses = InetAddress.getAllByName(host);
			for(InetAddress a : addresses){
				System.out.println("addresses:"+a);
				/*addresses:www.baidu.com/115.239.211.112
				addresses:www.baidu.com/115.239.210.27*/
			}
		}else{
			 InetAddress localHostAddress = InetAddress.getLocalHost();
			 System.out.println("localHostAddress:"+localHostAddress);//localHostAddress:ZS-PC/10.223.132.72
		}
	}

}
