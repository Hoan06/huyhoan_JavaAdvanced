package btvn.bai5;

import java.sql.*;

public class PatientController {

    public static void admitPatient(String name, int age, int bedId, double amount) {

        Connection conn = null;

        try {
            conn = DatabaseHelper.getConnection();
            conn.setAutoCommit(false);

            // 1. Check bed available
            String checkBed = "SELECT status FROM Beds WHERE id = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkBed);
            psCheck.setInt(1, bedId);

            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Bed not found!");
            }
            if (!rs.getString("status").equals("EMPTY")) {
                throw new SQLException("Bed is not available!");
            }

            // 2. Insert patient
            String insertPatient = "INSERT INTO Patients(name, age, bed_id, status) VALUES (?, ?, ?, 'ADMITTED')";
            PreparedStatement psPatient = conn.prepareStatement(insertPatient, Statement.RETURN_GENERATED_KEYS);
            psPatient.setString(1, name);
            psPatient.setInt(2, age);
            psPatient.setInt(3, bedId);

            int row1 = psPatient.executeUpdate();
            if (row1 == 0) throw new SQLException("Insert patient failed");

            ResultSet key = psPatient.getGeneratedKeys();
            key.next();
            int patientId = key.getInt(1);

            // 3. Update bed
            String updateBed = "UPDATE Beds SET status = 'OCCUPIED' WHERE id = ?";
            PreparedStatement psBed = conn.prepareStatement(updateBed);
            psBed.setInt(1, bedId);

            int row2 = psBed.executeUpdate();
            if (row2 == 0) throw new SQLException("Update bed failed");

            // 4. Insert payment
            String insertPayment = "INSERT INTO Payments(patient_id, amount, created_at) VALUES (?, ?, NOW())";
            PreparedStatement psPay = conn.prepareStatement(insertPayment);
            psPay.setInt(1, patientId);
            psPay.setDouble(2, amount);

            int row3 = psPay.executeUpdate();
            if (row3 == 0) throw new SQLException("Insert payment failed");

            conn.commit();
            System.out.println("Admit success!");

        } catch (Exception e) {

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Error: " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}