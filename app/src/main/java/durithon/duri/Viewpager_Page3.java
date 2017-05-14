package durithon.duri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class Viewpager_Page3 extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.viewpager_child,container,false);

        ImageView tutorial = (ImageView) linearLayout.findViewById(R.id.img_tutorial);
//        tutorial.setImageResource(R.drawable.music);
        Glide.with(this).load(R.drawable.music).into(tutorial);


        return linearLayout;
    }
}