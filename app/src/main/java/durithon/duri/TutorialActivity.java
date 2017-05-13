package durithon.duri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    int MAX_PAGE = 3;
    Fragment cur_fragment = new Fragment();

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);


//        startActivity(new Intent(this, SplashActivity.class));


//        new MusicListUtil();

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));

        TextView button_login_facebook = (TextView) findViewById(R.id.button_start);
        button_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(TutorialActivity.this, "", "시작  중입니다.", true);
                dialog.show();

                EndDialog endDialog = new EndDialog();
                endDialog.start();

            }
        });


        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+ viewPager.getAdapter().getItemPosition(viewPager.getCurrentItem()));

    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)
                return null;
            switch (position){
                case 0:
                    cur_fragment = new Viewpager_Page1();
                    break;
                case 1:
                    cur_fragment = new Viewpager_Page2();
                    break;
                case 2:
                    cur_fragment = new Viewpager_Page3();
                    break;
            }
            return cur_fragment;
        }
        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    private Handler DialogHandler = new Handler (){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            dialog.dismiss();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
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
