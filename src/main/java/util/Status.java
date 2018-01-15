package util;

public enum Status {
    NEW("new"), ON_PROCESS("on_process"), OLD("old"), CANCELED("canceled");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
