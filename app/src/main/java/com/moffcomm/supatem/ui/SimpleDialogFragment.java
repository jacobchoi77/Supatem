package com.moffcomm.supatem.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.moffcomm.supatem.R;

/**
 * Created by jacobsFactory on 2017-02-13.
 */

public class SimpleDialogFragment extends DialogFragment {

    private CallbackSimpleDialog callback;
    private boolean cancelable = true;

    public static SimpleDialogFragment newInstance(int titleRes, int msgRes, int okRes, int cancelRes, int neutralRes) {
        SimpleDialogFragment frag = new SimpleDialogFragment();
        Bundle args = new Bundle();
        args.putInt("titleRes", titleRes);
        args.putInt("msgRes", msgRes);
        args.putInt("okRes", okRes);
        args.putInt("cancelRes", cancelRes);
        args.putInt("neutralRes", neutralRes);
        frag.setArguments(args);
        return frag;
    }

    public static SimpleDialogFragment newInstance(int titleRes, int msgRes, int okRes, int cancelRes) {
        SimpleDialogFragment frag = new SimpleDialogFragment();
        Bundle args = new Bundle();
        args.putInt("titleRes", titleRes);
        args.putInt("msgRes", msgRes);
        args.putInt("okRes", okRes);
        args.putInt("cancelRes", cancelRes);
        frag.setArguments(args);
        return frag;
    }

    public static SimpleDialogFragment newInstance(int titleRes, int msgRes, int okRes) {
        SimpleDialogFragment frag = new SimpleDialogFragment();
        Bundle args = new Bundle();
        args.putInt("titleRes", titleRes);
        args.putInt("msgRes", msgRes);
        args.putInt("okRes", okRes);
        frag.setArguments(args);
        return frag;
    }

    public void setCallback(CallbackSimpleDialog callback) {
        this.callback = callback;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getArguments().containsKey("titleRes")) {
            builder.setTitle(getArguments().getInt("titleRes"));
        }
        if (getArguments().containsKey("msgRes")) {
            builder.setMessage(getArguments().getInt("msgRes"));
        }
        if (getArguments().containsKey("okRes")) {
            builder.setPositiveButton(getArguments().getInt("okRes"),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (callback != null)
                                callback.onPositiveClick();
                        }
                    }
            );
        }
        if (getArguments().containsKey("cancelRes")) {
            builder.setNegativeButton(getArguments().getInt("cancelRes"),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (callback != null)
                                callback.onNegativeClick();
                        }
                    }
            );
        }
        if (getArguments().containsKey("neutralRes")) {
            builder.setNeutralButton(getArguments().getInt("neutralRes"),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (callback != null)
                                callback.onNeutralClick();
                        }
                    }
            );
        }
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(cancelable);
        return dialog;
    }

    public interface CallbackSimpleDialog {
        void onPositiveClick();

        void onNegativeClick();

        void onNeutralClick();
    }
}