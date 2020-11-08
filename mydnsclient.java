import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.*;

public class MyDNSClient 
{
    private DatagramSocket socket;
    final int TIME_OUT = 5000;
    
    public MyDNSClient() throws SocketException
    {
        socket = new DatagramSocket();
    }
    
    public String Send(String msg, String address, int port) throws IOException
    {
        socket.setSoTimeout(TIME_OUT);
        DNSRequest toSend = new DNSRequest(address, "NS");
        byte[] sendBuffer = toSend.GetRequestBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length
                , InetAddress.getByName(address), port);
        socket.send(sendPacket);
        byte[] responseBuffer = new byte[1024];
        DatagramPacket recievePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        socket.receive(recievePacket);
        String recieveMsg = new String(recievePacket.getData(), 0
                , recievePacket.getLength());
        return recieveMsg;
    }
    
    public void CloseSocket()
    {
        socket.close();
    }
    
    private String GetHexString(String msg)
    {
        String hexString = "AAAA01000001000000000000";
        String[] parts = msg.split("\\.");
        
        for(int i = 0; i < parts.length; i++)
        {
            hexString += Integer.toHexString(parts[i].length());
            System.out.println(Integer.toHexString(parts[i].length()));
            
            for(int j = 0; j < parts[i].length(); j++)
            {
                int charVal = (int)parts[i].charAt(j);
                String hex = Integer.toHexString(charVal);
                hexString += hex;
            }
        }
        
        hexString += "0000010001";
        
        return hexString;
    }
}
