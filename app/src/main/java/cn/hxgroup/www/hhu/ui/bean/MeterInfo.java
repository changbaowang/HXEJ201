package cn.hxgroup.www.hhu.ui.bean;

/**
 * Created by hex170 on 2016/8/25.
 * Meter bean
 */
public class MeterInfo {
    private String meterId;
    private String meterPn;
    private String meterType;

    public MeterInfo(String meterId, String meterPn, String meterType) {
        this.meterId = meterId;
        this.meterPn = meterPn;
        this.meterType = meterType;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getMeterPn() {
        return meterPn;
    }

    public void setMeterPn(String meterPn) {
        this.meterPn = meterPn;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }
}
