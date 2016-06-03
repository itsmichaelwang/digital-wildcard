package com.github.itsmichaelwang.digitalwildcard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.desmond.squarecamera.ImageUtility;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DWc::Main";
    private static final int REQUEST_GET_BARCODE = 0;
    private static final int REQUEST_SELFIE = 1;

    private DigitalWildCard card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add toolbar to top of screen
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        card = new DigitalWildCard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu to toolbar once it loads
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // User chose to add a new WildCard
                Log.w(TAG, "Adding new WildCard");
                Intent intent = new Intent(this, ScannerActivity.class);
                startActivityForResult(intent, REQUEST_GET_BARCODE);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /*
     * This will run after we've gotten our barcode
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == REQUEST_GET_BARCODE) {
            String barcode = data.getStringExtra("barcode");
            card.setBarcode(barcode);
            Log.d(TAG, "Barcode received!");
            Log.d(TAG, barcode);

            Intent intent = new Intent(this, SelfieActivity.class);
            startActivityForResult(intent, REQUEST_SELFIE);
        }

        if (requestCode == REQUEST_SELFIE) {
            Uri photoUri = Uri.parse(data.getStringExtra("selfieUri"));
            card.setUri(photoUri);
            Log.d(TAG, "Selfie URI received!");
            Log.d(TAG, photoUri.toString());



        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}


