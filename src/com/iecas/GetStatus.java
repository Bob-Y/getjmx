package com.iecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetStatus {

    private String host = "master";
    private List<String> hostsip = new ArrayList<String>();
    private List<String> hostsname = new ArrayList<String>();
    private String port = "50070";

    public GetStatus(String host, String port) {
        this.host = host;
        this.port = port;
    }
    public GetStatus() {

    }
    public GetStatus(List<String> hostsip, List<String>hostsname) {
        this.hostsip = hostsip;
        this.hostsname = hostsname;
        this.port = "50075";
    }
    public GetStatus(String host) {
        this.host = host;
        this.port = "50070";
    }

     String loadJSONWithGet (String host, String port, String qry, String get) throws Exception {
        StringBuilder json = new StringBuilder();
//        try {
            String url = "http://" + host + ":" + port + "/jmx?get=" + qry + "::" + get;
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
                json.append(inputLine);
            }
            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return json.toString();
    }

    String setOne(String host, String port, String qry, String get) throws Exception {
        String value = null;
        String json = loadJSONWithGet(host, port, qry, get);
//        try {
            JSONObject jsonObject =  JSONObject.fromObject(json);
            String ja = jsonObject.getString("beans");
            JSONArray jsonArray =  JSONArray.fromObject(ja);
            value = jsonArray.getJSONObject(0).getString(get);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return value;
    }

    ArrayList<String> getIPAddr (String tmp) {
        JSONObject jsonObject = JSONObject.fromObject(tmp);
        Set set = jsonObject.keySet();
        ArrayList<String> nodes = new ArrayList<String>();
        for (Object key : set) {
            String ip = JSONObject.fromObject(jsonObject.get(key)).getString("xferaddr");
            ip = ip.split(":")[0];
            nodes.add(ip);
        }
        return nodes;
    }

    public NameNodeStatus getNameNodeStatus() {

        NameNodeStatus ns = new NameNodeStatus();

        try {
            String tmp = setOne(host, port, "Hadoop:service=NameNode,name=FSNamesystemState", "NumLiveDataNodes");
            ns.setNumlivedatanodes(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=FSNamesystemState", "NumDeadDataNodes");
            ns.setNumdeaddatanodes(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=JvmMetrics", "MemHeapUsedM");
            ns.setMemheapusedm(Double.parseDouble(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=JvmMetrics", "ThreadsBlocked");
            ns.setThreadsblocked(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=JvmMetrics", "ThreadsWaiting");
            ns.setThreadswaiting(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "Total");
            ns.setTotal(Double.parseDouble(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "Used");
            ns.setUsed(Double.parseDouble(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "PercentUsed");
            ns.setPercentused(Double.parseDouble(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "BlockPoolUsedSpace");
            ns.setBlockpoolusedspace(Double.parseDouble(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "TotalBlocks");
            ns.setTotalblocks(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "TotalFiles");
            ns.setTotalfiles(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "NumberOfMissingBlocks");
            ns.setNumberofmissingblocks(Integer.parseInt(tmp));

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "LiveNodes");
            ArrayList<String> ln = getIPAddr(tmp);
            ns.setLivenodes(ln);

            tmp = setOne(host, port, "Hadoop:service=NameNode,name=NameNodeInfo", "DeadNodes");
            ArrayList<String> dn = getIPAddr(tmp);
            ns.setDeadnodes(dn);

        } catch (Exception e) {
            ArrayList<String> nodes = new ArrayList<String>();
            nodes.add("Don't konw.");
            ns.setLivenodes(nodes);
            ns.setDeadnodes(nodes);
            System.out.println("NameNode is dead.");
        }

        return ns;
    }

    public List<DataNodeStatus> getDataNodeStatus() {

        List<DataNodeStatus> dss = new ArrayList<DataNodeStatus>();

        if (hostsip.size() == hostsname.size()) {
            for (int i = 0; i < hostsip.size(); i++) {
                DataNodeStatus ds = new DataNodeStatus();
                String qry = "Hadoop:service=DataNode,name=DataNodeActivity-" + hostsname.get(i) + "-50010";

                try {
                    String tmp = setOne(hostsip.get(i), port, qry, "ReadBlockOpAvgTime");
                    ds.setReadblockopavgtime(Double.parseDouble(tmp));

                    tmp = setOne(hostsip.get(i), port, qry, "WriteBlockOpAvgTime");
                    ds.setWriteblockopavgtime(Double.parseDouble(tmp));

                    tmp = setOne(hostsip.get(i), port, qry, "tag.Hostname");
                    ds.setHostname(tmp);

                    ds.setIp(hostsip.get(i));
                } catch (Exception e) {
                    ds.setHostname(hostsname.get(i));
                    ds.setIp(hostsip.get(i));
                }
                dss.add(ds);
            }
        }
        else {
            System.out.println("InPut Error!");
        }

        return dss;
    }
}
