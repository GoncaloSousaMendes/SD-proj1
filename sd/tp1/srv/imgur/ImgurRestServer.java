package sd.tp1.srv.imgur;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import sd.tp1.common.Discovery;
import sd.tp1.common.MulticastDiscovery;
import sd.tp1.srvREST.AlbumResource;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ImgurRestServer {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(8080).path("GalleryServerIMGUR").build();

		ResourceConfig config = new ResourceConfig();

		config.register(ImgurProxy.class);
		
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		
		System.err.println("GalleryServer started");
		String serviceURL = ""+localhostAddress().getCanonicalHostName()+":"+baseUri.getPort();
		String url = "http://"+serviceURL+ "/GalleryServerIMGUR";
		System.out.println(url);
		Discovery discovery = new MulticastDiscovery();
		discovery.registerService(new URL(url));
	}
	
	/**
	 * Return the IPv4 address of the local machine that is not a loopback address if available.
	 * Otherwise, returns loopback address.
	 * If no address is available returns null.
	 */
	private static InetAddress localhostAddress() {
		try {
			try {
				Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements()) {
					NetworkInterface n = e.nextElement();
					Enumeration<InetAddress> ee = n.getInetAddresses();
					while (ee.hasMoreElements()) {
						InetAddress i = ee.nextElement();
						if (i instanceof Inet4Address && !i.isLoopbackAddress())
							return i;
					}
				}
			} catch (SocketException e) {
				// do nothing
			}
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	
}
