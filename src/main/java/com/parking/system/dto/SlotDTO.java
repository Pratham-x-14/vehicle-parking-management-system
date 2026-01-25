package com.parking.system.dto;

public class SlotDTO {
    private String slotNumber;
    private com.parking.system.enums.VehicleType type;
    private boolean isAvailable;

    public SlotDTO() {
    }

    public SlotDTO(String slotNumber, com.parking.system.enums.VehicleType type, boolean isAvailable) {
        this.slotNumber = slotNumber;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public static SlotDTOBuilder builder() {
        return new SlotDTOBuilder();
    }

    public static class SlotDTOBuilder {
        private String slotNumber;
        private com.parking.system.enums.VehicleType type;
        private boolean isAvailable;

        public SlotDTOBuilder slotNumber(String slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }

        public SlotDTOBuilder type(com.parking.system.enums.VehicleType type) {
            this.type = type;
            return this;
        }

        public SlotDTOBuilder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public SlotDTO build() {
            return new SlotDTO(slotNumber, type, isAvailable);
        }
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public com.parking.system.enums.VehicleType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
