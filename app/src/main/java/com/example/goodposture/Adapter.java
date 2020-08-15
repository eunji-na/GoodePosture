package com.example.goodposture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.goodposture.tflite.ClassifierActivity;

import java.util.List;



public class Adapter extends PagerAdapter {

    public static int pageNum;
    private List<GoodPose> gpose;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<GoodPose> gpose, Context context) {
        this.gpose = gpose;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gpose.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pose, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(gpose.get(position).getImage());
        title.setText(gpose.get(position).getTitle());
        desc.setText(gpose.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0) {
                    pageNum = position;
                    Intent intent = new Intent(context, ClassifierActivity.class);
                    context.startActivity(intent);
                }
                if(position == 1){
                    pageNum = position;
                    Intent intent = new Intent(context, ClassifierActivity.class);
                    context.startActivity(intent);
                }
                if(position == 2){
                    pageNum = position;
                    Intent intent = new Intent(context, ClassifierActivity.class);
                    context.startActivity(intent);
                }
                if(position == 3){
                    pageNum = position;
                    Intent intent = new Intent(context, ClassifierActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
