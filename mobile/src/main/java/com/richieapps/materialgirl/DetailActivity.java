package com.richieapps.materialgirl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.richieapps.materialgirl.R;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        View image = findViewById(R.id.cardImage);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCircularReveal(view);
            }
        });
    }

    private void doCircularReveal(View view){
        final View toReveal = findViewById(R.id.revealedView);
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        float radius = Math.max(toReveal.getWidth(), toReveal.getHeight()) * 2.0f;
        toReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCircularReveal(view);
            }
        });
        if (toReveal.getVisibility() == View.INVISIBLE) {
            toReveal.setVisibility(View.VISIBLE);
            ViewAnimationUtils.createCircularReveal(toReveal, cx, cy, 0, radius).start();
        } else {
            Animator reveal = ViewAnimationUtils.createCircularReveal(
                    toReveal, cx, cy, radius, 0);
            reveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    toReveal.setVisibility(View.INVISIBLE);
                }
            });
            reveal.start();
        }
    }

    @Override
    public void onBackPressed() {
        if(findViewById(R.id.revealedView).getVisibility() == View.VISIBLE){
            doCircularReveal(findViewById(R.id.revealedView));
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
