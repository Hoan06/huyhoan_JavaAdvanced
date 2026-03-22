package btvn.btth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    public boolean addAppointment(Appointment appointment) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO appointments(patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try {
            con = DatabaseConnection.openConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(con, ps, null);
        }

        return false;
    }

    public boolean updateAppointment(Appointment appointment) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE appointments SET patient_name = ?, appointment_date = ?, doctor_name = ?, status = ? WHERE id = ?";

        try {
            con = DatabaseConnection.openConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());
            ps.setInt(5, appointment.getId());

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(con, ps, null);
        }

        return false;
    }

    public boolean deleteAppointment(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM appointments WHERE id = ?";

        try {
            con = DatabaseConnection.openConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(con, ps, null);
        }

        return false;
    }

    public Appointment getAppointmentById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM appointments WHERE id = ?";

        try {
            con = DatabaseConnection.openConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("id"));
                appointment.setPatientName(rs.getString("patient_name"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setDoctorName(rs.getString("doctor_name"));
                appointment.setStatus(rs.getString("status"));
                return appointment;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(con, ps, rs);
        }

        return null;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM appointments";

        try {
            con = DatabaseConnection.openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("id"));
                appointment.setPatientName(rs.getString("patient_name"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setDoctorName(rs.getString("doctor_name"));
                appointment.setStatus(rs.getString("status"));

                list.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(con, ps, rs);
        }

        return list;
    }
}
