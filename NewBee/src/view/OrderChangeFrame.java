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
public class OrderChangeFrame extends JFrame implements ActionListener {

	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblTelNotes;

	private JLabel lblKana;
	private JTextField txtKana;
	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;
	private JButton btnUpdate;

	public OrderChangeFrame() {

		setTitle("【注文情報変更】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("電話番号");
		lblTel.setBounds(130, 20, 50, 20);
		add(lblTel);

		txtTel = new JTextField();
		txtTel.setBounds(200, 20, 280, 20);
		add(txtTel);

		lblTelNotes = new JLabel("例：07012345678");
		lblTelNotes.setBounds(200, 40, 180, 20);
		add(lblTelNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(20, 80, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(120, 80, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);
		
		btnUpdate = new JButton("新規追加");
		btnUpdate.setBounds(840, 80, 90, 30);
		btnUpdate.addActionListener(this);
		add(btnUpdate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 130, 910, 250);
		add(scrollPane);

		String[] columnNames = { "電話番号", "顧客名", "注文コード", "旅行コース","開始日","終了日","合計（税込）"};
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
		
		column0.setPreferredWidth(80);
		column1.setPreferredWidth(100);
		column2.setPreferredWidth(100);
		column3.setPreferredWidth(330);
		column4.setPreferredWidth(100);
		column5.setPreferredWidth(100);
		column6.setPreferredWidth(100);
		
		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 450, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(950 + insets.left + insets.right, 500 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnDelete) {

			txtTel.setText("");

		} else if (e.getSource() == btnSearch) {

			String tel = txtTel.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			tel.replaceAll(" +", "");

			try {

				String[] data = { tel};
				String[][] tableData = NewBeeController.orderChange(data);

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

				NewBeeController.mainMenuDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}
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

				OrderControlUtility.systemErrorMessage(OrderChangeFrame.this, ex);
			}
		}
	}
}
