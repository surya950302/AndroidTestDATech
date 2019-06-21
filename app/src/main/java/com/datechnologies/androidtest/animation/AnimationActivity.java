package com.datechnologies.androidtest.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;

/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 * */

public class AnimationActivity extends AppCompatActivity implements View.OnTouchListener {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    Button btnAnimation;
    ImageView image;

    int windowwidth;
    int windowheight;

    private RelativeLayout.LayoutParams layoutParams;
    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setTitle(R.string.animation_button);


        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        btnAnimation  = (Button) findViewById(R.id.fadebtn);
        image = (ImageView) findViewById(R.id.animateimgview);

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();


        //image.setVisibility(View.VISIBLE);
        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageAnimation();
            }
        });
        image.setOnTouchListener(this);
        //Making image draggable


        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked

        // TODO: When the fade button is clicked, you must animate the D & A Technologies logo.
        // TODO: It should fade from 100% alpha to 0% alpha, and then from 0% alpha to 100% alpha

        // TODO: The user should be able to touch and drag the D & A Technologies logo around the screen.

        // TODO: Add a bonus to make yourself stick out. Music, color, fireworks, explosions!!!
    }

    //animation method
    public void imageAnimation()
    {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(image, "alpha",  0f, 1);
        fadeOut.setDuration(3000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(image, "alpha", 1f, 0f);
        fadeIn.setDuration(3000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeOut).after(fadeIn);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                //super.onAnimationEnd(animation);
                //mAnimationSet.start();
            }
        });
        mAnimationSet.start();

    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    float x,y=0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if(moving)
                {
                    x = motionEvent.getRawX() - image.getWidth()/2;
                    y = motionEvent.getRawY() - image.getHeight()*7/2;

                    image.setX(x);
                    image.setY(y);
                }
                break;

            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
