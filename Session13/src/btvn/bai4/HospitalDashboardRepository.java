package btvn.bai4;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HospitalDashboardRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/rikkei_hospital_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static List<PatientDTO> getEmergencyDashboard() {
        List<PatientDTO> result = new ArrayList<>();

        String sql = """
                SELECT 
                    bn.maBenhNhan,
                    bn.tenBenhNhan,
                    bn.tuoi,
                    bn.gioiTinh,
                    dv.maDichVu,
                    dv.tenDichVu,
                    dv.loai,
                    dv.soLuong,
                    dv.thoiGianSuDung
                FROM BenhNhan bn
                LEFT JOIN DichVuSuDung dv 
                    ON bn.maBenhNhan = dv.maBenhNhan
                WHERE bn.khoa = ?
                  AND bn.trangThai = 'DangNamVien'
                ORDER BY bn.maBenhNhan, dv.thoiGianSuDung
                """;

        Map<Integer, PatientDTO> patientMap = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Cấp cứu");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    int patientId = rs.getInt("maBenhNhan");

                    PatientDTO patient = patientMap.get(patientId);
                    if (patient == null) {
                        patient = new PatientDTO();
                        patient.setPatientId(patientId);
                        patient.setPatientName(rs.getString("tenBenhNhan"));
                        patient.setAge(rs.getInt("tuoi"));
                        patient.setGender(rs.getString("gioiTinh"));
                        patient.setServices(new ArrayList<>());
                        patientMap.put(patientId, patient);
                    }

                    /*
                     * LEFT JOIN may return NULL service.
                     * Must check before mapping to avoid fake data and keep empty list.
                     */
                    int serviceId = rs.getInt("maDichVu");
                    if (!rs.wasNull()) {
                        ServiceUsage service = new ServiceUsage();
                        service.setServiceId(serviceId);
                        service.setServiceName(rs.getString("tenDichVu"));
                        service.setType(rs.getString("loai"));
                        service.setQuantity(rs.getInt("soLuong"));
                        service.setUsedAt(rs.getTimestamp("thoiGianSuDung"));

                        patient.getServices().add(service);
                    }
                }
            }

            result = new ArrayList<>(patientMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        List<PatientDTO> list = getEmergencyDashboard();
        for (PatientDTO p : list) {
            System.out.println("Patient: " + p.getPatientName());
            if (p.getServices().isEmpty()) {
                System.out.println("  -> No services");
            } else {
                for (ServiceUsage s : p.getServices()) {
                    System.out.println("  -> " + s);
                }
            }
        }
    }
}
