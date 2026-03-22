package btvn.bai5;

import java.util.List;

public class DoctorService {
    private DoctorDAO doctorDAO = new DoctorDAO();

    public void displayAllDoctors() {
        List<Doctor> list = doctorDAO.findAll();

        if (list.isEmpty()) {
            System.out.println("Danh sách bác sĩ rỗng !");
            return;
        }

        System.out.println("===== DANH SÁCH BÁC SĨ =====");
        System.out.printf("%-15s %-30s %-25s\n", "Mã số", "Họ tên", "Chuyên khoa");

        for (Doctor doctor : list) {
            System.out.printf("%-15s %-30s %-25s\n",
                    doctor.getDoctorId(),
                    doctor.getFullName(),
                    doctor.getSpecialty());
        }
    }

    public void addDoctor(String doctorId, String fullName, String specialty) {
        if (doctorId == null || doctorId.trim().isEmpty()) {
            System.out.println("Mã bác sĩ không được để trống !");
            return;
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("Họ tên không được để trống !");
            return;
        }

        if (specialty == null || specialty.trim().isEmpty()) {
            System.out.println("Chuyên khoa không được để trống !");
            return;
        }

        if (specialty.length() > 50) {
            System.out.println("Chuyên khoa quá dài !");
            return;
        }

        if (doctorDAO.existsById(doctorId)) {
            System.out.println("Mã bác sĩ đã tồn tại !");
            return;
        }

        Doctor doctor = new Doctor(doctorId, fullName, specialty);
        boolean result = doctorDAO.insertDoctor(doctor);

        if (result) {
            System.out.println("Thêm bác sĩ thành công !");
        } else {
            System.out.println("Thêm bác sĩ thất bại !");
        }
    }

    public void statisticBySpecialty() {
        doctorDAO.countDoctorBySpecialty();
    }
}