
package view;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Customer;
import model.OrderControlUtility;
import model.OrderControlUtility2;
import model.OrderDetail;
import control.NewBeeController;

@SuppressWarnings("serial")
public class DeliveryConfirmFrame extends JFrame implements ActionListener/* , Printable */ {

	private JLabel lblIdCap;
	private JLabel lblId;

	private JLabel lblNameCap;
	private JLabel lblName;

	private JLabel lblTelCap;
	private JLabel lblTel;

	private JLabel lblKanaCap;
	private JLabel lblKana;

	private JLabel lblAddressCap;
	private JLabel lblAddress;

	private JLabel lblDateCap;
	private JLabel lblDate;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JLabel lblTotalCap;
	private JLabel lblTotal;

	private JLabel lblMessage;

	private JButton btnPrintOut;
	private JButton btnComplete;
	private JButton btnReturnSearch;

	public DeliveryConfirmFrame(ArrayList<OrderDetail> orderDetailList) {

		setTitle("【配達情報】 KIDDA-LA 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		OrderDetail orderDetail = orderDetailList.get(0);
		Customer customer = orderDetail.getCustomer();

		lblIdCap = new JLabel("ID");
		lblIdCap.setBounds(20, 20, 100, 20);
		add(lblIdCap);

		lblId = new JLabel("：　" + Integer.toString(customer.getCustId()));
		lblId.setBounds(120, 20, 360, 20);
		add(lblId);

		lblNameCap = new JLabel("氏名");
		lblNameCap.setBounds(20, 40, 100, 20);
		add(lblNameCap);

		lblName = new JLabel("：　" + customer.getCustName());
		lblName.setBounds(120, 40, 360, 20);
		add(lblName);

		lblKanaCap = new JLabel("カナ");
		lblKanaCap.setBounds(20, 60, 100, 20);
		add(lblKanaCap);

		lblKana = new JLabel("：　" + customer.getKana());
		lblKana.setBounds(120, 60, 360, 20);
		add(lblKana);

		lblTelCap = new JLabel("電話番号");
		lblTelCap.setBounds(20, 80, 100, 20);
		add(lblTelCap);

		lblTel = new JLabel("：　" + customer.getTel());
		lblTel.setBounds(120, 80, 360, 20);
		add(lblTel);

		lblAddressCap = new JLabel("住所");
		lblAddressCap.setBounds(20, 100, 100, 20);
		add(lblAddressCap);

		lblAddress = new JLabel("：　" + customer.getAddress());
		lblAddress.setBounds(120, 100, 360, 20);
		add(lblAddress);

		lblDateCap = new JLabel("日付");
		lblDateCap.setBounds(20, 120, 100, 20);
		add(lblDateCap);

		String date = orderDetail.getOrderDate();

		lblDate = new JLabel("：　" + date);
		lblDate.setBounds(120, 120, 360, 20);
		add(lblDate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 460, 210);
		add(scrollPane);

		String[][] tableData = OrderControlUtility2.orderToArray(orderDetailList);
		String[] columnNames = { "NO", "ID", "商品名", "サイズ", "数量", "単価", "小計" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		int total = 0;

		for (int i = 0; i < tableData.length; i++) {

			int quantity = Integer.parseInt(tableData[i][4]);
			int price = Integer.parseInt(tableData[i][5]);
			int subTotal = Integer.parseInt(tableData[i][6]);

			tableData[i][4] = String.format("%1$,d", quantity);
			tableData[i][5] = String.format("%1$,d", price);
			tableData[i][6] = String.format("%1$,d", subTotal);

			total = total + subTotal;
		}

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);
		TableColumn column6 = columnModel.getColumn(6);

		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(SwingConstants.CENTER);

		column0.setPreferredWidth(50);
		column0.setCellRenderer(alignCenter);

		column1.setPreferredWidth(40);
		column1.setCellRenderer(alignCenter);

		column2.setPreferredWidth(140);

		column3.setPreferredWidth(50);
		column3.setCellRenderer(alignCenter);

		DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
		alignRight.setHorizontalAlignment(SwingConstants.RIGHT);

		column4.setPreferredWidth(50);
		column4.setCellRenderer(alignRight);

		column5.setPreferredWidth(50);
		column5.setCellRenderer(alignRight);

		column6.setPreferredWidth(60);
		column6.setCellRenderer(alignRight);

		scrollPane.setViewportView(table);

		for (String[] rowData : tableData) {

			tableModel.addRow(rowData);
		}

		table.setEnabled(false);

		lblTotalCap = new JLabel("合計：　");
		lblTotalCap.setBounds(300, 370, 60, 20);
		add(lblTotalCap);

		String strTotal = String.format("%1$,3d", total);
		lblTotal = new JLabel(strTotal + "円", SwingConstants.RIGHT);
		lblTotal.setBounds(360, 370, 100, 20);
		add(lblTotal);

		lblMessage = new JLabel("※この情報は登録済みです。配達後、[配達完了]ボタンを押してください。");
		lblMessage.setBounds(20, 400, 460, 20);
		add(lblMessage);

		btnPrintOut = new JButton("印刷");
		btnPrintOut.setBounds(20, 450, 90, 30);
		btnPrintOut.addActionListener(this);
		add(btnPrintOut);

		btnComplete = new JButton("配達完了");
		btnComplete.setBounds(120, 450, 90, 30);
		btnComplete.addActionListener(this);
		add(btnComplete);

		btnReturnSearch = new JButton("検索へ戻る");
		btnReturnSearch.setBounds(220, 450, 120, 30);
		btnReturnSearch.addActionListener(this);
		add(btnReturnSearch);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(500 + insets.left + insets.right, 500 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnPrintOut) {

			try {

				NewBeeController.printOut(this);

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnComplete) {

			int result = JOptionPane.showConfirmDialog(this, "配達を完了します。よろしいですか？", "【配達完了確認】", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (result == 0) {

				String custId = lblId.getText();
				custId = custId.substring(2);

				try {

					int count = NewBeeController.deliveryComplete(custId);

					if (count != 0) {

						JOptionPane.showMessageDialog(this, "配達を完了しました。お疲れ様でした。", "【配達完了】", JOptionPane.INFORMATION_MESSAGE);
						lblDate.setText("：");
						tableModel.setRowCount(0);
						lblTotal.setText("");
						lblMessage.setText("※配達は完了しています。");
						btnPrintOut.setEnabled(false);
						btnComplete.setEnabled(false);

					} else {

						throw new Exception("配達完了処理に失敗しました！");
					}

				} catch (Exception ex) {

					OrderControlUtility.systemErrorMessage(this, ex);
				}
			}

		} else if (e.getSource() == btnReturnSearch) {

			setVisible(false);

			try {

				NewBeeController.customerSearchDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
