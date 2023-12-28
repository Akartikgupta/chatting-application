import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener {
    JTextField text;// Globally decleration
    // As we fetch the data written in the text field
    static JPanel a1;
    static DataOutputStream dout;
    static JFrame f = new JFrame();
    static Box vertical = Box.createVerticalBox();// Allined the messages one another

    Client() {
        f.setLayout(null);
        f.setLocation(800, 50);// Open at particular location
        f.getContentPane().setBackground(Color.WHITE);// Setting the background

        JPanel p1 = new JPanel();// Adding something on the frame
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        // Adding a Image into a panel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        // Scaled the image as the image is of high size
        // Converting back to the image icon
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        // If u add something on the panel then use the object of that class
        p1.add(back);
        // Adding the functionality to the back button
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                // setVisible(false);
            }
        });
        // Adding the profile image
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        // Adding the video option
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        // Adding the phone icon
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 30, 30);
        p1.add(phone);
        // Adding the morevert icon
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        // Adding the user name
        JLabel name = new JLabel("Lakshman");
        name.setBounds(110, 25, 100, 10);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        // Adding the status bar
        JLabel status = new JLabel("Active Now" + "*");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);
        // Now Working on the another part of the chat
        // Header part is complete now starting the lower part
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);
        // Adding the text field or message box
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        // Adding the button icon in the bottom
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        f.setUndecorated(true);// Use for removing the above exit header
        f.setSize(450, 700);// Frame size
        f.setVisible(true);// Visibility
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String out = text.getText();// As string can not allowed in jpanel as input
            // Output can be generated by this two lines without a box
            // For addding the box in messaged we create method(formatLabel)
            // JLabel output=new JLabel(out);
            // Adding output in the panel
            JPanel p2 = formatlabel(out);
            // p2.add(output);
            a1.setLayout(new BorderLayout());
            // User Sending messages in right side
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);// For alligning the messages
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical, BorderLayout.PAGE_START);
            // for sending the message to server
            dout.writeUTF(out);
            text.setText(" ");// After pressing the send button. Field becomes empty
            // For displaying the message
            // Or reload the page
            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception en) {
            en.printStackTrace();
        }
    }

    public static JPanel formatlabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"widht: 150px\">" + out + "</p></html>");

        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);
        // For adding the timing of sending the message
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public static void main(String[] args) {
        new Client();
        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (true) {
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatlabel(msg);
                JPanel left = new JPanel((new BorderLayout()));
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);

                f.validate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
