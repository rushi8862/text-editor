import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;

    //menu item for file
    JMenuItem newwindow,save,open;


    //menu items for edit
    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textarea;
    TextEditor(){
        //create frame
        frame=new JFrame("My Notepad");

        //initillize menubar
        menubar=new JMenuBar();

        //initiallize text area
        textarea=new JTextArea();

        //initiallize menu
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //initiallize file menu items
        newwindow=new JMenuItem("New Window");
        save=new JMenuItem("Save File");
        open=new JMenuItem("Open File");

        //add action listener

        newwindow.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);

        //add menu in file
        file.add(newwindow);
        file.add(save);
        file.add(open);

        //initiallize edit menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //add action listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        //add menu items in edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);


        //add menu in menubar
        menubar.add(file);
        menubar.add(edit);

        //set menubar in frame
        frame.setJMenuBar(menubar);

        //create panel

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area into panel
        panel.add(textarea,BorderLayout.CENTER);
        //create scroll pane

        JScrollPane scrollPane=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);
        frame.add(panel);

        //add text area
        frame.add(textarea);

        //location of frame
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionevent){
        if(actionevent.getSource()==cut){
            //implement cut
            textarea.cut();
        }
        if(actionevent.getSource()==paste){
            //implement paste
            textarea.paste();
        }
        if(actionevent.getSource()==copy){
            textarea.copy();
        }
        if(actionevent.getSource()==selectall){
            textarea.selectAll();
        }
        if(actionevent.getSource()==close){
            System.exit(0);
        }
        if(actionevent.getSource()==open){
            //open file chooser
            JFileChooser filechooser=new JFileChooser("C:");
            int chooseoption=filechooser.showOpenDialog(null);

            if(chooseoption==JFileChooser.APPROVE_OPTION){
                File file=filechooser.getSelectedFile();

                String filepath=file.getPath();
                try{
                    //initiallize file reader
                    FileReader filereader=new FileReader(filepath);

                    //initiallize buffer reader
                    BufferedReader bufferedReader=new BufferedReader(filereader);

                    String intermediate="",output="";
                    //read line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    textarea.setText(output);

                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }
        if(actionevent.getSource()==save){
            JFileChooser jFileChooser=new JFileChooser("C:");

            int chooseoption=jFileChooser.showSaveDialog(null);
            if(chooseoption==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter=new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                    textarea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                catch(IOException ioException){
                    ioException.printStackTrace();

                }

            }
        }

        if(actionevent.getSource()==newwindow){
            TextEditor texteditor=new TextEditor();
        }



    }
    public static void main(String[] args) {
        TextEditor texteditor=new TextEditor();
    }
}