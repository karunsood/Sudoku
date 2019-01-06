package com.karunsood.sudoku;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
class Sudoku implements ActionListener
{
	JFrame f;
	JButton b[][];
	JPanel p[];
	JButton start;
	JButton submit;
	JButton difficulty;
	JButton level;
	JPanel p2;
	String s;
	String str[]={"Easy","Normal","Hard","Expert","Genius"};
	int i,j,num,choice;
	CardLayout card=new CardLayout();
	int x[][][]={{{4,0,6,0,1,0,0,9,0},{0,5,0,4,0,0,0,2,0},
				     {1,0,9,0,3,0,0,7,0},{9,6,0,0,0,1,0,0,2},
				     {0,0,0,0,0,0,0,0,0},{2,0,0,3,0,0,0,6,7},
				     {0,8,0,0,3,0,1,0,9},{0,4,0,0,0,5,0,6,0},
				     {0,1,0,0,2,0,5,0,3}},
				     {{8,0,0,0,0,0,0,0,0},{0,5,0,3,0,0,0,7,8},
				     {0,1,7,0,0,6,9,0,0},{7,0,1,0,4,6,0,0,8},
				     {0,6,0,0,0,0,0,9,0},{8,0,0,2,9,0,7,0,4},
				     {0,0,2,3,0,0,1,8,0},{9,3,0,0,0,1,0,2,0},
				     {0,0,0,0,0,0,0,0,3}},
				     {{7,0,0,0,0,9,0,0,0},{0,5,0,0,0,0,1,2,9},
				     {8,0,0,0,4,0,0,0,7},{0,0,6,0,8,0,0,0,1},
				     {0,8,5,9,0,1,6,4,0},{4,0,0,0,7,0,5,0,0},
				     {3,0,0,0,9,0,0,0,7},{8,7,6,0,0,0,0,9,0},
				     {0,0,0,7,0,0,0,0,4}},
				     {{0,0,0,0,0,8,0,3,0},{0,6,3,9,0,0,0,0,0},
				     {0,5,2,6,0,0,1,0,0},{0,0,0,3,0,2,0,6,9},
				     {0,0,5,0,0,0,3,0,0},{3,6,0,9,0,7,0,0,0},
				     {0,0,1,0,0,3,8,7,0},{0,0,0,0,0,8,6,4,0},
				     {0,7,0,4,0,0,0,0,0}},
				     {{0,0,3,0,5,0,0,0,2},{0,0,0,1,0,2,0,0,5},
				     {0,2,0,0,0,0,8,0,0},{4,3,0,5,0,0,0,0,0},
				     {0,0,8,0,0,0,9,0,0},{0,0,0,0,0,9,0,1,7},
				     {0,0,8,0,0,0,0,9,0},{6,0,0,2,0,4,0,0,0},
				     {1,0,0,0,3,0,2,0,0}}};
	Sudoku(String s)		   
	{
		f=new JFrame(s);
		b=new JButton[9][9];
		p=new JPanel[9];
		p2=new JPanel();
		start=new JButton("Play");
		submit=new JButton("Submit");
		level=new JButton("Change Level");
		
		for(i=0;i<9;i++)
		{
			p[i]=new JPanel();
			p[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
			p[i].setLayout(new GridLayout(3,3));
			for(j=0;j<9;j++)
			{
				b[i][j]=new JButton();
				b[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
				b[i][j].setEnabled(false);
				b[i][j].setPreferredSize(new Dimension(50,50));
				b[i][j].addActionListener(new Common(this,i,j));
				p[i].add(b[i][j]);
			}
		}					
		f.setLayout(new GridLayout(4,3));
		for(i=0;i<9;i++)
		{
			f.add(p[i]);
		}
		s=str[0];
		difficulty=new JButton(s);
		difficulty.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		difficulty.addActionListener(this);
		f.add(difficulty);
		p2.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		p2.setLayout(card);
		start.addActionListener(this);
		p2.add(start);
		level.addActionListener(this);
		p2.add(level);
		f.add(p2);
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		submit.addActionListener(this);
		submit.setEnabled(false);
		f.add(submit);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(450,80);
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		
		int i,j,k,l,m,n,count=0,v=0;
		boolean confirm=false;
		String s1="";
		
		if(e.getSource()==start)
		{
			card.next(p2);
			difficulty.setEnabled(false);
			submit.setEnabled(true);
			display(this);
		}
		if(e.getSource()==level)
		{
			difficulty.setEnabled(true);
			submit.setText("Submit");
			submit.setEnabled(false);
			card.next(p2);
			for(i=0;i<9;i++)
			{
				for(j=0;j<9;j++)
				{
					b[i][j].setEnabled(false);
					b[i][j].setText("");
				}
			}
		}	
		if(e.getSource()==submit)
		{
			for(i=0;i<9;i++)
			{
				for(j=0;j<9;j++)
				{
					s=b[i][j].getText();
					if(s.equals(""))
					{
						submit.setText("Null Box Problem....");
						confirm=true;
						break;
					}
				}
				if(confirm==true)
				{
					break;
				}
			}
			for(k=0;k<9;k++)
			{
				if(confirm==true)
				{
					break;
				}
				for(i=0;i<9;i++)
				{
					if(confirm==true)
					{
						break;
					}
					count=0;
					for(j=0;j<9;j++)
					{
						s=b[k][i].getText();
						s1=b[k][j].getText();
						if(s.equals(s1))
						{
							count++;
							if(count>1)
							{
								submit.setText("Box Problem....");
								confirm=true;
								break;
							}
						}
					}
				}
			}
			for(i=0;i<3;i++)
			{
				if(confirm==true)
				{
					break;
				}
				for(j=0;j<3;j++)
				{
					if(confirm==true)
					{
						break;
					}
					for(k=i;k<9;k=k+3)
					{
						if(confirm==true)
						{
							break;
						}
						for(l=j;l<9;l=l+3)
						{
							if(confirm==true)
							{
								break;
							}
							count=0;
							for(m=i;m<9;m=m+3)
							{
								if(confirm==true)
								{
									break;
								}
								for(n=j;n<9;n=n+3)
								{
									s=b[m][n].getText();
									s1=b[k][l].getText();
									if(s.equals(s1))
									{
										count++;
										if(count>1)
										{
											submit.setText("Column Problem....");
											confirm=true;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			for(i=0;i<9;i=i+3)
			{
				if(confirm==true)
				{
					break;
				}
				for(j=0;j<9;j=j+3)
				{
					if(confirm==true)
					{
						break;
					}
					for(k=i;k<3+i;k++)
					{
						if(confirm==true)
						{
							break;
						}
						for(l=j;l<3+j;l++)
						{
							if(confirm==true)
							{
								break;
							}
							count=0;
							for(m=i;m<3+i;m++)
							{
								if(confirm==true)
								{
									break;
								}
								for(n=j;n<3+j;n++)
								{
									s=b[k][l].getText();
									s1=b[m][n].getText();
									if(s.equals(s1))
									{
										count++;
										if(count>1)
										{
											submit.setText("Row Problem....");
											confirm=true;
											break;
										}
									}
								}
							}
						}
					}
				}
			}			 
			if(confirm==false)
			{
				submit.setText("Solved....");
			}
		}
		if(e.getSource()==difficulty)
		{
			choice++;
			if(choice==5)
			{
				choice=0;
			}
			s1=str[choice];
			difficulty.setText(s1);
		}		
	}			
	public static void main(String... args)
	{
		new Sudoku("Sudoku");
	}
	static void display(Sudoku s)
	{
		for(s.i=0;s.i<9;s.i++)
		{
			for(s.j=0;s.j<9;s.j++)
			{
				if(s.x[s.choice][s.i][s.j]!=0)
				{
					s.b[s.i][s.j].setText(""+s.x[s.choice][s.i][s.j]);
				}
				else
				{
					s.b[s.i][s.j].setEnabled(true);
				}	
			}
		}
	}
}
class Common implements ActionListener
{
	Sudoku s;
	int i,j,num=1;
	Common(Sudoku s,int i,int j)
	{
		this.s=s;
		this.i=i;
		this.j=j;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(num==10)
		{
			num=0;
		}
		if(num==0)
		{
			s.b[i][j].setText("");
		}
		else
		{	
			s.b[i][j].setText(""+num);
		}
		num++;
	}
}

							