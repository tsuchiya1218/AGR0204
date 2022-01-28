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
import model.OrderControlUtility;
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

	public VehicleUpdateFrame() {

		setTitle("【移動手段データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("移動種類");
		lblTel.setBounds(60, 20, 100, 20);
		add(lblTel);

		txtTel = new JTextField();
		txtTel.setBounds(200, 20, 280, 20);
		add(txtTel);

		lblTelNotes = new JLabel("例：飛行機、JR、新幹線");
		lblTelNotes.setBounds(200, 40, 180, 20);
		add(lblTelNotes);

		btnSearch = new JButton("一覧表示");
		btnSearch.setBounds(40, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(150, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(260, 100, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 560,160);
		add(scrollPane);

		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(400, 120, 80, 30);
		btnUpdate.addActionListener(this);
		add(btnUpdate);

		String[] columnNames = { "ID", "種類", "便名", "出発駅", "到着駅", "出発日時","到着日時"};
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


		column0.setPreferredWidth(60);
		column1.setPreferredWidth(80);
		column2.setPreferredWidth(80);
		column3.setPreferredWidth(80);
		column4.setPreferredWidth(90);
		column5.setPreferredWidth(90);
		column6.setPreferredWidth(80);

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
		setSize(600 + insets.left + insets.right, 600 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnDelete) {

			txtTel.setText("");
			txtKana.setText("");

		} else if (e.getSource() == btnSearch) {

			String tel = txtTel.getText();
			String kana = txtKana.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			tel.replaceAll(" +", "");
			kana.replaceAll(" +", "");

			try {

				String[] data = { tel, kana };
				String[][] tableData = NewBeeController.customerSearch(data);

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

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.vehicleDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}
		}
		else if (e.getSource() == btnUpdate) {

			// 更新データをdbに渡すメソッド


		}
	}

	private class SearchMouseEvent extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			setVisible(false);

			int rowIndex = table.getSelectedRow();
			String custId = (String) table.getValueAt(rowIndex, 0);

			try {

				Customer customer = NewBeeController.orderInputDisplay(custId);
				new OrderInputFrame(customer);

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(VehicleUpdateFrame.this, ex);
			}
		}
	}
}
