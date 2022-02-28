/**
 * クラス名：	CustomerSearchFrame
 * 概要　　：	「顧客情報検索」画面
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package view;


import java.awt.Insets;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Live;
import model.ControlUtility;
import model.Spot;
import control.NewBeeController;

@SuppressWarnings("serial")
public class LiveUpdateFrame extends JFrame implements ActionListener {

	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;


	String[][] tableData = null;


	private JLabel lblThema;


	private JTextField txtThema;

	public LiveUpdateFrame() {

		setTitle("【ライブ観光データ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblThema = new JLabel("ライブ観光エリア");
		lblThema.setBounds(40, 20, 100, 20);
		add(lblThema);


		txtThema = new JTextField();
		txtThema.setBounds(160, 20, 280, 20);
		add(txtThema);

		lblKanaNotes = new JLabel("例：北海道");
		lblKanaNotes.setBounds(160, 50, 300, 20);
		add(lblKanaNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(20, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(130, 100, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 510, 300);
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		String[] columnNames = { "ライブID","エリア", "ライブ観光コース", "開始日時", "概要","代金"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);

		column0.setPreferredWidth(50);
		column1.setPreferredWidth(60);
		column2.setPreferredWidth(100);
		column3.setPreferredWidth(100);
		column4.setPreferredWidth(100);
		column4.setPreferredWidth(100);
		table.addMouseListener(new SearchMouseEvent());



		scrollPane.setViewportView(table);



		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 600, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(550 + insets.left + insets.right, 650 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnDelete) {

			txtThema.setText("");

		} else if (e.getSource() == btnSearch) {

			String thema = txtThema.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			thema.replaceAll(" +", "");

			try {

				String data = thema;
				 tableData = NewBeeController.liveSearch(data);

				 if (tableData != null) {

						tableModel.setRowCount(0);

						int i = 0;
						for (String[] rowData : tableData) {

							if(tableData[i][0] == null) {
								break;
							}else {
								tableModel.addRow(rowData);
							}
							i++;

						}
				} else {

					JOptionPane.showMessageDialog(this, "一致する情報は見つかりませんでした。", "【確認】", JOptionPane.INFORMATION_MESSAGE);
					tableModel.setRowCount(0);
				}

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {
			setVisible(false);

			try {

				NewBeeController.liveDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

	private class SearchMouseEvent extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			int rowIndex = table.getSelectedRow();
			String livetId = (String) table.getValueAt(rowIndex, 0);

			for(int i = 0; i < tableData.length; i++) {
				if(livetId.equals(tableData[i][0])) {
					new LiveDetailFrame(new Live(tableData[i][0],tableData[i][1],tableData[i][2],tableData[i][5],
							tableData[i][3],tableData[i][4],tableData[i][6]));
				}
			}
			setVisible(false);

		}


	}


}
