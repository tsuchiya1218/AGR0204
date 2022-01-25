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

import model.Customer;
import model.OrderControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class HotelUpdateFrame extends JFrame implements ActionListener {

	private JLabel lblTel;


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

	public HotelUpdateFrame() {

		setTitle("【ホテルデータ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("ホテルID");
		lblTel.setBounds(40, 20, 100, 20);
		add(lblTel);

		txtTid = new JTextField();
		txtTid.setBounds(160, 20, 280, 20);
		add(txtTid);

		lblKanaNotes = new JLabel("例：123456");
		lblKanaNotes.setBounds(160, 50, 180, 20);
		add(lblKanaNotes);

		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(740, 100, 80, 30);
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
		scrollPane.setBounds(20, 160, 800, 80);
		add(scrollPane);

		lblTourist = new JLabel("ホテル写真");
		lblTourist.setBounds(40, 250, 100, 20);
		add(lblTourist);

		btnImg = new JButton("写真変更");
		btnImg.setBounds(160, 250, 100, 30);
		btnImg.addActionListener(this);
		add(btnImg);

		String[] columnNames = { "ホテルID", "ホテル名", "所在地", "アクセス", "利用時間", "紹介"};
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
		column2.setPreferredWidth(160);
		column3.setPreferredWidth(160);
		column4.setPreferredWidth(80);
		column5.setPreferredWidth(220);



		scrollPane.setViewportView(table);

		lblImg2 = new JLabel();
		lblImg2.setBounds(60, 290, 275, 250);
		add(lblImg2);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 600, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(840 + insets.left + insets.right, 650 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {
		int defaultImg = 0; //0の場合は　写真を変更しない
		String imgPath = null;
		String addPath = null;

		if (e.getSource() == btnDelete) {

			txtTid.setText("");

		} else if (e.getSource() == btnSearch) {

			String tid = txtTid.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			tid.replaceAll(" +", "");

			try {

				String[] data = { tid};
				String[][] tableData = NewBeeController.customerSearch(data);

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

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {
			setVisible(false);

			try {

				NewBeeController.hotelDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
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
