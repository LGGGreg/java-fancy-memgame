
/*

Copyright (c) 2009, Greg Hendrickson 
All rights reserved. 

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met: 

 * Redistributions of source code must retain the above copyright notice, 
   this list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright 
   notice, this list of conditions and the following disclaimer in the 
   documentation and/or other materials provided with the distribution. 
 * Neither the name of MFD nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE. 

 */
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Gui extends JFrame implements ActionListener, ChangeListener
{
	
	private static final long serialVersionUID = 1L;
	private List<JButton> mahColorButtuns;
	private List<JButton> mahFOUNDColorButtuns;
	private JLabel score;
	private Board ourBoard;
	private JButton lastButton;
	private JButton otherLast;
	private Integer pairsLeft;
	private JPanel bottomPanel;
	private Integer tscore;
	private JSpinner xsize,ysize;
	private JCheckBox cbox;
	boolean hard;
	Timer t;
	public Gui()
	{
		
		
		JPanel topPanel = new JPanel();

		topPanel.add(new JLabel(" Grid Size: "));
		topPanel.add(xsize = new JSpinner(   new SpinnerNumberModel(4, //initial value
                               4, //min
                                100, //max
                               2)     ));
		topPanel.add(ysize = new JSpinner(   new SpinnerNumberModel(5, //initial value
                5, //min
                100, //max
               1)     ));
		xsize.addChangeListener(this);
		ysize.addChangeListener(this);
		
		cbox = new JCheckBox("Hard Mode (Tiles Change Color over time)");
		cbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {newGame();}});
		topPanel.add(cbox);

		JButton newGame = new JButton("New Game");
		newGame.addActionListener(this);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(newGame);
		score = new JLabel("Score:");
		score.setForeground(Color.RED);
		topPanel.add(score);
		JButton dacheet = new JButton("Cheat!");
		dacheet.addActionListener(this);
		topPanel.add(dacheet);
		
		bottomPanel = new JPanel();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		add(topPanel,BorderLayout.NORTH);
		
		
		add(bottomPanel, BorderLayout.CENTER);
		
		setTitle("Memeory Game - Laura N.");
		setSize(800,740);
		setVisible(true);
		
		 t = new Timer(1,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
			 {
				 if(hard==false)
					 return;
				 for(int i = 0;i<mahFOUNDColorButtuns.size();i++)
				 {
					 JButton temp = mahFOUNDColorButtuns.get(i);
					 Integer n = Integer.parseInt(temp.getText());
						temp.setBackground(ourBoard.getTile(n).getWhatColor());
				 }
				 if(lastButton!=null)
				 {
					 Integer n = Integer.parseInt(lastButton.getText());
						lastButton.setBackground(ourBoard.getTile(n).getWhatColor());
				 }
				 if(otherLast!=null)
				 {
					 Integer n = Integer.parseInt(otherLast.getText());
						otherLast.setBackground(ourBoard.getTile(n).getWhatColor());
				 }
				 
				//System.out.println("wo");
			}}});
		t.start();
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				t.stop();
				System.exit(0);
			}
		});
			 
				
		newGame();
	}
	
	
	private void newGame() 
	{

		otherLast=lastButton=null;
		tscore=0;
		mahColorButtuns= new LinkedList<JButton>();

		mahFOUNDColorButtuns= new LinkedList<JButton>();
		bottomPanel.removeAll();
		int x = 4;
		int y = 5;

		
		try {
			x=Integer.parseInt( xsize.getValue().toString());
			y= Integer.parseInt( ysize.getValue().toString());
		} catch (NumberFormatException e) {
		}
		//no odd numbers in the x!!
		if((x%2)!=0)
		{
			x++;
			xsize.setValue(x);
			return;
		}
		

		pairsLeft =(x*y/2);
		

		bottomPanel.setLayout(new GridLayout(x, y));
		
		for(Integer i = 0;i<(x*y);i++)
		{
			JButton newButton = new JButton(i.toString());
			newButton.addActionListener(this);
			newButton.setBackground(cbox.isSelected()?Color.BLACK:Color.WHITE);
			
			bottomPanel.add(newButton);
			mahColorButtuns.add(newButton);
		}
		bottomPanel.revalidate();
		hard=cbox.isSelected();
		ourBoard = new Board(x,y,cbox.isSelected());
		setScore();
	}

	private void setScore() {
		score.setText("Pairs Left : "+pairsLeft.toString() +". Score : "+tscore.toString());
		
	}

	public static void main(String argsp[])
	{
		new Gui();
	}
	public void actionPerformed(ActionEvent arg0) {
		JButton who = (JButton)arg0.getSource();
		//System.out.println(who.getText());
		if(who.getText().compareTo("New Game")==0)
		{
			newGame();
			
		}else if(who.getText().compareTo("Cheat!")==0)
		{
			for(int i =0;i<mahColorButtuns.size();i++)
			{
				mahColorButtuns.get(i).setBackground(ourBoard.getTile(i).getWhatColor());
				mahColorButtuns.get(i).setEnabled(false);
				if(hard)
				{
					mahFOUNDColorButtuns=mahColorButtuns;
					lastButton=otherLast=null;
				}
			}
			pairsLeft=0;tscore=1337;
			setScore();
		}
			else
		{
			
			Integer whatTile = Integer.parseInt(who.getText());
			
			if(lastButton==null)
			{
	
				who.setBackground(ourBoard.getTile(whatTile).getWhatColor());
				lastButton = who;
			}else 
			{
				if
				(
						(who.getText().compareTo(lastButton.getText())==0))
					
				{
				///we clicked a already selected button!
					return;
				}
				if(otherLast==null)
				{
	
					who.setBackground(ourBoard.getTile(whatTile).getWhatColor());
					otherLast=who;
					if(
							ourBoard.getTile(Integer.parseInt(otherLast.getText())).getWhatHue()==
									ourBoard.getTile(Integer.parseInt(lastButton.getText())).getWhatHue())
					{
						//inc score
						otherLast.setEnabled(false);
						lastButton.setEnabled(false);
						if(hard)
						{
							mahFOUNDColorButtuns.add(otherLast);
							mahFOUNDColorButtuns.add(lastButton);
						}
						pairsLeft--;
						otherLast=lastButton=null;
						tscore+=50;
						if(hard)tscore+=150;
						setScore();
						if(pairsLeft<=0)
						{
							//victory
							String extra = "";
							if(hard)
							{
								extra = " OMGZ YOU BEAT IT ON HARD. PROPS";
							}else
							{
								extra = " This only shows you have the basic memory capacity of a 2 year old\nTry hard mode next";
							}
							if(tscore>1337)
							{
								extra += "\nI must say.. ure doing it right";
							}else
							{
								extra += "\nLol, your score :P ure doin it wrong";
							}
							
							JOptionPane.showMessageDialog(this,"Congradulations!!!\nYou have beaten this memory game!\nYour score was\n"+
									tscore+extra);
						}
						return;
					}
					
				}else
				{
					if
					(
							(who.getText().compareTo(lastButton.getText())==0)
						||
						(who.getText().compareTo(otherLast.getText())==0))
					{
					///we clicked a already selected button!
						return;
					}
					lastButton.setBackground(cbox.isSelected()?Color.BLACK:Color.WHITE);
					otherLast.setBackground(cbox.isSelected()?Color.BLACK:Color.WHITE);
					otherLast=null;
					who.setBackground(ourBoard.getTile(whatTile).getWhatColor());
					lastButton = who;
				}
			}
			tscore-=4;
			setScore();

		}
		
	}

	public void stateChanged(ChangeEvent e) {
		
		newGame();
		
	}

}
