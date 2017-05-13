package durithon.duri;

import android.util.Log;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by JJH on 2016-09-16.
 */
public class Netty_FrameDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int length = in.readInt();
        Log.d("DataLength",""+length);

        if (in.readableBytes() < length)  {
            in.resetReaderIndex();
            return;
        }
        out.add(in.readBytes(length));

    }


}
