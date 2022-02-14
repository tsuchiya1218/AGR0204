

package view;

import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Customer;
import model.ControlUtility;
import model.Reviews;
import model.RoomDetailed;
import control.NewBeeController;

public class RoomUpdateFrame extends JFrame implements ActionListener {

	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblTelNotes;


	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lbl;
	private JLabel lblName;
	private JTextField txtName;
	private String[][] tableData;

	public RoomUpdateFrame() {

	setTitle("【部屋数更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("ホテル名");
		lblName.setBounds(60, 20, 100, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 20, 280, 20);
		add(txtName);

		lblTelNotes = new JLabel("例：ホテルリブマックス新橋(一部も可)");
		lblTelNotes.setBounds(200, 40, 280, 20);
		add(lblTelNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(60, 70, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 110, 760, 390);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		String[] columnNames = { "ホテルID","ホテル名","部屋ID","確保部屋数", "部屋タイプ","概要","残り部屋数"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);
		TableColumn column6 = columnModel.getColumn(6);

		column0.setPreferredWidth(80);
		column1.setPreferredWidth(100);
		column2.setPreferredWidth(80);
		column3.setPreferredWidth(100);
		column4.setPreferredWidth(100);
		column5.setPreferredWidth(100);
		column5.setPreferredWidth(200);

		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 520, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(800 + insets.left + insets.right, 600 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnDelete) {

			txtName.setText("");

		} else if (e.getSource() == btnSearch) {

			String name = txtName.getText();
			
			// 入力値の半角スペースと全角スペースを取り除く
			name.replaceAll(" +", "");

			try {

				String data = name;
				tableData = new String[][]{
					 {"001","ホテルリブマックス","1","50",
						 "ツイン","エアコン、セミダブルベッド（幅120cm）、液晶テレビ、セーフティボックス、小型冷蔵庫、緑茶セット、"
						 		+ "バスアメニティ付きのバスルームが備わっています。無料の有線インターネットを利用できます。","33"},
					 {"001","ホテルリブマックス","2","30",
							 "ダブル","エアコン、セミダブルベッド（幅120cm）、液晶テレビ、セーフティボックス、小型冷蔵庫、緑茶セット、"
							 		+ "バスアメニティ付きのバスルームが備わっています。無料の有線インターネットを利用できます。","15"}
					 };						
						NewBeeController.roomSearch(data);

				if (tableData != null) {

					tableModel.setRowCount(0);

					for (String[] rowData : tableData) {

						tableModel.addRow(rowData);
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

				NewBeeController.roomDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

	private class SearchMouseEvent extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setVisible(false);

			
			try {
			int rowIndex = table.getSelectedRow();
			String htelId = (String) table.getValueAt(rowIndex, 0);
			String roomId = (String) table.getValueAt(rowIndex, 2);
			
			for(int i = 0;i < tableData.length; i++ ) {
				if(htelId.equals(tableData[i][0]) && roomId.equals(tableData[i][2])) {
					new RoomDetailedFrame(new RoomDetailed(tableData[i][0],tableData[i][1],tableData[i][2],tableData[i][3],
							tableData[i][4],tableData[i][5],tableData[i][6]));
				}
			}
			} catch (Exception ex) {
			}
		}
	}
}
