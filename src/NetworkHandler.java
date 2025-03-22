import com.jcraft.jsch.*; //wtf is this??????

public class NetworkHandler {

    public static void main(String[] args) {


        System.out.println("Please enter an");

        

        try {
            // Create a session using the host, username, and password
            Session session = jsch.getSession(username, host, 22);
            session.setPassword(password);

            // Set user-known hosts file to avoid security warnings about unknown hosts
            session.setConfig("StrictHostKeyChecking", "no");

            // Connect to the remote server
            session.connect();
            System.out.println("Connected to the remote device!");

            // Execute a command on the remote device
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);


            // Print the result of the command
            System.out.println("Output: " + outputStream.toString());

            // Disconnect the session
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            System.err.println("SSH connection failed: " + e.getMessage());
        }
    }
}
