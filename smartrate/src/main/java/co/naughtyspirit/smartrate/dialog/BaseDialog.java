package co.naughtyspirit.smartrate.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 1/30/15.
 */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(Context context, int layout) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.FILL_PARENT;
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(0x7F000000));
    }
}
