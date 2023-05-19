package com.example.graph.entity;


public class Link {

  private String endId;
  private String endY;
  private String endX;
  private String startId;
  private String startY;
  private String startX;
  private String id;
  private long itemId;


  public String getEndId() {
    return endId;
  }

  public void setEndId(String endId) {
    this.endId = endId;
  }


  public String getEndY() {
    return endY;
  }

  public void setEndY(String endY) {
    this.endY = endY;
  }


  public String getEndX() {
    return endX;
  }

  public void setEndX(String endX) {
    this.endX = endX;
  }


  public String getStartId() {
    return startId;
  }

  public void setStartId(String startId) {
    this.startId = startId;
  }


  public String getStartY() {
    return startY;
  }

  public void setStartY(String startY) {
    this.startY = startY;
  }


  public String getStartX() {
    return startX;
  }

  public void setStartX(String startX) {
    this.startX = startX;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }

}
