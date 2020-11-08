import java.io.IOException;
import java.net.SocketException;

public class Mydns 
{
    public static void main(String[] args) 
    {
        final int MAX_ARGS = 2;
        final int PORT = 53;
        String domain = "";
        String ip = "";
        
        if(args.length < MAX_ARGS)
        {
            System.out.println("Not enough arguements USAGE:"
                    + "mydns [domain] [ip]");
            System.exit(0);
        }
        else if(args.length > MAX_ARGS)
        {
            System.out.println("too many arguements USAGE:"
                    + "mydns [domain] [ip]");
            System.exit(0);
        }
        else
        {
            domain = args[0];
            ip = args[1];
        }
        
        try
        {
            MyDNSClient client = new MyDNSClient();
            System.out.println(client.Send(domain, ip, PORT));
        }
        catch(SocketException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    
}
