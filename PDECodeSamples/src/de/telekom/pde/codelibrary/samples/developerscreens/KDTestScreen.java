package de.telekom.pde.codelibrary.samples.developerscreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.modules.login.OKDialog;

/**
 * Created with IntelliJ IDEA.
 * User: kdanner
 * Date: 10.05.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class KDTestScreen extends PDEActivity {

	@SuppressWarnings("unused")
    private final static String LOG_TAG = KDTestScreen.class.getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.developer_kd_button_test);

        ((PDEButton)findViewById(R.id.kdbuttontest)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KDTestScreen.this, OKDialog.class);
                startActivity(intent);
            }
        });

        ((PDEButton)findViewById(R.id.kdbuttontest2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KDTestScreen.this, OKDialog.class);
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_OKBUTTON_TEXT,"Schliessen");
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_MESSAGE  ,"Message");
                //intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_MESSAGE, "Benutzername oder Passwort ist nicht korrekt. Sie können sich in 5 Sekunden erneut einloggen.");
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_TITLE  ,"Left Side Title");
                startActivity(intent);
            }
        });

        findViewById(R.id.kdbuttontest3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KDTestScreen.this, OKDialog.class);
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_OKBUTTON_TEXT,"Schliessen");
                //intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_MESSAGE  ,"Message");
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_MESSAGE, "Benutzername oder Passwort ist nicht korrekt. Sie können sich in 5 Sekunden erneut einloggen.");
                intent.putExtra(OKDialog.PDE_OK_DIALOG_INTENT_EXTRA_TITLE  ,"Left Side Title");
                startActivity(intent);

            }
        });

//        float textSize = 12;
//        //PDETypeface font = PDETypeface.sDefaultFont;
//        PDETypeface font = PDETypeface.createByNameAndTypeface(Typeface.DEFAULT.toString(), Typeface.DEFAULT);
//        Paint.FontMetrics fm;
//
//        for (float i = 20; i < 45; i=i+0.01f) {
//            //fm =  PDEFontHelpers.getFontMetrics(font, i);
//            Log.d(LOG_TAG, String.format(Locale.ENGLISH,"%f: %d",i,PDEFontHelpers.getCapHeight(font, i)));
//        }
//
//        fm =  PDEFontHelpers.getFontMetrics(font, textSize);
//
//        Log.d(LOG_TAG, String.format(Locale.ENGLISH,"fm ascent: %f, bottom: %f, descent: %f, top: %f", fm.ascent, fm.bottom, fm.descent, fm.top));
//        Log.d(LOG_TAG, String.format(Locale.ENGLISH,"fm capHeight: %d, height: %f, topheight: %f", PDEFontHelpers.getCapHeight(font, textSize)
//                , PDEFontHelpers.getHeight(font, textSize), PDEFontHelpers.getTopHeight(font, textSize)));



    }
}