package btvn.bai5;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> findAll() {
        List<Doctor> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select doctor_id, full_name, specialty from Doctors";

        try {
            con = DBContext.openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty")
                );
                list.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBContext.closePrepared(con, ps, rs);
        }

        return list;
    }

    public boolean insertDoctor(Doctor doctor) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "insert into Doctors(doctor_id, full_name, specialty) values (?, ?, ?)";

        try {
            con = DBContext.openConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, doctor.getDoctorId());
            ps.setString(2, doctor.getFullName());
            ps.setString(3, doctor.getSpecialty());

            int rowAffected = ps.executeUpdate();
            return rowAffected > 0;

        } catch (Exception e) {
            System.out.println("Thêm bác sĩ thất bại: " + e.getMessage());
        } finally {
            DBContext.closePrepared(con, ps, null);
        }

        return false;
    }

    public boolean existsById(String doctorId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select doctor_id from Doctors where doctor_id = ?";

        try {
            con = DBContext.openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, doctorId);
            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBContext.closePrepared(con, ps, rs);
        }

        return false;
    }

    public void countDoctorBySpecialty() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select specialty, count(*) as total from Doctors group by specialty";

        try {
            con = DBContext.openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            boolean hasData = false;
            System.out.println("===== THỐNG KÊ CHUYÊN KHOA =====");
            System.out.printf("%-25s %-10s\n", "Chuyên khoa", "Số lượng");

            while (rs.next()) {
                String specialty = rs.getString("specialty");
                int total = rs.getInt("total");

                System.out.printf("%-25s %-10d\n", specialty, total);
                hasData = true;
            }

            if (!hasData) {
                System.out.println("Không có dữ liệu để thống kê !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBContext.closePrepared(con, ps, rs);
        }
    }
}
