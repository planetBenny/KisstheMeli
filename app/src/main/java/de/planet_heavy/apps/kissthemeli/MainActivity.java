package de.planet_heavy.apps.kissthemeli;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.Logger;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final int FROSCH_ID = 212121;

    private int iPoints;
    private int iRound;
    private int iCountdown;
    private ImageView frosch;
    private Random rnd = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //newGame();
        showStartFragment();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start) {
            startGame();
        } else if(v.getId() == R.id.playagain) {
            showStartFragment();
        } else if (v.getId() == FROSCH_ID) {
            Toast.makeText(this, R.string.kissed, Toast.LENGTH_SHORT).show();

            iPoints += iCountdown*1000;

            iRound++;
            initRound();
        }
    }

    private void startGame() {
        newGame();
    }

    private void showStartFragment() {

        ViewGroup container = (ViewGroup)findViewById(R.id.container1);

        container.removeAllViews();

        container.addView(getLayoutInflater().inflate(R.layout.fragment_start, null));

        container.findViewById(R.id.start).setOnClickListener(this);
    }

    private void showGameOverFragment() {

        ViewGroup container = (ViewGroup)findViewById(R.id.container1);

        container.addView(getLayoutInflater().inflate(R.layout.fragment_gameover, null));

        container.findViewById(R.id.playagain).setOnClickListener(this);
    }

    private void newGame()
    {
        iPoints = 0;
        iRound = 1;

        initRound();
    }

    private void initRound() {

        iCountdown = 10;

        ViewGroup container1 = (ViewGroup)findViewById(R.id.container1);
        container1.removeAllViews();

        WimmelView wv = new WimmelView(this);
        container1.addView(wv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        wv.setImageCount(7*(10+iRound));

        frosch = new ImageView(this);
        frosch.setId(FROSCH_ID);
        frosch.setImageResource(R.drawable.meli);
        frosch.setScaleType(ImageView.ScaleType.CENTER);

        float scale = getResources().getDisplayMetrics().density;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Math.round(64*scale), Math.round(61*scale));

        lp.leftMargin = rnd.nextInt(container1.getWidth() - 64);
        lp.topMargin = rnd.nextInt(container1.getHeight() - 61);
        lp.gravity = Gravity.TOP + Gravity.LEFT;

        frosch.setOnClickListener(this);

        container1.addView(frosch, lp);

        update();
    }

    private void update() {
        fillTextView(R.id.points, Integer.toString(iPoints));
        fillTextView(R.id.round, Integer.toString(iRound));
        fillTextView(R.id.countdown, Integer.toString(iCountdown * 1000));
    }

    private void fillTextView(int id, String text) {
        try {
            TextView tv = (TextView)findViewById(id);
            tv.setText(text);
        }
        catch (Exception ex) {
            String sss = ex.getMessage();
            sss += "";
        }
    }
}
