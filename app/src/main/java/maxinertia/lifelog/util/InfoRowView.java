package maxinertia.lifelog.util;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import maxinertia.lifelog.R;

/**
 * Created by Doria on 2017-02-24.
 */

public class InfoRowView {

    public static void setupView(Activity context, int resource, String keyString, String valueString) {
        RelativeLayout linLayout = (RelativeLayout) context.findViewById(resource);
        TextView key = (TextView) linLayout.findViewById(R.id.info_key);
        TextView value = (TextView) linLayout.findViewById(R.id.info_value);
        key.setText(keyString);
        value.setText(valueString);

        //TextView.Layout lParams = key.getLayoutParams().
    }
}
