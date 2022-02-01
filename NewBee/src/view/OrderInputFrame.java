/**
 * クラス名：	OrderInputFrame
 * 概要　　：	「注文／配達情報／顧客情報変更」画面
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package view;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import control.NewBeeController;
import model.Customer;
import model.Item;
import model.OrderControlUtility;
import model.OrderControlUtility2;
import model.OrderDetail;

@SuppressWarnings("serial")
public class OrderInputFrame extends JFrame implements ActionListener {

	private JLabel lblId;
	private JTextField txtId;

	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblTel;
	private JTextField txtTel;

	private JLabel lblKana;
	private JTextField txtKana;

	private JLabel lblAddress;
	private JTextField txtAddress;

	private JButton btnOrder;
	private JButton btnDelivery;
	private JButton btnModify;

	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnOrderRegister;
	private JButton btnReturn;

	public OrderInputFrame(Customer customer) {

		setTitle("【注文／配達確認／顧客情報変更】 KIDDA-LA 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId = new JLabel("ID");
		lblId.setBounds(20, 20, 100, 20);
		add(lblId);

		txtId = new JTextField();
		txtId.setBounds(120, 20, 360, 20);
		txtId.setEditable(false);
		add(txtId);

		lblName = new JLabel("氏名");
		lblName.setBounds(20, 40, 100, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(120, 40, 360, 20);
		add(txtName);

		lblKana = new JLabel("カナ");
		lblKana.setBounds(20, 60, 100, 20);
		add(lblKana);

		txtKana = new JTextField();
		txtKana.setBounds(120, 60, 360, 20);
		add(txtKana);

		lblTel = new JLabel("電話番号");
		lblTel.setBounds(20, 80, 100, 20);
		add(lblTel);

		txtTel = new JTextField();
		txtTel.setBounds(120, 80, 360, 20);
		add(txtTel);

		lblAddress = new JLabel("住所");
		lblAddress.setBounds(20, 100, 100, 20);
		add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(120, 100, 360, 20);
		add(txtAddress);

		btnOrder = new JButton("注文手続");
		btnOrder.setBounds(20, 130, 90, 30);
		btnOrder.addActionListener(this);
		add(btnOrder);

		btnDelivery = new JButton("配達確認");
		btnDelivery.setBounds(120, 130, 90, 30);
		btnDelivery.addActionListener(this);
		add(btnDelivery);

		btnModify = new JButton("顧客情報変更");
		btnModify.setBounds(220, 130, 120, 30);
		btnModify.addActionListener(this);
		add(btnModify);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 170, 460, 270);
		add(scrollPane);

		String[] columnNames = { "ID", "商品名", "サイズ", "数量", "単価（円：税込）" };
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);

		DefaultTableCellRenderer alignCenter = new DefaultTableCellRenderer();
		alignCenter.setHorizontalAlignment(SwingConstants.CENTER);

		column0.setPreferredWidth(50);
		column1.setPreferredWidth(170);

		column2.setPreferredWidth(60);
		column2.setCellRenderer(alignCenter);

		DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
		alignRight.setHorizontalAlignment(SwingConstants.RIGHT);

		column3.setPreferredWidth(50);
		column3.setCellRenderer(alignRight);

		column4.setPreferredWidth(110);
		column4.setCellRenderer(alignRight);

		scrollPane.setViewportView(table);

		btnOrderRegister = new JButton("注文登録");
		btnOrderRegister.setBounds(20, 450, 90, 30);
		btnOrderRegister.addActionListener(this);
		btnOrderRegister.setEnabled(false);
		add(btnOrderRegister);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(120, 450, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(500 + insets.left + insets.right, 500 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOrder) {

			btnOrderRegister.setEnabled(true);

			try {

				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					tableModel.removeRow(i);
				}

				String[][] tableData = NewBeeController.itemMenuDisplay();

				for (String[] rowData : tableData) {

					tableModel.addRow(rowData);
				}

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnDelivery) {

			String custId = txtId.getText();

			try {

				ArrayList<OrderDetail> orderDetailList = NewBeeController.deliveryConfirm(custId);

				if (orderDetailList.size() != 0) {

					setVisible(false);
					new DeliveryConfirmFrame(orderDetailList);

				} else {

					JOptionPane.showMessageDialog(this, "配達情報はありません。", "【確認】", JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnModify) {

			ConfirmDialog dialog = new ConfirmDialog();
			dialog.setVisible(true);

		} else if (e.getSource() == btnOrderRegister) {

			String custId = txtId.getText();
			int intCustId = Integer.parseInt(custId);

			Customer customer = new Customer(intCustId, "", "", "", "");

			String date = OrderControlUtility2.getDate();

			ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

			int flag = 0;

			for (int i = 0; i < table.getRowCount(); i++) {

				String quantity = (String) table.getValueAt(i, 3);

				if (!quantity.equals("")) {

					int intQuantity = Integer.parseInt(quantity);

					if (intQuantity != 0) {

						String itemId = (String) table.getValueAt(i, 0);
						String itemName = (String) table.getValueAt(i, 1);
						String size = (String) table.getValueAt(i, 2);
						String price = (String) table.getValueAt(i, 4);
						price = price.replaceAll(",", "");
						int intPrice = Integer.parseInt(price);

						Item item = new Item(itemId, itemName, size, intPrice);

						OrderDetail orderDetail = new OrderDetail(0, customer, item, date, intQuantity, null, 1);
						orderDetailList.add(orderDetail);

						if (flag == 0) {

							flag = 1;
						}
					}
				}
			}

			if (flag == 1) {

				int result = JOptionPane.showConfirmDialog(this, "注文情報を登録します。よろしいですか？", "【注文情報登録確認】",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == 0) {

					try {

						orderDetailList = NewBeeController.orderRegister(orderDetailList);

						JOptionPane.showMessageDialog(this, "注文情報を登録しました。", "【注文情報登録完了】", JOptionPane.INFORMATION_MESSAGE);

						setVisible(false);

						new DeliveryConfirmFrame(orderDetailList);

					} catch (Exception ex) {

						OrderControlUtility.systemErrorMessage(this, ex);
					}
				}
			} else {

				JOptionPane.showMessageDialog(this, "商品数量を入力してください。", "【確認】", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.customerSearchDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

	private class ConfirmDialog extends JDialog implements ActionListener {

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

		private JLabel lblMessage;

		private JButton btnDecide;
		private JButton btnReturn;

		public ConfirmDialog() {

			setTitle("【顧客情報変更確認】");
			setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			setLayout(null);

			lblIdCap = new JLabel("ID");
			lblIdCap.setBounds(20, 20, 100, 20);
			add(lblIdCap);

			lblId = new JLabel("：　" + txtId.getText());
			lblId.setBounds(120, 20, 360, 20);
			add(lblId);

			lblNameCap = new JLabel("氏名");
			lblNameCap.setBounds(20, 40, 100, 20);
			add(lblNameCap);

			lblName = new JLabel("：　" + txtName.getText());
			lblName.setBounds(120, 40, 360, 20);
			add(lblName);

			lblKanaCap = new JLabel("カナ");
			lblKanaCap.setBounds(20, 60, 100, 20);
			add(lblKanaCap);

			lblKana = new JLabel("：　" + txtKana.getText());
			lblKana.setBounds(120, 60, 360, 20);
			add(lblKana);

			lblTelCap = new JLabel("電話番号");
			lblTelCap.setBounds(20, 80, 100, 20);
			add(lblTelCap);

			lblTel = new JLabel("：　" + txtTel.getText());
			lblTel.setBounds(120, 80, 360, 20);
			add(lblTel);

			lblAddressCap = new JLabel("住所");
			lblAddressCap.setBounds(20, 100, 100, 20);
			add(lblAddressCap);

			lblAddress = new JLabel("：　" + txtAddress.getText());
			lblAddress.setBounds(120, 100, 360, 20);
			add(lblAddress);

			lblMessage = new JLabel("※この内容でよろしければ、[確定]ボタンを押してください。");
			lblMessage.setBounds(20, 130, 460, 20);
			add(lblMessage);

			btnDecide = new JButton("確定");
			btnDecide.setBounds(20, 160, 90, 30);
			btnDecide.addActionListener(this);
			add(btnDecide);

			btnReturn = new JButton("戻る");
			btnReturn.setBounds(120, 160, 90, 30);
			btnReturn.addActionListener(this);
			add(btnReturn);
		}

		public void addNotify() {

			super.addNotify();

			Insets insets = getInsets();
			setSize(500 + insets.left + insets.right, 210 + insets.top + insets.bottom);
			setLocationRelativeTo(this);
		}

		public void actionPerformed(ActionEvent e) {

			setVisible(false);

			if (e.getSource() == btnDecide) {

				try {

					String custId = txtId.getText();
					int intCustId = Integer.parseInt(custId);
					String custName = txtName.getText();
					String kana = txtKana.getText();
					String tel = txtTel.getText();
					String address = txtAddress.getText();

					Customer customer = new Customer(intCustId, custName, kana, tel, address);

					int count = NewBeeController.customerModify(customer);

					if (count != 0) {

						JOptionPane.showMessageDialog(this, "顧客情報を更新しました。", "【顧客情報変更完了】", JOptionPane.INFORMATION_MESSAGE);

					} else {

						throw new Exception("顧客情報更新処理に失敗しました！");
					}

				} catch (Exception ex) {

					OrderControlUtility.systemErrorMessage(OrderInputFrame.this, ex);
				}
			}
		}
	}
}
