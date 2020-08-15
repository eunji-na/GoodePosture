package com.example.goodposture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.goodposture.onboarding.OnboardingActivity;


public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);

        try {
            Thread.sleep(2000); // 대기 초 설정
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, OnboardingActivity.class));
        finish();
    }
}
