package com.example.demo.enums;

/**
 * 删除标记（0：正常；1：删除）
 * @author yy
 * @date 2016年12月13日
 */
public enum DelFlagState implements IEnumState {
	
	NORMAL("0", "正常"), DELETE("1", "删除");
	
	private final String _value;
	private final String _label;

	DelFlagState(String value, String label) {
		_value = value;
		_label = label;
	}

	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public String getLabel() {
		return _label;
	}
	
	public static String getLableByValue(String value) {
		String label = "";
		for (DelFlagState o : DelFlagState.values()) {
			if (o.getValue().equals(value)) {
				label = o.getLabel();
			}
		}
		return label;
	}
	
	public static String getValueByLable(String lable) {
		String value = "";
		for (DelFlagState o : DelFlagState.values()) {
			if (o.getLabel().equals(lable)) {
				value = o.getValue();
			}
		}
		return value;
	}
	
}
