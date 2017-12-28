package common.utils.bean;

import java.math.BigDecimal;

public class SalaryBean {
    BigDecimal pension;
    BigDecimal medical_insurance;
    BigDecimal unemployment_insurance;
    BigDecimal common_reserve_funds;

    public BigDecimal getPension() {
        return pension;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public BigDecimal getMedical_insurance() {
        return medical_insurance;
    }

    public void setMedical_insurance(BigDecimal medical_insurance) {
        this.medical_insurance = medical_insurance;
    }

    public BigDecimal getUnemployment_insurance() {
        return unemployment_insurance;
    }

    public void setUnemployment_insurance(BigDecimal unemployment_insurance) {
        this.unemployment_insurance = unemployment_insurance;
    }

    public BigDecimal getCommon_reserve_funds() {
        return common_reserve_funds;
    }

    public void setCommon_reserve_funds(BigDecimal common_reserve_funds) {
        this.common_reserve_funds = common_reserve_funds;
    }
}
