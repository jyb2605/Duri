package durithon.duri;

import android.content.Context;
import android.os.Looper;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Created by JJH on 2016-09-16.
 */
public class Netty_DuriClient extends Thread {

    static final String HOST = System.getProperty("host", "52.78.208.219");
    static final int PORT = Integer.parseInt(System.getProperty("port", "6700"));
    private static Channel ch;
    private Context context;

    public Netty_DuriClient(Context context){
        this.context = context;

    }

    @Override
    public void run() {
        super.run();
        final SslContext sslCtx;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Looper.prepare();
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.TRACE))
                    .handler(new Netty_DuriInitializer(sslCtx, context));

            // Start the connection attempt.
            ch = b.connect(HOST, PORT).sync().channel();
            Looper.loop();
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sendmessage(final String msg) {
        ch.writeAndFlush(msg);
    }

}
