/**
 * クラス名：	CustomerSearchDisplayAction
 * 概要　　：	顧客情報検索画面表示アクション
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package action;

import view.VehicleAddFrame;
import view.VehicleFrame;
import view.VehicleUpdateFrame;

public class VehicleDisplayAction {

	public void execute() {
		new VehicleFrame();
	}
	public void executeAdd() {
		new VehicleAddFrame();
	}
	public void executeUpdate() {
		new VehicleUpdateFrame();
	}
}
