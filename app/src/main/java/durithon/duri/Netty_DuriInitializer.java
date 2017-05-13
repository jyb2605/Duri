package durithon.duri;

import android.content.Context;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;

/**
 * Created by JJH on 2016-09-15.
 */
public class Netty_DuriInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;
    private Context context;

    public Netty_DuriInitializer(SslContext sslCtx, Context context) {
        this.sslCtx = sslCtx;
        this.context = context;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(sslCtx.newHandler(ch.alloc(), Netty_DuriClient.HOST, Netty_DuriClient.PORT));
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast("Netty_FrameDecoder", new Netty_FrameDecoder());
        pipeline.addLast("Netty_FrameEncoder", new Netty_FrameEncoder());
        pipeline.addLast(new Netty_DuriHandler(context));
    }
}
