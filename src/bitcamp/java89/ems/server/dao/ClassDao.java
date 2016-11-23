// 2016.11.07

package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.ClassRoom;

public class ClassDao {
  private static ClassDao classDao;
  static String message;
  private ArrayList<ClassRoom> list;
  private String filename = "classRoom-v1.7.data";

  public ClassDao() throws Exception {
    this.load();
  }

  public ClassDao(Scanner in, PrintStream out) throws Exception {
    this.load();
  }

  public static ClassDao getInstance() throws Exception {
    if (classDao == null) {
      classDao = new ClassDao();
    }
    return classDao;
  }

  // 저장된 파일에서 데이터 불러오기
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in2 = null;

    try {
      in0 = new FileInputStream(this.filename);
      in2 = new ObjectInputStream(in0);
      while (true) {
        this.list = (ArrayList<ClassRoom>) in2.readObject();
      }
    } catch (EOFException e) {
      // 파일을 전부 읽었을 경우.
    } catch (Exception e) {
      System.out.println("데이터 로딩중 에러 발생.");
      list = new ArrayList<ClassRoom>();
    } finally {
      try {
        in2.close();
        in0.close();
      } catch (Exception e) {
        // close 하다가 예외 발생시 무시.
      }
    }
  }
  
  // 전화부 데이터 추가
  synchronized public void insert(ClassRoom classRoom) {
    list.add(classRoom);
    try {save();} catch (Exception e){}
  }

  // 기존 강의실명 존재 여부 확인
  public boolean existCname(String cname) {
    for (ClassRoom classRoom : list) {
      if (classRoom.getClassName().equals(cname)) {
        return true;
      }
    }
    return false;
  }

  // 데이터 파일 변환 후 저장
  public void save() throws Exception {
    try {
      FileOutputStream out0 = new FileOutputStream(this.filename);
      ObjectOutputStream out2 = new ObjectOutputStream(out0);
      out2.writeObject(this.list);
      System.out.println("저장완료.");
      out2.close();
      out0.close();
    } catch (Exception e) {
      System.out.println("파일 저장 중 에러 발생.");
    }
  }

  // 강의실 데이터 삭제
  synchronized public void delete(String cname) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getClassName().equals(cname)) {
        list.remove(i);
        try {save();} catch (Exception e){}
        return;
      }
    }
  }

  // 강의실 데이터 목록 출력
  public ArrayList<ClassRoom> getList() {
    return this.list;
  }

  // 강의실 특정 데이터 출력
  public ArrayList<ClassRoom> getListByName(String name) {
    ArrayList<ClassRoom> results = new ArrayList<>();
    for (ClassRoom classRoom : list) {
      if (classRoom.getClassName().equals(name)) {
        results.add(classRoom);
      }
    }
    return results;
  }

  // 강의실 데이터 변경
  synchronized public void update(ClassRoom classRoom) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getClassName().equals(classRoom.getClassName())) {
        list.set(i, classRoom);
        try {save();} catch (Exception e){}
      }
    }
  }
}
