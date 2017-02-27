package maxinertia.lifelog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class GUI_Utility {

    public static void insertInputField(Activity activity, int parentViewResourceID, String text, @NonNull String hint) {
        ViewGroup view = (ViewGroup) activity.findViewById(parentViewResourceID);

        if(text == null) {
            View row = LayoutInflater.from(activity).inflate(R.layout.row_input_notitle, view, false);
            ((TextView) row.findViewById(R.id.row_input_field)).setHint(hint);
            view.addView(row);
        } else {
            View row = LayoutInflater.from(activity).inflate(R.layout.row_input_withtitle, view, false);
            ((TextView) row.findViewById(R.id.row_input_title)).setText(text);
            ((TextView) row.findViewById(R.id.row_input_field)).setHint(hint);
            view.addView(row);
        }
    }

    public static EditText getInputField(Activity activity, int parentViewResourceID, int index) {
        return ((EditText)(
                (LinearLayout)(
                        (LinearLayout) activity.findViewById(parentViewResourceID)
                ).getChildAt(index*2)
        ).getChildAt(0));
    }


    // ---------------------------------------------------------------------------------------------


    public static void insertDisplayField(Activity activity, int parentViewResourceID, String key, String value) {
        ViewGroup view = (ViewGroup) activity.findViewById(parentViewResourceID);
        View row = LayoutInflater.from(activity).inflate(R.layout.row_output, view, false);

        if(key!=null) {
            ((TextView) row.findViewById(R.id.row_output_key))
                    .setText(key);
        }
        if(value != null) {
            ((TextView) row.findViewById(R.id.row_output_value))
                    .setText(value);
        }

        view.addView(row);
    }

    // ---------------------------------------------------------------------------------------------

    public static void insertDivider(Activity activity, int parentViewResourceID) {
        ViewGroup view = (ViewGroup) activity.findViewById(parentViewResourceID);
        View row = LayoutInflater.from(activity).inflate(R.layout.divider, view, false);
        view.addView(row);
    }

}
