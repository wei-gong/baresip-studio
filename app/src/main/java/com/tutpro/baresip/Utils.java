package com.tutpro.baresip;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Utils {

    static public String getFileContents(File file) {
        if (!file.exists()) {
            Log.e("Baresip", "Failed to find file: " + file.getPath());
            return "";
        } else {
            Log.e("Baresip", "Found file: " + file.getPath());
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            try {
                FileInputStream in = new FileInputStream(file);
                try {
                    in.read(bytes);
                } finally {
                    in.close();
                }
                return new String(bytes);
            } catch (java.io.IOException e) {
                Log.e("Baresip", "Failed to read file: " + file.getPath() + ": " +
                        e.toString());
                return "";
            }
        }
    }

    static public void putFileContents(File file, String contents) {
        try {
            FileOutputStream fOut =
                    new FileOutputStream(file.getAbsoluteFile(), false);
            OutputStreamWriter fWriter = new OutputStreamWriter(fOut);
            try {
                fWriter.write(contents);
                fWriter.close();
                fOut.close();
            } catch (java.io.IOException e) {
                Log.e("Baresip", "Failed to put contents to file: " +
                        e.toString());
            }
        } catch (java.io.FileNotFoundException e) {
            Log.e("Baresip", "Failed to find contents file: " +
                    e.toString());
        }
    }

    static public void alertView( Context context, String message ) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}