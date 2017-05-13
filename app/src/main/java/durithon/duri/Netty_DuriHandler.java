package durithon.duri;

import android.content.Context;
import android.util.Log;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by JJH on 2016-09-15.
 */
public class Netty_DuriHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Context context;

    public Netty_DuriHandler(Context context){
        this.context = context;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        // Send the received message to all channels but the current one.
        // String a = bytebuf.toString(Charset.forName("UTF-8"));
        int bufcap = buf.capacity();

        byte[] bytemsg = new byte[bufcap];

        for (int i = 0; i < bufcap; i++) {
            bytemsg[i] = buf.getByte(i);
        }
        String str = new String(bytemsg, "UTF-8");

        Log.d("handlerString",""+str);

        String[] buffer = str.split(String.valueOf(SplashActivity.ascii),2);

        String buffer1 = buffer[1];
        switch (buffer[0]){

            case "100":

                break;

            case "110":
                break;

            case "111":
                break;

            case "112":
                break;
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
