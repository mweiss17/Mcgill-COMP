package a4posted;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


import a3.AutoComplete;
import a3.Trie;
import a3.TrieNode;

public class A3Panel extends JPanel implements ActionListener
{
String longPrefixString = "";
String allPrefixMatches = "";
String prefixString = "";
String Fname;
String testString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
ArrayList<String> allPrefixes = new ArrayList<String>();
JTextArea words = new JTextArea(10,10);
	

JLabel prefixLabel, wordsLabel;
JTextField prefix;
JScrollPane wordsPane;
int start, end;

GridBagLayout gridbag;
GridBagConstraints c;
//this.setLayout(gridbag);

	public A3Panel(String fileName) 
	{
		Fname = fileName; 
		//layout initialization
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);

		//PrefixLabel
		JLabel prefixLabel = new JLabel("Enter the prefix (and hit return)");
		prefixLabel.setFont(new Font("Times", Font.BOLD, 16));
		prefixLabel.setBackground(new Color(000, 200, 000));
		prefixLabel.setOpaque(true);
		prefixLabel.setHorizontalAlignment(SwingConstants.LEFT);
		c.gridx = 0;
		c.gridy = 0;
		add(prefixLabel, c);


		//First Arg textfield
		prefix = new JTextField(25);
		prefix.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		prefix.addActionListener(this);
		add(prefix, c);


		//words label
		JLabel wordsLabel = new JLabel("Below is a list of all the words with that prefix: ");
		wordsLabel.setFont(new Font("Times", Font.BOLD, 16));
		wordsLabel.setBackground(new Color(000, 200, 000));
		wordsLabel.setOpaque(true);
		wordsLabel.setHorizontalAlignment( SwingConstants.CENTER );
		c.gridx = 0;
		c.gridy = 2;
		add(wordsLabel, c);
		
		//wordstext area, added to the jscrollpane
		JScrollPane wordsPane = new JScrollPane(words);
		c.gridx = 0;
		c.gridy = 3;
		this.add(wordsPane, c);
	}
	

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == prefix)
		{
			ArrayList<String> list = new ArrayList<String>();
			list = readWordsFromFile(Fname);
			Trie trie = new Trie();
			trie.loadKeys(list);
			
			prefixString = prefix.getText();
			allPrefixes = trie.getAllPrefixMatches(prefixString);
			longPrefixString = "";
			for(int i = 0; i < allPrefixes.size(); i++)
			{
				longPrefixString = longPrefixString + allPrefixes.get(i) + "\n";
			}
			words.setText(longPrefixString); 
		}
		else
		{
			return;
		}
	}
	
	//This is from the AutoComplete method of A3. Used to open the file.
	public static ArrayList<String> readWordsFromFile(String filename)
	{
		ArrayList<String> wordsList = new ArrayList<String>();
		try
		{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			// Strip non-alphanumeric \\W
			scanner.useDelimiter("\\W+"); 
			while (scanner.hasNext())
			{
				wordsList.add(scanner.next());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return wordsList;
	}
	
}

