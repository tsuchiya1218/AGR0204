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
import model.RoomDetailed;
import model.VehicleDetailed;
import model.ControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class VehicleUpdateFrame extends JFrame implements ActionListener {

	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblTelNotes;

	private JLabel lblKana;
	private JTextField txtKana;
	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;
	private JButton btnUpdate;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;
	private JLabel lblName;
	private JTextField txtName;
	String[][] tableData;

	public VehicleUpdateFrame() {

		setTitle("【移動手段データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("便名");
		lblName.setBounds(20, 20, 80, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 20, 280, 20);
		add(txtName);

		lblTelNotes = new JLabel("例：AA176、のぞみ1号");
		lblTelNotes.setBounds(200, 40, 180, 20);
		add(lblTelNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(20, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(120, 100, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 660,300);
		add(scrollPane);

		String[] columnNames = { "便名", "出発駅", "到着駅", "出発日時","到着日時" ,"種類ID","価格"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);
		TableColumn column6 = columnModel.getColumn(6);


		column0.setPreferredWidth(110);
		column1.setPreferredWidth(90);
		column2.setPreferredWidth(90);
		column3.setPreferredWidth(90);
		column4.setPreferredWidth(90);
		column5.setPreferredWidth(90);
		column6.setPreferredWidth(100);

		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 550, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(700 + insets.left + insets.right, 600 + insets.top + insets.bottom);
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
				tableData = NewBeeController.vehicleSearch(data);

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

				NewBeeController.vehicleDisplay();

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
			String vehicleName = (String) table.getValueAt(rowIndex, 0);

			for(int i = 0;i < tableData.length; i++ ) {
				if(vehicleName.equals(tableData[i][0])) {
					new VehicleDetailedFrame(new VehicleDetailed(tableData[i][0],tableData[i][1],tableData[i][2],tableData[i][3],
							tableData[i][4],tableData[i][5],tableData[i][6],tableData[i][7]));
				}
			}
			} catch (Exception ex) {
			}
		}
	}
}
