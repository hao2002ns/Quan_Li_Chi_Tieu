package edu.xda.hongtt.model;
//Mô hình dữ liệu của loại chi
public class LoaiChi {
    public int id;
    public String tenLoaiChi;
    public int deleteFlag;// xóa trạng thái ánh xạ

    public LoaiChi(int id, String tenLoaiChi, int deleteFlag) {
        this.id = id;
        this.tenLoaiChi = tenLoaiChi;
        this.deleteFlag = deleteFlag;
    }

    public LoaiChi(String tenLoaiChi, int deleteFlag) {
        this.tenLoaiChi = tenLoaiChi;
        this.deleteFlag = deleteFlag;
    }

    public LoaiChi(int id, String tenLoaiChi) {
        this.id = id;
        this.tenLoaiChi = tenLoaiChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
