package co.naughtyspirit.smartrate.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import co.naughtyspirit.smartrate.R;
import co.naughtyspirit.smartrate.dialog.listener.OnSendClickListener;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 1/30/15.
 */
public class FeedbackDialog extends BaseDialog {

    private final OnSendClickListener onSendClickListener;

    public FeedbackDialog(final Context context, final OnSendClickListener onSendClickListener) {
        super(context, R.layout.smartrate_feedback_dialog);
        this.onSendClickListener = onSendClickListener;
        findViewById(R.id.smartrate_feedback_send_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText = (EditText) findViewById(R.id.smartrate_feedback_input);
        String feedbackMessage = editText.getText().toString();
        onSendClickListener.onSendClick(this, feedbackMessage);
    }
}