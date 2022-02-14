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

import model.ControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class HotelUpdateFrame extends JFrame implements ActionListener {



	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;

	private JLabel lblImg2;
	private JLabel lblTourist;
	private JButton btnImg;
	private JButton btnUpdate;
	private JTextField txtTid;


	private JLabel lblName;


	private JTextField txtName;

	public HotelUpdateFrame() {

		setTitle("【ホテルデータ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("ホテル名");
		lblName.setBounds(40, 20, 100, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(160, 20, 280, 20);
		add(txtName);

		lblKanaNotes = new JLabel("例：東京プラザホテル");
		lblKanaNotes.setBounds(160, 50, 180, 20);
		add(lblKanaNotes);

		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(840, 100, 80, 30);
		btnUpdate.addActionListener(this);
		add(btnUpdate);


		btnSearch = new JButton("検索");
		btnSearch.setBounds(40, 100, 90, 30);
		btnSearch.addActionListener(this);
		add(btnSearch);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(140, 100, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 900, 250);
		add(scrollPane);

		String[] columnNames = { "ホテルID", "ホテル名", "所在地", "アクセス", "チェックイン", "チェックアウト", "紹介","写真名"};
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
		TableColumn column7 = columnModel.getColumn(7);

		column0.setPreferredWidth(80);
		column1.setPreferredWidth(100);
		column2.setPreferredWidth(120);
		column3.setPreferredWidth(120);
		column4.setPreferredWidth(90);
		column5.setPreferredWidth(90);
		column6.setPreferredWidth(200);
		column7.setPreferredWidth(100);



		scrollPane.setViewportView(table);

		lblImg2 = new JLabel();
		lblImg2.setBounds(20, 300, 275, 250);
		add(lblImg2);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 650, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(940 + insets.left + insets.right, 700 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {
		int defaultImg = 0; //0の場合は　写真を変更しない
		String imgPath = null;
		String addPath = null;

		if (e.getSource() == btnDelete) {

			txtTid.setText("");

		} else if (e.getSource() == btnSearch) {

			String name = txtName.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			name.replaceAll(" +", "");

			try {

				String[][] tableData = NewBeeController.hotelSearch(name);

				if (tableData != null) {

					tableModel.setRowCount(0);

					for (String[] rowData : tableData) {
						int i = 0;
						if (i < 6) {
							tableModel.addRow(rowData);
						} else {
							imgPath = tableData[0][6];
							getImg(imgPath);
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

				NewBeeController.hotelDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		} else if (e.getSource() == btnUpdate) {
			if(defaultImg == 1) {
				//新しいパスを切り替える
				imgPath = addPath;
			}
			// 更新データをdbに渡すメソッド


		} else if (e.getSource() == btnImg) {
			defaultImg = 1;
			addPath = open();
		}
	}

	private void getImg(String imgPath) {

			ImageIcon icon = new ImageIcon(imgPath);
			lblImg2.setIcon(icon);

		}

	private String open() {
		File f = null;
		JFileChooser fc = new JFileChooser();
		// 画像ファイルの拡張子を設定
		fc.setFileFilter(new FileNameExtensionFilter("画像ファイル", "png", "jpg", "Jpeg", "GIF", "bmp"));
		// ファイル選択ダイアログを表示、戻り値がAPPROVE_OPTIONの場合、画像ファイルを開く
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			f = fc.getSelectedFile();
			// アイコンをラベルに設定
			ImageIcon icon = new ImageIcon(f.getPath());
			lblImg2.setIcon(icon);
		}
		String path = String.valueOf(f);
		return path;

	}

}
