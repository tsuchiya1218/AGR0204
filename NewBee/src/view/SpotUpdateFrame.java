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


import model.OrderControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class SpotUpdateFrame extends JFrame implements ActionListener {

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


	private String[][] customer;

	public SpotUpdateFrame() {

		setTitle("【観光地データ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("観光地名");
		lblTel.setBounds(40, 20, 100, 20);
		add(lblTel);

		txtTid = new JTextField();
		txtTid.setBounds(120, 20, 280, 20);
		add(txtTid);

		lblKanaNotes = new JLabel("例：　草津温泉(一部分も可)／大阪(都道府県名も可)");
		lblKanaNotes.setBounds(120, 50, 300, 20);
		add(lblKanaNotes);

		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(650, 100, 90, 30);
		btnUpdate.addActionListener(this);
		add(btnUpdate);

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
		scrollPane.setBounds(20, 160, 750, 80);
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
		



		scrollPane.setViewportView(table);
	//	lblImg2 = new JLabel();
		

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

	@SuppressWarnings("unused")
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
				String[][] tableData = customer = new String[][]{
					{"1","東福寺","京都府京都市東山区本町15丁目778","JR奈良線・京阪本線「東福寺駅」下車、南東へ徒歩10分。","10：00-18：00","東山区にある臨済宗東福寺派の本山「東福寺」。足利義満が相国寺を建立した後に定められた"+"/n"+"「京都五山」の第4位に列せられる。\n"
							+ "日本最古にして最大級の伽藍は「東福の伽藍面」と言われるほどに荘観を極めたが、度重なる兵火や失火で焼失。都度、再建を繰り返してきた。"},
//					{"2","伊藤二郎","09024681357","東京都千代田区神田小川町2-4-1"},
//					{"3","伊藤三郎","0314142135","東京都千代田区神田神保町1-1-1"}
					};

					
					//NewBeeController.spotUpdate(data);

				if (tableData != null) {

					tableModel.setRowCount(0);
					for (String[] rowData : tableData) {
							tableModel.addRow(rowData);
					}
					ImageIcon icon = new ImageIcon("img/kyoto.png");
					lblImg2 = new JLabel(icon);
					lblImg2.setBounds(60, 290, 480, 250);
					add(lblImg2);

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

				NewBeeController.spotDisplay();

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

//	private void getImg(String imgPath) {
//
//			ImageIcon icon = new ImageIcon(this.getClass().getResource(imgPath));
//			lblImg2.setIcon(icon);
//
//		}

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
