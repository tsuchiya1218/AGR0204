

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

import model.BookingCheck;
import model.Customer;
import model.ControlUtility;
import model.Reviews;
import control.NewBeeController;

public class ReviewsCheckFrame extends JFrame implements ActionListener {

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
	String[][] tableData;
	
	public ReviewsCheckFrame() {

		setTitle("【レビュー•評価をチェック】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		btnSearch = new JButton("レビュー•評価情報一覧");
		btnSearch.setBounds(20, 40, 220, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 100, 660, 450);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		String[] columnNames = { "ユーザーID","ユーザ名","注文コード","旅行コース","レビュー•評価内容","日付"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);

		column0.setPreferredWidth(100);
		column1.setPreferredWidth(100);
		column2.setPreferredWidth(80);
		column3.setPreferredWidth(100);
		column4.setPreferredWidth(200);
		column5.setPreferredWidth(80);

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
		setSize(700 + insets.left + insets.right, 700 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

	if(e.getSource() == btnSearch) {
			try {

				String[] data = { };
				 tableData = new String[][]{
					 {"001","電子","20220107","【鬼怒川温泉あけび「離れの湯」個室貸切露天風呂｜コース】ファミリー･女性同士･ご夫婦やカップルにおすすめ",
						 "良かったと思う。","2022211"},
					 {"002","電子","20220108","【鬼怒川温泉あけび「離れの湯」個室貸切露天風呂｜コース】ファミリー･女性同士･ご夫婦やカップルにおすすめ",
							 "良かったと思う。","2022211"}
					 };
				 
				 NewBeeController.reviewsSearch();

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

				NewBeeController.mainMenuDisplay();

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
			String tel = (String) table.getValueAt(rowIndex, 0);
			
			for(int i = 0;i < tableData.length; i++ ) {
				if(tel.equals(tableData[i][0])) {
					new ReviewsFrame(new Reviews(tableData[i][0],tableData[i][1],tableData[i][2],tableData[i][3],
							tableData[i][4],tableData[i][5]));
				}
			}
			} catch (Exception ex) {
			}
		}
	}
}
