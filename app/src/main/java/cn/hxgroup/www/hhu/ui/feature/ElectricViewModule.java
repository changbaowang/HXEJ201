package cn.hxgroup.www.hhu.ui.feature;

import android.view.View;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.ui.base.BaseViewModule;

/**
 * Created by CXJ on 2016/6/11.
 */
public class ElectricViewModule extends BaseViewModule {
    private TextView mKwAllTv;
    private TextView mKwATv;
    private TextView mKwBTv;
    private TextView mKwCTv;

    private TextView mKvarAllTv;
    private TextView mKvarATv;
    private TextView mKvarBTv;
    private TextView mKvarCTv;

    private TextView mFactorAllTv;
    private TextView mFactorATv;
    private TextView mFactorBTv;
    private TextView mFactorCTv;

    private TextView mUaTv;
    private TextView mUbTv;
    private TextView mUcTv;

    private TextView mIaTv;
    private TextView mIbTv;
    private TextView mIcTv;
    private TextView mIzeroTv;

    private TextView mKvaAllTv;
    private TextView mKvaATv;
    private TextView mKvaBTv;
    private TextView mKvaCTv;

    public static ElectricViewModule newInstance(View rootview) {
        ElectricViewModule viewmodule = new ElectricViewModule(rootview);
        viewmodule.init();
        return viewmodule;
    }

    private ElectricViewModule(View rootview) {
        super(rootview);
    }


    protected void initView() {
        mKwAllTv = (TextView) findViewById(R.id.electric_kw_all_tv);
        mKwATv = (TextView) findViewById(R.id.electric_kw_a_tv);
        mKwBTv = (TextView) findViewById(R.id.electric_kw_b_tv);
        mKwCTv = (TextView) findViewById(R.id.electric_kw_c_tv);

        mKvarAllTv = (TextView) findViewById(R.id.electric_kvar_all_tv);
        mKvarATv = (TextView) findViewById(R.id.electric_kvar_a_tv);
        mKvarBTv = (TextView) findViewById(R.id.electric_kvar_b_tv);
        mKvarCTv = (TextView) findViewById(R.id.electric_kvar_c_tv);

        mFactorAllTv = (TextView) findViewById(R.id.electric_factor_all_tv);
        mFactorATv = (TextView) findViewById(R.id.electric_factor_a_tv);
        mFactorBTv = (TextView) findViewById(R.id.electric_factor_b_tv);
        mFactorCTv = (TextView) findViewById(R.id.electric_factor_c_tv);

        mUaTv = (TextView) findViewById(R.id.electric_u_a_tv);
        mUbTv = (TextView) findViewById(R.id.electric_u_b_tv);
        mUcTv = (TextView) findViewById(R.id.electric_u_c_tv);

        mIaTv = (TextView) findViewById(R.id.electric_i_a_tv);
        mIbTv = (TextView) findViewById(R.id.electric_i_b_tv);
        mIcTv = (TextView) findViewById(R.id.electric_i_c_tv);
        mIzeroTv = (TextView) findViewById(R.id.electric_i_zero_tv);

        mKvaAllTv = (TextView) findViewById(R.id.electric_kva_all_tv);
        mKvaATv = (TextView) findViewById(R.id.electric_kva_a_tv);
        mKvaBTv = (TextView) findViewById(R.id.electric_kva_b_tv);
        mKvaCTv = (TextView) findViewById(R.id.electric_kva_c_tv);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public void update(UIElectricInfo info) {
        mKwAllTv.setText(info.getKw_All() + "kW");
        mKwATv.setText(info.getKw_A() + "kW");
        mKwBTv.setText(info.getKw_B() + "kW");
        mKwCTv.setText(info.getKw_C() + "kW");

        mKvarAllTv.setText(info.getKvar_All() + "kvar");
        mKvarATv.setText(info.getKvar_A() + "kvar");
        mKvarBTv.setText(info.getKvar_B() + "kvar");
        mKvarCTv.setText(info.getKvar_C() + "kvar");

        mFactorAllTv.setText(info.getF_ALL() + "%");
        mFactorATv.setText(info.getF_A() + "%");
        mFactorBTv.setText(info.getF_B() + "%");
        mFactorCTv.setText(info.getF_C() + "%");

        mUaTv.setText(info.getUa() + "V");
        mUbTv.setText(info.getUb() + "V");
        mUcTv.setText(info.getUc() + "V");

        mIaTv.setText(info.getIa() + "A");
        mIbTv.setText(info.getIb() + "A");
        mIcTv.setText(info.getIc() + "A");
        mIzeroTv.setText(info.getI_zero() + "A");

        mKvaAllTv.setText(info.getKva_All() + "kVA");
        mKvaATv.setText(info.getKva_A() + "kVA");
        mKvaBTv.setText(info.getKva_B() + "kVA");
        mKvaCTv.setText(info.getKva_C() + "kVA");
    }
}
