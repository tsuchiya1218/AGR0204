/**
 * クラス名：	CustomerSearchDisplayAction
 * 概要　　：	顧客情報検索画面表示アクション
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package action;

import view.HotelAddFrame;
import view.HotelFrame;
import view.HotelUpdateFrame;

public class HotelDisplayAction {

	public void execute() {
		new HotelFrame();
	}
	
	public void executeAdd() {
		new HotelAddFrame();
	}
	
	public void executeUpdata() {
		new HotelUpdateFrame();
	}
}
