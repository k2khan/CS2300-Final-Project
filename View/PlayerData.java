package View;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerData extends JPanel{
 private JLabel name;
 private JLabel rank;
 private JLabel score;
 
 public PlayerData(int size){
  setLayout(new GridLayout(1,size));
  initPlayerData();
 }

 public void initPlayerData(){
  rank = new JLabel();
  name = new JLabel();
  score = new JLabel();
     this.add(rank);
     this.add(name);
     this.add(score);
 }


 public void setName(String n){
  name.setText(n);
 }

 public String getName(){
  return name.getText();
 }


 public void setRank(String r){
  rank.setText(r);
 }

 public String getRank(){
  return rank.getText();
 }


 public void setScore(String s){
  score.setText(s);
 }

 public String getScore(){
  return score.getText();
 }
}
