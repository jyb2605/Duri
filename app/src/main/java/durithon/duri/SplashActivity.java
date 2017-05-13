package durithon.duri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {


    public static Netty_DuriClient netty_duriClient;
    private ProgressDialog dialog;

    public static char ascii = (char)0x2593;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        netty_duriClient = new Netty_DuriClient(getApplicationContext());
        netty_duriClient.start();
        TextView button_sign_up = (TextView) findViewById(R.id.button_sign_up);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(SplashActivity.this, "", "자동 회원가입 중입니다.", true);
                dialog.show();

                EndDialog endDialog = new EndDialog();
                endDialog.start();
            }
        });
        TextView button_sign_in = (TextView) findViewById(R.id.button_sign_in);
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(SplashActivity.this, "", "로그인 중입니다.", true);
                dialog.show();

                EndDialog endDialog = new EndDialog();
                endDialog.start();
            }
        });
    }

    private Handler DialogHandler = new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            dialog.dismiss();
            startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
//            startActivity(intent);
        }
    };

    private class EndDialog extends Thread {
        public void run(){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            DialogHandler.sendEmptyMessage(0);
        }
    }
}
