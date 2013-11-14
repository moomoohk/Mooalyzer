
package com.moomoohk.Mooalyzer.CSharp.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -661414621270925990L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel modFileTab = new JPanel();
		tabbedPane.addTab("Mod file", null, modFileTab, null);
		modFileTab.setLayout(null);
		
		JLabel lblModFile = new JLabel("Mod file:");
		lblModFile.setBounds(16, 17, 61, 16);
		modFileTab.add(lblModFile);
		
		textField = new JTextField();
		textField.setBounds(79, 11, 322, 28);
		modFileTab.add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse...");
		btnBrowse.setBounds(401, 12, 100, 29);
		modFileTab.add(btnBrowse);
		
		JButton btnParse = new JButton("Parse!");
		btnParse.setBounds(16, 45, 495, 29);
		modFileTab.add(btnParse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 86, 220, 276);
		modFileTab.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(281, 86, 220, 276);
		modFileTab.add(scrollPane_1);
		
		JPanel gameFileTab = new JPanel();
		tabbedPane.addTab("Game file", null, gameFileTab, null);
		
		JPanel resultFileTab = new JPanel();
		tabbedPane.addTab("Result file", null, resultFileTab, null);
		setSize(550, 450);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}

