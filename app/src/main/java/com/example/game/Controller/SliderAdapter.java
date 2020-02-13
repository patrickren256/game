package com.example.game.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.game.R;

public class SliderAdapter extends PagerAdapter {
    /**
     * Allows users to swipe to transition through pages.
     */

    private Context context;

    SliderAdapter(Context context){
        this.context = context;
    }

    private int[] slide_pictures = {
            R.drawable.room_escape,
            R.drawable.memory_game,
            R.drawable.runner
    };
    private String[] slide_headings = {
            "ROOM ESCAPE",
            "MEMORY GAME",
            "RUNNER"
    };

    private String[] slide_desc = {
            "Objective: Bring the key to the door.\nBlocks:\n      Blue: the player" +
                    "\n      Yellow: the key\n      Green: the door\n      Purple: pushable boxes" +
                    "\n      Red: teleports you to origin\n      Grey: walls",
            "A number of tiles will be blue.\nMemorize these and press the start button to begin.\n " +
                    "Then select all the correct tiles.\n" +
                    "Selecting all correct tiles will start a new round.\n." +
                    "Correct tiles +1000 points, incorrect -1000 points, clearing round without any " +
                    "incorrect +3000 points.\n" +
                    "The game ends after 20 seconds for each player.\n",
            "Avoid incoming enemies by tapping the controls at the bottom of the screen"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.activity_slide_layout, container, false);

        ImageView slideImageView =view.findViewById(R.id.image);
        TextView slideTitle =  view.findViewById(R.id.title);
        TextView slideDescription = view.findViewById(R.id.desc);

        slideImageView.setImageResource(slide_pictures[position]);
        slideTitle.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, @NonNull Object object){
        container.removeView((RelativeLayout) object);
    }
}
