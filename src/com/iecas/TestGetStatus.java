package com.iecas;

import java.util.ArrayList;
import java.util.List;

public class TestGetStatus {
    public static void main (String[] args) {
        GetStatus getStatus = new GetStatus();
        NameNodeStatus n = getStatus.getNameNodeStatus();
        System.out.println("NumLiveDataNodes: " + n.getNumlivedatanodes());
        System.out.println("PercentUsed: " + n.getPercentused());
        System.out.print("LiveNodes: ");
        for (String ln : n.getLivenodes()) {
            System.out.print(ln + " ");
        }
        System.out.println();
        System.out.print("DeadNodes: ");
        for (String dn : n.getDeadnodes()) {
            System.out.print(dn + " ");
        }
        System.out.println();

        List<String> hostsip = new ArrayList<String>();
        List<String> hostsname = new ArrayList<String>();
        hostsip.add("192.168.1.12");
        hostsip.add("192.168.1.13");
        hostsname.add("slave1");
        hostsname.add("slave2");
        getStatus = new GetStatus(hostsip, hostsname);
        List<DataNodeStatus> d = getStatus.getDataNodeStatus();
        for (int i = 0; i < d.size(); i++) {
            System.out.println(d.get(i).getIp());
            System.out.println(d.get(i).getHostname());
            System.out.println(d.get(i).getReadblockopavgtime());
            System.out.println(d.get(i).getWriteblockopavgtime());
        }
    }
}
