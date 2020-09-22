package View;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScoreBar extends JDialog{
    private JFrame frame;
    private int size,ROW,COL;
    private ArrayList playerDataList;
    private String fileName;
    private JLabel titleName,titleRank,titleScore;

    public ScoreBar(String f,int s) {
        frame = new JFrame("Score Board");
        fileName = f;
        size = s;
        initScoreBar(frame,size);
    }

    public void initScoreBar(JFrame frame,int size){
        Container c = getContentPane();
 

        COL = 3;
        ROW = size + 1;

        JPanel centerPanel = new JPanel(new GridLayout(ROW,1));

        JPanel titlePanel = new JPanel(new GridLayout(1,COL));
        titleRank = new JLabel("Rank");
        titlePanel.add(titleRank);
        titleName = new JLabel("Name");
        titlePanel.add(titleName);
        titleScore = new JLabel("Score");
        titlePanel.add(titleScore);
        centerPanel.add(titlePanel);

        playerDataList = loadPlayerData(fileName,size);
        for(int i = 0; i < playerDataList.size(); i++){
            centerPanel.add((PlayerData)playerDataList.get(i));
        }

       c.add(centerPanel,BorderLayout.CENTER);
        addWindowListener(
          new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
                System.exit(0);
           }
          }
        );
      }

      public ArrayList loadPlayerData(String fileName,int size){
          ArrayList dataTemp = new ArrayList();

          int index = 0;
          try{
              FileInputStream fs = new FileInputStream(fileName);
              InputStreamReader is = new InputStreamReader( fs,"utf-8" );
              BufferedReader reader = new BufferedReader(is);
              String line="";

              while ((line = reader.readLine()) !=null && index < size){
                  if(line.length() > 0){
                      int c = line.charAt(0);
                      if(c == 65279){
                          line = line.substring(1, line.length());
                      }
                  }
                  String [] tmp = line.split("[,]");
                  PlayerData pData = new PlayerData(size);
                  if(tmp.length != 2){
                      tmp = new String[]{"",""};
                  }
                  pData.setName(tmp[0]);
                  pData.setRank(String.valueOf(index+1));
                  pData.setScore(tmp[1]);
                  dataTemp.add(pData);
                  index++;
              }
              fs.close();
              reader.close();
       }catch(Exception e){
        e.printStackTrace();
       }
          
          for(int i = index; i < size; i++)
          {
              PlayerData pData = new PlayerData(size);
              pData.setRank(String.valueOf(i+1));
              dataTemp.add(pData);
          }
           return dataTemp;
      }


    public void changeNameScore(String name,String score){
            int rank = compareScore(score);

            if(rank >= 0)
            {
                ArrayList dataTemp = (ArrayList)playerDataList.clone();
                for(int i = dataTemp.size() -1; i > rank; i--){
                    PlayerData last = (PlayerData)dataTemp.get(i - 1);
                    PlayerData pd = (PlayerData)dataTemp.get(i);
             
                    pd.setName(last.getName());
                    pd.setScore(last.getScore());
                }

          
                PlayerData newPlayer = (PlayerData)dataTemp.get(rank);
                newPlayer.setName(name);
                newPlayer.setScore(score);
            }
    }
    public int compareScore(String score){
           int playerScore = Integer.parseInt(score);
           ArrayList dataTemp = (ArrayList)playerDataList.clone();
           for(int i = 0; i < dataTemp.size(); i++){
               PlayerData pd = (PlayerData)dataTemp.get(i);
               String tmpScore = pd.getScore();
               int pdScroe = Integer.MAX_VALUE;
            
               if(!tmpScore.equals("")){
                   pdScroe = Integer.parseInt(tmpScore);
               }
            
               if(playerScore < pdScroe){
                   return i;
               }
           }
           return -1;
    }


    public boolean writeData(String filename,ArrayList dataArr){
           ArrayList dataTemp = (ArrayList)dataArr.clone();
           File file = new File(filename);
           try{
               FileOutputStream fs = new FileOutputStream(file,false);
               OutputStreamWriter os = new OutputStreamWriter(fs,"utf8");
               BufferedWriter bufWriter = new BufferedWriter(os);
               for(int i = 0; i < dataTemp.size(); i++){
                   PlayerData pd = (PlayerData)dataTemp.get(i);
                   String nameScore = pd.getName() + "," + pd.getScore();
                   bufWriter.write(nameScore);
                   bufWriter.newLine();
               }
               bufWriter.close();
            }catch(IOException e){
            e.printStackTrace();
            System.out.println(filename + "write file error!!");
            return false;
            }
            return true;
    }

          
          public void setOddColor(Color oddColor){
           ArrayList al = (ArrayList)playerDataList.clone();
           for(int i = 0; i < al.size(); i++){
            JPanel pnl = (JPanel)al.get(i);
            if((i & 1) == 1){
             pnl.setBackground(oddColor);
            }
           }
          }

          public void setEvenColor(Color evenColor){
           ArrayList al = (ArrayList)playerDataList.clone();
           for(int i = 0; i < al.size(); i++){
            JPanel pnl = (JPanel)al.get(i);
            if((i & 1) != 1){
             pnl.setBackground(evenColor);
            }
           }
          }

         
          public void clear(){
           ArrayList al = (ArrayList)playerDataList.clone();
           for(int i = 0; i < al.size(); i++){
            PlayerData pData = (PlayerData)al.get(i);
            pData.setName("");
            pData.setScore("");
           }
           writeData(fileName,al);
          }
    
    public void showRecord()
    {
        setBounds(100, 100, 400, 800);
        setOddColor(new Color(255, 102, 102));
        setEvenColor(new Color(255, 255, 204));
        setVisible(true);
    }
    
    public void addRecord(String n, int t)
    {
        String time = Integer.toString(t);
        changeNameScore(n,time);
        writeData(fileName, playerDataList);
    }
}


