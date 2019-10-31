package com.guido.util;

public enum Xuezhi {
	Er("2", "[Ⅱ]"), 
	Wu("5", "[Ⅴ]"),
	Liu("6","[Ⅵ]"),
	Qi("7","[Ⅶ]"),
	Ba("8","[Ⅷ]"),
	Jiu("9","[Ⅸ]"),
	A("A","[5+3医]");
	
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Xuezhi(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
