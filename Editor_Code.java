import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
public class Editor extends JFrame implements ActionListener{
    // text component
    JTextArea t;
    // frame
    JFrame f;

    //constructor
    Editor()
    {
        //creat a frame
        f= new JFrame("Editor");

        try
        {
            //set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // set theme

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e)
        {
        }

        //text component
        t = new JTextArea();
        t.setLineWrap(true);
        t.setPreferredSize(new Dimension(450,450));
        t.setFont(new Font("Arial",Font.PLAIN,20));
        // creating menu bar
        JMenuBar mb = new JMenuBar();
        //file menu
        JMenu m1 = new JMenu("File");

        JMenuItem mi1 = new JMenuItem("New");
        mi1.addActionListener(this);
        m1.add(mi1);
        JMenuItem mi2 = new JMenuItem("Open");
        mi2.addActionListener(this);
        m1.add(mi2);
        JMenuItem mi3 = new JMenuItem("Save");
        mi3.addActionListener(this);
        m1.add(mi3);
        JMenuItem mi4 = new JMenuItem("Print");
        mi4.addActionListener(this);
        m1.add(mi4);

        // insert in menubar
        mb.add(m1);
        //edit menu 
        JMenu m2 = new JMenu("Edit");

        JMenuItem mi5 = new JMenuItem("Cut");
        mi5.addActionListener(this);
        m2.add(mi5);
        JMenuItem mi6 = new JMenuItem("Copy");
        mi6.addActionListener(this);
        m2.add(mi6);
        JMenuItem mi7 = new JMenuItem("Paste");
        mi7.addActionListener(this);
        m2.add(mi7); 

        //insert m2 in menu bar
        mb.add(m2);
        //close
        JMenuItem mc = new JMenuItem("Close");
        mc.addActionListener(this);

        //inserting close menubar
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500,500);
        f.show();
    }

    //if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if(s.equals("Cut"))
        {
            t.cut();
        }
        else if(s.equals("Copy"))
        {
            t.copy();
        }
        else if(s.equals("Paste"))
        {
            t.paste();
        }
        else if(s.equals("Save"))
        {
            JFileChooser j = new JFileChooser("f:");

            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION)
            {
                File fi =  new File(j.getSelectedFile().getAbsolutePath());
                try
                {
                    FileWriter wr = new FileWriter(fi,false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(t.getText());

                    w.flush();
                    w.close();
                }
                catch(Exception evt)
                {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(f,"you had canceled");
            }
        }
        else if(s.equals("Print"))
        {
            try{
                t.print();
            }
            catch(Exception evt)
            {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if(s.equals("Open"))
        {
            JFileChooser j = new JFileChooser("f:");

            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION)
            {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try
                {
                    String s1 = "",sl = "";

                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    sl = br.readLine();

                    while((sl = br.readLine()) != null)
                    {
                        sl= sl +"/n"+s1;
                    }

                    t.setText(sl);
                }
                catch(Exception evt)
                {
                    JOptionPane.showMessageDialog(f,evt.getMessage());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(f,"you had canceled");
            }
        }
        else if (s.equals("New"))
        {
            t.setText("");
        }
        else if(s.equals("Close"))
        {
            f.setVisible(false);
        }
    }
}
