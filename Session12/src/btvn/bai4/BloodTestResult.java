package btvn.bai4;

class BloodTestResult {
    private int patientId;
    private String testName;
    private double resultValue;

    public BloodTestResult(int patientId, String testName, double resultValue) {
        this.patientId = patientId;
        this.testName = testName;
        this.resultValue = resultValue;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getTestName() {
        return testName;
    }

    public double getResultValue() {
        return resultValue;
    }
}