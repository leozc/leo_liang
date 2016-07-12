package HelloWorldClient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import hello_world_service.*;

public class HelloWorldClient {
  public static void main(String [] args) {


    try {
      TTransport transport;

      transport = new TSocket("localhost", 9090);

      transport.open();

      TProtocol protocol = new TBinaryProtocol(transport);
      HelloWorldService.Client client = new HelloWorldService.Client(protocol);

      client.ping();

      transport.close();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }
}