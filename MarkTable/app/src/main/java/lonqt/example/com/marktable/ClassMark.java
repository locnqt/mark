package lonqt.example.com.marktable;

/**
 * Created by locnq on 3/30/2018.
 */

public class ClassMark {
    private String markID;
    private String subjectName;
    private String studentID;
    private String subjectID;
    private double CC ;
    private double KT ;
    private double TH ;
    private double thi ;
    private String KQ;
    private double TK;
    private double TK4;
    private String TKChu;
    private String xepLoai;
    private int sotc;
    private String semester;
    private String year;

    public ClassMark() {
    }

    public ClassMark(String markID, String subjectName, String studentID, String subjectID, double CC, double KT, double TH, double thi, String KQ, double TK, double TK4, String TKChu, String xepLoai, int sotc, String semester, String year) {
        this.markID = markID;
        this.subjectName = subjectName;
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.CC = CC;
        this.KT = KT;
        this.TH = TH;
        this.thi = thi;
        this.KQ = KQ;
        this.TK = TK;
        this.TK4 = TK4;
        this.TKChu = TKChu;
        this.xepLoai = xepLoai;
        this.sotc = sotc;
        this.semester = semester;
        this.year = year;
    }

    public String getMarkID() {
        return markID;
    }

    public void setMarkID(String markID) {
        this.markID = markID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getCC() {
        return CC;
    }

    public void setCC(double CC) {
        this.CC = CC;
    }

    public double getKT() {
        return KT;
    }

    public void setKT(double KT) {
        this.KT = KT;
    }

    public double getTH() {
        return TH;
    }

    public void setTH(double TH) {
        this.TH = TH;
    }

    public double getThi() {
        return thi;
    }

    public void setThi(double thi) {
        this.thi = thi;
    }

    public String getKQ() {
        return KQ;
    }

    public void setKQ(String KQ) {
        this.KQ = KQ;
    }

    public double getTK() {
        return TK;
    }

    public void setTK(double TK) {
        this.TK = TK;
    }

    public double getTK4() {
        return TK4;
    }

    public void setTK4(double TK4) {
        this.TK4 = TK4;
    }

    public String getTKChu() {
        return TKChu;
    }

    public void setTKChu(String TKChu) {
        this.TKChu = TKChu;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public int getSotc() {
        return sotc;
    }

    public void setSotc(int sotc) {
        this.sotc = sotc;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
