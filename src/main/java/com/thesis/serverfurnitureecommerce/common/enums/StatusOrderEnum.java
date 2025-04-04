package com.thesis.serverfurnitureecommerce.common.enums;

public enum StatusOrderEnum {
    PENDING("Chờ xác nhận"),
    APPROVED("Đã duyệt"),
    SHIPPING("Đang giao hàng"),
    COMPLETED("Hoàn thành"),
    CANCELED("Đã hủy");

    private final String displayName;

    StatusOrderEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    public static StatusOrderEnum fromString(String status) {
        for (StatusOrderEnum orderStatus : StatusOrderEnum.values()) {
            if (orderStatus.displayName.equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
