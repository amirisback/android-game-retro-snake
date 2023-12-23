package io.github.amirisback.retrosnake;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by faisalamircs on 23/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


public class AppConstants {

    static GameEngine gameEngine;

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    static int surfaceView_width;
    static int surfaceView_height;
    static Context context;
    static int pointSize;
    static int defaultPointSize;
    static int snakeColor = Color.WHITE;
    static int appleColor;

    public static void initialize(Context context){
        setScreenSize(context);
        AppConstants.context = context;
        setGameConstants();
        gameEngine = new GameEngine();
    }

    public static void setGameConstants() {
        AppConstants.pointSize = 24;
        AppConstants.defaultPointSize = 3;
        AppConstants.snakeColor = Color.WHITE;
        AppConstants.appleColor = Color.RED;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    /**
     * Set Screen Size
     * @param context
     */
    public static void setScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}
