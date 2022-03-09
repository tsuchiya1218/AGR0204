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

import model.ControlUtility;
import model.Spot;
import control.NewBeeController;

public class SpotUpdateFrame extends JFrame implements ActionListener {



	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;

	private JLabel lblTourist;
	private JButton btnImg;

	private String[][] tableData;


	private JLabel lblName;


	private JTextField txtName;



	public SpotUpdateFrame() {

		setTitle("【観光地データ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("観光地名");
		lblName.setBounds(40, 20, 100, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(120, 20, 280, 20);
		add(txtName);

		lblKanaNotes = new JLabel("例：　草津温泉(一部分も可)／大阪(都道府県名も可)");
		lblKanaNotes.setBounds(120, 50, 300, 20);
		add(lblKanaNotes);

		btnSearch = new JButton("検索");
		btnSearch.setBounds(40, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(150, 100, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 750, 300);
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		lblTourist = new JLabel("観光地写真");
		lblTourist.setBounds(40, 250, 80, 20);
		add(lblTourist);

		btnImg = new JButton("写真変更");
		btnImg.setBounds(140, 250, 100, 20);
		btnImg.addActionListener(this);
		add(btnImg);

		String[] columnNames = { "ID", "観光地名", "住所", "アクセス", "営業時間", "概要" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);


		column0.setPreferredWidth(60);
		column1.setPreferredWidth(120);
		column2.setPreferredWidth(145);
		column3.setPreferredWidth(145);
		column4.setPreferredWidth(80);
		column5.setPreferredWidth(200);
		table.addMouseListener(new SearchMouseEvent());

		scrollPane.setViewportView(table);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 650, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(800 + insets.left + insets.right, 700 + insets.top + insets.bottom);
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
				 tableData  = NewBeeController.spotSearch(data);

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

				NewBeeController.spotDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}



	private class SearchMouseEvent extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			int rowIndex = table.getSelectedRow();
			String spoId = (String) table.getValueAt(rowIndex, 0);

			for(int i = 0; i < tableData.length; i++) {

				if(spoId.equals(tableData[i][0])) {
					new SpotDetailFrame(new Spot(tableData[i][0],tableData[i][1],tableData[i][2],tableData[i][3]
							,tableData[i][4],tableData[i][5],tableData[i][6]));
				}
			}
			setVisible(false);

		}


	}



}
