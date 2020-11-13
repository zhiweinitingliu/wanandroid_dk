package com.wdk.component_base.network.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdk.component_base.R;


/****
 * 自定义的一个loading等待框
 *
 * @author Eason
 */
public class CustomerLoadDialog extends Dialog {

    TextView loadingMsg;

    LinearLayout loadingLayout;


    public CustomerLoadDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customer_loading);
//        setContentView(loadingLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setCancelable(true);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCanceledOnTouchOutside(false);

    }


}
