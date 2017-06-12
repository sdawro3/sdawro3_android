package com.sdaacademy.zientara.rafal.kpn;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Drawable cutDrawable;
    private Drawable paperDrawable;
    private Drawable stoneDrawable;

    private ImageView myAction;
    private ImageView computerActionImage;
    private ActionEnum myActionEnum;
    private ActionEnum computerActionEnum;
    private Random random;
    private int myScore;
    private int computerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        cutDrawable = ContextCompat.getDrawable(this, R.drawable.ic_content_cut_black_24dp);
        paperDrawable = ContextCompat.getDrawable(this, R.drawable.ic_content_copy_black_24dp);
        stoneDrawable = ContextCompat.getDrawable(this, R.drawable.ic_brightness_1_black_24dp);

        myAction = (ImageView) findViewById(R.id.myActionImage);
        computerActionImage = (ImageView) findViewById(R.id.computerActionImage);
    }

    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.cutButton:
                myAction.setImageDrawable(cutDrawable);
                myActionEnum = ActionEnum.CUT;
                break;
            case R.id.paperButton:
                myAction.setImageDrawable(paperDrawable);
                myActionEnum = ActionEnum.PAPER;
                break;
            case R.id.stoneButton:
                myAction.setImageDrawable(stoneDrawable);
                myActionEnum = ActionEnum.STONE;
                break;
        }

        computerActionEnum = getRandomAction();
        setComputerImage();
        GameResult gameResult = getGameResult();
        addScore(gameResult);
        checkScore();
    }

    private void checkScore() {
        if (myScore == 3)
            showAlertDialog("I win!");
        else if (computerScore == 3)
            showAlertDialog("I loose! :(");
    }

    private void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("End of game")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myScore = 0;
                        computerScore = 0;
                    }
                })
                .setNeutralButton("Lol",null)
                .setNegativeButton("Exit game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();

        alertDialog.show();
    }

    private void addScore(GameResult gameResult) {
        if (gameResult.equals(GameResult.WIN))
            myScore++;
        if (gameResult.equals(GameResult.LOSE))
            computerScore++;
    }

    private GameResult getGameResult() {
        if (myActionEnum.equals(computerActionEnum))
            return GameResult.TIE;
        if (isMyWin())
            return GameResult.WIN;
        return GameResult.LOSE;
    }

    private boolean isMyWin() {
        return (myActionEnum.equals(ActionEnum.CUT) && computerActionEnum.equals(ActionEnum.PAPER)) ||
                (myActionEnum.equals(ActionEnum.PAPER) && computerActionEnum.equals(ActionEnum.STONE)) ||
                (myActionEnum.equals(ActionEnum.STONE) && computerActionEnum.equals(ActionEnum.CUT));
    }

    private void setComputerImage() {
        Drawable computerDrawable = getDrawableForAction(computerActionEnum);
        computerActionImage.setImageDrawable(computerDrawable);
    }

    private ActionEnum getRandomAction() {
        int randomAction = random.nextInt(3);
        if (randomAction == 0)
            return ActionEnum.STONE;
        if (randomAction == 1)
            return ActionEnum.PAPER;
        return ActionEnum.CUT;
    }

    private Drawable getDrawableForAction(ActionEnum action) {
        switch (action) {
            default:
            case CUT:
                return cutDrawable;
            case PAPER:
                return paperDrawable;
            case STONE:
                return stoneDrawable;
        }
    }

    enum ActionEnum {
        STONE,
        PAPER,
        CUT;
    }

    enum GameResult {
        WIN,
        LOSE,
        TIE;
    }

}
