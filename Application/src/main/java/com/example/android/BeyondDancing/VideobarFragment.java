package com.example.android.BeyondDancing;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Observable;
import java.util.Observer;

public class VideobarFragment extends Fragment implements View.OnClickListener,Observer{
    DanceModel DModel;
    ImageButton dance;
    ImageButton interst;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DModel = DanceModel.getInstance();

        View v = inflater.inflate(R.layout.videobar, container, false);

        dance = (ImageButton) v.findViewById(R.id.danceit);
        dance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(),CameraActivity.class);
                startActivity(intent);
            }
        });

        interst = (ImageButton) v.findViewById(R.id.select);
        interst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DModel.fitforcompare()){
                    Intent intent = new Intent(v.getContext(), PlaybackActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent2 = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent2);
                }
            } });
        DModel.addObserver(this);
        return v;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();


        // Remove observer when activity is destroyed.
        DModel.deleteObserver(this);
    }
}
