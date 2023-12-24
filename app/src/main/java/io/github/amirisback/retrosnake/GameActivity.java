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
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.amirisback.retrosnake.databinding.ActivityGameBinding;


/**
 * Using Callback SurfaceHolder for Game Activity
 */

public class GameActivity extends BaseActivity<ActivityGameBinding> implements SurfaceHolder.Callback {

    GameThread gameThread;
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
        AppConstants.initialize(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameThread = new GameThread(holder);
        if (!gameThread.isRunning()) {
            gameThread = new GameThread(holder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        AppConstants.surfaceView_width = getBinding().surfaceView.getWidth();
        AppConstants.surfaceView_height = getBinding().surfaceView.getHeight();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.setIsRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException ignored) {

            }
        }
    }

    public void changeSnakeDirection(View view) {
        if (btn_click != null) {
            btn_click.start();
        }
        String tag = view.getTag().toString();
        MoveDirection moveDirection = MoveDirection.valueOf(tag);
        switch (moveDirection) {
            case UP:
                if (AppConstants.getGameEngine().movingPosition != MoveDirection.DOWN) {
                    AppConstants.getGameEngine().movingPosition = MoveDirection.UP;
                }
                break;
            case RIGHT:
                if (AppConstants.getGameEngine().movingPosition != MoveDirection.LEFT) {
                    AppConstants.getGameEngine().movingPosition = MoveDirection.RIGHT;
                }
                break;
            case LEFT:
                if (AppConstants.getGameEngine().movingPosition != MoveDirection.RIGHT) {
                    AppConstants.getGameEngine().movingPosition = MoveDirection.LEFT;
                }
                break;
            case DOWN:
                if (AppConstants.getGameEngine().movingPosition != MoveDirection.UP) {
                    AppConstants.getGameEngine().movingPosition = MoveDirection.DOWN;
                }
                break;
        }
    }

}
