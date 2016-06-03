package com.github.itsmichaelwang.digitalwildcard;

import android.net.Uri;

/**
 * Created by michael_wang on 5/26/16.
 */
public class DigitalWildCard {

    private String barcode;
    private Uri selfieUri;

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return this.barcode;
    }
    public void setUri(Uri selfieUri) { this.selfieUri = selfieUri; }
    public Uri getSelfieUri() { return this.selfieUri; }
}
