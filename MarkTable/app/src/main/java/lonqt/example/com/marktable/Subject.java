package lonqt.example.com.marktable;

/**
 * Created by locnq on 3/30/2018.
 */

public class Subject {

    private String subjectID;
    private String subjectName;
    private int soTC;

    private int CC_Percentage;
    private int BT_Percentage;
    private int TH_Percentage;
    private int KT_Percentage;
    private int THI_Percentage;

    public Subject(String subjectID, String subjectName, int soTC, int CC_Percentage, int BT_Percentage, int TH_Percentage, int KT_Percentage, int THI_Percentage) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.soTC = soTC;
        this.CC_Percentage = CC_Percentage;
        this.BT_Percentage = BT_Percentage;
        this.TH_Percentage = TH_Percentage;
        this.KT_Percentage = KT_Percentage;
        this.THI_Percentage = THI_Percentage;
    }

    public Subject() {
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

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public int getCC_Percentage() {
        return CC_Percentage;
    }

    public void setCC_Percentage(int CC_Percentage) {
        this.CC_Percentage = CC_Percentage;
    }

    public int getBT_Percentage() {
        return BT_Percentage;
    }

    public void setBT_Percentage(int BT_Percentage) {
        this.BT_Percentage = BT_Percentage;
    }

    public int getTH_Percentage() {
        return TH_Percentage;
    }

    public void setTH_Percentage(int TH_Percentage) {
        this.TH_Percentage = TH_Percentage;
    }

    public int getKT_Percentage() {
        return KT_Percentage;
    }

    public void setKT_Percentage(int KT_Percentage) {
        this.KT_Percentage = KT_Percentage;
    }

    public int getTHI_Percentage() {
        return THI_Percentage;
    }

    public void setTHI_Percentage(int THI_Percentage) {
        this.THI_Percentage = THI_Percentage;
    }
}
