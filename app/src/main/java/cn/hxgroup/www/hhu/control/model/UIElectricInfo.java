package cn.hxgroup.www.hhu.control.model;

/**
 * Created by CXJ on 2016/6/3.
 * 电压电流等信息
 */
public class UIElectricInfo {
    private String mDate;

    private String kw_All;//总有功功率;
    private String kw_A;//A相有功功率
    private String kw_B;//B相有功功率
    private String kw_C;//C相有功功率
    private String kvar_All;//总无功功率
    private String kvar_A;//A相无功功率
    private String kvar_B;//B相无功功率
    private String kvar_C;//C相无功功率

    private String f_ALL;//总功率因素
    private String f_A;//A相功率因素
    private String f_B;//B相功率因素
    private String f_C;//C相功率因素

    private String Ua;
    private String Ub;
    private String Uc;

    private String Ia;
    private String Ib;
    private String Ic;

    private String I_zero;//零序电流

    private String kva_All;
    private String kva_A;
    private String kva_B;
    private String kva_C;

    public UIElectricInfo(String kw_All, String kw_A, String kw_B, String kw_C, String kvar_All, String kvar_A, String kvar_B, String kvar_C, String f_ALL, String f_A, String f_B, String f_C, String ua, String ub, String uc, String ia, String ib, String ic, String i_zero, String kva_All, String kva_A, String kva_B, String kva_C) {
        this.kw_All = kw_All;
        this.kw_A = kw_A;
        this.kw_B = kw_B;
        this.kw_C = kw_C;
        this.kvar_All = kvar_All;
        this.kvar_A = kvar_A;
        this.kvar_B = kvar_B;
        this.kvar_C = kvar_C;
        this.f_ALL = f_ALL;
        this.f_A = f_A;
        this.f_B = f_B;
        this.f_C = f_C;
        Ua = ua;
        Ub = ub;
        Uc = uc;
        Ia = ia;
        Ib = ib;
        Ic = ic;
        I_zero = i_zero;
        this.kva_All = kva_All;
        this.kva_A = kva_A;
        this.kva_B = kva_B;
        this.kva_C = kva_C;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getKw_All() {
        return kw_All;
    }

    public void setKw_All(String kw_All) {
        this.kw_All = kw_All;
    }

    public String getKw_A() {
        return kw_A;
    }

    public void setKw_A(String kw_A) {
        this.kw_A = kw_A;
    }

    public String getKw_B() {
        return kw_B;
    }

    public void setKw_B(String kw_B) {
        this.kw_B = kw_B;
    }

    public String getKw_C() {
        return kw_C;
    }

    public void setKw_C(String kw_C) {
        this.kw_C = kw_C;
    }

    public String getKvar_All() {
        return kvar_All;
    }

    public void setKvar_All(String kvar_All) {
        this.kvar_All = kvar_All;
    }

    public String getKvar_A() {
        return kvar_A;
    }

    public void setKvar_A(String kvar_A) {
        this.kvar_A = kvar_A;
    }

    public String getKvar_B() {
        return kvar_B;
    }

    public void setKvar_B(String kvar_B) {
        this.kvar_B = kvar_B;
    }

    public String getKvar_C() {
        return kvar_C;
    }

    public void setKvar_C(String kvar_C) {
        this.kvar_C = kvar_C;
    }

    public String getF_ALL() {
        return f_ALL;
    }

    public void setF_ALL(String f_ALL) {
        this.f_ALL = f_ALL;
    }

    public String getF_A() {
        return f_A;
    }

    public void setF_A(String f_A) {
        this.f_A = f_A;
    }

    public String getF_B() {
        return f_B;
    }

    public void setF_B(String f_B) {
        this.f_B = f_B;
    }

    public String getF_C() {
        return f_C;
    }

    public void setF_C(String f_C) {
        this.f_C = f_C;
    }

    public String getUa() {
        return Ua;
    }

    public void setUa(String ua) {
        Ua = ua;
    }

    public String getUb() {
        return Ub;
    }

    public void setUb(String ub) {
        Ub = ub;
    }

    public String getUc() {
        return Uc;
    }

    public void setUc(String uc) {
        Uc = uc;
    }

    public String getIa() {
        return Ia;
    }

    public void setIa(String ia) {
        Ia = ia;
    }

    public String getIb() {
        return Ib;
    }

    public void setIb(String ib) {
        Ib = ib;
    }

    public String getIc() {
        return Ic;
    }

    public void setIc(String ic) {
        Ic = ic;
    }

    public String getI_zero() {
        return I_zero;
    }

    public void setI_zero(String i_zero) {
        I_zero = i_zero;
    }

    public String getKva_All() {
        return kva_All;
    }

    public void setKva_All(String kva_All) {
        this.kva_All = kva_All;
    }

    public String getKva_A() {
        return kva_A;
    }

    public void setKva_A(String kva_A) {
        this.kva_A = kva_A;
    }

    public String getKva_B() {
        return kva_B;
    }

    public void setKva_B(String kva_B) {
        this.kva_B = kva_B;
    }

    public String getKva_C() {
        return kva_C;
    }

    public void setKva_C(String kva_C) {
        this.kva_C = kva_C;
    }
}
