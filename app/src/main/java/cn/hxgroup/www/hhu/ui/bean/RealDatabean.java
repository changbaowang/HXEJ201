package cn.hxgroup.www.hhu.ui.bean;

/**
 * Created by hex170 on 2016/10/28.
 */
public class RealDatabean {
    private String ADDRESSID;
    private String CurrentTotalActivePower;
    private String CurrentAPhaseActivPower;
    private String CurrentBPhaseActivPower;
    private String CurrentCPhaseActivPower;
    private String CurrentTotalReactivePower;
    private String CurrentAPhaseReactivePower;
    private String CurrentBPhaseReactivePower;
    private String CurrentCPhaseReactivePower;
    private String CurrentTotalPoweFactor;
    private String CurrentAPhasePowerFactor;
    private String CurrentBPhasePowerFactor;
    private String CurrentCPhasePowerFactor;
    private String CurrentAPhaseVoltage;
    private String CurrentBPhaseVoltage;
    private String CurrentCPhaseVoltage;
    private String CurrentAPhaseCurrent;
    private String CurrentBPhaseCurrent;
    private String CurrentCPhaseCurrent;
    private String CurrentZeroSequenceCurrent;
    private String CurrentTotalApparentPower;
    private String TheCurrentAPhaseApparentPower;
    private String TheCurrentBPhaseApparentPower;
    private String TheCurrentCPhaseApparentPower;
    private String UaPhaseAngle;
    private String UbPhaseAngle;
    private String UcPhaseAngle;
    private String IaPhaseAngle;
    private String IbPhaseAngle;
    private String IcPhaseAngle;

    public RealDatabean(String ADDRESSID, String currentTotalActivePower,
                        String currentAPhaseActivPower, String currentBPhaseActivPower,
                        String currentCPhaseActivPower, String currentTotalReactivePower,
                        String currentAPhaseReactivePower, String currentBPhaseReactivePower,
                        String currentCPhaseReactivePower, String currentTotalPoweFactor,
                        String currentAPhasePowerFactor, String currentBPhasePowerFactor,
                        String currentCPhasePowerFactor, String currentAPhaseVoltage,
                        String currentBPhaseVoltage, String currentCPhaseVoltage,
                        String currentAPhaseCurrent, String currentBPhaseCurrent,
                        String currentCPhaseCurrent, String currentZeroSequenceCurrent,
                        String currentTotalApparentPower, String theCurrentAPhaseApparentPower,
                        String theCurrentBPhaseApparentPower, String theCurrentCPhaseApparentPower,
                        String uaPhaseAngle, String ubPhaseAngle, String ucPhaseAngle, String iaPhaseAngle,
                        String ibPhaseAngle, String icPhaseAngle) {
        this.ADDRESSID = ADDRESSID;
        CurrentTotalActivePower = currentTotalActivePower;
        CurrentAPhaseActivPower = currentAPhaseActivPower;
        CurrentBPhaseActivPower = currentBPhaseActivPower;
        CurrentCPhaseActivPower = currentCPhaseActivPower;
        CurrentTotalReactivePower = currentTotalReactivePower;
        CurrentAPhaseReactivePower = currentAPhaseReactivePower;
        CurrentBPhaseReactivePower = currentBPhaseReactivePower;
        CurrentCPhaseReactivePower = currentCPhaseReactivePower;
        CurrentTotalPoweFactor = currentTotalPoweFactor;
        CurrentAPhasePowerFactor = currentAPhasePowerFactor;
        CurrentBPhasePowerFactor = currentBPhasePowerFactor;
        CurrentCPhasePowerFactor = currentCPhasePowerFactor;
        CurrentAPhaseVoltage = currentAPhaseVoltage;
        CurrentBPhaseVoltage = currentBPhaseVoltage;
        CurrentCPhaseVoltage = currentCPhaseVoltage;
        CurrentAPhaseCurrent = currentAPhaseCurrent;
        CurrentBPhaseCurrent = currentBPhaseCurrent;
        CurrentCPhaseCurrent = currentCPhaseCurrent;
        CurrentZeroSequenceCurrent = currentZeroSequenceCurrent;
        CurrentTotalApparentPower = currentTotalApparentPower;
        TheCurrentAPhaseApparentPower = theCurrentAPhaseApparentPower;
        TheCurrentBPhaseApparentPower = theCurrentBPhaseApparentPower;
        TheCurrentCPhaseApparentPower = theCurrentCPhaseApparentPower;
        UaPhaseAngle = uaPhaseAngle;
        UbPhaseAngle = ubPhaseAngle;
        UcPhaseAngle = ucPhaseAngle;
        IaPhaseAngle = iaPhaseAngle;
        IbPhaseAngle = ibPhaseAngle;
        IcPhaseAngle = icPhaseAngle;
    }

    public String getADDRESSID() {
        return ADDRESSID;
    }

    public void setADDRESSID(String ADDRESSID) {
        this.ADDRESSID = ADDRESSID;
    }

    public String getCurrentTotalActivePower() {
        return CurrentTotalActivePower;
    }

    public void setCurrentTotalActivePower(String currentTotalActivePower) {
        CurrentTotalActivePower = currentTotalActivePower;
    }

    public String getCurrentAPhaseActivPower() {
        return CurrentAPhaseActivPower;
    }

    public void setCurrentAPhaseActivPower(String currentAPhaseActivPower) {
        CurrentAPhaseActivPower = currentAPhaseActivPower;
    }

    public String getCurrentBPhaseActivPower() {
        return CurrentBPhaseActivPower;
    }

    public void setCurrentBPhaseActivPower(String currentBPhaseActivPower) {
        CurrentBPhaseActivPower = currentBPhaseActivPower;
    }

    public String getCurrentCPhaseActivPower() {
        return CurrentCPhaseActivPower;
    }

    public void setCurrentCPhaseActivPower(String currentCPhaseActivPower) {
        CurrentCPhaseActivPower = currentCPhaseActivPower;
    }

    public String getCurrentTotalReactivePower() {
        return CurrentTotalReactivePower;
    }

    public void setCurrentTotalReactivePower(String currentTotalReactivePower) {
        CurrentTotalReactivePower = currentTotalReactivePower;
    }

    public String getCurrentAPhaseReactivePower() {
        return CurrentAPhaseReactivePower;
    }

    public void setCurrentAPhaseReactivePower(String currentAPhaseReactivePower) {
        CurrentAPhaseReactivePower = currentAPhaseReactivePower;
    }

    public String getCurrentBPhaseReactivePower() {
        return CurrentBPhaseReactivePower;
    }

    public void setCurrentBPhaseReactivePower(String currentBPhaseReactivePower) {
        CurrentBPhaseReactivePower = currentBPhaseReactivePower;
    }

    public String getCurrentCPhaseReactivePower() {
        return CurrentCPhaseReactivePower;
    }

    public void setCurrentCPhaseReactivePower(String currentCPhaseReactivePower) {
        CurrentCPhaseReactivePower = currentCPhaseReactivePower;
    }

    public String getCurrentTotalPoweFactor() {
        return CurrentTotalPoweFactor;
    }

    public void setCurrentTotalPoweFactor(String currentTotalPoweFactor) {
        CurrentTotalPoweFactor = currentTotalPoweFactor;
    }

    public String getCurrentAPhasePowerFactor() {
        return CurrentAPhasePowerFactor;
    }

    public void setCurrentAPhasePowerFactor(String currentAPhasePowerFactor) {
        CurrentAPhasePowerFactor = currentAPhasePowerFactor;
    }

    public String getCurrentBPhasePowerFactor() {
        return CurrentBPhasePowerFactor;
    }

    public void setCurrentBPhasePowerFactor(String currentBPhasePowerFactor) {
        CurrentBPhasePowerFactor = currentBPhasePowerFactor;
    }

    public String getCurrentCPhasePowerFactor() {
        return CurrentCPhasePowerFactor;
    }

    public void setCurrentCPhasePowerFactor(String currentCPhasePowerFactor) {
        CurrentCPhasePowerFactor = currentCPhasePowerFactor;
    }

    public String getCurrentAPhaseVoltage() {
        return CurrentAPhaseVoltage;
    }

    public void setCurrentAPhaseVoltage(String currentAPhaseVoltage) {
        CurrentAPhaseVoltage = currentAPhaseVoltage;
    }

    public String getCurrentBPhaseVoltage() {
        return CurrentBPhaseVoltage;
    }

    public void setCurrentBPhaseVoltage(String currentBPhaseVoltage) {
        CurrentBPhaseVoltage = currentBPhaseVoltage;
    }

    public String getCurrentCPhaseVoltage() {
        return CurrentCPhaseVoltage;
    }

    public void setCurrentCPhaseVoltage(String currentCPhaseVoltage) {
        CurrentCPhaseVoltage = currentCPhaseVoltage;
    }

    public String getCurrentAPhaseCurrent() {
        return CurrentAPhaseCurrent;
    }

    public void setCurrentAPhaseCurrent(String currentAPhaseCurrent) {
        CurrentAPhaseCurrent = currentAPhaseCurrent;
    }

    public String getCurrentBPhaseCurrent() {
        return CurrentBPhaseCurrent;
    }

    public void setCurrentBPhaseCurrent(String currentBPhaseCurrent) {
        CurrentBPhaseCurrent = currentBPhaseCurrent;
    }

    public String getCurrentCPhaseCurrent() {
        return CurrentCPhaseCurrent;
    }

    public void setCurrentCPhaseCurrent(String currentCPhaseCurrent) {
        CurrentCPhaseCurrent = currentCPhaseCurrent;
    }

    public String getCurrentZeroSequenceCurrent() {
        return CurrentZeroSequenceCurrent;
    }

    public void setCurrentZeroSequenceCurrent(String currentZeroSequenceCurrent) {
        CurrentZeroSequenceCurrent = currentZeroSequenceCurrent;
    }

    public String getCurrentTotalApparentPower() {
        return CurrentTotalApparentPower;
    }

    public void setCurrentTotalApparentPower(String currentTotalApparentPower) {
        CurrentTotalApparentPower = currentTotalApparentPower;
    }

    public String getTheCurrentAPhaseApparentPower() {
        return TheCurrentAPhaseApparentPower;
    }

    public void setTheCurrentAPhaseApparentPower(String theCurrentAPhaseApparentPower) {
        TheCurrentAPhaseApparentPower = theCurrentAPhaseApparentPower;
    }

    public String getTheCurrentBPhaseApparentPower() {
        return TheCurrentBPhaseApparentPower;
    }

    public void setTheCurrentBPhaseApparentPower(String theCurrentBPhaseApparentPower) {
        TheCurrentBPhaseApparentPower = theCurrentBPhaseApparentPower;
    }

    public String getTheCurrentCPhaseApparentPower() {
        return TheCurrentCPhaseApparentPower;
    }

    public void setTheCurrentCPhaseApparentPower(String theCurrentCPhaseApparentPower) {
        TheCurrentCPhaseApparentPower = theCurrentCPhaseApparentPower;
    }

    public String getUaPhaseAngle() {
        return UaPhaseAngle;
    }

    public void setUaPhaseAngle(String uaPhaseAngle) {
        UaPhaseAngle = uaPhaseAngle;
    }

    public String getUbPhaseAngle() {
        return UbPhaseAngle;
    }

    public void setUbPhaseAngle(String ubPhaseAngle) {
        UbPhaseAngle = ubPhaseAngle;
    }

    public String getUcPhaseAngle() {
        return UcPhaseAngle;
    }

    public void setUcPhaseAngle(String ucPhaseAngle) {
        UcPhaseAngle = ucPhaseAngle;
    }

    public String getIaPhaseAngle() {
        return IaPhaseAngle;
    }

    public void setIaPhaseAngle(String iaPhaseAngle) {
        IaPhaseAngle = iaPhaseAngle;
    }

    public String getIbPhaseAngle() {
        return IbPhaseAngle;
    }

    public void setIbPhaseAngle(String ibPhaseAngle) {
        IbPhaseAngle = ibPhaseAngle;
    }

    public String getIcPhaseAngle() {
        return IcPhaseAngle;
    }

    public void setIcPhaseAngle(String icPhaseAngle) {
        IcPhaseAngle = icPhaseAngle;
    }
}
