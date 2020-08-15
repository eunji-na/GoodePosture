package com.example.goodposture;


import android.animation.ArgbEvaluator;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.goodposture.Alarm.AlarmActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<GoodPose> gpose;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Dialog infDialog;

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpose = new ArrayList<>();
        gpose.add(new GoodPose(R.drawable.pose1, "거북목 교정 운동", "장시간 공부나 업무로 인해 거북목(일자목)으로 변해버린 목을 교정을 도와주는 동작"));
        gpose.add(new GoodPose(R.drawable.pose2, "어깨 교정 운동", "잘못된 자세로 인해 굽은 어깨를 펴주는데 도움이 되는 동작"));
        gpose.add(new GoodPose(R.drawable.pose3, "허리/척추 스트레칭", "장시간 같은 자세와 많은 활동으로 인해 뻐근해진 허리를 풀어주는 동작"));
        gpose.add(new GoodPose(R.drawable.pose4, "허리 스트레칭", "장시간 서있거나 앉아있음으로 인해 뻐근한 허리를 풀어주는 동작"));

        adapter = new Adapter(gpose, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Alarm Button
        Button btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlarmActivity();
            }
        });

        //Information Button
        ImageButton btnInform = findViewById(R.id.btnInform);
        btnInform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCustominfDialog();
            }
        });

    }

    public void openAlarmActivity() {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }

    public void MyCustominfDialog() {
        infDialog = new Dialog(MainActivity.this);
        infDialog.setContentView(R.layout.infdialog);
        infDialog.setTitle("custom finish dialog");

        WindowManager.LayoutParams params = infDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        infDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        Button confirm = (Button) infDialog.findViewById(R.id.confirm_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infDialog.cancel();
            }
        });

        infDialog.show();
    }

}
