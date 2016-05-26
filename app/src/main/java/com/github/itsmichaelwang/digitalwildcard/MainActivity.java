package com.github.itsmichaelwang.digitalwildcard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Used for logging success or failure messages
    private static final String TAG = "DWc::Main";
    private static final int REQUEST_GET_BARCODE = 0;
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
        if (requestCode == REQUEST_GET_BARCODE && resultCode == Activity.RESULT_OK) {
            String barcode = data.getStringExtra("barcode");
            card.setBarcode(barcode);
            Log.d(TAG, "Barcode received!");


        }
    }
}
