package com.mobintum.soccergamefragments;


import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM_COLOR = "paramColor";
    private static final String ARG_PARAM_NAME = "paramName";
    private ImageView imgPlayer, imgBall;
    private TextView txtName;
    private int color;
    private String name;
    private PlayerChat chat;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.chat = (PlayerChat) getActivity();
    }

    public static PlayerFragment newInstance(int color, String name){
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_COLOR, color);
        args.putString(ARG_PARAM_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            Bundle args = getArguments();
            this.color = args.getInt(ARG_PARAM_COLOR);
            this.name = args.getString(ARG_PARAM_NAME);
        }
    }

    public PlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        imgPlayer = (ImageView) view.findViewById(R.id.imgPlayer);
        imgBall = (ImageView) view.findViewById(R.id.imgBall);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtName.setText(name);
        imgPlayer.setColorFilter(color);
        imgBall.setOnClickListener(this);
        return view;
    }

    public void showBall(boolean show){
        if(show)
            imgBall.setVisibility(View.VISIBLE);
        else
            imgBall.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        chat.passBall(getRandomPosition(0,5));
    }

    public static int getRandomPosition(int s, int e){
        Random r = new Random();
        int low = s;
        int high = e;
        int result = r.nextInt(high-low) + low;
        return result;
    }


    public interface PlayerChat{
        public void passBall(int position);
    }
}
