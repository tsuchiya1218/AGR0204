

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

public class EvaluateCheckFrame extends JFrame implements ActionListener {

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
	private JButton btnOk;
	private JButton btnCancel;

	public EvaluateCheckFrame() {

		setTitle("【レビュー•評価をチェック】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		btnOk = new JButton("OK");
		btnOk.setBounds(620, 40, 90, 30);
		btnOk.addActionListener(this);
		add(btnOk);
		
		btnCancel = new JButton("削除");
		btnCancel.setBounds(740, 40, 90, 30);
		btnCancel.addActionListener(this);
		add(btnCancel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 810, 450);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		String[] columnNames = { "会員ID","ユーザ名","注文コード", "コース種類","レビュー•評価内容","日付"};
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

		column0.setPreferredWidth(80);
		column1.setPreferredWidth(100);
		column2.setPreferredWidth(100);
		column3.setPreferredWidth(100);
		column4.setPreferredWidth(330);
		column5.setPreferredWidth(100);

		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 620, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(850 + insets.left + insets.right, 700 + insets.top + insets.bottom);
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

				OrderControlUtility.systemErrorMessage(EvaluateCheckFrame.this, ex);
			}
		}
	}
}
