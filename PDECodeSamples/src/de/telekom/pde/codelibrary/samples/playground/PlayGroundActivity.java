/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.playground;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.helpers.PDETypeface;



public class PlayGroundActivity extends PDEActivity {

    @SuppressWarnings("unused")
    final static String LOG_TAG = "PlayGroundActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playground_activity);

        // find screen background and set DTUIBackground color
        // with this value the light or dark background is chosen depending on the library setting
        (findViewById(R.id.playground_linear_layout)).setBackgroundColor(
                PDEColor.DTUIBackgroundColor().getIntegerColor());

        // find the label text and set DTUIText color
        // with this value the light or dark text color is chosen depending on the library setting
        ((TextView)findViewById(R.id.playground_playground_text)).setTextColor(PDEColor.
                DTUITextColor().getIntegerColor());
        // set the default font (telegrotesk)
        ((TextView)findViewById(R.id.playground_playground_text)).setTypeface(PDETypeface.sDefaultFont.getTypeface());
        // set the default size for the text (and telegrotesk font)
        ((TextView)findViewById(R.id.playground_playground_text)).setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.TeleGroteskDefaultSize));

        // todo Add your test code here! Just start coding.

    }
}