package btvn.btth;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repository = new AppointmentRepository();

        // 1. Thêm lịch khám
        Appointment newAppointment = new Appointment(
                "Nguyen Van A",
                Date.valueOf("2026-03-25"),
                "Dr. Tran",
                "Pending"
        );

        boolean addResult = repository.addAppointment(newAppointment);
        System.out.println("Add appointment: " + addResult);

        // 2. Lấy tất cả lịch khám
        List<Appointment> appointments = repository.getAllAppointments();
        System.out.println("Danh sách lịch khám:");
        for (Appointment a : appointments) {
            System.out.println(
                    a.getId() + " | " +
                            a.getPatientName() + " | " +
                            a.getAppointmentDate() + " | " +
                            a.getDoctorName() + " | " +
                            a.getStatus()
            );
        }

        // 3. Lấy lịch khám theo ID
        Appointment appointment = repository.getAppointmentById(1);
        if (appointment != null) {
            System.out.println("Tìm theo ID = 1:");
            System.out.println(
                    appointment.getId() + " | " +
                            appointment.getPatientName() + " | " +
                            appointment.getAppointmentDate() + " | " +
                            appointment.getDoctorName() + " | " +
                            appointment.getStatus()
            );
        } else {
            System.out.println("Không tìm thấy lịch khám có ID = 1");
        }

        // 4. Cập nhật lịch khám
        Appointment updateAppointment = new Appointment(
                1,
                "Nguyen Van A",
                Date.valueOf("2026-03-26"),
                "Dr. Le",
                "Confirmed"
        );

        boolean updateResult = repository.updateAppointment(updateAppointment);
        System.out.println("Update appointment: " + updateResult);

        // 5. Xóa lịch khám
        boolean deleteResult = repository.deleteAppointment(2);
        System.out.println("Delete appointment: " + deleteResult);
    }
}
