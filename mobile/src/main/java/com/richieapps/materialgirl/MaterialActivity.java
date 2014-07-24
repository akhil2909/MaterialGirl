package com.richieapps.materialgirl;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;


public class MaterialActivity extends Activity {
    View cardImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        findViewById(R.id.fab).setOutline(outline);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("ToolBar Title");
        toolbar.inflateMenu(R.menu.incard_toolbar_menu);
        toolbar.setBackground(new ColorDrawable(Color.YELLOW));
        toolbar.showOverflowMenu();
        ((FrameLayout)findViewById(R.id.toolbar_frame)).addView(toolbar);
        cardImage = findViewById(R.id.cardImage);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.ic_action_share){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MaterialActivity.this, cardImage, "cardImage");
                    Intent intent = new Intent(MaterialActivity.this, DetailActivity.class);
                    startActivity(intent, options.toBundle());

                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.material, menu);
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
