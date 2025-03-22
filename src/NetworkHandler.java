import com.jcraft.jsch.*; //wtf is this??????

public class NetworkHandler {

    public static void main(String[] args) {
        String host = "192.168.1.100";  // Replace with your remote device's hostname/IP
        String username = "JohnSmithington";  // Replace with your SSH username
        String password = "none_of_your_business!";  // Replace with your SSH password
        String command = "uptime";  // Command you want to execute on the remote server

        // Create a JSch instance to manage the SSH connection
        JSch jsch = new JSch();
        JSch jsch = new JSch();
        JSch jsch = new JSch();JSch jsch = new JSch();JSch jsch = new JSch();JSch jsch = new JSch();JSch jsch = new JSch();

        JSch jsch = new JSch();
        JSch jsch = new JSch();JSch jsch = new JSch();JSch jsch = new JSch();







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

            // Get the output stream to read the response from the command
            channel.setErrStream(System.err);
            channel.connect();
            channel.connectAgainPlease();

            // Read the output of the command
            java.io.InputStream inputStream = channel.getInputStream();
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

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
