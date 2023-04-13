package edu.xda.hongtt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.xda.hongtt.model.Chi;
import edu.xda.hongtt.model.LoaiChi;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "QLCT";

    // ---Table Loai Chi ---

    private static final String KEY_NAME_TABLE_LOAICHI = "loaiChi";
    private static final String KEY_TABLE_ID_LOAICHI = "id";
    private static final String KEY_TABLE_NAME_LOAICHI = "tenLoaiChi";
    private static final String KEY_TABLE_DELETEFLAG_LOAICHI = "deleteFlag";

    // ---Table Chi---

    private static final String KEY_NAME_TABLE_CHI = "chi";
    private static final String KEY_TABLE_ID_CHI = "id";
    private static final String KEY_TABLE_TENMUCCHI_CHI = "tenMucChi";
    private static final String KEY_TABLE_DINHMUCCHI_CHI = "dinhMucChi";
    private static final String KEY_TABLE_DONVICHI_CHI = "donViCHI";
    private static final String KEY_TABLE_THOIDIEMAPDUNGCHI_CHI = "thoiDiemApDungChi";
    private static final String KEY_TABLE_DANHGIA_CHI = "danhGia";
    private static final String KEY_TABLE_DELETEFLAG_CHI = "deleteFlag";
    private static final String KEY_TABLE_IDLOAICHI_CHI = "idLoaiChi";

    private static final String CREATE_CLASS_TABLE_LOAICHI = "CREATE TABLE " + KEY_NAME_TABLE_LOAICHI + "(" + KEY_TABLE_ID_LOAICHI + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_NAME_LOAICHI + " TEXT," + KEY_TABLE_DELETEFLAG_LOAICHI + " INTEGER" + ")";

    private static final String CREATE_CLASS_TABLE_CHI = "CREATE TABLE " + KEY_NAME_TABLE_CHI + "(" + KEY_TABLE_ID_CHI + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_TENMUCCHI_CHI + " TEXT," + KEY_TABLE_DINHMUCCHI_CHI + " DECIMAL," + KEY_TABLE_DONVICHI_CHI + " TEXT," + KEY_TABLE_THOIDIEMAPDUNGCHI_CHI + " DATETIME," + KEY_TABLE_DANHGIA_CHI + " INTEGER," + KEY_TABLE_DELETEFLAG_CHI + " INTEGER," + KEY_TABLE_IDLOAICHI_CHI + " INTEGER" + ")";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetDate(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLASS_TABLE_LOAICHI);
        db.execSQL(CREATE_CLASS_TABLE_CHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_CHI); // mã bảng chi
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_LOAICHI); // mã loại chi
        onCreate(db);
    }


    public void addLoaiChi(LoaiChi loaiChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_NAME_LOAICHI, loaiChi.getTenLoaiChi());
        values.put(KEY_TABLE_DELETEFLAG_LOAICHI, loaiChi.getDeleteFlag());

        db.insert(KEY_NAME_TABLE_LOAICHI, null, values);
        db.close();
    }
    public void addChi(Chi chi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_TENMUCCHI_CHI, chi.getTenMucChi());
        values.put(KEY_TABLE_DINHMUCCHI_CHI, chi.getDinhMucChi());
        values.put(KEY_TABLE_DONVICHI_CHI, chi.getDonViChi());
        values.put(KEY_TABLE_THOIDIEMAPDUNGCHI_CHI, chi.getThoiDiemApDungChi());
        values.put(KEY_TABLE_DANHGIA_CHI, chi.getDanhGia());
        values.put(KEY_TABLE_DELETEFLAG_CHI, chi.getDeleteFlag());
        values.put(KEY_TABLE_IDLOAICHI_CHI, chi.getIdLoaiChi());
        db.insert(KEY_NAME_TABLE_CHI, null, values);
        db.close();
    }
}


