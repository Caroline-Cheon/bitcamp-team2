package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class ClassRoom implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String cName;          // 강의실 이름
  protected String fInfo;          // 강의실 층 정보
  protected int cIty;              // 강의실 수용 인원
  protected int cQty;              // 캐비닛 수
  protected int air;               // 에어컨 수
  protected boolean pBim;          // 프로젝트 빔 여부

  public ClassRoom() {}

  public ClassRoom(String className, String floorInfo, int capacity
                 , int cabinetQty, int airconditionerQty, boolean projectBim) {

    this.cName = className;
    this.fInfo = floorInfo;
    this.cIty = capacity;
    this.cQty = cabinetQty;
    this.air = airconditionerQty;
    this.pBim = projectBim;
  }

  public String getClassName() {
    return cName;
  }

  public void setClassName(String className) {
    this.cName = className;
  }

  public String getFloorInfo() {
    return fInfo;
  }

  public void setFloorInfo(String floorInfo) {
    this.fInfo = floorInfo;
  }

  public int getCapacity() {
    return cIty;
  }

  public void setCapacity(int capacity) {
    this.cIty = capacity;
  }

  public int getCabinetQty() {
    return cQty;
  }

  public void setCabinetQty(int cabinetQty) {
    this.cQty = cabinetQty;
  }

  public int getAirconditionerQty() {
    return air;
  }

  public void setAirconditionerQty(int airconditionerQty) {
    this.air = airconditionerQty;
  }

  public boolean isProjectBim() {
    return pBim;
  }

  public void setProjectBim(boolean projectBim) {
    this.pBim = projectBim;
  }

  @Override
  public String toString() {
    return "ClassRoom [className=" + cName + ", floorInfo=" + fInfo + ", capacity=" + cIty + ", cabinetQty="
        + cQty + ", airconditionerQty=" + air + ", projectBim=" + pBim + "]";
  }
}

/*
  => #
*/
