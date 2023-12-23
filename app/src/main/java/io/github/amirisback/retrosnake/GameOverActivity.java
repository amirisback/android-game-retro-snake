package io.github.amirisback.retrosnake;

import android.os.Bundle;

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

    }

}
