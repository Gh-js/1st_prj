package kr.co.sist.user.vo;

import java.util.Date;

public class HistoryVO {

	private String inbound;
	private String detail;
	private int price;
	
	public HistoryVO() {
		super();
	}

	public HistoryVO(String inbound, String detail, int price) {
		super();
		this.inbound = inbound;
		this.detail = detail;
		this.price = price;
	}

	public String getInbound() {
		return inbound;
	}

	public String getDetail() {
		return detail;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "HistoryVO [inbound=" + inbound + ", detail=" + detail + ", price=" + price + "]";
	}
	
	
	
}
