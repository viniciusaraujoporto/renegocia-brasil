package dataprev.renegociabrasil.provider;

import android.content.ContentProvider;
import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Administrador on 24/10/2017.
 */

public class CnpjProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri
            .parse("content://dataprev.renegociabrasil.provider.cnpjprovider");

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
