package io.github.amirisback.retrosnake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.amirisback.retrosnake.databinding.ActivityGameOverBinding;

/**
 * Created by faisalamircs on 23/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


public class GameOverActivity extends BaseActivity<ActivityGameOverBinding> {

    @NonNull
    @Override
    protected ActivityGameOverBinding setupViewBinding() {
        return ActivityGameOverBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int score = getIntent().getExtras().getInt("score");
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int scoreSP = pref.getInt("scoreSP", 0);
        SharedPreferences.Editor editor = pref.edit();
        if (score > scoreSP){
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }
        getBinding().tvPoints.setText(String.valueOf(score));
        getBinding().tvPersonalBest.setText(String.valueOf(scoreSP));
    }

    public void restart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }

}
