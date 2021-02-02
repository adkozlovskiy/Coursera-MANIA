package com.elegion.recyclertest;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Loader extends AsyncTaskLoader<String> {

    private final String id;

    public Loader(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    public String loadInBackground() {
        try {
            TimeUnit.SECONDS.sleep(2);
            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND "
                            + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",
                    new String[]{id, String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)},
                    null);

            if (cursor != null && cursor.moveToFirst()) {
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                cursor.close();

                return number;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onCanceled(String data) {
        super.onCanceled(data);
        Toast toast = Toast.makeText(getContext(),
                "Запрос отменен", Toast.LENGTH_SHORT);
        toast.show();
    }
}
