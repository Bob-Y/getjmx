package com.iecas;

import java.util.ArrayList;

public class NameNodeStatus {

    private int numlivedatanodes;
    private int numdeaddatanodes;
    private double memheapusedm;
    private int threadsblocked;
    private int threadswaiting;
    private double total;
    private double used;
    private double percentused;
    private double blockpoolusedspace;
    private int totalblocks;
    private int totalfiles;
    private int numberofmissingblocks;
    private ArrayList<String> livenodes;
    private ArrayList<String> deadnodes;

    public int getNumlivedatanodes() {
        return numlivedatanodes;
    }

    public void setNumlivedatanodes(int numlivedatanodes) {
        this.numlivedatanodes = numlivedatanodes;
    }

    public int getNumdeaddatanodes() {
        return numdeaddatanodes;
    }

    public void setNumdeaddatanodes(int numdeaddatanodes) {
        this.numdeaddatanodes = numdeaddatanodes;
    }

    public double getMemheapusedm() {
        return memheapusedm;
    }

    public void setMemheapusedm(double memheapusedm) {
        this.memheapusedm = memheapusedm;
    }

    public int getThreadsblocked() {
        return threadsblocked;
    }

    public void setThreadsblocked(int threadsblocked) {
        this.threadsblocked = threadsblocked;
    }

    public int getThreadswaiting() {
        return threadswaiting;
    }

    public void setThreadswaiting(int threadswaiting) {
        this.threadswaiting = threadswaiting;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getPercentused() {
        return percentused;
    }

    public void setPercentused(double percentused) {
        this.percentused = percentused;
    }

    public double getBlockpoolusedspace() {
        return blockpoolusedspace;
    }

    public void setBlockpoolusedspace(double blockpoolusedspace) {
        this.blockpoolusedspace = blockpoolusedspace;
    }

    public int getTotalblocks() {
        return totalblocks;
    }

    public void setTotalblocks(int totalblocks) {
        this.totalblocks = totalblocks;
    }

    public int getTotalfiles() {
        return totalfiles;
    }

    public void setTotalfiles(int totalfiles) {
        this.totalfiles = totalfiles;
    }

    public int getNumberofmissingblocks() {
        return numberofmissingblocks;
    }

    public void setNumberofmissingblocks(int numberofmissingblocks) {
        this.numberofmissingblocks = numberofmissingblocks;
    }

    public ArrayList<String> getLivenodes() {
        return livenodes;
    }

    public void setLivenodes(ArrayList<String> livenodes) {
        this.livenodes = livenodes;
    }

    public ArrayList<String> getDeadnodes() {
        return deadnodes;
    }

    public void setDeadnodes(ArrayList<String> deadnodes) {
        this.deadnodes = deadnodes;
    }
}
