package durithon.duri;

import android.util.Log;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by JJH on 2016-09-16.
 */
public class Netty_FrameEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {

        byte[] data = msg.getBytes();
        int dataLength = data.length;

        out.writeInt(dataLength);  // data length
        out.writeBytes(data);      // data
        ctx.writeAndFlush(out);

        Log.d("encode1", ""+data);
        Log.d("encode2", ""+dataLength);
        Log.d("encode3", ""+out);
    }
}
