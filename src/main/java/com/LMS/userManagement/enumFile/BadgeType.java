package com.LMS.userManagement.enumFile;

public enum BadgeType {

        GOLD(1),
        SILVER(2),
        BRONZE(3);

        private final int value;

        BadgeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static BadgeType fromValue(int value) {
            for (BadgeType type : BadgeType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid BadgeType value: " + value);
        }
    }


