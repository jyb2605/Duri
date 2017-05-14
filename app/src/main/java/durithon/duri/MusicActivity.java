package durithon.duri;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JJH on 2017-05-14.
 */

public class MusicActivity extends AppCompatActivity {



    Button 신나요;
    Button 불안해요;
    Button 졸려요;
    Button 슬퍼요;

    Button onoff_btn;

    ListView listView;

    ArrayList<Data> 신난다노래;
    MusicAdapter adapter;

    int flag = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        신나요 = (Button)findViewById(R.id.btn_emotion1);
        불안해요 = (Button)findViewById(R.id.btn_emotion2);
        졸려요 = (Button)findViewById(R.id.btn_emotion3);
        슬퍼요 = (Button)findViewById(R.id.btn_emotion4);

        신나요.setOnClickListener(onClickListener);
        불안해요.setOnClickListener(onClickListener);
        졸려요.setOnClickListener(onClickListener);
        슬퍼요.setOnClickListener(onClickListener);


        onoff_btn = (Button) findViewById(R.id.onoff_btn);
        onoff_btn.setOnClickListener(onClickListener);

        신난다노래 = new ArrayList<>();


        신난다노래.add(new Data("소원"));
        신난다노래.add(new Data("널 생각해"));
        신난다노래.add(new Data("백색소음"));
        신난다노래.add(new Data("너였다면"));
        신난다노래.add(new Data("월광 1악장"));
        신난다노래.add(new Data("Lazenca, Save Us"));
        listView = (ListView) findViewById(R.id.lst_music);
        adapter = new MusicAdapter(신난다노래 ,MusicActivity.this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SplashActivity.netty_duriClient.sendmessage("interestStart"+SplashActivity.ascii+신난다노래.get(i).getTitle());
                flag = i;
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.btn_emotion1:
                    adapter = new MusicAdapter(신난다노래 ,MusicActivity.this);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;

                case R.id.btn_emotion2:

                    break;


                case R.id.btn_emotion3:

                    break;


                case R.id.btn_emotion4:


                    break;

                case R.id.onoff_btn:
                    if(flag != -1){
                        SplashActivity.netty_duriClient.sendmessage("interestStop"+SplashActivity.ascii+신난다노래.get(flag).getTitle());
                    }

                    break;

            }

        }
    };



    class MusicAdapter extends BaseAdapter {
        ArrayList<Data> data_list = new ArrayList<>();
        Context con;
        LayoutInflater inflater;
        MusicAdapter(ArrayList<Data> data_list, Context con){
//            this.data_list=data_list;

            this.data_list = data_list;

            this.con=con;

            inflater= (LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        private class ViewHolder {
            ImageView 노래이미지;
            TextView 노래제목;
            TextView 노래시간;
        }


        @Override
        public int getCount() {
            return data_list.size();
        }

        @Override
        public Object getItem(int i) {
            return data_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convert_view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(convert_view == null){
                viewHolder = new ViewHolder();
                convert_view = inflater.inflate(R.layout.activity_map_detail_history_list_item, viewGroup, false);
                viewHolder.노래이미지 = (ImageView) convert_view.findViewById(R.id.imageView5);
                viewHolder.노래제목 = (TextView) convert_view.findViewById(R.id.textView7);
                viewHolder.노래시간 = (TextView) convert_view.findViewById(R.id.textView8);
                convert_view.setTag(viewHolder);
            }else {
                viewHolder =(ViewHolder)convert_view.getTag();
            }
            viewHolder.노래제목.setText(data_list.get(i).getTitle());
//            viewHolder.노래시간.setText(data_list.get(i));



            return convert_view;
        }
    }


}