package lsieun.network.p2p.freepastry;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collection;

import rice.environment.Environment;
import rice.pastry.NodeHandle;
import rice.pastry.NodeIdFactory;
import rice.pastry.PastryNode;
import rice.pastry.PastryNodeFactory;
import rice.pastry.leafset.LeafSet;
import rice.pastry.socket.SocketPastryNodeFactory;
import rice.pastry.standard.RandomNodeIdFactory;

public class FreePastryExample {
    public FreePastryExample(
            int bindPort,
            InetSocketAddress bootAddress,
            Environment environment) throws Exception {
        NodeIdFactory nodeIdFactory = new RandomNodeIdFactory(environment);
        PastryNodeFactory factory = new SocketPastryNodeFactory(nodeIdFactory, bindPort, environment);
        PastryNode node = factory.newNode();

        FreePastryApplication application = new FreePastryApplication(node);
        node.boot(bootAddress);

        System.out.println("Node: " + node.getId().toStringFull() + " created");
        //environment.getTimeSource().sleep(10000);
        //Id randomId = nodeIdFactory.generateNodeId();
        //application.routeMessage(randomId);

        environment.getTimeSource().sleep(10000);
        LeafSet leafSet = node.getLeafSet();
        Collection<NodeHandle> collection = leafSet.getUniqueSet();
        for (NodeHandle nodeHandle : collection) {
            application.routeMessageDirect(nodeHandle);
            environment.getTimeSource().sleep(1000);
        }
    }

    public static void main(String[] args) {
        Environment environment = new Environment();
        environment.getParameters().setString("nat_search_policy", "never");

        try {
            int bindPort = 9001;
            int bootPort = 9001;
            InetAddress bootInetAddress = InetAddress.getByName("192.168.0.104");
            InetSocketAddress bootAddress = new InetSocketAddress(bootInetAddress, bootPort);
            System.out.println("InetAddress: " + bootInetAddress);
            FreePastryExample freePastryExample = new FreePastryExample(bindPort, bootAddress, environment);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
