package co.naughtyspirit.smartrate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import co.naughtyspirit.smartrate.dialog.FeedbackDialog;
import co.naughtyspirit.smartrate.dialog.LoveDialog;
import co.naughtyspirit.smartrate.dialog.RateDialog;
import co.naughtyspirit.smartrate.dialog.listener.OnNoClickListener;
import co.naughtyspirit.smartrate.dialog.listener.OnSendClickListener;
import co.naughtyspirit.smartrate.dialog.listener.OnYesClickListener;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 1/30/15.
 */
public class SmartRate {

    private Context context;
    private static String appName;

    public SmartRate(Context context) {
        this.context = context;
    }

    public static void show(Context context, String appName) {
        SmartRate.appName = appName;
        new SmartRate(context).createDialog();
    }

    private void createDialog() {
        LoveDialog loveDialog = new LoveDialog(context, appName, onLoveYesClickListener, onLoveNoClickListener);
        loveDialog.show();
    }

    private OnYesClickListener onLoveYesClickListener = new OnYesClickListener() {
        @Override
        public void onYesClick(Dialog dialog, View view) {
            dialog.dismiss();
            RateDialog rateDialog = new RateDialog(context, appName, onRateYesClickListener, onRateNoClickListener);
            rateDialog.show();
        }
    };

    private OnNoClickListener onLoveNoClickListener = new OnNoClickListener() {
        @Override
        public void onNoClick(Dialog dialog, View view) {
            dialog.dismiss();
            FeedbackDialog feedbackDialog = new FeedbackDialog(context, onSendClickListener);
            feedbackDialog.show();
        }
    };

    private OnYesClickListener onRateYesClickListener = new OnYesClickListener() {
        @Override
        public void onYesClick(Dialog dialog, View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    };

    private OnNoClickListener onRateNoClickListener = new OnNoClickListener() {
        @Override
        public void onNoClick(Dialog dialog, View view) {
            dialog.dismiss();
        }
    };

    private OnSendClickListener onSendClickListener = new OnSendClickListener() {
        @Override
        public void onSendClick(Dialog dialog, String feedbackMessage) {
            dialog.dismiss();
            // send feedback
        }
    };
}
