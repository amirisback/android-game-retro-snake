package io.github.amirisback.retrosnake;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.amirisback.retrosnake.databinding.ActivityMainBinding;

/**
 * Created by faisalamircs on 23/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @NonNull
    @Override
    protected ActivityMainBinding setupViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().btnPlay.setOnClickListener(v -> play());
        getBinding().tvPlay.setOnClickListener(v -> play());

    }

    private void play() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

}
