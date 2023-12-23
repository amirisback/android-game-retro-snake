package io.github.amirisback.retrosnake;

/**
 * Created by faisalamircs on 23/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.amirisback.retrosnake.databinding.ActivityGameBinding;


/**
 * Using Callback SurfaceHolder for Game Activity
 */

public class GameActivity extends BaseActivity<ActivityGameBinding> implements SurfaceHolder.Callback {

    SurfaceHolder surfaceHolder;
    MediaPlayer btn_click;

    @NonNull
    @Override
    protected ActivityGameBinding setupViewBinding() {
        return ActivityGameBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_click = MediaPlayer.create(this, R.raw.btn_click);
        getBinding().surfaceView.getHolder().addCallback(this);
        getBinding().surfaceView.setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

}
