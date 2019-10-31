package com.lsts.process.menu.pojo;

public class ViewButton extends Button
{
  private String type;
  private String url;
  private String key;

  public String getType()
  {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}
}