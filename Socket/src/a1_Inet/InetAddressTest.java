package a1_Inet;

/** InetAddress
 *  > IP: Internet Protocol
 *  > DNS: Domain Name Server
 *  > 域名： www.   .com
 *  > 本地回路地址：127.0.0.1 ---> localhost(域名)
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress inet1 = InetAddress.getByName("192.168.0.101");
        System.out.println(inet1);

        InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet2);

        InetAddress inet3 = InetAddress.getByName("localhost");
        System.out.println(inet3);

        InetAddress inet4 = InetAddress.getLocalHost();
        System.out.println(inet4);
    }
}
