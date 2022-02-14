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
public class LiveUpdateFrame extends JFrame implements ActionListener {



	private JLabel lblKanaNotes;

	private JButton btnSearch;
	private JButton btnDelete;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnReturn;

	private JLabel lblImg2;
	private JButton btnImg;
	private JButton btnUpdate;


	private JLabel lblTheme;


	private JTextField txtTheme;



	public LiveUpdateFrame() {

		setTitle("【ライブ観光データ更新】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTheme = new JLabel("ライブ観光テーマ");
		lblTheme.setBounds(40, 20, 100, 20);
		add(lblTheme);

		txtTheme = new JTextField();
		txtTheme.setBounds(160, 20, 280, 20);
		add(txtTheme);

		lblKanaNotes = new JLabel("例：世界遺産エジプト・ギザ");
		lblKanaNotes.setBounds(160, 50, 280, 20);
		add(lblKanaNotes);

		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(450, 100, 80, 30);
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
		scrollPane.setBounds(20, 160, 610, 300);
		add(scrollPane);


		String[] columnNames = { "ライブ観光ID", "ライブ観光テーマ", "開始日時", "概要","写真名"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);

		column0.setPreferredWidth(100);
		column1.setPreferredWidth(130);
		column2.setPreferredWidth(80);
		column3.setPreferredWidth(200);
		column4.setPreferredWidth(100);



		scrollPane.setViewportView(table);

		

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 600, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(650 + insets.left + insets.right, 650 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {
		int defaultImg = 0; //0の場合は　写真を変更しない
		String imgPath = null;
		String addPath = null;

		if (e.getSource() == btnDelete) {

			txtTheme.setText("");

		} else if (e.getSource() == btnSearch) {

			String theme = txtTheme.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			theme.replaceAll(" +", "");

			try {
				String[][] tableData = NewBeeController.liveSearch(theme);

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

				NewBeeController.liveDisplay();

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
