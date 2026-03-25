package btvn.bai4;

import java.sql.*;
import java.util.*;

class ServiceUsage {
    private int serviceId;
    private String serviceName;
    private String type;
    private int quantity;
    private Timestamp usedAt;

    public ServiceUsage() {}

    public ServiceUsage(int serviceId, String serviceName, String type, int quantity, Timestamp usedAt) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.type = type;
        this.quantity = quantity;
        this.usedAt = usedAt;
    }

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Timestamp getUsedAt() { return usedAt; }
    public void setUsedAt(Timestamp usedAt) { this.usedAt = usedAt; }

    @Override
    public String toString() {
        return "ServiceUsage{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", usedAt=" + usedAt +
                '}';
    }
}

class PatientDTO {
    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private List<ServiceUsage> services;

    public PatientDTO() {
        services = new ArrayList<>();
    }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public List<ServiceUsage> getServices() { return services; }
    public void setServices(List<ServiceUsage> services) { this.services = services; }
}

