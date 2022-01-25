
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

import model.OrderControlUtility;
import control.NewBeeController;

public class CustomerSearchFrame extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblTelNotes;

	
	
	private JLabel lblKana;
	private JTextField txtKana;
	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;
	private JButton btnFreeze;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;

	public CustomerSearchFrame() {

		setTitle("【会員情報管理】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("会員ID");
		lblTel.setBounds(20, 20, 180, 20);
		add(lblTel);

		txtTel = new JTextField();
		txtTel.setBounds(200, 20, 280, 20);
		add(txtTel);

		lblTelNotes = new JLabel("例：123456");
		lblTelNotes.setBounds(200, 40, 180, 20);
		add(lblTelNotes);

		lblKana = new JLabel("氏名（全角カタカナ）");
		lblKana.setBounds(20, 70, 180, 20);
		add(lblKana);

		txtKana = new JTextField();
		txtKana.setBounds(200, 70, 280, 20);
		add(txtKana);

		lblKanaNotes = new JLabel("例：キダ タロウ");
		lblKanaNotes.setBounds(200, 90, 180, 20);
		add(lblKanaNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(20, 120, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(120, 120, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 500, 270);
		add(scrollPane);

		String[] columnNames = { "ID", "氏名", "電話", "住所", " " };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		column0.setPreferredWidth(40);
		column1.setPreferredWidth(90);
		column2.setPreferredWidth(90);
		column3.setPreferredWidth(200);
		column4.setPreferredWidth(80);
		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 450, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		btnFreeze = new JButton("禁止");
		btnFreeze.setBounds(400, 120, 90, 30);
		btnFreeze.addActionListener(this);
		add(btnFreeze);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(540 + insets.left + insets.right, 540 + insets.top + insets.bottom);
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

		} else if (e.getSource() == btnFreeze) {
			// cusidをdbに渡すメソッド

			btnFreeze.setEnabled(false);
		}
	}

	private class SearchMouseEvent extends MouseAdapter {
		String custId;

		public void mouseClicked(MouseEvent e) {


			int rowIndex = table.getSelectedRow();
			custId = (String) table.getValueAt(rowIndex, 0);

		}

		public String getCustId() {
			return custId;
		}

	}
}
