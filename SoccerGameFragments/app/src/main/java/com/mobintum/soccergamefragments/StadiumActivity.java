package com.mobintum.soccergamefragments;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class StadiumActivity extends AppCompatActivity implements PlayerFragment.PlayerChat {

    private FragmentManager dt;
    private int blue, yellow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);
        //if(savedInstanceState== null) {
            this.blue = getResources().getColor(R.color.blue);
            this.yellow = getResources().getColor(R.color.yellow);
            this.dt = getSupportFragmentManager();

            FragmentTransaction ft = dt.beginTransaction();
            ft.add(R.id.goalkeeperBlue, PlayerFragment.newInstance(blue, "Juan"));
            ft.add(R.id.goalkeeperYellow, PlayerFragment.newInstance(yellow, "Pedro"));
            ft.add(R.id.playerLeftBlue, PlayerFragment.newInstance(blue, "Luis"));
            ft.add(R.id.playerLeftYellow, PlayerFragment.newInstance(yellow, "Pablo"));
            ft.add(R.id.playerRightBlue, PlayerFragment.newInstance(blue, "Jesus"));
            ft.add(R.id.playerRightYellow, PlayerFragment.newInstance(yellow, "Manuel"));
            ft.commit();
        /*}else{
            for(Fragment fragment:dt.getFragments()){
                FragmentTransaction ft = dt.beginTransaction();
                ft.replace(fragment.getId(),fragment);
                ft.commit();
            }
        }*/


    }

    @Override
    protected void onStart() {
        super.onStart();
        startGame();
    }

    public void startGame(){
        if(dt!=null){
            deleteBall();
            int index = (dt.getFragments().size()>1)? dt.getFragments().size()-1 : 0;
            PlayerFragment playerFragment = (PlayerFragment) dt.getFragments().get(PlayerFragment.getRandomPosition(0,5));
            playerFragment.showBall(true);
        }
    }

    public void deleteBall(){
        for(Fragment fragment: dt.getFragments()){
            PlayerFragment playerFragment = (PlayerFragment) fragment;
            playerFragment.showBall(false);
        }
    }

    @Override
    public void passBall(int position) {
        deleteBall();
        PlayerFragment playerFragment = (PlayerFragment) dt.getFragments().get(position);
        playerFragment.showBall(true);

    }
}
