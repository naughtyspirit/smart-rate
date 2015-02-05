package co.naughtyspirit.smartrate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private static SmartRate instance;
    private final SharedPreferences preferences;
    private Context context;
    private String appName;
    private int showRun;
    private String showLocation;
    private long appRun;

    public SmartRate(Context context, String appSpiceId, String appId) {
        this.context = context;
        this.appName = getApplicationName(context);
//        AppSpice.init(context, appSpiceId, appId);
        preferences = context.getSharedPreferences(Constants.SMART_RATE_PREFERENCES, Context.MODE_PRIVATE);
        incrementAppRuns();
//        AppSpice.getVariable("smartRateShowTime");
    }
//
//    @Subscribe
//    public void onShowTimeReceived(VariableProperties variableProperties) {
//        showRun = variableProperties.getInt("showRun");
//        showLocation = variableProperties.get("showLocation");
//    }

    private void incrementAppRuns() {
        appRun = preferences.getLong(Constants.SMART_RATE_APP_RUNS_KEY, 0);
        appRun++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(Constants.SMART_RATE_APP_RUNS_KEY, appRun);
        editor.apply();
    }

    public static void init(Context context, String appSpiceId, String appId) {
        instance = new SmartRate(context, appSpiceId, appId);
    }

    private static String getApplicationName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public static void show(String showLocation) {
        instance.showDialog(showLocation);
    }

    private void showDialog(String showLocation) {
        if (appRun == showRun && this.showLocation.equals(showLocation)) {
            LoveDialog loveDialog = new LoveDialog(context, appName, onLoveYesClickListener, onLoveNoClickListener);
            loveDialog.show();
        }
    }

    private OnYesClickListener onLoveYesClickListener = new OnYesClickListener() {
        @Override
        public void onYesClick(Dialog dialog, View view) {
//            AppSpice.track(Constants.APP_SPICE_NAMESPACE, "love.yes.click");
            dialog.dismiss();
            RateDialog rateDialog = new RateDialog(context, appName, onRateYesClickListener, onRateNoClickListener);
            rateDialog.show();
        }
    };

    private OnNoClickListener onLoveNoClickListener = new OnNoClickListener() {
        @Override
        public void onNoClick(Dialog dialog, View view) {
//            AppSpice.track(Constants.APP_SPICE_NAMESPACE, "love.no.click");
            dialog.dismiss();
            FeedbackDialog feedbackDialog = new FeedbackDialog(context, onSendClickListener);
            feedbackDialog.show();
        }
    };

    private OnYesClickListener onRateYesClickListener = new OnYesClickListener() {
        @Override
        public void onYesClick(Dialog dialog, View view) {
//            AppSpice.track(Constants.APP_SPICE_NAMESPACE, "rate.yes.click");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    };

    private OnNoClickListener onRateNoClickListener = new OnNoClickListener() {
        @Override
        public void onNoClick(Dialog dialog, View view) {
//            AppSpice.track(Constants.APP_SPICE_NAMESPACE, "rate.no.click");
            dialog.dismiss();
        }
    };

    private OnSendClickListener onSendClickListener = new OnSendClickListener() {
        @Override
        public void onSendClick(Dialog dialog, String feedbackMessage) {
//            AppSpice.track(Constants.APP_SPICE_NAMESPACE, "feedback.send.click");
            dialog.dismiss();
            // send feedback
        }
    };
}
