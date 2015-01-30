package co.naughtyspirit.smartrate.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import co.naughtyspirit.smartrate.R;
import co.naughtyspirit.smartrate.dialog.listener.OnNoClickListener;
import co.naughtyspirit.smartrate.dialog.listener.OnYesClickListener;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 1/30/15.
 */
public class LoveDialog extends BaseDialog {

    private final OnYesClickListener onYesClickListener;
    private final OnNoClickListener onNoClickListener;

    public LoveDialog(final Context context, String appName, final OnYesClickListener onYesClickListener, final OnNoClickListener onNoClickListener) {
        super(context, R.layout.smartrate_love_dialog);
        this.onYesClickListener = onYesClickListener;
        this.onNoClickListener = onNoClickListener;

        findViewById(R.id.smartrate_love_yes_button).setOnClickListener(this);
        findViewById(R.id.smartrate_love_no_button).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.smartrate_love_dialog_title);
        textView.setText(context.getString(R.string.smartrate_do_you_love, appName));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.smartrate_love_yes_button) {
            onYesClickListener.onYesClick(LoveDialog.this, v);
        } else if (v.getId() == R.id.smartrate_love_no_button) {
            onNoClickListener.onNoClick(LoveDialog.this, v);
        }
    }
}