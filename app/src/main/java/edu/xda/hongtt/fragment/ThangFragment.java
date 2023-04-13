package edu.xda.hongtt.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import edu.xda.hongtt.R;
import edu.xda.hongtt.adapter.spAdapter;
import edu.xda.hongtt.data.MyDatabaseHelper;
import edu.xda.hongtt.model.ThangNam;

public class ThangFragment extends Fragment {
    TextView txtTongChi;
    Spinner spThang;
    int tongChi = 0;
    MyDatabaseHelper database;
    Calendar calendar = Calendar.getInstance();// lấy lịch theo múi giờ được đặt
    String thangHienTai;
    ArrayList<ThangNam> thangNamArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thang, container, false);
        initControls(v);
        initEvents();
        return v;
    }
    private void initEvents() {
        spThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ThangNam thangNam = thangNamArrayList.get(i);
                if (thangNam.getId() == 0){
                    ngayHienTai();
                    getChi();
                    txtTongChi.setText("Tổng Chi: " + FormatCost(tongChi) + " VND");
                }else {
                    thangHienTai = String.valueOf(thangNam.getId()) + "/" + calendar.get(Calendar.YEAR);
                    getChi();
                    txtTongChi.setText("Tổng Chi: " + FormatCost(tongChi) + " VND");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void initControls(View v) {
        txtTongChi = v.findViewById(R.id.txtTongchiThang);
        spThang = v.findViewById(R.id.spChonThang);
        database = new MyDatabaseHelper(getContext());
        thangNamArrayList = new ArrayList<>();

        thangNamArrayList.add(new ThangNam(0,"Chọn tháng"));
        for (int i = 1; i < 13; i++){
            thangNamArrayList.add(new ThangNam(i,"Tháng " + i));
        }
        spAdapter adapter = new spAdapter(getContext(), thangNamArrayList);
        spThang.setAdapter(adapter);
    }
    public String FormatCost(long cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e) {
            return cost + "";
        }
    }
    public void getChi(){
        Cursor cursor = database.GetDate("SELECT * FROM chi WHERE deleteFlag = '0'");
        int usd = 0;
        int toVnd = 23255;
        int vnd = 0;
        int vietNamDong = 0;
        while (cursor.moveToNext()) {
            int dinhMucChi = cursor.getInt(2);
            String donViChi = cursor.getString(3);
            String ngayThang = cursor.getString(4);
            if (ngayThang.contains(thangHienTai)){
                if (donViChi.equalsIgnoreCase("USD")){
                    usd = usd + dinhMucChi;
                    vnd = (usd * toVnd);
                }
                if (donViChi.equalsIgnoreCase("VND")){
                    vietNamDong = vietNamDong + dinhMucChi;
                }
            }
        }
        tongChi = vnd + vietNamDong;
    }
    public void ngayHienTai(){
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        thangHienTai = (mMonth+1) + "/" + mYear;
    }

}
