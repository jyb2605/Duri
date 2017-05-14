package durithon.duri;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by JJH on 2016-09-15.
 */
public class Netty_DuriHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private Context context;
    final static String SENDMESAGGE = "passMessage";

    public Netty_DuriHandler(Context context){
        this.context = context;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        Log.d("","네티연결 성공");
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

        final String buffer1 = buffer[1];
        Log.e("str",""+buffer1);
        switch (buffer[0]){

            /*
              음악재생 프로토콜

             */
            case "interestStart":
                //content = 녹음파일 포지션값

                break;

            case "interestStop":
                //content = 녹음파일 포지션값

                break;

            case "sleepyStart":
                //content = 녹음파일 포지션값

                break;

            case "sleepyStop":
                //content = 녹음파일 포지션값

                break;

            case "anxietyStart":
                //content = 녹음파일 포지션값

                break;

            case "anxietyStop":
                //content = 녹음파일 포지션값

                break;

            case "sadStart":
                //content = 녹음파일 포지션값

                break;

            case "sadStop":
                //content = 녹음파일 포지션값

                break;


            /*
              녹음 프로토콜

             */
            case "record":
                //content = 녹음파일 포지션값

                break;


            /*
              만보계 프로토콜

             */
            case "a":
                //content = 걸음수

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(MapActivity.isMapActivity == true){
                            MapActivity.heartbit.setText(buffer1);
                        }
                    }
                });
                break;


            /*
             위도경도  프로토콜

          */
            case "latlon":
                //content = 위도/경도

                if (MapActivity.isMapActivity) {
                    Log.d("gps", buffer1);
                    //gps: 37.583753267411325▓127.00747646391298
                    /*
                    String[] strings = buffer1.split(String.valueOf(SplashActivity.ascii));
                    lat = Double.parseDouble(strings[0]);
                    lon = Double.parseDouble(strings[1]);
                    LatLng latLng = new LatLng(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]));

                Log.d("buffer: ","아오 왜 안되냐 " +buffer1);
                break;


                    */
                    // mapActivity.display(latLng);
                    passMessageToActivity(buffer1);

                }

                break;

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void passMessageToActivity(String message){
        Intent intent = new Intent();
        intent.setAction(SENDMESAGGE);
        intent.putExtra("message",message);
        context.sendBroadcast(intent);
    }
}