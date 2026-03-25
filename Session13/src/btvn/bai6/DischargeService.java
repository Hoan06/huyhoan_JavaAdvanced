package btvn.bai6;

import java.sql.*;

public class DischargeService {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void dischargePatient(int patientId, double amount) {

        Connection conn = null;
        PreparedStatement psInvoice = null;
        PreparedStatement psUpdatePatient = null;
        PreparedStatement psFreeBed = null;
        PreparedStatement psFindBed = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            /*
             * BUSINESS FLOW:
             * 1. Create invoice
             * 2. Update patient status → 'Discharged'
             * 3. Free bed → set status 'EMPTY'
             *
             * RULE:
             * - All 3 steps must succeed → COMMIT
             * - Any step fails → ROLLBACK
             *
             * PURPOSE:
             * - Avoid: patient charged but not discharged
             * - Avoid: bed still occupied after discharge
             */

            conn.setAutoCommit(false);

            // 1. Create invoice
            String sqlInvoice = "INSERT INTO INVOICES(patient_id, amount, created_at) VALUES (?, ?, NOW())";
            psInvoice = conn.prepareStatement(sqlInvoice);
            psInvoice.setInt(1, patientId);
            psInvoice.setDouble(2, amount);

            int r1 = psInvoice.executeUpdate();
            if (r1 == 0) {
                throw new SQLException("Create invoice failed");
            }

            // 2. Update patient status
            String sqlPatient = "UPDATE PATIENTS SET status = 'Discharged' WHERE id = ?";
            psUpdatePatient = conn.prepareStatement(sqlPatient);
            psUpdatePatient.setInt(1, patientId);

            int r2 = psUpdatePatient.executeUpdate();
            if (r2 == 0) {
                throw new SQLException("Patient not found or update failed");
            }

            // 3. Find bed of patient
            String sqlFindBed = "SELECT id FROM BEDS WHERE patient_id = ?";
            psFindBed = conn.prepareStatement(sqlFindBed);
            psFindBed.setInt(1, patientId);
            rs = psFindBed.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Bed not found for patient");
            }

            String bedId = rs.getString("id");

            // 4. Free bed
            String sqlBed = "UPDATE BEDS SET patient_id = NULL, status = 'EMPTY' WHERE id = ?";
            psFreeBed = conn.prepareStatement(sqlBed);
            psFreeBed.setString(1, bedId);

            int r3 = psFreeBed.executeUpdate();
            if (r3 == 0) {
                throw new SQLException("Free bed failed");
            }

            conn.commit();
            System.out.println("Discharge success!");

        } catch (SQLException e) {

            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Transaction failed: " + e.getMessage());

        } finally {
            try {
                if (rs != null) rs.close();
                if (psInvoice != null) psInvoice.close();
                if (psUpdatePatient != null) psUpdatePatient.close();
                if (psFindBed != null) psFindBed.close();
                if (psFreeBed != null) psFreeBed.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        dischargePatient(101, 500000);
    }
}