package com.iecas;

public class DataNodeStatus {
    private String ip;
    private String hostname;
    private double readblockopavgtime;
    private double writeblockopavgtime;

    public double getReadblockopavgtime() {
        return readblockopavgtime;
    }

    public void setReadblockopavgtime(double readblockopavgtime) {
        this.readblockopavgtime = readblockopavgtime;
    }

    public double getWriteblockopavgtime() {
        return writeblockopavgtime;
    }

    public void setWriteblockopavgtime(double writeblockopavgtime) {
        this.writeblockopavgtime = writeblockopavgtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
