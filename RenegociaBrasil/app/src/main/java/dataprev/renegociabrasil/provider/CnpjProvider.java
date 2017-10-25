package dataprev.renegociabrasil.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.io.IOException;
import java.util.HashMap;

import dataprev.renegociabrasil.database.DataBaseHelper2;

/**
 * Created by Administrador on 24/10/2017.
 */

public class CnpjProvider extends ContentProvider {
    // Authority do nosso provider, a ser usado nas Uris.
    public static final String AUTHORITY =
            "dataprev.renegociabrasil.provider.CnpjProvider";

    // Nome do arquivo que irá conter o banco de dados.
    private static  final String DATABASE_NAME = "bd-renegocia.db";

    // Versao do banco de dados.
    // Este valor é importante pois é usado em futuros updates do DB.
    private static  final int  DATABASE_VERSION = 1;

    // Nome da tabela que irá conter as anotações.
    private static final  String NOTES_TABLE = "Contribuintes";

    // 'Id' da Uri referente às notas do usuário.
    private  static final int NOTES = 1;

    // Tag usada para imprimir os logs.
    public static final String TAG = "CnpjProvider";

    // Instância da classe utilitária
    private DataBaseHelper2 mHelper;

    // Uri matcher - usado para extrair informações das Uris
    private static final UriMatcher mMatcher;

    private static HashMap<String, String> mProjection;

    static {
        mProjection = new HashMap<String, String>();
        mProjection.put(Notes.NOTE_ID, Notes.NOTE_ID);
        mProjection.put(Notes.TEXT, Notes.TEXT);
        mProjection.put(Notes.NM_ESTAB, Notes.NM_ESTAB);
    }

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, NOTES_TABLE, NOTES);
    }


    /////////////////////////////////////////////////////////////////
    //           Métodos overrided de ContentProvider              //
    /////////////////////////////////////////////////////////////////
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (mMatcher.match(uri)) {
            case NOTES:
                count = db.delete(NOTES_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (mMatcher.match(uri)) {
            case NOTES:
                return Notes.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (mMatcher.match(uri)) {
            case NOTES:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                long rowId = db.insert(NOTES_TABLE, Notes.TEXT, values);
                if (rowId > 0) {
                    Uri noteUri = ContentUris.withAppendedId(
                            Notes.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(
                            noteUri, null);
                    return noteUri;
                }
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        mHelper = new DataBaseHelper2(getContext());;
        try {
            mHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Aqui usaremos o SQLiteQueryBuilder para construir
        // a query que será feito ao DB, retornando um cursor
        // que enviaremos à aplicação.
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        switch (mMatcher.match(uri)) {
            case NOTES:
                // O Builer receberá dois parametros: a tabela
                // onde será feita a busca, e uma projection -
                // que nada mais é que uma HashMap com os campos
                // que queremos recuperar do banco de dados.
                builder.setTables(NOTES_TABLE);
                builder.setProjectionMap(mProjection);
                break;

            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }

        cursor = builder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int count;
        switch (mMatcher.match(uri)) {
            case NOTES:
                count = db.update(NOTES_TABLE, values,
                        selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(
                        "URI desconhecida " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /////////////////////////////////////////////////////////////////
    //                Inner Classes utilitárias                    //
    /////////////////////////////////////////////////////////////////
    public static final class  Notes implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://"
                + CnpjProvider.AUTHORITY + "/Contribuintes");

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CnpjProvider.AUTHORITY;

        public static final String NOTE_ID = "_id";

        public static final String TEXT = "id_procurador";
        public static final String NM_ESTAB = "nm_estab";
    }

}
