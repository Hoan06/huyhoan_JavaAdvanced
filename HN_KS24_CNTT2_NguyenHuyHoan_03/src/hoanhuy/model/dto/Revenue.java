package hoanhuy.model.dto;

import java.math.BigDecimal;

public class Revenue {
    private String timeLabel;
    private int totalOrders;
    private BigDecimal totalRevenue;

    public Revenue() {
    }

    public Revenue(String timeLabel, int totalOrders, BigDecimal totalRevenue) {
        this.timeLabel = timeLabel;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(String timeLabel) {
        this.timeLabel = timeLabel;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
