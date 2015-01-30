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
public class RateDialog extends BaseDialog {

    private final OnYesClickListener onYesClickListener;
    private final OnNoClickListener onNoClickListener;

    public RateDialog(final Context context, String appName, final OnYesClickListener onYesClickListener, final OnNoClickListener onNoClickListener) {
        super(context, R.layout.smartrate_rate_dialog);
        this.onYesClickListener = onYesClickListener;
        this.onNoClickListener = onNoClickListener;
        findViewById(R.id.smartrate_rate_yes_button).setOnClickListener(this);
        findViewById(R.id.smartrate_rate_no_button).setOnClickListener(this);

        TextView dialogTitle = (TextView) findViewById(R.id.smartrate_rate_dialog_title);
        dialogTitle.setText(context.getString(R.string.smartrate_rate_dialog_title, appName));

        TextView dialogMessage = (TextView) findViewById(R.id.smartrate_rate_dialog_message);
        dialogMessage.setText(context.getString(R.string.smartrate_rate_dialog_message, appName));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.smartrate_rate_yes_button) {
            onYesClickListener.onYesClick(RateDialog.this, v);
        } else if (v.getId() == R.id.smartrate_rate_no_button) {
            onNoClickListener.onNoClick(RateDialog.this, v);
        }
    }
}